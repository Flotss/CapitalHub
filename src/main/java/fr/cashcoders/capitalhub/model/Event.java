package fr.cashcoders.capitalhub.model;

import java.time.LocalDateTime;

public record Event(String description) {

    private static final LocalDateTime date = LocalDateTime.now();

    public LocalDateTime getDate() {
        return date;
    }
}
