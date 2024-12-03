package com.keyin.model;

public class Admin extends User {
    public Admin(int accountID, String username, String password, String email, String role){
        super(accountID, email, username, password, role); // call parent class
    }

    // will need to view all users, delete them and view all of the products
}