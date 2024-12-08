package com.keyin.DAO;

import com.keyin.database.DatabaseConnection;
import com.keyin.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Add product to database
    public void addProduct(Product product) {
        String query = "INSERT INTO Products (product_name, product_description, price, quantity, seller_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setInt(5, product.getSellerID());
            preparedStatement.executeUpdate();
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
        }
    }

    // Get product by ID
    public Product getProductDetails(int productID) {
        Product product = null;
        String query = "SELECT * FROM Products WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("product_name"), // Correct column name for product name
                            resultSet.getDouble("price"),
                            resultSet.getInt("seller_id"),
                            resultSet.getInt("quantity"),
                            resultSet.getString("product_description"), // Correct column name for description
                            null // Seller info can be set if needed later
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product details: " + e.getMessage());
        }
        return product;
    }

    // Get products by seller ID
    public List<Product> getProductsBySeller(int sellerID) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE seller_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sellerID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("product_name"), // Correct column name for product name
                            resultSet.getDouble("price"),
                            resultSet.getInt("seller_id"),
                            resultSet.getInt("quantity"),
                            resultSet.getString("product_description"), // Correct column name for description
                            null // Seller info can be set if needed later
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching seller's products: " + e.getMessage());
        }
        return products;
    }

    // Update product in database
    public void updateProduct(Product product) {
        String query = "UPDATE Products SET product_name = ?, product_description = ?, price = ?, quantity = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setInt(5, product.getProductID());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Product update failed. No such product found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
        }
    }

    // Delete product by ID
    public void deleteProduct(int productID) {
        String query = "DELETE FROM Products WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productID);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Product deletion failed. No such product found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
    }
}
