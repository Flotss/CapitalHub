package fr.cashcoders.capitalhub;

import fr.cashcoders.capitalhub.controller.PortefeuilleController;
import fr.cashcoders.capitalhub.view.HistoryView;
import fr.cashcoders.capitalhub.view.MainView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CapitalHubApp extends Application {
    public static Stage primaryStage;
    public static int WIDTH = 1500;
    public static int HEIGHT = 800;
    private static MainView mainView;
    private static HistoryView historyView;
    private PortefeuilleController portefeuilleController;

    public static void changeScene(Parent parent) {
        VBox root = new VBox();

        MenuItem accueilItem = new MenuItem("Accueil");
        accueilItem.setOnAction(event -> {
            mainView.show();
            System.out.println("Accueil");
        });
        MenuItem historyItem = new MenuItem("Historique");
        historyItem.setOnAction(event -> {
            historyView.show();
        });
        Menu menu = new Menu("View");
        menu.getItems().addAll(accueilItem, historyItem);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);
        root.getChildren().add(menuBar);

        // Pane for parent
        Pane pane = new Pane();
        pane.getChildren().add(parent);

        root.getChildren().add(pane);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeUI() {
        portefeuilleController = new PortefeuilleController();
        mainView = new MainView(portefeuilleController);
        historyView = new HistoryView(portefeuilleController);
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