package fr.cashcoders.capitalhub.controller.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {
    public static String hashPassword(String password) {
        try {
            // Créez un objet MessageDigest avec l'algorithme SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Convertissez le mot de passe en tableau de bytes
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

            // Calculez le hachage en utilisant les bytes du mot de passe
            byte[] hashedBytes = digest.digest(passwordBytes);

            // Convertissez le hachage en représentation hexadécimale
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            // Retournez le hachage sous forme de chaîne hexadécimale
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            // Gérez l'exception NoSuchAlgorithmException
            e.printStackTrace();
            return null;
        }
    }
}
