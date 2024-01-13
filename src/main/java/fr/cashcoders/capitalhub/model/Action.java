package fr.cashcoders.capitalhub.model;

import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;

public class Action implements DBInterface {
    private String name;
    private int id;
    private double price;
    // TODO API URL

    public Action(int id, String name, double price) {
        this.name = name;
        this.id = id;
        this.price = price;

        if (this.id == 0) {
            try {
                save();
            } catch (SQLException e) {
                Logger.getLogger(this.getClass().getName()).warning(e.getMessage());
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws SQLException {
        this.price = price;
        update();
    }

    @Override
    public void save() throws SQLException {
        String query = "INSERT INTO action (id, name, value) VALUES (DEFAULT, ?, ?) " +
                "ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name, value = EXCLUDED.value " +
                "RETURNING id;";

        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, this.name);
            preparedStatement.setDouble(2, this.price);

            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                this.id = resultSet.getInt("id");
            }
        }
    }


    @Override
    public void update() throws SQLException {
        String query = "UPDATE action SET name = ?, value = ? WHERE id = ?;";
        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, this.name);
            preparedStatement.setDouble(2, this.price);
            preparedStatement.setInt(3, this.id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete() throws SQLException {
        String query = "DELETE FROM action WHERE id = ?;";
        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Action action)) return false;
        return id == action.id && Double.compare(price, action.price) == 0 && Objects.equals(name, action.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, price);
    }
}
