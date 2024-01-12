package fr.cashcoders.capitalhub.controller.Aggregator;

import fr.cashcoders.capitalhub.model.History;
import fr.cashcoders.capitalhub.model.Period;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MonthlyAggregator implements AggregatorStrategy {
    @Override
    public void aggregate(List<History> histories, Map<LocalDateTime, Integer> data) {
        // Agrégation des données
        for (History h : histories) {
            LocalDateTime date = h.getDate();
            int value = (int) h.getPrice();

            if (date.getYear() == LocalDate.now().getYear() && date.getMonth() == LocalDate.now().getMonth()) {
                LocalDateTime dayKey = date.withHour(0).withMinute(0).withSecond(0).withNano(0);
                data.put(dayKey, value);
            }
        }
    }

    @Override
    public Period getPeriod() {
        return Period.MONTH;
    }
}
