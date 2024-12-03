package com.keyin.model;

public class Seller extends User {
    public Seller(int accountID, String username, String password, String email, String role){
        super(accountID, email, username, password, role); // call parent class
    }


    // seller will need to add, change, view and delete products
}