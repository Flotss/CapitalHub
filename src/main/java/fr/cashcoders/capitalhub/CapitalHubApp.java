package fr.cashcoders.capitalhub;

import fr.cashcoders.capitalhub.controller.MenuController;
import fr.cashcoders.capitalhub.controller.PortefeuilleController;
import fr.cashcoders.capitalhub.database.DataBaseConnectionSingleton;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CapitalHubApp extends Application {
    public static Stage primaryStage;
    public static int WIDTH = 1500;
    public static int HEIGHT = 800;


    public static MainView mainView;
    public static HistoryView historyView;
    public static PortefeuilleDetailsView portefeuilleDetailsView;


    public static void changeScene(Parent parent) {
        DataBaseConnectionSingleton dataBaseConnectionSingleton = DataBaseConnectionSingleton.getInstance();
        Connection connection = dataBaseConnectionSingleton.getConnection();
        // make a select * from portefeuille where id = 1
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from portefeuille where idportefeuille = 1");
            // print
            preparedStatement.executeQuery();
            System.out.println(preparedStatement.getResultSet());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeUI() {
        PortefeuilleController portefeuilleController = new PortefeuilleController();
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