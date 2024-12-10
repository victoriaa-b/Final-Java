package com.keyin.model;

import com.keyin.DAO.ProductDAO;
import com.keyin.services.ProductService;
import com.keyin.model.Product;

import java.util.List;

public class Seller extends User {
    private int seller_id; // Updated variable name
    private ProductService productService;
    private ProductDAO productDAO;

    // Constructor for existing seller with accountID
    public Seller(int accountID, String username, String password, String email, String role) {
        super(accountID, email, username, password, role);

        if (!role.equals("SELLER")) {
            throw new IllegalArgumentException("User must have SELLER role to be a Seller.");
        }
        this.seller_id = accountID; // Updated variable name
        this.productService = new ProductService(); // Initialize a shared product service
    }

    // Constructor for new seller without accountID
    public Seller(String username, String password, String email) {
        super(0, email, username, password, "SELLER");
        this.seller_id = getAccountID(); // Use the accountID when assigned
        this.productService = new ProductService();
    }

    public Seller(int id, String username, String userPassword, String email) {
        // Call the parent constructor (User) to initialize common fields
        super(id, username, userPassword, email, "seller");
    }


    public int getSellerId() {
        return seller_id;
    }

    public void addProduct(String name, double price, int quantity, String description) {
        productService.addProduct(name, price, quantity, description, seller_id);
    }

    public void updateProduct(int productId, String newName, Double newPrice, Integer newQuantity, String newDescription) {
        productService.updateProduct(productId, seller_id, newName, newPrice, newQuantity, newDescription);
    }

    public void deleteProduct(int productId) {
        productService.deleteProduct(productId, seller_id);
    }

    public void viewAllProducts() {
        List<Product> products = productService.getProductsBySeller(seller_id);
        if (products.isEmpty()) {
            System.out.println("You have no products listed.");
        } else {
            System.out.println("Your Products:");
            for (Product product : products) {
                System.out.println(product); // Ensure `Product` class has a `toString` method for better display
            }
        }
    }
}
