package fr.cashcoders.capitalhub.controller.aggregator;

import fr.cashcoders.capitalhub.controller.filter.FilterStrategy;
import fr.cashcoders.capitalhub.model.History;
import fr.cashcoders.capitalhub.model.Portefeuille;
import javafx.scene.chart.XYChart;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAggregator {

    public XYChart.Series<String, Integer> getSeries(Portefeuille portefeuille, FilterStrategy filterStrategy) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName(portefeuille.getName());

        List<History> history = portefeuille.getHistory(filterStrategy.getPeriod());
        Map<LocalDateTime, Integer> data = new HashMap<>();

        AggregatorStrategy aggregatorStrategy = AggregatorFactory.getAggregator(filterStrategy.getPeriod());
        aggregatorStrategy.aggregate(history, data);
        filterStrategy.applyFilter(series, data);

        return series;
    }
}
