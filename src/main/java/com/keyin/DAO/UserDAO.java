package com.keyin.DAO;

import com.keyin.model.User;
import com.keyin.model.Admin;
import com.keyin.model.Seller;
import com.keyin.model.Buyer;
import com.keyin.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    // Get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String role = resultSet.getString("user_role").toLowerCase();
                User user;
                switch (role) {
                    case "buyer":
                        user = new Buyer(
                                resultSet.getInt("id"),
                                resultSet.getString("username"),
                                resultSet.getString("user_password"),
                                resultSet.getString("email")
                        );
                        break;
                    case "seller":
                        user = new Seller(
                                resultSet.getInt("id"),
                                resultSet.getString("username"),
                                resultSet.getString("user_password"),
                                resultSet.getString("email")
                        );
                        break;
                    case "admin":
                        user = new Admin(
                                resultSet.getInt("id"),
                                resultSet.getString("username"),
                                resultSet.getString("user_password"),
                                resultSet.getString("email")
                        );
                        break;
                    default:
                        throw new RuntimeException("Unknown role: " + role);
                }
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users: " + e.getMessage());
        }

        return users;
    }

    // Get a user by ID from the database
    public User getUserById(int userId) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String role = resultSet.getString("user_role").toLowerCase();
                    User user;
                    switch (role) {
                        case "buyer":
                            user = new Buyer(
                                    resultSet.getInt("id"),
                                    resultSet.getString("username"),
                                    resultSet.getString("user_password"),
                                    resultSet.getString("email")
                            );
                            break;
                        case "seller":
                            user = new Seller(
                                    resultSet.getInt("id"),
                                    resultSet.getString("username"),
                                    resultSet.getString("user_password"),
                                    resultSet.getString("email")
                            );
                            break;
                        case "admin":
                            user = new Admin(
                                    resultSet.getInt("id"),
                                    resultSet.getString("username"),
                                    resultSet.getString("user_password"),
                                    resultSet.getString("email")
                            );
                            break;
                        default:
                            throw new RuntimeException("Unknown role: " + role);
                    }
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Admin method to delete a user by ID (admin can delete any user)
    public void deleteUserById(int userId) {
        String query = "SELECT user_role FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String role = resultSet.getString("user_role").toLowerCase();

                    if (role.equals("seller")) {
                        // If the user is a seller, delete their associated products
                        deleteProductsBySellerId(userId);
                    }
                }
            }

            // Now delete the user
            String deleteQuery = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, userId);
                int rowsAffected = deleteStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User with ID " + userId + " has been successfully deleted.");
                } else {
                    System.out.println("No user found with ID " + userId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete all products by seller ID
    private void deleteProductsBySellerId(int sellerId) {
        String query = "DELETE FROM products WHERE seller_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sellerId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("All products by seller with ID " + sellerId + " have been successfully deleted.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting seller products: " + e.getMessage());
        }
    }
}
