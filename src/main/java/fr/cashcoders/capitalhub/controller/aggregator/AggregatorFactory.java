package fr.cashcoders.capitalhub.controller.aggregator;

import fr.cashcoders.capitalhub.model.Period;

public class AggregatorFactory {
    public static AggregatorStrategy getAggregator(Period period) {
        return switch (period) {
            case YEAR -> new YearlyAggregator();
            case MONTH -> new MonthlyAggregator();
            case DAY -> new DailyAggregator();
            default -> throw new IllegalArgumentException("Invalid period");
        };
    }
}
