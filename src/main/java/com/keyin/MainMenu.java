package com.keyin;

import com.keyin.services.UserService;
import java.util.Scanner;

// handles all the logic
public class MainMenu {
    private final UserService userService;
    private final Scanner scanner;

    // need to initialize
    public MainMenu(UserService userService) {
        this.userService = userService;
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
                   /// login();
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

        System.out.println("Enter email your email:");
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

    }


