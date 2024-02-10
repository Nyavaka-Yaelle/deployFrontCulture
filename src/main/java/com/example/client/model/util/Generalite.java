package com.example.client.model.util;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import org.springframework.stereotype.Component;
import java.security.SecureRandom;

@Component
public class Generalite {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-!^&";
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomString() {
        int length = 15;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    public static String getSubstringFrom16(String input) {
        if (input.length() <= 15) {
            return ""; // Retourne une chaîne vide si la longueur de l'entrée est inférieure ou égale à 15
        } else {
            return input.substring(15); // Retourne la sous-chaîne à partir de la 16ème lettre
        }
    }
    public static boolean isActive(String active, String item) {
        if (active!=null && item!=null && active.equals(item)) return true; 
        return false;
    }
}