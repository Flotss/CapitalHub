package fr.cashcoders.capitalhub.view;

import fr.cashcoders.capitalhub.model.Portefeuille;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.HBox;

public class PortefeuilleView extends HBox {

    public PortefeuilleView(Portefeuille portefeuille) {
        this.setPadding(new Insets(10));
        this.setSpacing(50);
        Label name = new Label(portefeuille.getName());
        Label description = new Label(portefeuille.getDescription());

        Label value = new Label(portefeuille.getLastValue() + "");

        // Création de l'effet de lumière
        Lighting lightEffect = new Lighting();
        lightEffect.setSurfaceScale(5.0);
        lightEffect.setSpecularExponent(50.0);
        lightEffect.setSpecularConstant(1.0);
        lightEffect.setDiffuseConstant(0.5);

        // Ajout de l'effet de lumière au HBox  si on hover
        this.setOnMouseEntered(event -> {
            this.setEffect(lightEffect);
        });

        // Suppression de l'effet de lumière au HBox si on quitte le hover
        this.setOnMouseExited(event -> {
            this.setEffect(null);
        });


        this.getChildren().addAll(name, description, value);
    }

}
