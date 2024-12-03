package com.keyin.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Getting the system to hash the passwords
    public static String hashPassword( String plainPassword){
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Validate the password against a harsh
    public static boolean validatePassword(String plainPassword, String hashPassword) {
        return BCrypt.checkpw(plainPassword, hashPassword);

    }

}