package fr.cashcoders.capitalhub.controller;

import fr.cashcoders.capitalhub.model.ActionProduit;
import fr.cashcoders.capitalhub.model.Portefeuille;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;

public class ListActionController implements ControllerInterface {

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
        nameColumn.setMinWidth(67);

        TableColumn<ActionProduit, Double> quantiryColumn = new TableColumn<>("Nombre d'actifs");
        quantiryColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getQuantity()));
        quantiryColumn.setMinWidth(67);

        TableColumn<ActionProduit, Double> valueColumn = new TableColumn<>("Valeur");
        valueColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getActionValue()));
        valueColumn.setMinWidth(67);

        TableColumn<ActionProduit, Double> valueUnitColumn = new TableColumn<>("Valeur unitaire");
        valueUnitColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAction().getPrice()));
        valueUnitColumn.setMinWidth(67);

        tableViewActions.getColumns().setAll(nameColumn, quantiryColumn, valueColumn, valueUnitColumn);
        refresh();
    }

    @FXML
    public void refresh() {
        Platform.runLater(() -> {
            updateLineChart();
        });

    }

    public void updateLineChart() {
        tableViewActions.getItems().clear();

        List<ActionProduit> actionProduits = portefeuille.getActionsProducts();
        tableViewActions.getItems().addAll(actionProduits);

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


    public void actionBuySell() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ActionBuySell.fxml"));

    }
}
