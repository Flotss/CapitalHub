package fr.cashcoders.capitalhub.controller.aggregator;

import fr.cashcoders.capitalhub.model.Period;

/**
 * The AggregatorFactory class provides a factory method to create instances of AggregatorStrategy based on the specified Period.
 * It allows for the creation of aggregator strategies for different time periods, such as yearly, monthly, or daily.
 */
public class AggregatorFactory {

    /**
     * Creates and returns an instance of AggregatorStrategy based on the specified Period.
     *
     * @param period The Period for which an aggregator strategy should be created (e.g., YEAR, MONTH, DAY).
     * @return An instance of AggregatorStrategy corresponding to the specified period.
     * @throws IllegalArgumentException If an invalid period is provided.
     */
    public static AggregatorStrategy getAggregator(Period period) {
        return switch (period) {
            case YEAR -> new YearlyAggregator();
            case MONTH -> new MonthlyAggregator();
            case DAY -> new DailyAggregator();
            default -> throw new IllegalArgumentException("Invalid period");
        };
    }
}
