package com.keyin.services;

import com.keyin.database.DatabaseConnection;
import com.keyin.model.Product;
import com.keyin.model.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private Connection connection;

    public ProductService() {
        this.connection = DatabaseConnection.getConnection();  // Assuming DatabaseConnection class is correctly set up
    }

    // Add a new product for a seller
    public void addProduct(String name, double price, int quantity, String description, Seller seller) {
        String query = "INSERT INTO products (name, price, quantity, description, seller_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);
            stmt.setString(4, description);
            stmt.setInt(5, seller.getSellerID());  // Assign seller ID
            stmt.executeUpdate();
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing product
    public void updateProduct(int productID, String name, double price, int quantity) {
        String query = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);
            stmt.setInt(4, productID);
            stmt.executeUpdate();
            System.out.println("Product updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a product
    public void deleteProduct(int productID) {
        String query = "DELETE FROM products WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productID);
            stmt.executeUpdate();
            System.out.println("Product deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all products for buyers (display all products)
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int productID = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                int sellerID = rs.getInt("seller_id"); // Seller info (optional)
                products.add(new Product(productID, name, price, quantity, sellerID, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Find products by name for buyers (search)
    public List<Product> findProductsByName(String productName) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE name LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + productName + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                int sellerID = rs.getInt("seller_id");
                products.add(new Product(productID, name, price, quantity, sellerID, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Get product details by ID for both buyers and sellers
    public Product getProductDetails(int productId) {
        String query = "SELECT * FROM products WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                int sellerID = rs.getInt("seller_id");
                return new Product(productId, name, price, quantity, sellerID, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if the product is not found
    }

    // Get products by seller ID for sellers (view their own products)
    public List<Product> getProductsBySeller(int sellerID) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, sellerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                products.add(new Product(productID, name, price, quantity, sellerID, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
