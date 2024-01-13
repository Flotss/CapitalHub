package fr.cashcoders.capitalhub.view;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.controller.ControllerInterface;
import fr.cashcoders.capitalhub.controller.ListActionController;
import fr.cashcoders.capitalhub.controller.Model;
import fr.cashcoders.capitalhub.controller.PieChartController;
import fr.cashcoders.capitalhub.model.Portefeuille;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.util.List;

public class PortefeuilleDetailsView implements Observer {

    private final Model model;
    private final Portefeuille portefeuille;
    private List<ControllerInterface> controllers;


    public PortefeuilleDetailsView(Model model, Portefeuille portefeuille) {
        this.model = model;
        this.portefeuille = portefeuille;
        model.setObserver(this);
    }

    public void show() {
        // PieChart for actions fXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ActionPieChart.fxml"));
        ControllerInterface controllerPieChart = new PieChartController(model, portefeuille);
        loader.setController(controllerPieChart);


        // Action scrollPane fXML
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ListActions.fxml"));
        ControllerInterface controllerListActions = new ListActionController(model, portefeuille);
        loader2.setController(controllerListActions);

        controllers = List.of(controllerPieChart, controllerListActions);


        VBox root = new VBox();
        try {
            root.getChildren().add(loader.load());
            root.getChildren().add(loader2.load());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        CapitalHubApp.changeScene(root);
    }

    @Override
    public void update() {
        for (ControllerInterface controller : controllers) {
            controller.refresh();
        }
    }
}
