package fr.cashcoders.capitalhub.controller.filter;

import fr.cashcoders.capitalhub.model.Period;
import javafx.scene.chart.XYChart;

import java.time.LocalDateTime;
import java.util.Map;

public interface FilterStrategy {
    void applyFilter(XYChart.Series<String, Integer> series,
                     Map<LocalDateTime, Integer> value);

    Period getPeriod();
}
