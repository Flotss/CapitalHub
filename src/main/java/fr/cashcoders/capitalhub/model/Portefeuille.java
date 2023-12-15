package fr.cashcoders.capitalhub.model;

import fr.cashcoders.capitalhub.view.Observer;

import java.util.ArrayList;
import java.util.List;

public class Portefeuille implements Observable, DBInterface {
    private String name;
    private String description;
    private List<Transaction> transactions;

    private List<Observer> observers = new ArrayList<Observer>();

    public Portefeuille(String name, String description) {
        this.name = name;
        this.description = description;
        this.transactions = new ArrayList<Transaction>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        notify();
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : this.observers) {
            o.update(this, null);
        }
    }

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }
}
