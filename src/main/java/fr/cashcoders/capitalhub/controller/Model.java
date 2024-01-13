package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.controller.aggregator.DataAggregator;
import fr.cashcoders.capitalhub.controller.filter.FilterFactory;
import fr.cashcoders.capitalhub.controller.filter.FilterStrategy;
import fr.cashcoders.capitalhub.controller.utils.APIActionScheduler;
import fr.cashcoders.capitalhub.controller.utils.DatabaseFeeder;
import fr.cashcoders.capitalhub.database.DataBaseConnectionSingleton;
import fr.cashcoders.capitalhub.model.*;
import fr.cashcoders.capitalhub.view.HistoryView;
import fr.cashcoders.capitalhub.view.MainView;
import fr.cashcoders.capitalhub.view.PortefeuilleDetailsView;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

    public Model(User user) throws SQLException {
        this.user = user;
        this.portefeuilles = new ArrayList<>();
        this.currencies = new ArrayList<>();

        DatabaseFeeder.load(user, portefeuilles, currencies, connection);
        this.currency = currencies.get(0);
        apiActionScheduler = new APIActionScheduler(this);
//        apiActionScheduler.run();
    }

//    public void createPortefeuille(String name, String description) {
//        Portefeuille portefeuille = new Portefeuille(name, description);
//        portefeuilles.add(portefeuille);
//    }
//
//    public void addAction(Portefeuille portefeuille, Action action) {
//        portefeuille.addAction(action);
//        this.addEvent(new Event("Transaction added to " + portefeuille.getName()), portefeuille);
//    }
//
//    public void clonePortefeuille(Portefeuille portefeuille) {
//        Portefeuille clone = new Portefeuille(portefeuille.getName(), portefeuille.getDescription());
//        for (Action action : portefeuille.getActions()) {
//            clone.addAction(action);
//        }
//        portefeuilles.add(clone);
//        this.addEvent(new Event("Portefeuille " + portefeuille.getName() + " cloned"), portefeuille);
//    }

    public List<Portefeuille> getPortefeuilles() {
        return portefeuilles;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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


    public XYChart.Series<String, Integer> getSeries(Portefeuille portefeuille, Period filter) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName(portefeuille.getName());

        FilterStrategy filterStrategy = FilterFactory.getFilter(filter);
        DataAggregator dataAggregator = new DataAggregator();
        series = dataAggregator.getSeries(portefeuille, filterStrategy);

        return series;
    }

    private LocalDateTime resetTime(LocalDateTime dateTime) {
        return dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
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
    }
}
