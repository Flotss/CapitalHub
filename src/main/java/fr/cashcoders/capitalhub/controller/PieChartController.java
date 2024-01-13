package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.model.ActionProduit;
import fr.cashcoders.capitalhub.model.Portefeuille;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class PieChartController implements ControllerInterface {
    private final Model model;
    private Portefeuille portefeuille;

    private double totalValue;

    @FXML
    private PieChart actionsPieChart;

    public PieChartController(Model model, Portefeuille portefeuille) {
        this.model = model;
        this.portefeuille = portefeuille;
        this.totalValue = totalValue();
    }

    @FXML
    public void initialize() {
        refresh();

        for (PieChart.Data data : actionsPieChart.getData()) {
            data.getNode().setOnMouseEntered(event -> {
                data.getNode().setScaleX(1.1);
                data.getNode().setScaleY(1.1);
            });
            data.getNode().setOnMouseExited(event -> {
                data.getNode().setScaleX(1);
                data.getNode().setScaleY(1);
            });
        }
    }

    @FXML
    public void refresh() {
        this.totalValue = totalValue();

        this.actionsPieChart.getData().clear();
        for (ActionProduit actionProduit : portefeuille.getActionsProduct()) {
            double valueTronque = (actionProduit.getActionValue() * 100);
            double pourcentage = (valueTronque / totalValue);

            this.actionsPieChart.getData().add(new PieChart.Data(actionProduit.getActionName() + " / " + String.format("%.2f", pourcentage) + "%", actionProduit.getActionValue() / totalValue * 100));
        }
    }

    private double totalValue() {
        double total = 0;
        this.portefeuille = model.getPortefeuille(portefeuille.getName());
        assert portefeuille != null;
        for (ActionProduit actionProduit : portefeuille.getActionsProduct()) {
            total += actionProduit.getActionValue();
        }
        return total;
    }
}
