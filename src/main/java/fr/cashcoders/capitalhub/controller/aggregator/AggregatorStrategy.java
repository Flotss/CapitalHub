package fr.cashcoders.capitalhub.controller.aggregator;

import fr.cashcoders.capitalhub.model.History;
import fr.cashcoders.capitalhub.model.Period;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * The AggregatorStrategy interface defines the contract for aggregating historical data over a specific time period.
 * Implementing classes should provide specific aggregation implementations for different data sources and criteria.
 */
public interface AggregatorStrategy {

    /**
     * Aggregates historical data from a list of History objects and updates the provided data map accordingly.
     *
     * @param histories A list of History objects representing the historical data to be aggregated.
     * @param data      A map representing the data to be updated, where keys are LocalDateTime objects and values are integers.
     */
    void aggregate(List<History> histories, Map<LocalDateTime, Integer> data);

    /**
     * Retrieves the period associated with the aggregator strategy.
     *
     * @return The Period object indicating the time period covered by the aggregation.
     */
    Period getPeriod();
}

