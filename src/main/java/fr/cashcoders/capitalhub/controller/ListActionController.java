package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.model.ActionProduit;
import fr.cashcoders.capitalhub.model.Portefeuille;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.util.List;

public class ListActionController {

    private final Model model;
    private final Portefeuille portefeuille;


    @FXML
    private TableView<ActionProduit> tableViewActions;

    public ListActionController(Model model, Portefeuille portefeuille) {
        this.model = model;
        this.portefeuille = portefeuille;
    }

    @FXML
    public void initialize() {
        // Create columns for the TableView
        TableColumn<ActionProduit, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getActionName()));
        nameColumn.setMinWidth(90);

        TableColumn<ActionProduit, Double> quantiryColumn = new TableColumn<>("Nombre d'actifs");
        quantiryColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getQuantity()));
        quantiryColumn.setMinWidth(90);

        TableColumn<ActionProduit, Double> valueColumn = new TableColumn<>("Valeur");
        valueColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getActionValue()));
        valueColumn.setMinWidth(90);

        tableViewActions.getColumns().setAll(nameColumn, quantiryColumn, valueColumn);
        refresh();
    }

    @FXML
    public void refresh() {
        tableViewActions.getItems().clear();

        List<ActionProduit> actionProduits = portefeuille.getActionsProduct();
        tableViewActions.getItems().addAll(actionProduits);

        // Add click event listener to the TableView
        tableViewActions.setRowFactory(tv -> {
            TableRow<ActionProduit> row = new TableRow<>();
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
