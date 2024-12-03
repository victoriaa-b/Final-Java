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
        List<String> products = productService.getAllProducts();
        for (String product : products) {
            System.out.println(product);
        }
    }

    // Simulate searching for a product
    public void searchProduct(String productName) {
        System.out.println("Searching for products matching: " + productName);
        List<String> matchingProducts = productService.searchProductsByName(productName);
        if (matchingProducts.isEmpty()) {
            System.out.println("No products found matching: " + productName);
        } else {
            for (String product : matchingProducts) {
                System.out.println(product);
            }
        }
    }

    // Simulate viewing product details
    public void viewProductDetails(int productId) {
        System.out.println("Fetching details for product ID: " + productId);
        String productDetails = productService.getProductDetails(productId);
        if (productDetails == null) {
            System.out.println("Product not found for ID: " + productId);
        } else {
            System.out.println(productDetails);
        }
    }
}
