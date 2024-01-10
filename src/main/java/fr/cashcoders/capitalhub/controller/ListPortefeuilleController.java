package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.model.Portefeuille;
import fr.cashcoders.capitalhub.view.PortefeuilleView;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.List;

public class ListPortefeuilleController {

    @FXML
    private VBox vboxPortefeuille;

    private Model model;

    public ListPortefeuilleController(Model model) {
        this.model = model;
    }

    // INITIALIZATION
    @FXML
    public void initialize() {
        refresh();
    }

    @FXML
    public void refresh() {
        vboxPortefeuille.getChildren().clear();

        List<Portefeuille> portefeuilles = model.getPortefeuilles();
        for (Portefeuille portefeuille : portefeuilles) {
            PortefeuilleView portefeuilleView = new PortefeuilleView(portefeuille);
            vboxPortefeuille.getChildren().add(portefeuilleView);
        }
    }
}
