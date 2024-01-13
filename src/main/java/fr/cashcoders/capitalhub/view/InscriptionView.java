package fr.cashcoders.capitalhub.view;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.model.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class InscriptionView implements Observer {


    public void show() {
        // ListPortefeuille fXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InscriptionForm.fxml"));


        VBox root = new VBox();
        try {
            root.getChildren().add(loader.load());
        } catch (Exception e) {
            System.out.println(getClass().getResource("InscriptionForm.fxml"));
            System.out.println(e.getMessage());
            System.out.println(getClass().getResource(""));
        }

        CapitalHubApp.changeScene(root);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
