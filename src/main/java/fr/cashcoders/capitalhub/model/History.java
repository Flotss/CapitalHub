package fr.cashcoders.capitalhub.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class History implements DBInterface {
    private int id;
    private Portefeuille portefeuille;
    private Action action;
    private double price;
    private LocalDateTime date;


    public History(int id, Portefeuille portefeuille, Action action, double price, LocalDateTime date) {
        this.id = id;
        this.portefeuille = portefeuille;
        this.action = action;
        this.price = price;
        this.date = date;

        if (this.id == 0) {
            try {
                save();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void save() throws SQLException {
        String query = "INSERT INTO history (idAction, price, date) VALUES (?, ?, ?) " +
                "ON CONFLICT (idAction) DO UPDATE SET price = EXCLUDED.price " +
                "RETURNING id;"; // Return the id from the history table

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, this.action.getId());
            preparedStatement.setDouble(2, this.price);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(this.date));

            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                this.id = resultSet.getInt("id");
            }
        }
    }




    @Override
    public void update() throws SQLException {
        String query = "UPDATE history SET price = ?, date = ? WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, this.action.getId());
            preparedStatement.setDouble(2, this.price);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(this.date));
            preparedStatement.setInt(4, this.id);
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
