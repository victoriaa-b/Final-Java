package com.keyin;

import com.keyin.services.UserService;
//make main entry point
public class App {
    public static void main(String[] args){
        UserService userService = new UserService();
        MainMenu mainMenu = new MainMenu(userService);
        mainMenu.show();
    }
}


