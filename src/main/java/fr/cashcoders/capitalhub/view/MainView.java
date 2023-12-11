package fr.cashcoders.capitalhub.view;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.controller.PortefeuilleController;
import fr.cashcoders.capitalhub.model.Transaction;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainView implements Observer {

    private final PortefeuilleController portefeuilleController;

    public MainView(PortefeuilleController portefeuilleController) {
        this.portefeuilleController = portefeuilleController;
    }

    public void show() {
        VBox root = new VBox();
        HBox hBox = new HBox();

        List<Transaction> transactions = portefeuilleController.getTransactions(0);


// LineChar
//        LineChart lineChart = new LineChart(portefeuilleController.getTransactions(0));

        CapitalHubApp.changeScene(root);
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
