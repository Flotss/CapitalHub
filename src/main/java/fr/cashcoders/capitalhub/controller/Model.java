package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.controller.aggregator.DataAggregator;
import fr.cashcoders.capitalhub.controller.utils.APIActionScheduler;
import fr.cashcoders.capitalhub.controller.utils.DatabaseFeeder;
import fr.cashcoders.capitalhub.database.DataBaseConnectionSingleton;
import fr.cashcoders.capitalhub.model.*;
import fr.cashcoders.capitalhub.view.HistoryView;
import fr.cashcoders.capitalhub.view.MainView;
import fr.cashcoders.capitalhub.view.Observer;
import fr.cashcoders.capitalhub.view.PortefeuilleDetailsView;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Model {

    public final static List<Action> actions = new ArrayList<>();

    private User user;
    private final List<Portefeuille> portefeuilles;
    private final MainView mainView = CapitalHubApp.mainView;
    private final PortefeuilleDetailsView portefeuilleDetailsView = CapitalHubApp.portefeuilleDetailsView;
    private final HistoryView historyView = CapitalHubApp.historyView;
    private final List<Currency> currencies;
    private final Connection connection = DataBaseConnectionSingleton.getInstance().getConnection();
    private Currency currency;
    private APIActionScheduler apiActionScheduler;

    private Observer observer = null;

    public Model(User user) throws SQLException {
        this.user = user;
        this.portefeuilles = new ArrayList<>();
        this.currencies = new ArrayList<>();

        DatabaseFeeder.load(user, portefeuilles, currencies, connection);
        this.currency = currencies.get(0);
        apiActionScheduler = new APIActionScheduler(this);
//        apiActionScheduler.run();

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
        return portefeuille.getActionsProduct();
    }

    public List<ActionProduit> getActionsProduit(int index) {
        return portefeuilles.get(index).getActionsProduct();
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
        List<Action> actionsAlreadyUpdated = new ArrayList<>();
        for (Portefeuille portefeuille : portefeuilles) {
            for (ActionProduit actionProduit : portefeuille.getActionsProduct()) {
                Action action = actionProduit.getAction();
                if (actions.containsKey(action.getName()) && !actionsAlreadyUpdated.contains(action)) {
                    try {
                        action.setPrice(actions.get(action.getName()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    actionsAlreadyUpdated.add(action);
                }
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
}
