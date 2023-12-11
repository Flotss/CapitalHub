package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.model.*;
import fr.cashcoders.capitalhub.view.HistoryView;
import fr.cashcoders.capitalhub.view.MainView;
import fr.cashcoders.capitalhub.view.PortefeuilleDetailsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortefeuilleController {
    private final List<Portefeuille> portefeuilles;
    private final Map<Portefeuille, History> history;
    private final MainView mainView;
    private final PortefeuilleDetailsView portefeuilleDetailsView;
    private final HistoryView historyView;
    private Currency currency;

    public PortefeuilleController() {
        this.portefeuilles = new ArrayList<>();
        this.currency = new Currency("EUR", "Euro", "€");
        this.history = new HashMap<>();
        this.mainView = new MainView(this);
        this.portefeuilleDetailsView = new PortefeuilleDetailsView(this);
        this.historyView = new HistoryView(this);
    }

    public void createPortefeuille(String name, String description) {
        Portefeuille portefeuille = new Portefeuille(name, description);
        portefeuilles.add(portefeuille);
    }

    public void addTransaction(Portefeuille portefeuille, Transaction transaction) {
        portefeuille.addTransaction(transaction);
        this.addEvent(new Event("Transaction added to " + portefeuille.getName()), portefeuille);
    }

    public void clonePortefeuille(Portefeuille portefeuille) {
        Portefeuille clone = new Portefeuille(portefeuille.getName(), portefeuille.getDescription());
        for (Transaction transaction : portefeuille.getTransactions()) {
            clone.addTransaction(transaction);
        }
        portefeuilles.add(clone);
        this.addEvent(new Event("Portefeuille " + portefeuille.getName() + " cloned"), portefeuille);
    }

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

    public List<Transaction> getTransactions(Portefeuille portefeuille) {
        return portefeuille.getTransactions();
    }

    public List<Transaction> getTransactions(int index) {
        return portefeuilles.get(index).getTransactions();
    }

}
