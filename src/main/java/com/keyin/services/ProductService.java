package com.keyin.services;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static int productCounter = 1; // Auto-increment for product IDs
    private List<Product> products = new ArrayList<>();

    public ProductService() {
        // Dummy data for testing
        addProduct("Laptop", 1000.0, 10, 1); // Seller ID 1
        addProduct("Smartphone", 700.0, 15, 1); // Seller ID 1
        addProduct("Headphones", 200.0, 5, 2); // Seller ID 2
    }

    // Add a new product
    public void addProduct(String name, double price, int quantity, int sellerID) {
        Product product = new Product(productCounter++, name, price, quantity, sellerID);
        products.add(product);
        System.out.println("Product added: " + product);
    }

    // Update a product by ID
    public void updateProduct(int productID, String name, double price, int quantity) {
        for (Product product : products) {
            if (product.getProductID() == productID) {
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                System.out.println("Product updated: " + product);
                return;
            }
        }
        System.out.println("Product not found with ID: " + productID);
    }

    // Delete a product by ID
    public void deleteProduct(int productID) {
        products.removeIf(product -> product.getProductID() == productID);
        System.out.println("Product with ID " + productID + " has been deleted.");
    }

    // Fetch all products
    public List<Product> getAllProducts() {
        return products;
    }

    // Search products by name
    public List<Product> searchProductsByName(String keyword) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    // Fetch product details by ID
    public Product getProductDetails(int productID) {
        for (Product product : products) {
            if (product.getProductID() == productID) {
                return product;
            }
        }
        return null; // Product not found
    }

    // Fetch all products by a seller
    public List<Product> getProductsBySeller(int sellerID) {
        List<Product> sellerProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getSellerID() == sellerID) {
                sellerProducts.add(product);
            }
        }
        return sellerProducts;
    }
}
