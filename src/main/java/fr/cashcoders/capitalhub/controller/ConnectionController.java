package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.model.User;
import fr.cashcoders.capitalhub.view.InscriptionView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ConnectionController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label error;

    @FXML
    private Button connectionButton;

    public void connection() {
        connectionButton.setDisable(true);
        if (!verifyFields()) {
            connectionButton.setDisable(false);
            return;
        }

        User user = null;
        try {
            user = User.getUserFromDB(username.getText(), password.getText());
        } catch (SQLException e) {
            error.setText("Erreur : " + e.getMessage());
            connectionButton.setDisable(false);
        }

        if (user != null) {
            CapitalHubApp.initializeUI(user);
        } else {
            error.setText("Erreur : Utilisateur non trouvé");
            connectionButton.setDisable(false);
        }
    }


    public void inscription() {
        InscriptionView inscriptionView = new InscriptionView();
        inscriptionView.show();
    }

    public boolean verifyFields() {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            error.setText("Erreur : Veuillez remplir tous les champs");
            return false;
        }

        return true;
    }


}
