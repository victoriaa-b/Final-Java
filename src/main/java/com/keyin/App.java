package com.keyin;

import com.keyin.services.UserService;
import com.keyin.services.ProductService;
// This is the main entry point
public class App {
    public static void main(String[] args){
        UserService userService = new UserService();
        ProductService productService = new ProductService();
        MainMenu mainMenu = new MainMenu(userService, productService);
        mainMenu.show();
    }
}


