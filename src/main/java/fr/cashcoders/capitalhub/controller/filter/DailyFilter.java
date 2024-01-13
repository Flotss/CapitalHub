package fr.cashcoders.capitalhub.controller.filter;

import fr.cashcoders.capitalhub.model.Period;
import javafx.scene.chart.XYChart;

import java.time.LocalDateTime;
import java.util.Map;

public class DailyFilter implements FilterStrategy {
    @Override
    public void applyFilter(XYChart.Series<String, Integer> series, Map<LocalDateTime, Integer> value) {
        int lastValue = 0;
        LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime tomorrow = today.withHour(LocalDateTime.now().getHour());

        // Parcourir chaque heure de la journée actuelle
        for (LocalDateTime date = today; date.isBefore(tomorrow); date = date.plusHours(1)) {
            int valueHours = value.getOrDefault(date, lastValue);
            series.getData().add(new XYChart.Data<>(date.getHour() + "h", valueHours));

            if (valueHours != 0 && valueHours != lastValue) {
                lastValue = valueHours;
            }
        }
    }

    @Override
    public Period getPeriod() {
        return Period.DAY;
    }
}
