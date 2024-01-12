package fr.cashcoders.capitalhub.controller.Aggregator;

import fr.cashcoders.capitalhub.model.History;
import fr.cashcoders.capitalhub.model.Period;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class YearlyAggregator implements AggregatorStrategy {
    @Override
    public void aggregate(List<History> histories, Map<LocalDateTime, Integer> data) {
        for (History h : histories) {
            LocalDateTime date = h.getDate();
            int value = (int) h.getPrice();

            // Pour chaque mois de l'année actuelle
            if (date.getYear() == LocalDate.now().getYear()) {
                LocalDateTime monthKey = date.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
                data.put(monthKey, value);
            }
        }
    }

    @Override
    public Period getPeriod() {
        return Period.YEAR;
    }
}
