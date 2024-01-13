package fr.cashcoders.capitalhub.controller.aggregator;

import fr.cashcoders.capitalhub.controller.filter.FilterFactory;
import fr.cashcoders.capitalhub.controller.filter.FilterStrategy;
import fr.cashcoders.capitalhub.model.History;
import fr.cashcoders.capitalhub.model.Period;
import fr.cashcoders.capitalhub.model.Portefeuille;
import javafx.scene.chart.XYChart;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The DataAggregator class provides methods for aggregating and filtering historical data for visualization.
 * It generates an XYChart.Series containing aggregated data based on the specified Portefeuille and time period (Period).
 */
public class DataAggregator {

    /**
     * Generates an XYChart.Series containing aggregated data for the specified Portefeuille and time period.
     * The data is first aggregated using an AggregatorStrategy based on the period, and then filtered using a FilterStrategy.
     *
     * @param portefeuille The Portefeuille for which data should be aggregated and visualized.
     * @param period       The time period (e.g., YEAR, MONTH, DAY) for which data should be aggregated and visualized.
     * @return An XYChart.Series containing aggregated and filtered data for visualization.
     */
    public XYChart.Series<String, Integer> getSeries(Portefeuille portefeuille, Period period) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName(portefeuille.getName());

        List<History> history = portefeuille.getHistory(period);
        Map<LocalDateTime, Integer> data = new HashMap<>();

        AggregatorStrategy aggregatorStrategy = AggregatorFactory.getAggregator(period);
        aggregatorStrategy.aggregate(history, data);
        FilterStrategy filterStrategy = FilterFactory.getFilter(period);
        filterStrategy.applyFilter(series, data);

        return series;
    }
}
