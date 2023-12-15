package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.CapitalHubApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class MenuController implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        String text = menuItem.getText();

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
