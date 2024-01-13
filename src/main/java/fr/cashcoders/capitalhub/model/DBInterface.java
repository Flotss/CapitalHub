package fr.cashcoders.capitalhub.model;

import fr.cashcoders.capitalhub.database.DataBaseConnectionSingleton;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface representing database operations.
 * Classes implementing this interface are expected to provide methods for saving, updating, and deleting data.
 */
public interface DBInterface {
    Connection connection = DataBaseConnectionSingleton.getInstance().getConnection();

    /**
     * Saves data to the database.
     *
     * @throws SQLException if a database error occurs.
     */
    void save() throws SQLException;

    /**
     * Updates existing data in the database.
     *
     * @throws SQLException if a database error occurs.
     */
    void update() throws SQLException;

    /**
     * Deletes data from the database.
     *
     * @throws SQLException if a database error occurs.
     */
    void delete() throws SQLException;
}