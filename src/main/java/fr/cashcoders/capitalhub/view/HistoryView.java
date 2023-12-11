package fr.cashcoders.capitalhub.view;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.controller.PortefeuilleController;
import fr.cashcoders.capitalhub.model.Observable;
import javafx.scene.control.Label;

public class HistoryView implements Observer {

    private final PortefeuilleController portefeuilleController;

    public HistoryView(PortefeuilleController portefeuilleController) {
        this.portefeuilleController = portefeuilleController;
    }

    public void show() {
        Label label = new Label("Historique");

        CapitalHubApp.changeScene(label);
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
