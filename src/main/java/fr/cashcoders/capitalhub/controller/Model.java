package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.controller.utils.LoadDataBase;
import fr.cashcoders.capitalhub.database.DataBaseConnectionSingleton;
import fr.cashcoders.capitalhub.model.*;
import fr.cashcoders.capitalhub.view.HistoryView;
import fr.cashcoders.capitalhub.view.MainView;
import fr.cashcoders.capitalhub.view.PortefeuilleDetailsView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    private final List<Portefeuille> portefeuilles;
    private final Map<Portefeuille, History> history;

    private Currency currency;
    private List<Currency> currencies;

    private final MainView mainView = CapitalHubApp.mainView;
    private final PortefeuilleDetailsView portefeuilleDetailsView = CapitalHubApp.portefeuilleDetailsView;
    private final HistoryView historyView = CapitalHubApp.historyView;
    private Connection connection = DataBaseConnectionSingleton.getInstance().getConnection();

    public Model() throws SQLException {
        this.portefeuilles = new ArrayList<>();
        this.history = new HashMap<>();
        this.currencies = new ArrayList<>();

        LoadDataBase.load(portefeuilles, history, currencies, connection);
//        this.currency = currencies.get(0);

        System.out.println("Portefeuilles: " + portefeuilles + " 1 ;" + portefeuilles.get(0) + "\n");

        System.out.println("History: " + history);
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

    public void addEvent(Event event, Portefeuille portefeuille) {
        if (!history.containsKey(portefeuille)) {
            history.put(portefeuille, new History());
        }
        history.get(portefeuille).addEvent(event);
    }

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

    public Map<Portefeuille, History> getHistory() {
        return history;
    }

    public History getHistory(Portefeuille portefeuille) {
        return history.get(portefeuille);
    }

    public List<Action> getTransactions(Portefeuille portefeuille) {
        return portefeuille.getActions();
    }

    public List<Action> getTransactions(int index) {
        return portefeuilles.get(index).getActions();
    }


}
