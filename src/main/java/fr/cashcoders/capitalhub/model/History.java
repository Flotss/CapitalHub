package fr.cashcoders.capitalhub.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class History implements DBInterface {
    private final Portefeuille portefeuille;
    private final Action action;
    private final double price;
    private final LocalDateTime date;
    private int id;


    public History(int id, Portefeuille portefeuille, Action action, double price, LocalDateTime date) {
        this.id = id;
        this.portefeuille = portefeuille;
        this.action = action;
        this.price = price;
        this.date = date;

        if (this.id == 0)
            save();
    }

    public History(Portefeuille portefeuille, Action action, double price) {
        this(0, portefeuille, action, price, LocalDateTime.now());
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
            e.printStackTrace();
        }
    }

    public void insert() throws SQLException {
        String insertQuery = "INSERT INTO history (idportefeuille ,idAction, price, date) VALUES (? ,?, ?, ?) RETURNING id";

        try (var preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, this.portefeuille.getId());
            preparedStatement.setInt(2, this.action.getId());
            preparedStatement.setDouble(3, this.price);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(this.date));


            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                this.id = resultSet.getInt("id");
            }
        }
    }


    @Override
    public void update() throws SQLException {
        String query = "UPDATE history SET price = ?, date = ? WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, this.price);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(this.date));
            preparedStatement.setInt(3, this.id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete() throws SQLException {
        String query = "DELETE FROM history WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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

    public double getPrice() {
        return price;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
