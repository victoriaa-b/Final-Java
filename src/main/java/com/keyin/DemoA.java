package com.keyin;

import com.keyin.model.Buyer;
import com.keyin.model.Seller;

public class DemoA {
    public static void main(String[] args) {
//        // Create a Buyer instance
//        Buyer buyer = new Buyer(1, "tech_buyer", "securepassword", "buyer@example.com");
//
//        // Browse all products
//        System.out.println("Buyer browsing products:");
//        buyer.browseProducts();
//
//        // Search for a specific product
//        System.out.println("\nBuyer searching for 'Laptop':");
//        buyer.searchProduct("Laptop");
//
//        // View details of a specific product
//        System.out.println("\nBuyer viewing product details for ID 1:");
//        buyer.viewProductDetails(1);
//
//        // Try to view a non-existent product
//        System.out.println("\nBuyer viewing product details for ID 99:");
//        buyer.viewProductDetails(99);

        //Create a seller instance
        Seller seller = new Seller(1, "tech_seller", "password123", "seller@example.com", "SELLER");

        // Add new products
        System.out.println("Adding products...");
        seller.addProduct("Gaming Laptop", 1500.0, 5);
        seller.addProduct("Wireless Mouse", 50.0, 20);
        seller.addProduct("Mechanical Keyboard", 120.0, 10);

        // View products listed by this seller
        System.out.println("\nViewing my products:");
        seller.viewMyProducts();

        // Update a product
        System.out.println("\nUpdating product with ID 1...");
        seller.updateProduct(1, "High-End Gaming Laptop", 1600.0, 4);

        // View updated products
        System.out.println("\nViewing my products after update:");
        seller.viewMyProducts();

        // Delete a product
        System.out.println("\nDeleting product with ID 2...");
        seller.deleteProduct(2);

        // View products after deletion
        System.out.println("\nViewing my products after deletion:");
        seller.viewMyProducts();
    }
}

