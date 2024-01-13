package fr.cashcoders.capitalhub.model;

import java.sql.SQLException;
import java.util.logging.Logger;

public class ActionProduit implements DBInterface {
    private final Action action;
    private final Portefeuille portefeuille;
    private final double quantity;

    public ActionProduit(Action action, Portefeuille portefeuille, double quantity) {
        this.portefeuille = portefeuille;
        this.quantity = quantity;
        this.action = action;

        if (this.action.getId() == 0) {
            try {
                this.action.save();
            } catch (SQLException e) {
                Logger.getLogger(this.getClass().getName()).warning(e.getMessage());
            }
        }
    }

    public double getQuantity() {
        return quantity;
    }

    public Action getAction() {
        return action;
    }

    public int getIdAction() {
        return action.getId();
    }


    public String getActionName() {
        return this.action.getName();
    }

    public String getPriceOfOneAction() {
        return String.valueOf(this.action.getPrice());
    }

    public double getActionValue() {
        Action action = this.action;
        return action.getPrice() * this.quantity;
    }


    @Override
    public void save() throws SQLException {
        String query = "INSERT INTO actionProduit (idAction, idportefeuille, quantity) VALUES (?, ?, ?) ON CONFLICT (idAction, idportefeuille, quantity) DO UPDATE SET quantity = EXCLUDED.quantity;";
        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, this.action.getId());
            preparedStatement.setInt(2, this.portefeuille.getId());
            preparedStatement.setDouble(3, this.quantity);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update() throws SQLException {
        String query = "UPDATE actionProduit SET quantity = ? WHERE idaction = ? AND idportefeuille = ?;";
        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, this.quantity);
            preparedStatement.setInt(2, this.action.getId());
            preparedStatement.setInt(3, this.portefeuille.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete() throws SQLException {
        String query = "DELETE FROM actionProduit WHERE idaction = ? AND idportefeuille = ?;";
        try (var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, this.action.getId());
            preparedStatement.setInt(2, this.portefeuille.getId());
            preparedStatement.executeUpdate();
        }
    }


}
