package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.model.Period;
import fr.cashcoders.capitalhub.model.Portefeuille;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

import java.util.List;

public class LineChartHomeController implements ControllerInterface {

    private final Model model;
    @FXML
    private LineChart<String, Integer> lineChart;

    private Period filter = Period.MONTH;

    public LineChartHomeController(Model model) {
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


    private void setFilter(Period filter) {
        if (this.filter.equals(filter)) return;

        this.filter = filter;
        refresh();
    }

    public void filterDay() {
        setFilter(Period.DAY);
    }

    public void filterMonth() {
        setFilter(Period.MONTH);
    }

    public void filterYear() {
        setFilter(Period.YEAR);
    }

}
