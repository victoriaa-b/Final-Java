package com.keyin.model;

public class Admin extends User {

    // admin objects user that comes from the db
    public Admin(int accountID, String username, String password, String email, String role) {
        super(accountID, email, username, password, role); // call parent class
    }

    // create new admin user without the accountID
    public Admin(String username, String password, String email) {
        super(0, email, username, password, "ADMIN"); // Use 0 or -1 as temporary ID
    }
    // will need to view all users, delete them and view all of the products
}