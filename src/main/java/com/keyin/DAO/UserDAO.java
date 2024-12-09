package com.keyin.DAO;

import com.keyin.model.User;
import com.keyin.model.Admin;
import com.keyin.model.Seller;
import com.keyin.model.Buyer;
import com.keyin.database.DatabaseConnection;

import java.sql.*;

public class UserDAO {
    private final Connection connection;

    public UserDAO() {
        this.connection = DatabaseConnection.getConnection();
        if (this.connection == null) {
            throw new RuntimeException("Failed to connect to the database");
        }
    }

    // Create a new user
    public void createUser(User user) {
        String sql = "INSERT INTO users (username, user_password, email, user_role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement prepState = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepState.setString(1, user.getUsername());
            prepState.setString(2, user.getPassword());
            prepState.setString(3, user.getEmail());

            // Normalize role to lowercase before saving to the database
            prepState.setString(4, user.getRole().toLowerCase());  // Convert role to lowercase

            prepState.executeUpdate();

            ResultSet dbResult = prepState.getGeneratedKeys();
            if (dbResult.next()) {
                user.setAccountID(dbResult.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw new RuntimeException("Error creating user", e);
        }
    }



    // Find a user by username
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement prepState = connection.prepareStatement(sql)) {
            prepState.setString(1, username);
            ResultSet dbResult = prepState.executeQuery();

            if (dbResult.next()) {
                String role = dbResult.getString("user_role");         // Fetch `user_role`
                role = role.toLowerCase();  // Normalize role to lowercase

                String password = dbResult.getString("user_password"); // Fetch `user_password`
                String email = dbResult.getString("email");            // Fetch `email`

                User user;
                switch (role) {
                    case "buyer":
                        user = new Buyer(username, password, email);
                        break;
                    case "seller":
                        user = new Seller(username, password, email);
                        break;
                    case "admin":
                        user = new Admin(username, password, email);
                        break;
                    default:
                        throw new RuntimeException("Unknown role: " + role);
                }
                user.setAccountID(dbResult.getInt("id"));  // Assuming `id` column exists
                return user;
            }
            return null; // No user found
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user", e);
        }
    }
}
