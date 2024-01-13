package fr.cashcoders.capitalhub.controller.aggregator;

import fr.cashcoders.capitalhub.model.Action;
import fr.cashcoders.capitalhub.model.History;
import fr.cashcoders.capitalhub.model.Period;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YearlyAggregator implements AggregatorStrategy {
    @Override
    public void aggregate(List<History> histories, Map<LocalDateTime, Integer> data) {
        Map<LocalDateTime, Map<Action, Integer>> monthActionValues = new HashMap<>();
        LocalDateTime currentMonth = null;

        for (History h : histories) {
            LocalDateTime date = h.getDate();
            Action action = h.getAction();
            int value = (int) h.getPrice();

            // Pour chaque mois de l'année actuelle
            if (date.getYear() == LocalDate.now().getYear()) {
                LocalDateTime monthKey = date.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);

                if (!monthKey.equals(currentMonth)) {
                    currentMonth = monthKey;
                    monthActionValues.clear(); // Réinitialise la carte pour le nouveau mois
                }

                // Obtient ou crée la carte des actions pour le mois donné
                Map<Action, Integer> actionMap = monthActionValues.computeIfAbsent(monthKey, k -> new HashMap<>());

                // Additionne la valeur à l'action spécifique pour ce mois
                actionMap.put(action, value);

                // Calcule la somme totale pour le mois et met à jour la carte `data`
                data.put(monthKey, actionMap.values().stream().mapToInt(Integer::intValue).sum());
            }
        }
    }

    @Override
    public Period getPeriod() {
        return Period.YEAR;
    }
}
