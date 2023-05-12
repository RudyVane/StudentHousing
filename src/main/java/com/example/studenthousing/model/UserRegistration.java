package com.example.studenthousing.model;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class UserRegistration {

    String username;
    String email;
    String password;

    public UserRegistration(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = encrypt(password);
    }

    public void setPassword(String password) {
        this.password = encrypt(password);
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Function to store a encrypted password
    // Used https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/ as help
    private String encrypt(String password) {
        // Iterations determines strength of algorithm
        int iterations = 65536;
        int keyLength = 128;

        try {
            byte[] salt = getSalt();
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
            SecretKeyFactory SKFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            // Generate hashed password, return in hexadecimal form
            byte[] hash = SKFactory.generateSecret(spec).getEncoded();
            // System.out.println(iterations + ":" + HexUtils.toHexString(salt) + ":" + HexUtils.toHexString(hash));
            return iterations + ":" + toHex(salt) + ":" + toHex(hash);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e1) {
            e1.getCause();
            e1.getMessage();
        }
        // Return signalling string, that something went wrong with password
        return iterations + ":SomethingWentWrongHere";
    }

    private byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
}
