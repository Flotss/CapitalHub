package fr.cashcoders.capitalhub.view;

import fr.cashcoders.capitalhub.CapitalHubApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class ConnectionView {
    public void show() {
        // Connection form fXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConnectionForm.fxml"));

        VBox root = new VBox();
        try {
            root.getChildren().add(loader.load());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        CapitalHubApp.changeScene(root);
    }

}
