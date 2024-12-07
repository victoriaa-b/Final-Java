package com.keyin.services;

import com.keyin.model.Seller;
public class Product {
    private final int productID;
    private String name;
    private double price;
    private int quantity;
    private final int sellerID;
    private final String description;
    private final Seller seller;

    // Constructor
    public Product(int productID, String name, double price, int quantity, int sellerID, String description, Seller seller) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellerID = sellerID;
        this.description = description;
        this.seller = seller;
    }

    // Getters and Setters
    public int getProductID() {
        return productID;
    }

    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Added seller info
    public int getSellerID() {
        return sellerID;
    }
    public Seller getSeller() {
        return seller;
    }

    @Override
    public String toString() {
        return "Product ID: " + productID + ", Name: " + name + ", Price: $" + price +
                ", Quantity: " + quantity + ", Seller: " + seller.getUsername() +
                " (ID: " + seller.getSellerID() + "), Seller Email: " + seller.getEmail();
    }
}

