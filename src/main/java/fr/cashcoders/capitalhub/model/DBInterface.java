package fr.cashcoders.capitalhub.model;

import java.sql.SQLException;

public interface DBInterface {
    void save() throws SQLException;

    void delete() throws SQLException;
}
