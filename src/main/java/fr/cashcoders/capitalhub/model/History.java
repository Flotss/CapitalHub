package fr.cashcoders.capitalhub.model;

import fr.cashcoders.capitalhub.database.DataBaseConnectionSingleton;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class History implements DBInterface {
    private final List<Event> events;

    public History(List<Event> events) {
        this.events = events;
    }

    public History() {
        this.events = new ArrayList<Event>();
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public boolean removeEvent(Event event) {
        return this.events.remove(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public void save() throws SQLException {
        DataBaseConnectionSingleton db = DataBaseConnectionSingleton.getInstance();
        PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO history (description) VALUES (?)");

    }

    @Override
    public void delete() {

    }
}
