package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.controller.utils.DatabaseFeeder;
import fr.cashcoders.capitalhub.database.DataBaseConnectionSingleton;
import fr.cashcoders.capitalhub.model.ActionProduit;
import fr.cashcoders.capitalhub.model.Currency;
import fr.cashcoders.capitalhub.model.History;
import fr.cashcoders.capitalhub.model.Portefeuille;
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

public class Model {


    private final List<Portefeuille> portefeuilles;
    private final MainView mainView = CapitalHubApp.mainView;
    private final PortefeuilleDetailsView portefeuilleDetailsView = CapitalHubApp.portefeuilleDetailsView;
    private final HistoryView historyView = CapitalHubApp.historyView;
    private final List<Currency> currencies;
    private final Connection connection = DataBaseConnectionSingleton.getInstance().getConnection();
    private Currency currency;

    public Model() throws SQLException {
        this.portefeuilles = new ArrayList<>();
        this.currencies = new ArrayList<>();

        DatabaseFeeder.load(portefeuilles, currencies, connection);
        this.currency = currencies.get(0); // ! TODO : REGARDER çA

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


    public XYChart.Series<String, Integer> getSeries(Portefeuille portefeuille, String filter) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName(portefeuille.getName());

        List<History> history = portefeuille.getHistory();
        Map<LocalDateTime, Integer> dayToTotalValue = new HashMap<>();
        Map<LocalDateTime, Integer> monthToTotalValue = new HashMap<>();
        Map<LocalDate, Integer> yearToTotalValue = new HashMap<>();

        int lastValue = 0;

        // Agrégation des données
        for (History h : history) {
            LocalDateTime date = h.getDate();
            int price = (int) h.getPrice();

            // ! HAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
            // TODO : AJOUTE LE DERNIER PRIX DE L'ACTION SI IL N'Y A PAS DE TRANSACTION CE JOUR LA
            if (date.getDayOfMonth() == LocalDate.now().getDayOfMonth()) { // VOIR TOUT LA JOURNEE AVEC TOUTES LES MINUTES
                date = date.withSecond(0).withNano(0);
                dayToTotalValue.put(date, dayToTotalValue.getOrDefault(date, 0) + price);
            }

            if (date.getMonth() == LocalDate.now().getMonth()) { // VOIR TOUT LES JOURS DU MOIS AVEC TOUTES LES HEURES
                date = date.withMinute(0).withSecond(0).withNano(0);
                monthToTotalValue.put(date, monthToTotalValue.getOrDefault(date, 0) + price);
            }

            if (date.getYear() == LocalDate.now().getYear()) { // VOIR TOUT LES MOIS DE L'ANNEE AVEC TOUTES LES JOURS
                date = date.withHour(0).withMinute(0).withSecond(0).withNano(0); // ON PEUT L'IGNORER CAR LORS DE LA CREATION DE L'HISTORIQUE, ON NE PREND PAS EN COMPTE LES HEURES
                yearToTotalValue.put(date.toLocalDate(), yearToTotalValue.getOrDefault(date.toLocalDate(), 0) + price);
            }
        }

        // LENGTH OF MAPS
        System.out.println(dayToTotalValue.values().size());
        System.out.println(monthToTotalValue.values().size());
        System.out.println(yearToTotalValue.values().size());

        // VALUE SUM
        System.out.println(dayToTotalValue.values().stream().mapToInt(Integer::intValue).sum());
        System.out.println(monthToTotalValue.values().stream().mapToInt(Integer::intValue).sum());
        System.out.println(yearToTotalValue.values().stream().mapToInt(Integer::intValue).sum());


        // PRINT ALL DAYS from monthToTotalValue
        System.out.println("Ce mois ci :");
        for (LocalDateTime date : monthToTotalValue.keySet()) {
            System.out.println(date.getDayOfMonth() + "/" + date.getMonthValue() + " : " + monthToTotalValue.get(date));
        }


        // Construction des données pour la série
        if ("day".equals(filter)) {
            LocalDateTime today = LocalDateTime.now().withSecond(0).withNano(0);
            List<Integer> hours = new ArrayList<>();

            // Parcourir chaque heure de la journée actuelle
            for (LocalDateTime date = today; !date.isAfter(today.plusDays(1)); date = date.plusHours(1)) {
                String hourLabel = date.getHour() + "h";
                int totalValueForHour = dayToTotalValue.getOrDefault(date, 0);
                series.getData().add(new XYChart.Data<>(hourLabel, totalValueForHour));
            }
        } else if ("month".equals(filter)) {
            LocalDateTime today = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
            LocalDateTime endDate = today.with(TemporalAdjusters.lastDayOfMonth());
            List<Integer> days = new ArrayList<>();

            for (LocalDateTime date = today; !date.isAfter(endDate); date = date.plusDays(1)) {
                String dayLabel = date.getDayOfMonth() + "/" + date.getMonthValue();
                if (days.isEmpty() || !days.contains(date.getDayOfMonth())) {
                    series.getData().add(new XYChart.Data<>(dayLabel, monthToTotalValue.getOrDefault(date, 0)));
                    days.add(date.getDayOfMonth());
                } else {
                    series.getData().add(new XYChart.Data<>("", monthToTotalValue.getOrDefault(date, 0)));
                }


                series.getData().add(new XYChart.Data<>(date.getDayOfMonth() + "/" + date.getMonthValue(), monthToTotalValue.getOrDefault(date, 0)));
            }
        } else if ("year".equals(filter)) {
            LocalDate startDate = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
            LocalDate endDate = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
            List<Integer> months = new ArrayList<>();

            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusMonths(1)) {
                String monthLabel = date.getMonth().toString();
                if (months.isEmpty() || !months.contains(date.getMonthValue())) {
                    series.getData().add(new XYChart.Data<>(monthLabel, yearToTotalValue.getOrDefault(date, 0)));
                    months.add(date.getMonthValue());
                } else {
                    series.getData().add(new XYChart.Data<>("", yearToTotalValue.getOrDefault(date, 0)));
                }
            }
        }

        return series;
    }


    public Portefeuille getPortefeuille(String name) {
        for (Portefeuille portefeuille : portefeuilles) {
            if (portefeuille.getName().equals(name)) {
                return portefeuille;
            }
        }
        return null;
    }
}
