package com.keyin.model;

import com.keyin.services.ProductService;
import com.keyin.services.Product;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

    private final List<User> userList; // Simulated user database
    private final ProductService productService; // Product management

    // Constructor with accountID
    public Admin(int accountID, String username, String password, String email) {
        super(accountID, email, username, password, "ADMIN");
        this.userList = new ArrayList<>();
        this.productService = new ProductService(); // Initialize ProductService
    }

    // Constructor without accountID
    public Admin(String username, String password, String email) {
        super(0, email, username, password, "ADMIN");
        this.userList = new ArrayList<>();
        this.productService = new ProductService(); // Initialize ProductService
    }

    // Add user to the system (for testing purposes)
    public void addUser(User user) {
        userList.add(user);
    }

    // View all users with contact information
    public void viewAllUsers() {
        System.out.println("List of all users:");
        if (userList.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (User user : userList) {
                System.out.println("ID: " + user.getAccountID() + ", Username: " + user.getUsername() +
                        ", Email: " + user.getEmail() + ", Role: " + user.getRole());
            }
        }
    }

    // Delete user by ID
    public void deleteUser(int userId) {
        User userToRemove = null;
        for (User user : userList) {
            if (user.getAccountID() == userId) {
                userToRemove = user;
                break;
            }
        }
        if (userToRemove != null) {
            userList.remove(userToRemove);
            System.out.println("User with ID " + userId + " has been removed.");
        } else {
            System.out.println("User with ID " + userId + " not found.");
        }
    }

    // View all products with seller details
    public void viewAllProducts() {
        System.out.println("List of all products:");
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
}
