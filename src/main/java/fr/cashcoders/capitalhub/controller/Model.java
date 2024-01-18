package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.controller.aggregator.DataAggregator;
import fr.cashcoders.capitalhub.controller.utils.APIActionScheduler;
import fr.cashcoders.capitalhub.controller.utils.DatabaseFeeder;
import fr.cashcoders.capitalhub.database.DataBaseConnectionSingleton;
import fr.cashcoders.capitalhub.exception.TransactionException;
import fr.cashcoders.capitalhub.model.*;
import fr.cashcoders.capitalhub.view.Observer;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Model {

    public final static List<Action> actions = new ArrayList<>();

    private final User user;
    private final List<Portefeuille> portefeuilles;
    private final List<Currency> currencies;
    private final Connection connection = DataBaseConnectionSingleton.getInstance().getConnection();
    private final APIActionScheduler apiActionScheduler;
    private Currency currency;
    private Observer observer = null;

    public Model(User user) throws SQLException {
        this.user = user;
        this.portefeuilles = new ArrayList<>();
        this.currencies = new ArrayList<>();

        DatabaseFeeder.load(user, portefeuilles, currencies, connection);
        this.currency = currencies.get(1);

        apiActionScheduler = new APIActionScheduler(this);
        apiActionScheduler.run();
    }

    public void createPortefeuille(String name, String description) {
        Portefeuille portefeuille = new Portefeuille(user, name, description);
        portefeuilles.add(portefeuille);
        notifyObserver();
    }

    public void addAction(Portefeuille portefeuille, ActionProduit action) {
        portefeuille.addActionProduit(action);
        notifyObserver();
    }

    public void clonePortefeuille(Portefeuille portefeuille) {
        Portefeuille clone = new Portefeuille(portefeuille);
        portefeuilles.add(clone);
        notifyObserver();
    }

    public void deletePortefeuille(Portefeuille portefeuille) {
        portefeuille.delete();
        portefeuilles.remove(portefeuille);
        notifyObserver();
    }

    public List<Portefeuille> getPortefeuilles() {
        return portefeuilles;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
        notifyObserver();
    }

    public List<History> getHistory(Portefeuille portefeuille) {
        return portefeuille.getHistory();
    }

    public List<ActionProduit> getActionsProduits(Portefeuille portefeuille) {
        return portefeuille.getActionsProducts();
    }

    public List<ActionProduit> getActionsProduit(int index) {
        return portefeuilles.get(index).getActionsProducts();
    }

    public void addTransaction(Transaction transaction, Portefeuille portefeuille) {
        portefeuille.addTransaction(transaction);
        notifyObserver();
    }


    public XYChart.Series<String, Integer> getSeries(Portefeuille portefeuille, Period filter) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName(portefeuille.getName());

        DataAggregator dataAggregator = new DataAggregator();
        series = dataAggregator.getSeries(portefeuille, filter);

        return series;
    }


    public Portefeuille getPortefeuille(String name) {
        for (Portefeuille portefeuille : portefeuilles) {
            if (portefeuille.getName().equals(name)) {
                return portefeuille;
            }
        }
        return null;
    }

    public void updateActions(Map<String, Double> actions) {
        List<Action> actionsToUpdate = Model.actions;
        for (Action action : actionsToUpdate) {
            if (actions.containsKey(action.getSymbol())) {
                try {
                    action.setPrice(actions.get(action.getSymbol()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        for (Portefeuille portefeuille : portefeuilles) {
            for (ActionProduit actionProduit : portefeuille.getActionsProducts()) {
                Action action = actionProduit.getAction();
                Double price = action.getPrice();
                portefeuille.addHistory(new History(portefeuille, action, price * actionProduit.getQuantity()));
            }
        }
        notifyObserver();
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }


    public void notifyObserver() {
        if (observer != null) {
            observer.update();
        }
    }

    public void makeTransaction(Portefeuille portefeuille, Action action, TransactionType type, double value, double quantity) throws TransactionException {
        // Verifier qu'on ne vend pas une action qu'on a pas
        if (type == TransactionType.SELL) {
            ActionProduit actionProduit = portefeuille.getActionProduit(action);
            if (actionProduit == null || actionProduit.getQuantity() < quantity) {
                throw new TransactionException("Vous ne pouvez pas vendre une action que vous n'avez pas");
            }
        }


        Transaction transaction = new Transaction(0, portefeuille, action, value, LocalDateTime.now(), currency.code(), type);
        portefeuille.addTransaction(transaction);


        ActionProduit actionProduit = portefeuille.getActionProduit(action);
        if (actionProduit == null) {
            actionProduit = new ActionProduit(action, portefeuille, 0);
            portefeuille.addActionProduit(actionProduit);
        }
        if (type == TransactionType.BUY) {
            actionProduit.setQuantity(actionProduit.getQuantity() + quantity);
        } else {
            actionProduit.setQuantity(actionProduit.getQuantity() - quantity);
        }


        // If quantity is 0, delete the actionProduit
        if (actionProduit.getQuantity() == 0) {
            portefeuille.removeActionProduit(actionProduit);
        }

        portefeuille.addHistory(new History(portefeuille, action, actionProduit.getQuantity() * action.getPrice()));

        notifyObserver();
    }
}
