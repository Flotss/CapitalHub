package fr.cashcoders.capitalhub.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionSingleton {
    private static final Dotenv dotenv = Dotenv.configure().load();
    private static final String POSTGRES_URL = dotenv.get("POSTGRES_URL");
    private static final String POSTGRES_USER = dotenv.get("POSTGRES_USER");
    private static final String POSTGRES_PASSWORD = dotenv.get("POSTGRES_PASSWORD");
    private static DataBaseConnectionSingleton instance = null;
    private Connection connection = null;

    private DataBaseConnectionSingleton() {
        try {
            Class.forName("org.postgresql.Driver"); // ! Warning:(19, 27) The module 'fr.cashcoders.capitalhub' does not have the module 'org.postgresql.jdbc' in requirements
            connection = DriverManager.getConnection(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataBaseConnectionSingleton getInstance() {
        if (instance == null) {
            synchronized (DataBaseConnectionSingleton.class) {
                if (instance == null) {
                    instance = new DataBaseConnectionSingleton();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
