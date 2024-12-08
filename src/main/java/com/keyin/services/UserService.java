package com.keyin.services;

import com.keyin.model.User;
import com.keyin.model.Buyer;
import com.keyin.model.Seller;
import com.keyin.model.Admin;
import com.keyin.DAO.UserDAO; // Import your UserDAO
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private final UserDAO userDAO; // To interact with the database
    private User currentUser; // To store the current logged-in user

    // Constructor
    public UserService() {
        userDAO = new UserDAO(); // Initialize the UserDAO
    }

    // Register a new user (optional, if using in-memory map for testing)
    public void registerUser(String username, String password, String email, String role) throws Exception {
        if (userDAO.findByUsername(username) != null) {
            throw new Exception("Username already taken");
        }

        // Create a new user depending on the role
        User newUser;
        switch (role.toUpperCase()) {
            case "BUYER":
                newUser = new Buyer(username, password, email);
                break;
            case "SELLER":
                newUser = new Seller(username, password, email);
                break;
            case "ADMIN":
                newUser = new Admin(username, password, email);
                break;
            default:
                throw new Exception("Invalid role");
        }

        // Save user to the database
        userDAO.createUser(newUser); // Ensure this method is implemented in UserDAO
    }

    // Login method (using UserDAO to check the database)
    public User login(String username, String password) {
        User user = userDAO.findByUsername(username); // Fetch the user from the database
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Validate the password
        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Set up the current logged-in user
        this.currentUser = user;
        return user;
    }

    // Get the current logged-in user
    public User getCurrentUser() {
        return this.currentUser;
    }}


