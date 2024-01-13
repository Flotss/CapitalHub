package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.model.Portefeuille;
import fr.cashcoders.capitalhub.view.PortefeuilleDetailsView;
import javafx.animation.PauseTransition;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class ListPortefeuilleController implements ControllerInterface {

    private final Model model;
    @FXML
    private TableView<Portefeuille> tableViewPortefeuille;

    private Portefeuille selectedPortefeuille;

    public ListPortefeuilleController(Model model) {
        this.model = model;
    }

    @FXML
    public void initialize() {
        tableViewPortefeuille.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Create columns for the TableView
        TableColumn<Portefeuille, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        nameColumn.setMinWidth(90);

        TableColumn<Portefeuille, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDescription()));
        descriptionColumn.setMinWidth(90);

        TableColumn<Portefeuille, Double> valueColumn = new TableColumn<>("Valeur");
        valueColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getValeur()));
        valueColumn.setMinWidth(90);

        // Add the columns to the TableView
        tableViewPortefeuille.getColumns().setAll(nameColumn, descriptionColumn, valueColumn);

        refresh();
    }

    @FXML
    public void refresh() {
        tableViewPortefeuille.getItems().clear();
// Clear the existing data

        // Get the data from the model and add it to the TableView
        List<Portefeuille> portefeuilles = model.getPortefeuilles();
        tableViewPortefeuille.getItems().addAll(portefeuilles);

        // Créez un menu contextuel
        ContextMenu contextMenu = new ContextMenu();
        MenuItem ouvrirItemMenu = new MenuItem("Ouvrir");
        MenuItem clonerItemMenu = new MenuItem("Cloner");
        MenuItem deleteItemMenu = new MenuItem("Supprimer");
        MenuItem ModifierItemMenu = new MenuItem("Modifier");

        ouvrirItemMenu.setOnAction(event -> {
            System.out.println("Ouvrir");
            PortefeuilleDetailsView portefeuilleDetailsView = new PortefeuilleDetailsView(model, selectedPortefeuille);
            portefeuilleDetailsView.show();
        });

        clonerItemMenu.setOnAction(event -> {
            model.clonePortefeuille(selectedPortefeuille);
        });

        deleteItemMenu.setOnAction(event -> {
            model.deletePortefeuille(selectedPortefeuille);
        });

        ModifierItemMenu.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Modifier le portefeuille : " + selectedPortefeuille.getName());

            // FIELD FOR NAME AND DESCRIPTION (label + textfield)
            Label labelName = new Label("Nom : ");
            TextField textFieldName = new TextField(selectedPortefeuille.getName());
            Label labelDescription = new Label("Description : ");
            TextField textFieldDescription = new TextField(selectedPortefeuille.getDescription());
            Label info = new Label("Veuillez remplir les champs ci-dessous");

            // BUTTONS
            Button buttonModifier = new Button("Mise à jour");

            // LAYOUT
            VBox layout = new VBox();
            layout.getChildren().addAll(new HBox(labelName, textFieldName), new HBox(labelDescription, textFieldDescription), buttonModifier, info);
            layout.setAlignment(Pos.CENTER);

            // ACTION
            buttonModifier.setOnAction(event1 -> {
                if (textFieldName.getText().isEmpty()) {
                    info.setText("Veuillez remplir le champ nom");
                    info.setStyle("-fx-text-fill: red");
                    return;
                }

                selectedPortefeuille.setName(textFieldName.getText());
                selectedPortefeuille.setDescription(textFieldDescription.getText());
                model.notifyObserver();

                info.setText("Portefeuille mis à jour");
                info.setStyle("-fx-text-fill: green");

                // Wait 1 second before closing the window
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event2 -> stage.close());
                delay.play();
            });

            stage.setScene(new Scene(layout));
            stage.show();
        });
        contextMenu.getItems().addAll(ouvrirItemMenu, clonerItemMenu, deleteItemMenu, ModifierItemMenu);

        tableViewPortefeuille.setRowFactory(tv -> {
            TableRow<Portefeuille> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                selectedPortefeuille = row.getItem();

                // Double click on row -> open PortefeuilleDetailsView
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    PortefeuilleDetailsView portefeuilleDetailsView = new PortefeuilleDetailsView(model, selectedPortefeuille);
                    portefeuilleDetailsView.show();
                }

                // Right click on row -> open PortefeuilleDetailsView
                if (!row.isEmpty() && event.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(row, event.getScreenX(), event.getScreenY());
                }
            });


            row.setOnMouseEntered(event -> {
                if (!row.isEmpty()) {
                    row.setStyle("-fx-background-color: lightgray;");  // Change color on hover
                }
            });
            row.setOnMouseExited(event -> {
                if (!row.isEmpty()) {
                    row.setStyle("");  // Reset to default on hover exit
                }
            });
            return row;
        });
    }

    public void createPorteFeuille() {
        // STAGE FOR NEW PORTEFEUILLE
        Stage stage = new Stage();
        stage.setTitle("Créer un portefeuille");

        // FIELD FOR NAME AND DESCRIPTION (label + textfield)
        Label labelName = new Label("Nom : ");
        TextField textFieldName = new TextField();
        Label labelDescription = new Label("Description : ");
        TextField textFieldDescription = new TextField();
        Label info = new Label("Veuillez remplir les champs ci-dessous");

        // BUTTONS
        Button buttonCreate = new Button("Créer");

        // LAYOUT
        VBox layout = new VBox();
        layout.getChildren().addAll(new HBox(labelName, textFieldName), new HBox(labelDescription, textFieldDescription), buttonCreate, info);
        layout.setAlignment(Pos.CENTER);

        // ACTION
        buttonCreate.setOnAction(event -> {
            if (textFieldName.getText().isEmpty()) {
                info.setText("Veuillez remplir le champ nom");
                info.setStyle("-fx-text-fill: red");
            } else {
                model.createPortefeuille(textFieldName.getText(), textFieldDescription.getText());
                model.notifyObserver();

                info.setText("Portefeuille créé");
                info.setStyle("-fx-text-fill: green");

                // Wait 1 second before closing the window
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event1 -> stage.close());
                delay.play();
            }
        });

        stage.setScene(new Scene(layout));
        stage.show();
    }
}
