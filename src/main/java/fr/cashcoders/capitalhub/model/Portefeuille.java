package fr.cashcoders.capitalhub.model;

import fr.cashcoders.capitalhub.view.Observer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portefeuille implements Observable, DBInterface {
    private int id;
    private final int idUser;
    private final String name;
    private final String description;
    private final List<ActionProduit> actionsProducts;
    private final List<Transaction> transactions;
    private final List<History> history;

    private final List<Observer> observers = new ArrayList<>();

    public Portefeuille(int id, int idUser, String name, String description) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.description = description;
        this.actionsProducts = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.history = new ArrayList<>();

        if (this.id == 0) {
            this.save();
        }
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

    public List<ActionProduit> getActionsProduct() {
        return actionsProducts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<History> getHistory() {
        return history;
    }

    public void addActionProduit(ActionProduit action) {
        this.actionsProducts.add(action);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void addHistory(History history) {
        this.history.add(history);
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

    public double getLastValue() {
        Map<LocalDate, Integer> dateToTotalValue = new HashMap<>();
        LocalDate lastDate = LocalDate.MIN;
        for (History h : this.history) {
            LocalDate date = h.getDate().toLocalDate();
            int price = (int) h.getPrice();

            dateToTotalValue.put(date, dateToTotalValue.getOrDefault(date, 0) + price);

            if (date.isAfter(lastDate)) {
                lastDate = date;
            }
        }

        // Get the last value, if there is no value, return 0
        return dateToTotalValue.getOrDefault(lastDate, 0);
    }

    @Override
    public void save() {
        String query = "INSERT INTO portefeuille (idUser, name, description) VALUES (?, ?, ?) " +
                "ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name, description = EXCLUDED.description " +
                "RETURNING id;";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, this.idUser);
            preparedStatement.setString(2, this.name);
            preparedStatement.setString(3, this.description);


            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                this.id = resultSet.getInt("id"); // Retrieve the ID from the result set
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete() {
        transactions.forEach(transaction -> {
            try {
                transaction.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        String query = "DELETE FROM portefeuille WHERE id = ?;";
        try (var preparedStement = connection.prepareStatement(query)) {
            preparedStement.setInt(1, this.id);
            preparedStement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                ", actions=" + actionsProducts +
                ", observers=" + observers +
                '}';
    }
}
