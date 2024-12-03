package com.keyin.model;

public class Buyer extends User {
    // taking buyer info from the db
    public Buyer(int accountID, String username, String password, String email, String role) {
        super(accountID, email, username, password, role);
    }

    // create new buyer user without the accountID
    public Buyer(String username, String password, String email) {
        super(0, email, username, password, "BUYER");
    }
}
// will need to browse, view and add products