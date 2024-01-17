package fr.cashcoders.capitalhub.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DataBaseConnectionSingleton {
    public static final Dotenv dotenv = Dotenv.configure().load();
    private static final String POSTGRES_URL = dotenv.get("POSTGRES_URL");
    private static final String POSTGRES_USER = dotenv.get("POSTGRES_USER");
    private static final String POSTGRES_PASSWORD = dotenv.get("POSTGRES_PASSWORD");
    private Connection connection = null;

    private DataBaseConnectionSingleton() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(this.getClass().getName()).warning(e.getMessage());
        }
    }

    public static DataBaseConnectionSingleton getInstance() {
        return InstanceHolder.instance;
    }

    public Connection getConnection() {
        return getInstance().connection;
    }

    private static final class InstanceHolder {
        private static final DataBaseConnectionSingleton instance = new DataBaseConnectionSingleton();
    }
}
