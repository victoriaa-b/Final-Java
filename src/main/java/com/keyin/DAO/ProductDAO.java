package com.keyin.DAO;

import com.keyin.database.DatabaseConnection;
import com.keyin.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Add product to database
    public boolean addProduct(Product product) {
        // First, check if the seller is a valid seller
        String checkRoleQuery = "SELECT user_role FROM Users WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement checkRoleStatement = connection.prepareStatement(checkRoleQuery)) {
            checkRoleStatement.setInt(1, product.getSellerId());
            ResultSet roleResult = checkRoleStatement.executeQuery();

            if (roleResult.next()) {
                String role = roleResult.getString("user_role");
                if ("SELLER".equalsIgnoreCase(role)) {
                    // Proceed with adding the product if the user is a seller
                    String query = "INSERT INTO Products (product_name, product_description, price, quantity, seller_id) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, product.getName());
                        preparedStatement.setString(2, product.getDescription());
                        preparedStatement.setDouble(3, product.getPrice());
                        preparedStatement.setInt(4, product.getQuantity());
                        preparedStatement.setInt(5, product.getSellerId());
                        int rowsInserted = preparedStatement.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("Product added successfully!");
                            return true; // Indicate success
                        }
                    }
                } else {
                    System.out.println("The user is not a seller.");
                }
            } else {
                System.out.println("Seller not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
        }
        return false; // Indicate failure if product was not added
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
                            resultSet.getString("product_description"),
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
    public boolean updateProduct(Product product) {
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
                return true; // Indicate success
            } else {
                System.out.println("Product update failed. No such product found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
        }
        return false; // Indicate failure if product update failed
    }

    // Delete product by ID
    public boolean deleteProduct(int productID) {
        String query = "DELETE FROM Products WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productID);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully!");
                return true; // Indicate success
            } else {
                System.out.println("Product deletion failed. No such product found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
        return false; // Indicate failure if product deletion failed
    }
}
