package fr.cashcoders.capitalhub.model;

import fr.cashcoders.capitalhub.database.DataBaseConnectionSingleton;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBInterface {
    Connection connection = DataBaseConnectionSingleton.getInstance().getConnection();

    void save() throws SQLException;

    void update() throws SQLException;

    void delete() throws SQLException;
}
