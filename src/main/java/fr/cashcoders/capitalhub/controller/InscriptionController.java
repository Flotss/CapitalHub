package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.model.User;
import fr.cashcoders.capitalhub.view.ConnectionView;
import javafx.fxml.FXML;
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

    public void inscription() throws SQLException {
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
            System.out.println("User found");
            error.setText("Il existe déjà un utilisateur avec ce nom");
        } else {
            user = User.insertNewUser(username.getText(), password.getText());
            CapitalHubApp.initializeUI(user);
        }
    }

    private User createUser() {
        User user = null;
        try {
            user = User.getUserFromDB(username.getText(), password.getText());
        } catch (SQLException e) {
            error.setText("Erreur : " + e.getMessage());
        }
        return user;
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
