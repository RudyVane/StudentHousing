package com.example.studenthousing;

import com.example.studenthousing.user.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;


@RestController
public class RegisterController {

    // Get SQL-password
//    @Autowired
//    private Environment env;

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

        // DEBUG print the new user
//        System.out.println("Username: " + username);
//        System.out.println("Email: " + email);
//        System.out.println("Password: " + password);

        // Connect to database and insert new user into user table
        try {
            // Load the MySQL driver and create connection to database
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/student_housing", "root", "");

            // Create a statement object
            Statement stmt = conn.createStatement();

            // Add user to database
            String sql = String.format("INSERT INTO user "
                    + "(username, email, password) "
                    + "VALUES ('%s', '%s', '%s')",
                    username, email, password);
            stmt.executeUpdate(sql);
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
