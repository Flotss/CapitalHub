package fr.cashcoders.capitalhub.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

public class LineChartController {

    @FXML
    private LineChart<String, Integer> lineChart;


    private PortefeuilleController portefeuilleController;

    public LineChartController(PortefeuilleController portefeuilleController) {
        this.portefeuilleController = portefeuilleController;
    }


    public void handle(ActionEvent event) {
        // Refresh data from porteufeuilleController

        // Serie
        LineChart.Series<String, Integer> series = new LineChart.Series<>();
        series.setName("My portfolio");

        if (!lineChart.getData().isEmpty()) {
            lineChart.getData().removeAll(lineChart.getData());
            return;
        }


        // Add data to serie
        series.getData().add(new LineChart.Data<>("Jan", 23));
        series.getData().add(new LineChart.Data<>("Feb", 14));
        series.getData().add(new LineChart.Data<>("Mar", 15));
        series.getData().add(new LineChart.Data<>("Apr", 24));
        series.getData().add(new LineChart.Data<>("May", 34));
        series.getData().add(new LineChart.Data<>("Jun", 36));
        series.getData().add(new LineChart.Data<>("Jul", 22));
        series.getData().add(new LineChart.Data<>("Aug", 45));
        series.getData().add(new LineChart.Data<>("Sep", 43));
        series.getData().add(new LineChart.Data<>("Oct", 17));
        series.getData().add(new LineChart.Data<>("Nov", 29));
        series.getData().add(new LineChart.Data<>("Dec", 25));

    }
}
