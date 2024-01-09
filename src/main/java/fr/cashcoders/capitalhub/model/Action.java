package fr.cashcoders.capitalhub.model;

import java.sql.SQLException;

public class Action implements DBInterface {
    private String name;
    private int id;
    private double price;
    // TODO API URL

    public Action(int id, String name, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
