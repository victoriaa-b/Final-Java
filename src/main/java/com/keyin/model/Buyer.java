package com.keyin.model;

public class Buyer extends User {
    public Buyer(int accountID, String username, String password, String email, String role) {
        super(accountID, username, password, email, "BUYER"); // a call to the parent class
    }
}

// will need to browse, view and add products