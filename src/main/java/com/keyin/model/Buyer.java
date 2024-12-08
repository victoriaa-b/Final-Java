package com.keyin.model;

import com.keyin.services.ProductService;

import java.util.List;

public class Buyer extends User {

    private final ProductService productService;

    // Constructor without accountID
    public Buyer(String username, String password, String email) {
        super(username, password, email, "BUYER");
        this.productService = new ProductService(); // Initialize ProductService
    }

    // Constructor with accountID
    public Buyer(int accountID, String username, String password, String email) {
        super(accountID, email, username, password, "BUYER");
        this.productService = new ProductService(); // Initialize ProductService
    }

    // Simulate browsing products
    public void browseProducts() {
        System.out.println("Browsing all products:");
        List<Product> products = productService.getAllProducts();  // Fetch products from the database
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    // Simulate searching for a product by name
    public void searchProduct(String productName) {
        System.out.println("Searching for products matching: " + productName);
        List<Product> matchingProducts = productService.findProductsByName(productName); // Fetch matching products from the database
        if (matchingProducts.isEmpty()) {
            System.out.println("No products found matching: " + productName);
        } else {
            for (Product product : matchingProducts) {
                System.out.println(product);
            }
        }
    }

    // Simulate viewing product details by ID
    public void viewProductDetails(int productId) {
        System.out.println("Fetching details for product ID: " + productId);
        Product productDetails = productService.getProductDetails(productId); // Fetch product details from the database
        if (productDetails == null) {
            System.out.println("Product not found for ID: " + productId);
        } else {
            System.out.println(productDetails);
        }
    }
}
