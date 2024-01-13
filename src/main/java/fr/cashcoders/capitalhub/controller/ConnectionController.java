package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.model.User;
import fr.cashcoders.capitalhub.view.InscriptionView;
import javafx.fxml.FXML;
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

    public void connection() {
        if (!verifyFields()) {
            return;
        }

        User user = null;
        try {
            user = User.getUserFromDB(username.getText(), password.getText());
        } catch (SQLException e) {
            error.setText("Erreur : " + e.getMessage());
        }

        if (user != null) {
            CapitalHubApp.initializeUI(user);
        } else {
            System.out.println("User not found");
            error.setText("Erreur : Utilisateur non trouvé");
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
