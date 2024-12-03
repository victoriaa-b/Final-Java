package com.keyin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.keyin.model.User;

import com.keyin.database.DatabaseConnection;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void createUser(User user) {
        String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement prepState = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepState.setString(1, user.getUsername());
            prepState.setString(2, user.getPassword());
            prepState.setString(3, user.getEmail());
            prepState.setString(4, user.getRole());
            prepState.executeUpdate();

            ResultSet dbResult = prepState.getGeneratedKeys();
            if (dbResult.next()) {
                user.setAccountID(dbResult.getInt(1)); // Correct usage
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating user", e);
        }
    }
}

