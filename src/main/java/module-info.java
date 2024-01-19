module fr.cashcoders.capitalhub {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    opens fr.cashcoders.capitalhub to javafx.fxml;
    exports fr.cashcoders.capitalhub;

    // org.postgresql.jdbc
    requires org.postgresql.jdbc;
    requires com.google.gson;

    opens fr.cashcoders.capitalhub.controller to javafx.fxml;
    exports fr.cashcoders.capitalhub.controller;

    opens fr.cashcoders.capitalhub.controller.utils.fetcher to com.google.gson;
    exports fr.cashcoders.capitalhub.controller.utils.fetcher;


}