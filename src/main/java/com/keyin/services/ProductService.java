// File added for testing only.
// It will change when the database will connect to it

package com.keyin.services;

import java.util.List;
import java.util.ArrayList;

public class ProductService {
    // Simulating a product database with a list
    private List<String> products = new ArrayList<>();

    public ProductService() {
        // Add some dummy data for testing
        products.add("1: Laptop - $1000");
        products.add("2: Smartphone - $700");
        products.add("3: Headphones - $200");
    }

    // Fetch all products
    public List<String> getAllProducts() {
        return products;
    }

    // Search products by name
    public List<String> searchProductsByName(String keyword) {
        List<String> matchingProducts = new ArrayList<>();
        for (String product : products) {
            if (product.toLowerCase().contains(keyword.toLowerCase())) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    // Fetch product details by ID
    public String getProductDetails(int productId) {
        for (String product : products) {
            if (product.startsWith(productId + ":")) {
                return product;
            }
        }
        return null; // Product not found
    }
}

// File added for testing only.
// It will change when the database will connect to it

