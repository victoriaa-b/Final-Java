package com.keyin;

import com.keyin.model.Buyer;

public class DemoA {
    public static void main(String[] args) {
        // Create a Buyer instance
        Buyer buyer = new Buyer("john_doe", "password123", "john@example.com");

        // Demonstrate Buyer functionality
        System.out.println("Buyer Info: " + buyer);

        // Browse all products
        buyer.browseProducts();

        // Search for a specific product
        buyer.searchProduct("Laptop");

        // View details for a specific product
        buyer.viewProductDetails(1);
    }
}

