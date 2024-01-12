package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.model.Portefeuille;
import fr.cashcoders.capitalhub.view.PortefeuilleDetailsView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;

import java.util.List;

public class ListPortefeuilleController {

    private final Model model;
    @FXML
    private TableView<Portefeuille> tableViewPortefeuille;

    public ListPortefeuilleController(Model model) {
        this.model = model;
    }

    // INITIALIZATION
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
        // Clear the existing data
        tableViewPortefeuille.getItems().clear();

        // Get the data from the model and add it to the TableView
        List<Portefeuille> portefeuilles = model.getPortefeuilles();
        tableViewPortefeuille.getItems().addAll(portefeuilles);

        // Add click event listener to the TableView
        tableViewPortefeuille.setRowFactory(tv -> {
            TableRow<Portefeuille> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    Portefeuille clickedRow = row.getItem();
                    // Handle the click event here
                    PortefeuilleDetailsView portefeuilleDetailsView = new PortefeuilleDetailsView(model, clickedRow);
                    portefeuilleDetailsView.show();
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
}
