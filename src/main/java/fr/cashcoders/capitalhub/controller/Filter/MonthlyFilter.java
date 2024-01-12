package fr.cashcoders.capitalhub.controller.Filter;

import fr.cashcoders.capitalhub.model.Period;
import javafx.scene.chart.XYChart;

import java.time.LocalDateTime;
import java.util.Map;

public class MonthlyFilter implements FilterStrategy {
    @Override
    public void applyFilter(XYChart.Series<String, Integer> series, Map<LocalDateTime, Integer> value) {
        int lastValue = 0;
        LocalDateTime startDay = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(lastValue);

        for (LocalDateTime date = startDay; !date.isAfter(today); date = date.plusDays(1)) {
            int valueDay = value.getOrDefault(date, lastValue);

            series.getData().add(new XYChart.Data<>(date.getDayOfMonth() + "/" + date.getMonthValue(), value.getOrDefault(date, lastValue)));

            if (valueDay != 0 && valueDay != lastValue) {
                lastValue = valueDay;
            }
        }
    }

    @Override
    public Period getPeriod() {
        return Period.MONTH;
    }
}
