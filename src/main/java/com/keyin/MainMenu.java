package com.keyin;

import com.keyin.services.UserService;
import com.keyin.services.ProductService;
import com.keyin.model.User;
import com.keyin.model.Buyer;
import com.keyin.model.Seller;
import com.keyin.model.Admin;

import java.util.Scanner;

// Handles all the logic
public class MainMenu {
    private final UserService userService;
    private final ProductService productService;
    private final Scanner scanner;

    public MainMenu(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
        this.scanner = new Scanner(System.in);
    }

    // Display the main menu
    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to the E-Commerce Shop!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    running = false;
                    System.out.println("Thanks for Visiting!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Logic for User Registration
    private void register() {
        System.out.println("Please Enter a username:");
        String username = scanner.nextLine();

        System.out.println("Please Enter a password:");
        String password = scanner.nextLine();

        System.out.println("Please Enter your email:");
        String email = scanner.nextLine();

        System.out.println("Enter one of these roles (Buyer, Seller, Admin):");
        String role = scanner.nextLine();

        try {
            userService.registerUser(username, password, email, role);  // Call UserService method
            System.out.println("Registration successful!");
        } catch (Exception error) {
            System.out.println("Registration failed: " + error.getMessage());
        }
    }

    // Logic for User Login
    private void login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        try {
            User user = userService.login(username, password);
            System.out.println("Login successful! Greetings, " + user.getUsername());

            // Role-based menu
            getRoleMenu(user);
        } catch (Exception error) {
            System.out.println("Login failed: " + error.getMessage());
        }
    }

    // Display menu based on user role
    private void getRoleMenu(User user) {
        switch (user.getRole().toUpperCase()) {
            case "BUYER":
                Buyer buyer = new Buyer(user.getAccountID(), user.getUsername(), user.getPassword(), user.getEmail());
                displayBuyerMenu(buyer);
                break;

            case "SELLER":
                Seller seller = new Seller(user.getAccountID(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRole());
                displaySellerMenu(seller);
                break;

            case "ADMIN":
                Admin admin = new Admin(user.getAccountID(), user.getUsername(), user.getPassword(), user.getEmail());
                displayAdminMenu(admin);
                break;

            default:
                System.out.println("Role not recognized. Please contact support.");
                break;
        }
    }


    private void displayBuyerMenu(Buyer buyer) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Buyer Menu ===");
            System.out.println("Greetings, " + buyer.getUsername() + "!");
            System.out.println("====================");
            System.out.println("1. View All Products");
            System.out.println("2. Search for a Product");
            System.out.println("3. View Product Info");
            System.out.println("4. Logout");
            System.out.println("====================");
            System.out.print("Enter your choice: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Clear the newline

            switch (option) {
                case 1:
                    // Buyer browses all products
                    buyer.browseProducts();
                    break;

                case 2:
                    // Buyer searches for a product by name
                    System.out.println("Enter the product name to search:");
                    String productName = scanner.nextLine();
                    buyer.searchProduct(productName);
                    break;

                case 3:
                    // Buyer views product details by ID
                    System.out.println("Enter the product ID to view details:");
                    int productId = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline
                    buyer.viewProductDetails(productId);
                    break;

                case 4:
                    // Buyer logs out
                    System.out.println("Logging out...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }



    // Seller Menu
    private void displaySellerMenu(Seller seller) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Seller Menu ===");
            System.out.println("Greetings, " + seller.getUsername() + "!");
            System.out.println("====================");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View My Products");
            System.out.println("5. Logout");
            System.out.println("====================");
            System.out.print("Enter your choice: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("\nEnter product name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter product price:");
                    double price = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline character

                    System.out.println("Enter product quantity:");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character

                    System.out.println("Enter product description:");
                    String description = scanner.nextLine();

                    seller.addProduct(name, price, quantity, description);
                    break;

                case 2:
                    System.out.println("\nEnter the product ID to update:");
                    int productId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.println("Enter the new product name (leave blank to keep unchanged):");
                    String newName = scanner.nextLine();
                    newName = newName.isEmpty() ? null : newName;

                    System.out.println("Enter the new price (leave blank to keep unchanged):");
                    String newPriceInput = scanner.nextLine();
                    Double newPrice = newPriceInput.isEmpty() ? null : Double.parseDouble(newPriceInput);

                    System.out.println("Enter the new quantity (leave blank to keep unchanged):");
                    String newQuantityInput = scanner.nextLine();
                    Integer newQuantity = newQuantityInput.isEmpty() ? null : Integer.parseInt(newQuantityInput);

                    System.out.println("Enter the new description (leave blank to keep unchanged):");
                    String newDescription = scanner.nextLine();
                    newDescription = newDescription.isEmpty() ? null : newDescription;

                    seller.updateProduct(productId, newName, newPrice, newQuantity, newDescription);
                    break;

                case 3:
                    System.out.println("\nEnter the product ID to delete:");
                    int productIdToDelete = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    seller.deleteProduct(productIdToDelete);
                    break;

                case 4:
                    System.out.println("\nDisplaying all your products...");
                    seller.viewAllProducts();
                    break;

                case 5:
                    System.out.println("\nLogging out...");
                    running = false;
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }


    // Admin menu
    private void displayAdminMenu(Admin admin) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("Greetings, Admin!");
            System.out.println("====================");
            System.out.println("1. View All Users");
            System.out.println("2. Delete a User");
            System.out.println("3. View All Products");
            System.out.println("4. Logout");
            System.out.println("====================");
            System.out.print("Enter your choice: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            admin = (Admin) userService.getCurrentUser();

            switch (option) {
                case 1:
                    System.out.println("\nDisplaying all users...");
                    admin.viewAllUsers();
                    break;

                case 2:
                    System.out.print("\nEnter the ID of the user to delete: ");
                    int userId = scanner.nextInt();
                    admin.deleteUser(userId);  // Calls Admin's method to delete a user
                    break;

                case 3:
                    System.out.println("\nDisplaying all products...");
                    admin.viewAllProducts();
                    break;

                case 4:
                    System.out.println("\nLogging out...");
                    running = false;
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
}
