package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.model.User;
import fr.cashcoders.capitalhub.view.ConnectionView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class InscriptionController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label error;

    @FXML
    private Button inscriptionButton;

    public void inscription() throws SQLException {
        inscriptionButton.setDisable(true);
        if (!verifyFields()) {
            inscriptionButton.setDisable(false);
            return;
        }

        User user = null;
        try {
            user = User.getUserFromDB(username.getText(), password.getText());
        } catch (SQLException e) {
            error.setText("Erreur : " + e.getMessage());
            inscriptionButton.setDisable(false);
        }

        if (user != null) {
            error.setText("Il existe déjà un utilisateur avec ce nom");
            inscriptionButton.setDisable(false);
        } else {
            user = User.insertNewUser(username.getText(), password.getText());
            CapitalHubApp.initializeUI(user);
        }
    }

    public void connection() {
        ConnectionView connectionView = new ConnectionView();
        connectionView.show();
    }


    public boolean verifyFields() {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            error.setText("Erreur : Veuillez remplir tous les champs");
            return false;
        }

        return true;
    }


}
