package fr.cashcoders.capitalhub.model;

import fr.cashcoders.capitalhub.view.Observer;

import java.util.ArrayList;
import java.util.List;

public class Portefeuille implements Observable, DBInterface {
    private int id;
    private int idUser;
    private String name;
    private String description;
    private List<Action> actions;
    private List<Transaction> transactions;
    private List<Observer> observers = new ArrayList<>();

    public Portefeuille(int id, int idUser, String name, String description) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.description = description;
        this.actions = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
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

    @Override
    public void update() {

    }

    @Override
    public String toString() {
        return "Portefeuille{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", actions=" + actions +
                ", observers=" + observers +
                '}';
    }
}
