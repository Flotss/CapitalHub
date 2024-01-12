package fr.cashcoders.capitalhub.controller.Filter;

import fr.cashcoders.capitalhub.model.Period;

public class FilterFactory {
    public static FilterStrategy getFilter(Period period) {
        switch (period) {
            case YEAR:
                return new YearlyFilter();
            case MONTH:
                return new MonthlyFilter();
            case DAY:
                return new DailyFilter();
            default:
                throw new IllegalArgumentException("Invalid period");
        }
    }
}
