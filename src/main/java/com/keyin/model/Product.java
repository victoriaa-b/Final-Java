package com.keyin.model;

public class Product {
    private final int productID;
    private String name;
    private double price;
    private int quantity;
    private int sellerId;  // Changed to follow camelCase convention
    private String description;
    private Seller seller;  // Optional field, might not always be used

    // Constructor for a new product (without seller)
    public Product(String name, String description, double price, int quantity, int sellerId) {
        this.productID = 0;  // Default to 0 for new product, to be set by the database
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.sellerId = sellerId;
    }

    // Constructor for an existing product (with seller)
    public Product(int productID, String name, double price, int quantity, int sellerId, String description, Seller seller) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellerId = sellerId;
        this.description = description;
        this.seller = seller;
    }

    // Constructor for an existing product (without seller)
    public Product(int productID, String name, double price, int quantity, int sellerId, String description) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellerId = sellerId;
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

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
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
                ? "Seller: " + seller.getUsername() + " (ID: " + seller.getSellerId() + "), Seller Email: " + seller.getEmail()
                : "Seller ID: " + sellerId;  // Handling when seller is null
        return String.format("Product ID: %d, Name: %s, Price: $%.2f, Quantity: %d, Description: %s, %s",
                productID, name, price, quantity, description, sellerInfo);
    }
}
