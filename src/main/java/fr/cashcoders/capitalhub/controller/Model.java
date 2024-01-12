package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.controller.utils.APIActionScheduler;
import fr.cashcoders.capitalhub.controller.utils.DatabaseFeeder;
import fr.cashcoders.capitalhub.database.DataBaseConnectionSingleton;
import fr.cashcoders.capitalhub.model.*;
import fr.cashcoders.capitalhub.view.HistoryView;
import fr.cashcoders.capitalhub.view.MainView;
import fr.cashcoders.capitalhub.view.PortefeuilleDetailsView;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.cashcoders.capitalhub.model.Period.*;

public class Model {


    private final List<Portefeuille> portefeuilles;
    private final MainView mainView = CapitalHubApp.mainView;
    private final PortefeuilleDetailsView portefeuilleDetailsView = CapitalHubApp.portefeuilleDetailsView;
    private final HistoryView historyView = CapitalHubApp.historyView;
    private final List<Currency> currencies;
    private final Connection connection = DataBaseConnectionSingleton.getInstance().getConnection();
    private Currency currency;
    private APIActionScheduler apiActionScheduler;

    public Model() throws SQLException {
        this.portefeuilles = new ArrayList<>();
        this.currencies = new ArrayList<>();

        DatabaseFeeder.load(portefeuilles, currencies, connection);
        this.currency = currencies.get(0);
        apiActionScheduler = new APIActionScheduler(this);
//        apiActionScheduler.run();
    }

//    public void createPortefeuille(String name, String description) {
//        Portefeuille portefeuille = new Portefeuille(name, description);
//        portefeuilles.add(portefeuille);
//    }
//
//    public void addAction(Portefeuille portefeuille, Action action) {
//        portefeuille.addAction(action);
//        this.addEvent(new Event("Transaction added to " + portefeuille.getName()), portefeuille);
//    }
//
//    public void clonePortefeuille(Portefeuille portefeuille) {
//        Portefeuille clone = new Portefeuille(portefeuille.getName(), portefeuille.getDescription());
//        for (Action action : portefeuille.getActions()) {
//            clone.addAction(action);
//        }
//        portefeuilles.add(clone);
//        this.addEvent(new Event("Portefeuille " + portefeuille.getName() + " cloned"), portefeuille);
//    }

    public void showMainView() {
        mainView.show();
    }


    public void showHistoryView() {
        historyView.show();
    }

    public List<Portefeuille> getPortefeuilles() {
        return portefeuilles;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<History> getHistory(Portefeuille portefeuille) {
        return portefeuille.getHistory();
    }

    public List<ActionProduit> getActionsProduits(Portefeuille portefeuille) {
        return portefeuille.getActionsProduct();
    }

    public List<ActionProduit> getActionsProduit(int index) {
        return portefeuilles.get(index).getActionsProduct();
    }


    public XYChart.Series<String, Integer> getSeries(Portefeuille portefeuille, Period filter) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName(portefeuille.getName());

        List<History> history = portefeuille.getHistory();
        Map<LocalDateTime, Integer> dayToTotalValue = new HashMap<>();
        Map<LocalDateTime, Integer> monthToTotalValue = new HashMap<>();
        Map<LocalDate, Integer> yearToTotalValue = new HashMap<>();


        // Agrégation des données
        for (History h : history) {
            LocalDateTime date = h.getDate();
            int price = (int) h.getPrice();

            if (date.getDayOfYear() == LocalDate.now().getDayOfYear() && date.getYear() == LocalDate.now().getYear()) {  // VOIR TOUT LA JOURNEE AVEC TOUS LES HEURES
                date = date.withMinute(0).withSecond(0).withNano(0);
                dayToTotalValue.put(date, dayToTotalValue.getOrDefault(date, 0) + price);
            }

            if (date.getYear() == LocalDate.now().getYear() && date.getMonth() == LocalDate.now().getMonth()) { // VOIR TOUT LES JOURS DU MOIS
                date = date.withHour(0).withMinute(0).withSecond(0).withNano(0); // ON PEUT L'IGNORER CAR LORS DE LA CREATION DE L'HISTORIQUE, ON NE PREND PAS EN COMPTE LES HEURES
                monthToTotalValue.put(date, monthToTotalValue.getOrDefault(date, 0) + price);
            }

            if (date.getYear() == LocalDate.now().getYear()) { // VOIR TOUT LES MOIS DE L'ANNEE AVEC TOUTES LES MOIS
                date = date.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0); // ON PEUT L'IGNORER CAR LORS DE LA CREATION DE L'HISTORIQUE, ON NE PREND PAS EN COMPTE LES HEURES
                yearToTotalValue.put(date.toLocalDate(), yearToTotalValue.getOrDefault(date.toLocalDate(), 0) + price);
            }
        }


        int lastValue = 0;
        // Construction des données pour la série
        if (DAY.equals(filter)) {
            LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime tomorrow = today.withHour(23);

            // Parcourir chaque heure de la journée actuelle
            for (LocalDateTime date = today; date.isBefore(tomorrow); date = date.plusHours(1)) {
                int valueHours = dayToTotalValue.getOrDefault(date, lastValue);
                series.getData().add(new XYChart.Data<>(date.getHour() + "h", valueHours));

                if (valueHours != 0 && valueHours != lastValue) {
                    lastValue = valueHours;
                }
                System.out.println("Hour : " + date.getHour() + " / " + date.getMinute() + " : " + valueHours);
            }
        } else if (MONTH.equals(filter)) {
            LocalDateTime today = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(lastValue);
            LocalDateTime endDate = today.with(TemporalAdjusters.lastDayOfMonth());

            System.out.println("Today : " + today.getDayOfMonth());
            System.out.println("End : " + endDate.getDayOfMonth());

            for (LocalDateTime date = today; !date.isAfter(endDate); date = date.plusDays(1)) {
                int valueDay = monthToTotalValue.getOrDefault(date, lastValue);

                series.getData().add(new XYChart.Data<>(date.getDayOfMonth() + "/" + date.getMonthValue(), monthToTotalValue.getOrDefault(date, lastValue)));

                if (valueDay != 0) {
                    lastValue = valueDay;
                }
            }

        } else if (YEAR.equals(filter)) {
            LocalDate startDate = LocalDate.now().withMonth(1).withDayOfMonth(1);
            LocalDate endDate = LocalDate.now().withMonth(12).withDayOfMonth(1);

            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusMonths(1)) {
                String monthLabel = date.getMonth().toString();
                int valueMonth = yearToTotalValue.getOrDefault(date, lastValue);
                series.getData().add(new XYChart.Data<>(monthLabel, yearToTotalValue.getOrDefault(date, lastValue)));

                if (valueMonth != 0) {
                    lastValue = valueMonth;
                }
            }
        }

        return series;
    }

    private LocalDateTime resetTime(LocalDateTime dateTime) {
        return dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }


    public Portefeuille getPortefeuille(String name) {
        for (Portefeuille portefeuille : portefeuilles) {
            if (portefeuille.getName().equals(name)) {
                return portefeuille;
            }
        }
        return null;
    }

    public void updateActions(Map<String, Double> actions) {
        List<Action> actionsAlreadyUpdated = new ArrayList<>();
        for (Portefeuille portefeuille : portefeuilles) {
            for (ActionProduit actionProduit : portefeuille.getActionsProduct()) {
                Action action = actionProduit.getAction();
                if (actions.containsKey(action.getName()) && !actionsAlreadyUpdated.contains(action)) {
                    try {
                        action.setPrice(actions.get(action.getName()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    actionsAlreadyUpdated.add(action);
                }
            }
        }
    }
}
