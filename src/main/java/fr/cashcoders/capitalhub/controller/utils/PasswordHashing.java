package fr.cashcoders.capitalhub.controller.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The PasswordHashing class provides methods for securely hashing passwords using the SHA-256 algorithm.
 * This class is used to create password hashes that can be stored and verified later.
 */
public class PasswordHashing {

    /**
     * Hashes the given password using the SHA-256 algorithm.
     *
     * @param password The password to be hashed.
     * @return A hexadecimal representation of the password hash, or null if an error occurs.
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

            byte[] hashedBytes = digest.digest(passwordBytes);

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
