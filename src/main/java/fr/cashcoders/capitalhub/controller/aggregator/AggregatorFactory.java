package fr.cashcoders.capitalhub.controller.aggregator;

import fr.cashcoders.capitalhub.model.Period;

public class AggregatorFactory {
    public static AggregatorStrategy getAggregator(Period period) {
        switch (period) {
            case YEAR:
                return new YearlyAggregator();
            case MONTH:
                return new MonthlyAggregator();
            case DAY:
                return new DailyAggregator();
            default:
                throw new IllegalArgumentException("Invalid period");
        }
    }
}
