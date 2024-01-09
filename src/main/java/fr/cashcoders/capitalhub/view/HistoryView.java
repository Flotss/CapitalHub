package fr.cashcoders.capitalhub.view;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.controller.Model;
import fr.cashcoders.capitalhub.model.Observable;
import javafx.scene.control.Label;

public class HistoryView implements Observer {

    private final Model model;

    public HistoryView(Model model) {
        this.model = model;
    }

    public void show() {
        Label label = new Label("Historique");

        CapitalHubApp.changeScene(label);
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
