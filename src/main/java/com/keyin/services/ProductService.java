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
    public void addProduct(String name, double price, int quantity, String description, Seller seller) {
        Product product = new Product(0, name, price, quantity, seller.getSellerId(), description, seller);
        if (productDAO.addProduct(product)) {
            System.out.println("Product added successfully!");
        }
    }

    // Update an existing product
    public void updateProduct(int productID, String name, double price, int quantity) {
        Product product = new Product(productID, name, price, quantity, 0, "", null);
        if (productDAO.updateProduct(product)) {
            System.out.println("Product updated successfully!");
        }
    }

    // Delete a product
    public void deleteProduct(int productID) {
        if (productDAO.deleteProduct(productID)) {
            System.out.println("Product deleted successfully!");
        }
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

    // Get products by seller ID
    public List<Product> getProductsBySeller(int sellerID) {
        return productDAO.getProductsBySeller(sellerID);
    }
}
