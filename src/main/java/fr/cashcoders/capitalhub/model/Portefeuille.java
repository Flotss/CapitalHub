package fr.cashcoders.capitalhub.model;

import fr.cashcoders.capitalhub.view.Observer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portefeuille implements DBInterface {
    private int id;
    private final int idUser;
    private String name;
    private String description;
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

    public Portefeuille(User user, String name, String description) {
        this.idUser = user.getId();
        this.name = name;
        this.description = description;
        this.actionsProducts = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.history = new ArrayList<>();
        save();
    }

    // CLONE CONSTRUCTOR
    public Portefeuille(Portefeuille portefeuille) {
        this.idUser = portefeuille.getIdUser();
        this.name = portefeuille.getName();
        this.description = portefeuille.getDescription();
        this.actionsProducts = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.history = new ArrayList<>();
        save();
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

    public void setName(String name) {
        this.name = name;
        save();
    }

    public void setDescription(String description) {
        this.description = description;
        save();
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

    public List<History> getHistory(Period period) {
        // Get the history of all the actions
        List<History> history = new ArrayList<>(this.history);
        switch (period) {
            case DAY:
                history.removeIf(h -> h.getDate().toLocalDate().isBefore(LocalDate.now().minusDays(1)));
                break;
            case MONTH:
                history.removeIf(h -> h.getDate().toLocalDate().isBefore(LocalDate.now().minusMonths(1)));
                break;
            case YEAR:
                history.removeIf(h -> h.getDate().toLocalDate().isBefore(LocalDate.now().minusYears(1)));
                break;
        }

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

    public double getValeur() {
        return getLastValue();
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
                this.id = resultSet.getInt("id");
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

        actionsProducts.forEach(actionProduit -> {
            try {
                actionProduit.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        history.forEach(history -> {
            try {
                history.delete();
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
        String query = "UPDATE portefeuille SET name = ?, description = ? WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.name);
            preparedStatement.setString(2, this.description);
            preparedStatement.setInt(3, this.id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
