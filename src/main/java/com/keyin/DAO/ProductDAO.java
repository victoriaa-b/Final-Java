package com.keyin.DAO;

import com.keyin.database.DatabaseConnection;
import com.keyin.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Insert product to database
    public void insertProduct(String name, double price, int quantity, String description, int sellerId) {
        String query = "INSERT INTO Products (product_name, price, quantity, product_description, seller_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setString(4, description);
            preparedStatement.setInt(5, sellerId);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Product added successfully.");
            } else {
                System.out.println("Failed to add product.");
            }
        } catch (SQLException e) {
            System.err.println("Error while adding product: " + e.getMessage());
        }
    }

    // Update product in database
    public void updateProduct(int productId, int sellerId, String newName, Double newPrice, Integer newQuantity, String newDescription) {
        String query = "UPDATE Products SET product_name = COALESCE(?, product_name), price = COALESCE(?, price), " +
                "quantity = COALESCE(?, quantity), product_description = COALESCE(?, product_description) " +
                "WHERE id = ? AND seller_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newName);
            preparedStatement.setObject(2, newPrice); // Allow null values for optional updates
            preparedStatement.setObject(3, newQuantity);
            preparedStatement.setString(4, newDescription);
            preparedStatement.setInt(5, productId);
            preparedStatement.setInt(6, sellerId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Failed to update product. Ensure the product ID is correct and belongs to you.");
            }
        } catch (SQLException e) {
            System.err.println("Error while updating product: " + e.getMessage());
        }
    }

    // Delete product by ID
    public void deleteProduct(int productId, int sellerId) {
        String query = "DELETE FROM Products WHERE id = ? AND seller_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, sellerId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Failed to delete product. Ensure the product ID is correct and belongs to you.");
            }
        } catch (SQLException e) {
            System.err.println("Error while deleting product: " + e.getMessage());
        }
    }

    // Get products by seller ID
    public List<Product> getProductsBySeller(int sellerId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE seller_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, sellerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("product_name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity"),
                            resultSet.getInt("seller_id"),
                            resultSet.getString("product_description")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching seller's products: " + e.getMessage());
        }

        return products;
    }

    // Get all products
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("product_name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("seller_id"),
                        resultSet.getString("product_description"),
                        null // Seller info can be set if needed later
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products: " + e.getMessage());
        }
        return products;
    }

    // Find products by name
    public List<Product> findProductsByName(String productName) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE product_name LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + productName + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("product_name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity"),
                            resultSet.getInt("seller_id"),
                            resultSet.getString("product_description"),
                            null // Seller info can be set if needed later
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products by name: " + e.getMessage());
        }
        return products;
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
                            resultSet.getString("product_name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity"),
                            resultSet.getInt("seller_id"),
                            resultSet.getString("product_description"),
                            null // Seller info can be set if needed later
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product details: " + e.getMessage());
        }
        return product;
    }
}
