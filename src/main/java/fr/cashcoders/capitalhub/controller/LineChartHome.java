package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.model.Portefeuille;
import fr.cashcoders.capitalhub.model.Transaction;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class LineChartHome {

    @FXML
    private LineChart<String, Integer> lineChart;

    private Model model;

    public LineChartHome(Model model) {
        this.model = model;
    }

    @FXML
    public void initialize() {


        // Serie
        refresh();

    }

    @FXML
    public void refresh() {
        if (!lineChart.getData().isEmpty()) {
            lineChart.getData().removeAll(lineChart.getData());
            return;
        }

        List<Portefeuille> portefeuilles = model.getPortefeuilles();
        for (Portefeuille portefeuille : portefeuilles) {
            // Create a map to store total amount for each hour
            Map<LocalDateTime, Double> totalAmountPerHour = new HashMap<>();
            // Create a map to store total amount for each day
            Map<LocalDate, Double> totalAmountPerDay = new HashMap<>();

            // Create a set to store unique days
            Set<LocalDate> uniqueDays = new HashSet<>();

            List<Transaction> transactions = portefeuille.getTransactions();
            for (Transaction transaction : transactions) {
                LocalDateTime dateTime = transaction.date();
                double prix = transaction.prix();

                // Round the date time to the nearest hour
                LocalDateTime hour = dateTime.truncatedTo(ChronoUnit.HOURS);
                // Get the date part of the date time
                LocalDate date = dateTime.toLocalDate();

                // Add the transaction amount to the total for the hour
                totalAmountPerHour.put(hour, totalAmountPerHour.getOrDefault(hour, 0.0) + prix);
                // Add the transaction amount to the total for the day
                totalAmountPerDay.put(date, totalAmountPerDay.getOrDefault(date, 0.0) + prix);

                // Add the date to the set of unique days
                uniqueDays.add(date);
            }

            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName(portefeuille.getName());

            // If there are more than 3 unique days, use day display
            if (uniqueDays.size() > 3) {
                // Add data to series
                for (Map.Entry<LocalDate, Double> entry : totalAmountPerDay.entrySet()) {
                    series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue().intValue()));
                }
            } else {
                // Add data to series
                for (Map.Entry<LocalDateTime, Double> entry : totalAmountPerHour.entrySet()) {
                    series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue().intValue()));
                }
            }

            // Add serie to lineChart
            lineChart.getData().add(series);
        }
    }
}
