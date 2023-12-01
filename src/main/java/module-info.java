module fr.cashcoders.capitalhub {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens fr.cashcoders.capitalhub to javafx.fxml;
    exports fr.cashcoders.capitalhub;
}