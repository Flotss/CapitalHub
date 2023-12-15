package fr.cashcoders.capitalhub.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ListPortefeuilleController {

    @FXML
    private VBox listePortefeuille;

    private PortefeuilleController portefeuilleController;

    public ListPortefeuilleController(PortefeuilleController portefeuilleController) {
        this.portefeuilleController = portefeuilleController;
    }

    // INITIALIZATION
    @FXML
    public void initialize() {
        // Add portefeuille to listPortefeuille
    }

    @FXML
    public void refresh() {
        // Refresh data from porteufeuilleController
    }
}
