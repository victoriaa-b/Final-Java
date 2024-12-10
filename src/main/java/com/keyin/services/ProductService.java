package com.keyin.services;

import com.keyin.DAO.ProductDAO;
import com.keyin.model.Product;
import com.keyin.model.Seller;

import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO(); // Using ProductDAO for database interaction
    }

    // Add a new product
    public void addProduct(String name, double price, int quantity, String description, int sellerId) {
        productDAO.insertProduct(name, price, quantity, description, sellerId);
    }

    // Update an existing product
    public void updateProduct(int productId, int sellerId, String newName, Double newPrice, Integer newQuantity, String newDescription) {
        productDAO.updateProduct(productId, sellerId, newName, newPrice, newQuantity, newDescription);
    }

    // Delete a product
    public void deleteProduct(int productId, int sellerId) {
        productDAO.deleteProduct(productId, sellerId);
    }

    // Get products by seller ID
    public List<Product> getProductsBySeller(int sellerId) {
        return productDAO.getProductsBySeller(sellerId);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    // Find products by name
    public List<Product> findProductsByName(String productName) {
        return productDAO.findProductsByName(productName);
    }

    // Get product details by ID
    public Product getProductDetails(int productID) {
        return productDAO.getProductDetails(productID);
    }
}
