package fr.cashcoders.capitalhub.controller.Aggregator;

import fr.cashcoders.capitalhub.model.History;
import fr.cashcoders.capitalhub.model.Period;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AggregatorStrategy {
    void aggregate(List<History> histories, Map<LocalDateTime, Integer> data);
    Period getPeriod();
}
