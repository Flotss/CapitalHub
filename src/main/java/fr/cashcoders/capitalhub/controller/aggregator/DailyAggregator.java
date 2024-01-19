package fr.cashcoders.capitalhub.controller.aggregator;

import fr.cashcoders.capitalhub.model.Action;
import fr.cashcoders.capitalhub.model.History;
import fr.cashcoders.capitalhub.model.Period;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyAggregator implements AggregatorStrategy {
    @Override
    public void aggregate(List<History> histories, Map<LocalDateTime, Integer> data) {
        Map<LocalDateTime, Map<Action, Integer>> hourActionValues = new HashMap<>();

        // Agrégation des données
        for (History h : histories) {
            LocalDateTime date = h.getDate();
            Action action = h.getAction();
            int value = (int) h.getPrice();

            // Pour chaque heure du jour actuel
            if (date.getDayOfYear() == LocalDate.now().getDayOfYear() && date.getYear() == LocalDate.now().getYear()) {
                LocalDateTime hourKey = date.withMinute(0).withSecond(0).withNano(0);

                // Obtient ou crée la carte des actions pour l'heure donnée
                Map<Action, Integer> actionMap = hourActionValues.computeIfAbsent(hourKey, k -> new HashMap<>());

                // Additionne la valeur à l'action spécifique pour cette heure
                actionMap.merge(action, value, Integer::sum);

                // Calcule la somme totale pour l'heure et met à jour la carte `data`
                data.put(hourKey, actionMap.values().stream().mapToInt(Integer::intValue).sum());
            }
        }
    }

    @Override
    public Period getPeriod() {
        return Period.DAY;
    }
}
