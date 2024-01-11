package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.model.Portefeuille;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

import java.util.List;

public class LineChartHome {

    private final Model model;
    @FXML
    private LineChart<String, Integer> lineChart;

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
            lineChart.getData().add(model.getSeries(portefeuille));
        }
    }

}
