package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.model.Action;
import fr.cashcoders.capitalhub.model.History;
import fr.cashcoders.capitalhub.model.Portefeuille;
import fr.cashcoders.capitalhub.model.Transaction;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.io.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class LineChartHome {

    @FXML
    private LineChart<String, Integer> lineChart;

    private final Model model;

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
        }

        List<Portefeuille> portefeuilles = model.getPortefeuilles();
        for (Portefeuille portefeuille : portefeuilles) {
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName(portefeuille.getName());

            // History
            List<History> history = portefeuille.getHistory();
            Map<LocalDate, Integer> dateToTotalValue = new HashMap<>();

            for (History h : history) {
                LocalDate date = h.getDate().toLocalDate();
                int price = (int) h.getPrice();

                dateToTotalValue.put(date, dateToTotalValue.getOrDefault(date, 0) + price);
            }

            for (Map.Entry<LocalDate, Integer> entry : dateToTotalValue.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
            }

            lineChart.getData().add(series);
        }
    }

}
