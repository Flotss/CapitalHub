package fr.cashcoders.capitalhub.model;

import fr.cashcoders.capitalhub.controller.utils.PasswordHashing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements DBInterface {
    private int id;
    private final String username;


    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public static User getUserFromDB(String username, String password) throws SQLException {
        String hashPassword = PasswordHashing.hashPassword(password);


        PreparedStatement a = connection.prepareStatement("SELECT * FROM UserTable WHERE username = ? AND password = ?");
        a.setString(1, username);
        a.setString(2, hashPassword);
        a.executeQuery();
        ResultSet resultSet = a.getResultSet();
        if (resultSet.next()) {
            System.out.println("User found");
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("username"));
            return new User(resultSet.getInt("id"), resultSet.getString("username"));
        }
        return null;
    }

    public static User insertNewUser(String username, String password) throws SQLException {
        String hashPassword = PasswordHashing.hashPassword(password);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO UserTable (username, password) VALUES (?, ?) RETURNING id, username;");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, hashPassword);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new User(resultSet.getInt("id"), resultSet.getString("username"));
        }
        return null;
    }


    @Override
    public void save() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO UserTable (username, password) VALUES (?, ?) RETURNING id;");
        preparedStatement.setString(1, this.username);
        preparedStatement.setString(2, PasswordHashing.hashPassword("password"));
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            this.id = resultSet.getInt("id");
        }
    }

    @Override
    public void update() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE UserTable SET username = ? WHERE id = ?;");
        preparedStatement.setString(1, this.username);
        preparedStatement.setInt(2, this.id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM UserTable WHERE id = ?;");
        preparedStatement.setInt(1, this.id);
        preparedStatement.executeUpdate();
    }
}
