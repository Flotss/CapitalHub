package fr.cashcoders.capitalhub.model;

import java.sql.SQLException;

public class ActionProduit implements DBInterface {
    private int quantity;
    private Action action;

    public ActionProduit(int quantity, Action action) {
        this.quantity = quantity;
        this.action = action;
    }

    public int getQuantity() {
        return quantity;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public void save() throws SQLException {

    }

    @Override
    public void update() throws SQLException {

    }

    @Override
    public void delete() throws SQLException {

    }
}
