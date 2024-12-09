package com.keyin.model;

import com.keyin.DAO.ProductDAO;
import com.keyin.services.ProductService;
import com.keyin.model.Product;

import java.util.List;

public class Seller extends User {
    private final int seller_id; // Updated variable name
    private final ProductService productService;
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

    public int getSellerId() { // Updated method name
        return seller_id;
    }

    // Add a new product
    public void addProduct(String name, double price, int quantity, String description) {
        productService.addProduct(name, price, quantity, description, this); // Save to the database
    }

    // Update an existing product
    public void updateProduct(int productID, String name, double price, int quantity) {
        productService.updateProduct(productID, name, price, quantity); // Update the database
    }

    // Delete a product
    public void deleteProduct(int productID) {
        productService.deleteProduct(productID); // Delete from the database
    }

    // View all products listed by this seller
    public void viewMyProducts() {
        List<Product> sellerProducts = productService.getProductsBySeller(this.seller_id); // Fetch from the database
        if (sellerProducts.isEmpty()) {
            System.out.println("You have no products listed.");
        } else {
            System.out.println("Your Products:");
            for (Product product : sellerProducts) {
                System.out.println(product); // Prints the product using toString method
            }
        }
    }
}
