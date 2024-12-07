package com.keyin.services;

import com.keyin.model.User;
import com.keyin.model.Buyer;
import com.keyin.model.Seller;
import com.keyin.model.Admin;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String, User> users; // A map to store users by username
    private User currentUser; // This will store the current logged-in user

    // Constructor
    public UserService() {
        users = new HashMap<>();
        // Here you can add some sample users for testing purposes
        // users.put("admin", new Admin(1, "admin", "password", "admin@example.com"));
    }

    // Register a new user
    public void registerUser(String username, String password, String email, String role) throws Exception {
        if (users.containsKey(username)) {
            throw new Exception("Username already taken");
        }

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

        users.put(username, newUser);
    }

public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // validate the password
        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new RuntimeException("Invalid password"); 
        }

        // Sets up the current user
        this.currentUser = user;
        return user;
    }

    // need to get the current user info
    public User getCurrentUser() {
        return this.currentUser;
    }

}
