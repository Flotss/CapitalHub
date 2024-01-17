package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.model.Portefeuille;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class BuySellController {
    private final Portefeuille portefeuille;
    private final Model model;

    @FXML
    ChoiceBox<String> choiceBoxType;

    @FXML
    ChoiceBox<String> choiceBoxAction;
    @FXML
    Label labelActionValue;

    @FXML
    Label infoLabel;


    public BuySellController(Model model, Portefeuille portefeuille) {
        this.model = model;
        this.portefeuille = portefeuille;
    }

    public void buy() {
        // TODO
    }
}
