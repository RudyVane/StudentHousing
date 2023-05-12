package com.example.studenthousing;

import com.example.studenthousing.user.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.Map;


@RestController
public class RegisterController {

    @Autowired
    private Environment env;

    private String getSQLPass() {
        return env.getProperty("SQLPass");
    }

    // Using POST to /register will add a user to the user-table
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistration user) {

        // Check the request for correct input
        if (user == null) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }

        String username;
        String email;
        String password;

        // Import the given User via UserRegistration
        try {
            username = user.getUsername();
            email = user.getEmail();
            password = user.getPassword();
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }

        // Check if all parameters are correctly loaded, else show error message
        if (username == null || email == null || password == null) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }

        // DEBUG print the new user
//        System.out.println("Username: " + username);
//        System.out.println("Email: " + email);
//        System.out.println("Password: " + password);

        // Connect to database and insert new user into user table
        try {
            // Load the MySQL driver and create connection to database
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/student_housing", "root", getSQLPass());

            // Add user to database
            String sql = "INSERT INTO user "
                    + "(username, email, password) "
                    + "VALUES (?, ?, ?)";

            // Create a statement object
            PreparedStatement stmt = conn.prepareStatement(sql); {
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.executeUpdate();
            }
        } catch (
                SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(Map.of(
                "username", username,
                "email", email,
                "status", "Account created!"),
                HttpStatus.OK);
    }
}
