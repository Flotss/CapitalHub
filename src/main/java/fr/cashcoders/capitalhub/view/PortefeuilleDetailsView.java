package fr.cashcoders.capitalhub.view;

import fr.cashcoders.capitalhub.CapitalHubApp;
import fr.cashcoders.capitalhub.controller.ListActionController;
import fr.cashcoders.capitalhub.controller.Model;
import fr.cashcoders.capitalhub.controller.PieChartController;
import fr.cashcoders.capitalhub.model.Observable;
import fr.cashcoders.capitalhub.model.Portefeuille;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class PortefeuilleDetailsView implements Observer {

    private final Model model;
    private final Portefeuille portefeuille;


    public PortefeuilleDetailsView(Model model, Portefeuille portefeuille) {
        this.model = model;
        this.portefeuille = portefeuille;
    }

    public void show() {
        // PieChart for actions fXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ActionPieChart.fxml"));
        loader.setController(new PieChartController(model, portefeuille));


        // Action scrollPane fXML
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ListActions.fxml"));
        loader2.setController(new ListActionController(model, portefeuille));


        VBox root = new VBox();
        try {
            root.getChildren().add(loader.load());
            root.getChildren().add(loader2.load());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        CapitalHubApp.changeScene(root);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
