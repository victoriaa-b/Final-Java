package com.keyin.model;

public class Seller extends User {
    // taking seller info with accountID from db
    public Seller(int accountID, String username, String password, String email, String role) {
        super(accountID, email, username, password, role);
    }

    // creating new seller without accountID
    public Seller(String username, String password, String email) {
        super(0, email, username, password, "SELLER");
    }
}