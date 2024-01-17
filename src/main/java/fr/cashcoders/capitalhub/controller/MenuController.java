package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.CapitalHubApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class MenuController implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        Button menu = (Button) event.getSource();
        String text = menu.getText();

        switch (text) {
            case "Accueil":
                CapitalHubApp.mainView.show();
                break;
            case "Historique":
                CapitalHubApp.historyView.show();
                break;
            default:
                break;
        }

    }
}
