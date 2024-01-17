package fr.cashcoders.capitalhub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class Transaction implements DBInterface {
    private final Portefeuille portefeuille;
    private final Action action;
    private final double prix;
    private final LocalDateTime date;
    private final String codecurrency;
    private final TransactionType type;
    private int id;

    public Transaction(int id, Portefeuille portefeuille, Action action, double prix, LocalDateTime date,
                       String codecurrency, TransactionType type) {
        this.id = id;
        this.portefeuille = portefeuille;
        this.action = action;
        this.prix = prix;
        this.date = date;
        this.codecurrency = codecurrency;
        this.type = type;
        save();
    }


    public void insert() throws SQLException {
        String insertQuery = "INSERT INTO transaction (idportefeuille, idaction, price, date, codecurrency, type) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";

        try (var preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, this.portefeuille.getId());
            preparedStatement.setInt(2, this.action.getId());
            preparedStatement.setDouble(3, this.prix);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(this.date));
            preparedStatement.setString(5, this.codecurrency);
            preparedStatement.setString(6, this.type.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                this.id = resultSet.getInt("id");
            }
        }
    }

    @Override
    public void save() {
        try {
            if (this.id == 0) {
                insert();
            } else {
                update();
            }
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).warning(e.getMessage());
        }
    }

    @Override
    public void update() throws SQLException {
        String query = "UPDATE transaction SET price = ?, date = ?, codecurrency = ? WHERE id = ?;";
        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, this.prix);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(this.date));
            preparedStatement.setString(3, this.codecurrency);
            preparedStatement.setInt(4, this.id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete() throws SQLException {
        String query = "DELETE FROM transaction WHERE id = ?;";
        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
        }
    }

    public int getId() {
        return id;
    }

    public Portefeuille getPortefeuille() {
        return portefeuille;
    }

    public Action getAction() {
        return action;
    }

    public double getPrix() {
        return prix;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getCodecurrency() {
        return codecurrency;
    }

    public TransactionType getType() {
        return type;
    }
}
