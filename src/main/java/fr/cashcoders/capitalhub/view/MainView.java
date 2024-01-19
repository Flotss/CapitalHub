package fr.cashcoders.capitalhub.view;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.controller.ControllerInterface;
import fr.cashcoders.capitalhub.controller.LineChartHomeController;
import fr.cashcoders.capitalhub.controller.ListPortefeuilleController;
import fr.cashcoders.capitalhub.controller.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.util.List;

public class MainView implements Observer {

    private final Model model;
    private List<ControllerInterface> controllers;

    public MainView(Model model) {
        this.model = model;
        model.setObserver(this);
    }

    public void show() {
        // Line cart fXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LineChartHome.fxml"));
        ControllerInterface controllerLineChart = new LineChartHomeController(model);
        loader.setController(controllerLineChart);


        // ListPortefeuille fXML
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ListPortefeuille.fxml"));
        ControllerInterface controllerListPortefeuille = new ListPortefeuilleController(model);
        loader2.setController(controllerListPortefeuille);

        controllers = List.of(controllerLineChart, controllerListPortefeuille);


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
