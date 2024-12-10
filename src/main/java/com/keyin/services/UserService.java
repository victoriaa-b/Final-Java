package com.keyin.services;

import com.keyin.model.User;
import com.keyin.model.Buyer;
import com.keyin.model.Seller;
import com.keyin.model.Admin;
import com.keyin.DAO.UserDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;
    private User currentUser;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void registerUser(String username, String password, String email, String role) {
        if (userDAO.findByUsername(username) != null) {
            throw new RuntimeException("Username already taken");
        }

        // Normalize the role to lowercase before saving
        role = role.toLowerCase();

        User newUser;
        switch (role) {
            case "buyer":
                newUser = new Buyer(username, BCrypt.hashpw(password, BCrypt.gensalt()), email);
                break;
            case "seller":
                newUser = new Seller(username, BCrypt.hashpw(password, BCrypt.gensalt()), email);
                break;
            case "admin":
                newUser = new Admin(username, BCrypt.hashpw(password, BCrypt.gensalt()), email);
                break;
            default:
                throw new RuntimeException("Invalid role");
        }

        userDAO.createUser(newUser);
    }



    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Validate the password
        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Normalize user role to lowercase for consistent comparison
        String role = user.getRole().toLowerCase();

        // Check the role and handle accordingly
        switch (role) {
            case "buyer":
                // Buyer-specific logic
                break;
            case "seller":
                // Seller-specific logic
                break;
            case "admin":
                // Admin-specific logic
                break;
            default:
                throw new RuntimeException("Unknown role: " + role);
        }

        // Set the current logged-in user
        this.currentUser = user;
        return user;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // Delete user by ID
    public void deleteUser(int userId) {
        userDAO.deleteUserById(userId);  // Calls DAO to delete user by ID
    }

    // Get a user by ID
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);  // Calls DAO to fetch user by ID
    }
}
