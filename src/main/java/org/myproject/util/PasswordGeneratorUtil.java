package org.myproject.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility class to generate BCrypt encrypted passwords for testing/development
 */
public class PasswordGeneratorUtil {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // List of test passwords to encrypt
        String[] passwords = {
            "password",
            "admin123",
            "temple123",
            "monk123",
            "helper123",
            "member123"
        };

        System.out.println("Generated BCrypt encrypted passwords:");
        System.out.println("===================================");

        for (String password : passwords) {
            String encryptedPassword = encoder.encode(password);
            System.out.println("Original password: " + password);
            System.out.println("Encrypted password: " + encryptedPassword);
            System.out.println("Verification test: " + encoder.matches(password, encryptedPassword));
            System.out.println("-----------------------------------");
        }

        // Generate a single password if needed
        String customPassword = "yourCustomPassword";
        System.out.println("\nCustom password encryption:");
        System.out.println("Original password: " + customPassword);
        System.out.println("Encrypted password: " + encoder.encode(customPassword));

        // Quick encrypt - just the hash for easy copying
        System.out.println("\nQuick encrypt (hash only):");
        System.out.println(encoder.encode("password"));
    }

    /**
     * Utility method to encrypt a single password
     * @param rawPassword the password to encrypt
     * @return BCrypt encrypted password
     */
    public static String encryptPassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(rawPassword);
    }

    /**
     * Utility method to verify if a raw password matches an encrypted one
     * @param rawPassword the raw password to check
     * @param encodedPassword the encrypted password to check against
     * @return true if passwords match, false otherwise
     */
    public static boolean verifyPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }
}
