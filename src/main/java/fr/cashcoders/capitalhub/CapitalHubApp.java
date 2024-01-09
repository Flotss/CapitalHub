package fr.cashcoders.capitalhub;

import fr.cashcoders.capitalhub.controller.MenuController;
import fr.cashcoders.capitalhub.controller.Model;
import fr.cashcoders.capitalhub.view.HistoryView;
import fr.cashcoders.capitalhub.view.MainView;
import fr.cashcoders.capitalhub.view.PortefeuilleDetailsView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CapitalHubApp extends Application {
    public static Stage primaryStage;

    public static MainView mainView;
    public static HistoryView historyView;
    public static PortefeuilleDetailsView portefeuilleDetailsView;


    public static void changeScene(Parent parent) {
        VBox root = new VBox();

        MenuController menuController = new MenuController();

        MenuItem accueilItem = new MenuItem("Accueil");
        accueilItem.setOnAction(menuController);
        MenuItem historyItem = new MenuItem("Historique");
        historyItem.setOnAction(menuController);
        Menu menu = new Menu("View");
        menu.getItems().addAll(accueilItem, historyItem);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);
        root.getChildren().add(menuBar);

        // Pane for parent
        Pane pane = new Pane();
        pane.getChildren().add(parent);

        root.getChildren().add(pane);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Now you can get the width and height

        primaryStage.setWidth(root.getWidth());
        primaryStage.setHeight(root.getHeight());
    }

    private void initializeUI() {
        Model model = null;
        try {
            model = new Model();
        } catch (SQLException e) {
            System.err.println("Error with database connection" + e.getMessage());
        }
        mainView = new MainView(model);
        historyView = new HistoryView(model);
        mainView.show();
    }

    @Override
    public void start(Stage stage) {
        CapitalHubApp.primaryStage = stage;
        initializeUI();
    }

    public void start() {
        launch();
    }
}