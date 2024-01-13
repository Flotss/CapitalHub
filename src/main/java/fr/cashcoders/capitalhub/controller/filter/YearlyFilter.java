package fr.cashcoders.capitalhub.controller.filter;

import fr.cashcoders.capitalhub.model.Period;
import javafx.scene.chart.XYChart;

import java.time.LocalDateTime;
import java.util.Map;

public class YearlyFilter implements FilterStrategy {
    @Override
    public void applyFilter(XYChart.Series<String, Integer> series, Map<LocalDateTime, Integer> value) {
        LocalDateTime endDate = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime startDate = endDate.minusMonths(11);

        int lastValue = 0;
        for (LocalDateTime date = startDate; !date.isAfter(endDate); date = date.plusMonths(1)) {
            String monthLabel = date.getMonth().toString();
            int valueMonth = value.getOrDefault(date, lastValue);
            series.getData().add(new XYChart.Data<>(monthLabel, valueMonth));

            if (valueMonth != 0 && valueMonth != lastValue) {
                lastValue = valueMonth;
            }
        }

    }

    @Override
    public Period getPeriod() {
        return Period.YEAR;
    }
}
