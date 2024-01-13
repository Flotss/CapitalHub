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

public class DataAggregator {

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
