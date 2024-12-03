package com.keyin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.keyin.model.User;
import com.keyin.model.Admin;
import com.keyin.model.Seller;
import com.keyin.model.Buyer;
import com.keyin.database.DatabaseConnection;

public class UserDAO {
    private final Connection connection;

    public UserDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // creating new user / registered
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
                user.setAccountID(dbResult.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating user", e);
        }
    }

    // find user by username in db
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement prepState = connection.prepareStatement(sql)) {
            prepState.setString(1, username);
            ResultSet dbResult = prepState.executeQuery();

            if (dbResult.next()) {
                String role = dbResult.getString("role");
                String password = dbResult.getString("password");
                String email = dbResult.getString("email");

                User user;
                switch (role) {
                    case "BUYER":
                        user = new Buyer(username, password, email);
                        break;
                    case "SELLER":
                        user = new Seller(username, password, email);
                        break;
                    case "ADMIN":
                        user = new Admin(username, password, email);
                        break;
                    default:
                        throw new RuntimeException("Unknown role: " + role);
                }
                user.setAccountID(dbResult.getInt("id"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user", e);
        }
    }
}