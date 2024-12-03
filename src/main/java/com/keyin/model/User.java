package com.keyin.model;

public class User {
    private int accountID;
    private String email;
    private String username;
    private String password;  // notes for later use BCrypt Maven
    private String role; // buyer, seller and admin - to do later

    // Conctructors
    public User() {}

    public User(int accountID, String email, String username, String password, String role ){
        this.accountID = accountID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // getter and setters

    public int getAccountID() {
        return accountID;
    }
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    // Note: check if we need to diplays the info here with toString
}
