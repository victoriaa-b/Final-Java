package com.keyin.model;

import com.keyin.services.ProductService;
import com.keyin.services.Product;

import java.util.List;

public class Seller extends User {
    private int sellerID;
    private ProductService productService;

    // Constructor for existing seller with accountID
    public Seller(int accountID, String username, String password, String email, String role) {
        super(accountID, email, username, password, role);
        this.sellerID = accountID;
        this.productService = new ProductService(); // Initialize a shared product service
    }

    // Constructor for new seller without accountID
    public Seller(String username, String password, String email) {
        super(0, email, username, password, "SELLER");
        this.sellerID = getAccountID(); // Use the accountID when assigned
        this.productService = new ProductService();
    }

    // Add a new product
    public void addProduct(String name, double price, int quantity) {
        productService.addProduct(name, price, quantity, this.sellerID);
    }

    // Update an existing product
    public void updateProduct(int productID, String name, double price, int quantity) {
        productService.updateProduct(productID, name, price, quantity);
    }

    // Delete a product
    public void deleteProduct(int productID) {
        productService.deleteProduct(productID);
    }

    // View all products listed by this seller
    public void viewMyProducts() {
        List<Product> sellerProducts = productService.getProductsBySeller(this.sellerID);
        if (sellerProducts.isEmpty()) {
            System.out.println("You have no products listed.");
        } else {
            System.out.println("Your Products:");
            for (Product product : sellerProducts) {
                System.out.println(product);
            }
        }
    }
}
