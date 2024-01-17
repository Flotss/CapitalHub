package fr.cashcoders.capitalhub;

import fr.cashcoders.capitalhub.controller.MenuController;
import fr.cashcoders.capitalhub.controller.Model;
import fr.cashcoders.capitalhub.model.User;
import fr.cashcoders.capitalhub.view.ConnectionView;
import fr.cashcoders.capitalhub.view.HistoryView;
import fr.cashcoders.capitalhub.view.MainView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;


/**
 * Main application class for CapitalHubApp.
 * It extends JavaFX's Application class
 */
public class CapitalHubApp extends Application {
    public static Stage primaryStage;

    public static MainView mainView;
    public static HistoryView historyView;


    /**
     * Changes the scene of the application to the given parent
     *
     * @param parent Parent to change the scene to
     */
    public static void changeScene(Parent parent) {
        VBox root = new VBox();

        MenuController menuController = new MenuController();
        HBox menu = new HBox();
        Button homeButton = new Button("Accueil");
        homeButton.setOnAction(menuController);
        Button historyButton = new Button("Historique");
        historyButton.setOnAction(menuController);
        menu.getChildren().addAll(homeButton, historyButton);
        root.getChildren().add(menu);


        // Pane for parent
        Pane pane = new Pane();
        pane.getChildren().add(parent);

        root.getChildren().add(pane);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setWidth(625);
        primaryStage.setHeight(900);
    }

    private static Button createButtonSquareNoStyle(String text) {
        Button button = new Button(text);
        button.setPrefWidth(100);
        button.setPrefHeight(100);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: white; -fx-font-size: 20px;");
        return button;
    }

    /**
     * Initializes the UI with the given user
     *
     * @param user User to initialize the UI with
     */
    public static void initializeUI(User user) {
        Model model = null;
        try {
            model = new Model(user);
            mainView = new MainView(model);
            historyView = new HistoryView(model);
            mainView.show();
        } catch (SQLException e) {
            System.err.println("Error with database connection" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the UI
     */
    private void connectionUI() {
        ConnectionView connectionView = new ConnectionView();
        connectionView.show();
    }

    /**
     * Starts the application
     *
     * @param stage Stage to start the application with
     */
    @Override
    public void start(Stage stage) {
        CapitalHubApp.primaryStage = stage;
        connectionUI();
    }

    /**
     * Starts the application
     */
    public void start() {
        launch();
    }
}