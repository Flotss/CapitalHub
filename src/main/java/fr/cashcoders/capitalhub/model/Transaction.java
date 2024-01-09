package fr.cashcoders.capitalhub.model;


import java.time.LocalDateTime;

public record Transaction(int id, int idportefeuille, int idaction, double prix, LocalDateTime date,
                          String codecurrency, TransactionType type) {
}
