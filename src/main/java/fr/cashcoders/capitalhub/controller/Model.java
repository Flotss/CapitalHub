package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.controller.utils.DatabaseFeeder;
import fr.cashcoders.capitalhub.database.DataBaseConnectionSingleton;
import fr.cashcoders.capitalhub.model.*;
import fr.cashcoders.capitalhub.view.HistoryView;
import fr.cashcoders.capitalhub.view.MainView;
import fr.cashcoders.capitalhub.view.PortefeuilleDetailsView;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    private final List<Portefeuille> portefeuilles;

    private Currency currency;
    private List<Currency> currencies;

    private final MainView mainView = CapitalHubApp.mainView;
    private final PortefeuilleDetailsView portefeuilleDetailsView = CapitalHubApp.portefeuilleDetailsView;
    private final HistoryView historyView = CapitalHubApp.historyView;
    private Connection connection = DataBaseConnectionSingleton.getInstance().getConnection();

    public Model() throws SQLException {
        this.portefeuilles = new ArrayList<>();
        this.currencies = new ArrayList<>();

        DatabaseFeeder.load(portefeuilles, currencies, connection);
//        this.currency = currencies.get(0); // ! TODO : REGARDER çA

        System.out.println("Portefeuilles: " + portefeuilles + " 1 ;" + portefeuilles.get(0) + "\n");
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

    public void showMainView() {
        mainView.show();
    }

    public void showPortefeuilleDetailsView(Portefeuille portefeuille) {
        portefeuilleDetailsView.show(portefeuille);
    }

    public void showHistoryView() {
        historyView.show();
    }

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

    public XYChart.Series<String, Integer> getSeries(Portefeuille portefeuille) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName(portefeuille.getName());

        // History
        List<History> history = portefeuille.getHistory();
        Map<LocalDate, Integer> dateToTotalValue = new HashMap<>();

        for (History h : history) {
            LocalDate date = h.getDate().toLocalDate();
            int price = (int) h.getPrice();

            dateToTotalValue.put(date, dateToTotalValue.getOrDefault(date, 0) + price);
        }

        for (Map.Entry<LocalDate, Integer> entry : dateToTotalValue.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
        }

        return series;
    }



}
