package com.keyin.services;

import org.mindrot.jbcrypt.BCrypt;
import com.keyin.DAO.UserDAO;
import com.keyin.model.User;
import com.keyin.model.Admin;
import com.keyin.model.Seller;
import com.keyin.model.Buyer;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void registerUser(String username, String password, String email, String role) {

        // hash password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // decide user type by role selected
        User user;
        switch (role.toUpperCase()) {
            case "BUYER":
                user = new Buyer(username, hashedPassword, email);
                break;
            case "SELLER":
                user = new Seller(username, hashedPassword, email);
                break;
            case "ADMIN":
                user = new Admin(username, hashedPassword, email);
                break;
            default:
                throw new RuntimeException("Invalid role: " + role);
        }

        userDAO.createUser(user); // will create a user object
    }

    //  check to see if login with username is valid
    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // validate the password
        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}