package fr.cashcoders.capitalhub.controller.filter;

import fr.cashcoders.capitalhub.model.Period;

/**
 * The FilterFactory class provides a factory method to create instances of FilterStrategy based on the specified Period.
 * It allows for the creation of filter strategies for different time periods, such as yearly, monthly, or daily.
 */
public class FilterFactory {

    /**
     * Creates and returns an instance of FilterStrategy based on the specified Period.
     *
     * @param period The Period for which a filter strategy should be created (e.g., YEAR, MONTH, DAY).
     * @return An instance of FilterStrategy corresponding to the specified period.
     * @throws IllegalArgumentException If an invalid period is provided.
     */
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
