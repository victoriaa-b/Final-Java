package com.keyin.model;

public class Product {
    private final int productID;
    private String name;
    private double price;
    private int quantity;
    private int sellerID;
    private String description;
    private Seller seller;

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

    // Constructor without seller object
    public Product(String name, String description, double price, int quantity) {
        this.productID = 0;  // Default to 0 for new product, to be set by the database
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.sellerID = 0;  // Seller will be set later
    }

    public Product(int productID, String name, double price, int quantity, int sellerID, String description) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellerID = sellerID;
        this.description = description;
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
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        String sellerInfo = (seller != null)
                ? "Seller: " + seller.getUsername() + " (ID: " + seller.getSellerID() + "), Seller Email: " + seller.getEmail()
                : "Seller ID: " + sellerID;
        return "Product ID: " + productID +
                ", Name: " + name +
                ", Price: $" + String.format("%.2f", price) +  // Format price to 2 decimal places
                ", Quantity: " + quantity +
                ", Description: " + description +
                ", " + sellerInfo;
    }
}
