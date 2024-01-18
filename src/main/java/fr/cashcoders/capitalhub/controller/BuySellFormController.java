package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.exception.TransactionException;
import fr.cashcoders.capitalhub.model.Action;
import fr.cashcoders.capitalhub.model.ActionProduit;
import fr.cashcoders.capitalhub.model.Portefeuille;
import fr.cashcoders.capitalhub.model.TransactionType;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fr.cashcoders.capitalhub.model.TransactionType.BUY;
import static fr.cashcoders.capitalhub.model.TransactionType.SELL;

public class BuySellFormController {
    private final Portefeuille portefeuille;
    private final Model model;
    private final Stage stage;

    @FXML
    ChoiceBox<TransactionType> choiceBoxType;

    @FXML
    ChoiceBox<Action> choiceBoxAction;
    @FXML
    Label labelActionValue;

    @FXML
    Label infoLabel;

    @FXML
    TextField textFieldQuantity;


    public BuySellFormController(Model model, Portefeuille portefeuille, Stage stage) {
        this.model = model;
        this.portefeuille = portefeuille;
        this.stage = stage;
    }


    @FXML
    public void initialize() {
        // Action
        choiceBoxAction.getItems().addAll(Model.actions);
        choiceBoxAction.getSelectionModel().selectFirst();
        choiceBoxAction.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            labelActionValue.setText(String.valueOf(choiceBoxAction.getValue().getPrice() * Double.parseDouble(textFieldQuantity.getText())));
        });

        // Type
        choiceBoxType.getItems().addAll(BUY, SELL);
        choiceBoxType.getSelectionModel().selectFirst();
        choiceBoxType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setLabelActionValueFromType();
        });

        // Quantity
        textFieldQuantity.setText("0");

        setLabelActionValue();
    }


    public void inputQuantity() {
        // Replace all non numeric characters and not a dot et un seul dot si deux dot supprimer le deuxieme
        String regex = "[^\\d.]|(?<=\\.)\\.|^\\.";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textFieldQuantity.getText());

        if (matcher.find()) {
            textFieldQuantity.setText(matcher.replaceAll(""));
        }


        try {
            double quantity = Double.valueOf(textFieldQuantity.getText());
            if (quantity < 0) {
                textFieldQuantity.setText("0");
            }
        } catch (NumberFormatException ignored) {
        }

        setLabelActionValue();
    }

    private void setLabelActionValue() {
        String formattedValue = String.format("%.2f", choiceBoxAction.getValue().getPrice() * Double.parseDouble(textFieldQuantity.getText()));
        labelActionValue.setText(formattedValue + "$");
    }

    private void setLabelActionValueFromType() {
        if (choiceBoxType.getValue() == SELL) {
            ActionProduit actionProduit = portefeuille.getActionProduit(choiceBoxAction.getValue());
            double quantity = actionProduit == null ? 0 : actionProduit.getQuantity();
            textFieldQuantity.setText(String.valueOf(quantity));
        } else {
            setLabelActionValue();
        }
    }

    public void cancel() {
        close();
    }

    public void makeTransaction() {
        if (!verifyFields()) {
            return;
        }

        double quantity = Double.parseDouble(textFieldQuantity.getText());
        double price = choiceBoxAction.getValue().getPrice() * quantity;
        if (choiceBoxType.getValue() == SELL) {
            price *= -1;
        }
        String formattedPrice = String.format("%.2f", price);
        infoLabel.setText("Transaction effectuée pour " + formattedPrice + "$");
        try {
            model.makeTransaction(portefeuille, choiceBoxAction.getValue(), choiceBoxType.getValue(), price, quantity);
        } catch (TransactionException e) {
            infoLabel.setText(e.getMessage());
        }
    }


    private void close() {
        stage.close();
    }

    private boolean verifyFields() {
        if (textFieldQuantity.getText().isEmpty()) {
            infoLabel.setText("Veuillez entrer une quantité");
            return false;
        }
        try {
            double quantity = Double.parseDouble(textFieldQuantity.getText());
            if (quantity <= 0) {
                infoLabel.setText("Veuillez entrer une quantité positive");
                return false;
            }
        } catch (NumberFormatException e) {
            infoLabel.setText("Veuillez entrer une quantité valide");
            return false;
        }
        return true;
    }
}
