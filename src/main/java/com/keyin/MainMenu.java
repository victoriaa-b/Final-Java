package com.keyin;

import com.keyin.services.UserService;
import java.util.Scanner;
import com.keyin.model.User;
import com.keyin.services.ProductService;
import com.keyin.model.Seller;
import com.keyin.services.Product;
import java.util.List;
import com.keyin.model.Admin;

// handles all the logic
public class MainMenu {
    private final UserService userService;
    private final ProductService productService;
    private final Scanner scanner;

    // need to initialize
    public MainMenu(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
        this.scanner = new Scanner(System.in);
    }

    // display the menu
    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to the E-Commerce Shop!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int option = scanner.nextInt(); // takes the user options
            scanner.nextLine();

            switch (option) {
                case 1:
                    // they will register here
                    register();
                    break;
                case 2:
                    // they will need to login here
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

    // Logic for User to Register an account
    private void register() {
        System.out.println("Please Enter an username:");
        String username = scanner.nextLine();

        System.out.println("Please Enter an password:");
        String password = scanner.nextLine();

        System.out.println("Please Enter email your email:");
        String email = scanner.nextLine();

        System.out.println("Enter one of these roles (Buyer, Seller, Admin):");
        String role = scanner.nextLine();

        try {
            userService.registerUser(username, password, email, role);  // Call UserService method
            System.out.println("Registration successful!"); // registration worked
        } catch (Exception error) {
            System.out.println("Registration failed: " + error.getMessage());  // error occurred
        }
    }

    // Logic for user login
    private void login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        try {
            User user = userService.login(username, password);
            System.out.println("Login was successful! Greetings, " + user.getUsername());

            // every role gets their own menu
            getRoleMenu(user);
        } catch (Exception error) {
            System.out.println("Login failed: " + error.getMessage());
        }
    }

    // logic  to show the user menu
    private void getRoleMenu(User user) {
        switch (user.getRole().toUpperCase()) {
            case "BUYER":
                DisplayBuyerMenu();
                break;
            case "SELLER":
                DisplaySellerMenu((Seller) user); // Passing the seller object for the seller menu
                break;
            case "ADMIN":
                DisplayAdminMenu();
                break;
            default:
                System.out.println("The role option failed to load");
        }
    }

    // Buyer menu
    private void DisplayBuyerMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Greetings, Buyer!");
            System.out.println("Buyer Menu Selection:");
            System.out.println("1. View All Products");
            System.out.println("2. Search for a Product");
            System.out.println("3. View Product Info");
            System.out.println("4. Logout");

            int option = scanner.nextInt(); // buyer needs to pick an option
            scanner.nextLine();

            switch (option) {
                case 1:
                    // Buyer can view all products
                    System.out.println("Displaying all available products...");
                    List<Product> products = productService.getAllProducts();
                    if (products != null && !products.isEmpty()) {
                        for (Product product : products) {
                            System.out.println("Product Name: " + product.getName());
                            System.out.println("Product ID: " + product.getProductID());
                        }
                    } else {
                        System.out.println("No products available.");
                    }
                    break;

                case 2:
                    // Buyer can search for a product by its ID
                    System.out.println("Enter the product ID to search:");
                    String searchId = scanner.nextLine();
                    Product searchedProduct = productService.getProductDetails(searchId);
                    if (searchedProduct != null) {
                        System.out.println("Product Found: " + searchedProduct.getName());
                    } else {
                        System.out.println("Product could not be found.");
                    }
                    break;

                case 3:
                    // Buyer can view product details by using the ID
                    System.out.println("Enter the product ID to view details:");
                    String infoId = scanner.nextLine();
                    Product productDetails = productService.getProductDetails(infoId);
                    if (productDetails != null) {
                        System.out.println("Product Name: " + productDetails.getName());
                        System.out.println("Price: $" + productDetails.getPrice());
                        System.out.println("Description: " + productDetails.getDescription());
                    } else {
                        System.out.println("Product could not be found.");
                    }
                    break;

                case 4:
                    // Buyer has the option to log out
                    System.out.println("Logging out...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Seller Menu
    private void DisplaySellerMenu(Seller seller) {
        boolean running = true;
        while (running) {
            System.out.println("Greetings, Seller!");
            System.out.println("Seller Menu Selection:");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View My Products");
            System.out.println("5. Logout");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1: // Seller can add Product - Take info for the product
                    System.out.println("Enter product name:");
                    String name = scanner.nextLine();

                    System.out.println("Enter product price:");
                    double price = scanner.nextDouble();

                    System.out.println("Enter product quantity:");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.println("Enter product description:");
                    String description = scanner.nextLine();

                    // DOUBLE CHECK
                    // Need to call addProduct from ProductService
                    productService.addProduct(name, price, quantity, description, seller);
                    System.out.println("Product added successfully!");
                    break;

                case 2: //  Seller can update Product info
                    System.out.println("Enter the Product ID to update:");
                    int updateProductID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter new product name (or press Enter to keep unchanged):");
                    String newName = scanner.nextLine();
                    System.out.println("Enter new product price (or -1 to keep unchanged):");
                    double newPrice = scanner.nextDouble();
                    System.out.println("Enter new product quantity (or -1 to keep unchanged):");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    seller.updateProduct(updateProductID,
                            newName.isEmpty() ? null : newName,
                            newPrice < 0 ? null : newPrice,
                            newQuantity < 0 ? null : newQuantity);
                    System.out.println("Product updated successfully!");
                    break;

                case 3: // Seller can delete a Product
                    System.out.println("Enter the Product ID to delete:");
                    int deleteProductID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    seller.deleteProduct(deleteProductID);
                    System.out.println("Product deleted successfully!");
                    break;

                case 4: // Seller can view their Products
                    System.out.println("Displaying your products...");
                    seller.viewMyProducts();
                    break;

                case 5: // Seller can logout
                    System.out.println("Logging out...");
                    running = false;
                    break;

                default: // If option is wrong or doesnt exist
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Admin menu
    private void DisplayAdminMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Greetings, Admin!");
            System.out.println("Admin Menu Selection:");
            System.out.println("1. View All Users");
            System.out.println("2. Delete a User");
            System.out.println("3. View All Products");
            System.out.println("4. Logout");

            int option = scanner.nextInt();
            scanner.nextLine();

            Admin admin = (Admin) userService.getCurrentUser(); // take the user that is currently login on

            switch (option) {
                case 1:
                    //DOUBLE CHECK THAT
                    // display all users - contact info
                    // Admin can view all users
                    System.out.println("Displaying all users...");
                    admin.viewAllUsers();
                    break;

                case 2:
                    // Admin can delete a register user
                    System.out.println("Enter the user ID to delete:");
                    int userIdToDelete = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    admin.deleteUser(userIdToDelete);
                    break;

                case 3:
                    // DOUBLE CHECK
                    // Admin can view all products
                    // see list of products in the system
                    // list need to include seller name and info
                    System.out.println("Displaying all products...");
                    admin.viewAllProducts();
                    break;

                case 4:
                    // Admin can logout
                    System.out.println("Logging out...");
                    running = false;
                    break;

                default:
                    // If option is wrong or doesnt exist
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}