package com.keyin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/DatabaseConnection";
    private static final String USER = "postgresql";
    private static final String PASSWORD = "Password";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException error) {
            throw new RuntimeException("Error! Could not connect to the database!", error);
        }
    }
}
