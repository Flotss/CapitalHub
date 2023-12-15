package fr.cashcoders.capitalhub.view;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.controller.LineChartController;
import fr.cashcoders.capitalhub.controller.ListPortefeuilleController;
import fr.cashcoders.capitalhub.controller.PortefeuilleController;
import fr.cashcoders.capitalhub.model.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class MainView implements Observer {

    private final PortefeuilleController portefeuilleController;

    public MainView(PortefeuilleController portefeuilleController) {
        this.portefeuilleController = portefeuilleController;
    }

    public void show() {
        // LIne cart fXML

        System.out.println(getClass().getResource(""));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LineChart.fxml"));
        loader.setController(new LineChartController(portefeuilleController));


        // ListPortefeuille fXML
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ListPortefeuille.fxml"));
        loader2.setController(new ListPortefeuilleController(portefeuilleController));


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
    public void update(Observable o, Object arg) {

    }
}
