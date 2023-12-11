package fr.cashcoders.capitalhub.model;

import java.util.ArrayList;
import java.util.List;

public class History {
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

}
