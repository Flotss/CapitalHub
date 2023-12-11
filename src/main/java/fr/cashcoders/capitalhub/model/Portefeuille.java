package fr.cashcoders.capitalhub.model;

import java.util.ArrayList;
import java.util.List;

public class Portefeuille {
    private String name;
    private String description;
    private List<Transaction> transactions;

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
}
