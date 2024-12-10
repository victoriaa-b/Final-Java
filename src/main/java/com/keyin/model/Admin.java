package com.keyin.model;

import com.keyin.services.ProductService;
import com.keyin.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

    private final ProductService productService; // Product management
    private final UserService userService;// User management

    // Constructor with accountID
    public Admin(int accountID, String username, String user_password, String email) {
        super(accountID, email, username, user_password, "ADMIN"); // Pass user_password to the superclass constructor
        this.userService = new UserService(); // Initialize UserService to fetch users
        this.productService = new ProductService(); // Initialize ProductService
    }

    // Constructor without accountID
    public Admin(String username, String user_password, String email) {
        super(0, email, username, user_password, "ADMIN"); // Pass user_password to the superclass constructor
        this.userService = new UserService(); // Initialize UserService
        this.productService = new ProductService(); // Initialize ProductService
    }

    // View all users with contact information
    public void viewAllUsers() {
        List<User> users = userService.getAllUsers(); // Fetch all users using UserService
        if (users.isEmpty()) {
            System.out.println("No users found in the system.");
        } else {
            System.out.println("List of All Users:");
            for (User user : users) {
                System.out.println(user); // Ensure `User` class has a `toString` method for better display
            }
        }
    }

    // Method to delete a user by ID
    public void deleteUser(int userId) {
        userService.deleteUser(userId);  // Delete user using UserService
        System.out.println("User with ID " + userId + " has been deleted.");
    }

    // View all products with seller info
    public void viewAllProducts() {
        System.out.println("Display list of all products:");
        List<Product> products = productService.getAllProducts();  // Fetch products from the database
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
}
