package fr.cashcoders.capitalhub.controller.filter;

import fr.cashcoders.capitalhub.model.Period;
import javafx.scene.chart.XYChart;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * The FilterStrategy interface defines the contract for applying filters to data represented as XYChart.Series.
 * Implementing classes should provide specific filter implementations for different data sources and criteria.
 */
public interface FilterStrategy {

    /**
     * Applies a filter to the provided data and updates the XYChart.Series accordingly.
     *
     * @param series The XYChart.Series to which the filter will be applied.
     * @param value  A map representing the data to be filtered, where keys are LocalDateTime objects and values are integers.
     */
    void applyFilter(XYChart.Series<String, Integer> series, Map<LocalDateTime, Integer> value);

    /**
     * Retrieves the period associated with the filter strategy.
     *
     * @return The Period object indicating the time period covered by the filter.
     */
    Period getPeriod();
}

