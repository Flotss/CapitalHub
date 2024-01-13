package fr.cashcoders.capitalhub.controller.aggregator;

import fr.cashcoders.capitalhub.model.Action;
import fr.cashcoders.capitalhub.model.History;
import fr.cashcoders.capitalhub.model.Period;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyAggregator implements AggregatorStrategy {
    @Override
    public void aggregate(List<History> histories, Map<LocalDateTime, Integer> data) {
        Map<LocalDateTime, Map<Action, Integer>> dailyActionValues = new HashMap<>();
        LocalDateTime currentDay = null;

        // Agrégation des données
        for (History h : histories) {
            LocalDateTime date = h.getDate();
            Action action = h.getAction();
            int value = (int) h.getPrice();

            // Vérifie si l'histoire est dans l'année et le mois courants
            if (date.getYear() == LocalDate.now().getYear() && date.getMonth() == LocalDate.now().getMonth()) {
                LocalDateTime dayKey = date.withHour(0).withMinute(0).withSecond(0).withNano(0);

                if (!dayKey.equals(currentDay)) {
                    currentDay = dayKey;
                    dailyActionValues.clear();
                }

                // Obtient ou crée la carte des actions pour le jour donné
                Map<Action, Integer> actionMap = dailyActionValues.computeIfAbsent(dayKey, k -> new HashMap<>());

                // Additionne la valeur à l'action spécifique pour ce jour
                actionMap.put(action, value);

                // Calcule la somme totale pour le jour et met à jour la carte `data`
                data.put(dayKey, actionMap.values().stream().mapToInt(Integer::intValue).sum());
            }
        }
    }


    @Override
    public Period getPeriod() {
        return Period.MONTH;
    }
}
