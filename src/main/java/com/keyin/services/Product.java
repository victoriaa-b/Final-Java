package com.keyin.services;

public class Product {
    private int productID;
    private String name;
    private double price;
    private int quantity;
    private int sellerID;

    // Constructor
    public Product(int productID, String name, double price, int quantity, int sellerID) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellerID = sellerID;
    }

    // Getters and Setters
    public int getProductID() {
        return productID;
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

    public int getSellerID() {
        return sellerID;
    }

    @Override
    public String toString() {
        return "Product Details: " +
                "ID=" + productID +
                ", Name='" + name + '\'' +
                ", Price=" + price +
                ", Quantity=" + quantity +
                ", SellerID=" + sellerID;
    }
}

