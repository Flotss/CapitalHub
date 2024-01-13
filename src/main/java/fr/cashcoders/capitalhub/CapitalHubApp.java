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

        primaryStage.setWidth(625);
        primaryStage.setHeight(1000);
    }

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

    private void connectionUI() {
        ConnectionView connectionView = new ConnectionView();
        connectionView.show();
    }

    @Override
    public void start(Stage stage) {
        CapitalHubApp.primaryStage = stage;
//        connectionUI();

        initializeUI(new User(1, "test"));
    }

    public void start() {
        launch();
    }
}