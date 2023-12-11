package fr.cashcoders.capitalhub.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(TransactionType type, LocalDateTime date, BigDecimal price, String symbol) {
}
