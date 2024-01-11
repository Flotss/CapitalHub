package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.model.Portefeuille;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

import java.util.List;

public class LineChartHome {

    private final Model model;
    @FXML
    private LineChart<String, Integer> lineChart;
    private String filter = "year";

    public LineChartHome(Model model) {
        this.model = model;
    }

    @FXML
    public void initialize() {
        refresh();
    }

    @FXML
    public void refresh() {
        if (!lineChart.getData().isEmpty()) {
            lineChart.getData().removeAll(lineChart.getData());
        }

        List<Portefeuille> portefeuilles = model.getPortefeuilles();
        for (Portefeuille portefeuille : portefeuilles) {
            lineChart.getData().add(model.getSeries(portefeuille, filter));
        }

    }


    private void setFilter(String filter) {
        if (this.filter.equals(filter)) return;

        this.filter = filter;
        refresh();
    }

    public void filterDay() {
        setFilter("day");
    }

    public void filterMonth() {
        setFilter("month");
    }

    public void filterYear() {
        setFilter("year");
    }

}
