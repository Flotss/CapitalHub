package fr.cashcoders.capitalhub.view;

import fr.cashcoders.capitalhub.controller.PortefeuilleController;
import fr.cashcoders.capitalhub.model.Observable;
import fr.cashcoders.capitalhub.model.Portefeuille;
import javafx.scene.control.Label;

public class PortefeuilleDetailsView implements Observer {

    private final PortefeuilleController portefeuilleController;

    public PortefeuilleDetailsView(PortefeuilleController portefeuilleController) {
        this.portefeuilleController = portefeuilleController;
    }

    public void show(Portefeuille portefeuille) {
        Label label = new Label(portefeuille.getName() + " details");
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
