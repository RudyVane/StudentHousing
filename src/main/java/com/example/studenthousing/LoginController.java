package com.example.studenthousing;

import com.example.studenthousing.model.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private Environment env;

    public String getSQLPass() {
        return env.getProperty("SQLPass");
    }

    // Using POST to /register will add a user to the user-table
    @PostMapping("/login")
    public ResponseEntity<?> register(@RequestBody UserLogin user) {

        // Check the request for correct input
        if (user == null) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }

        String username;
        String password;

        // Import the given User via UserRegistration
        try {
            username = user.getUsername();
            password = user.getPassword();
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }

        // DEBUG print the new user
//        System.out.println("Username: " + username);
//        System.out.println("Password: " + password);

        // Connect to database and validate this login try
        try {
            // Load the MySQL driver and create connection to database
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/student_housing", "root", getSQLPass());

            // Create a statement object
            Statement stmt = conn.createStatement();

            // Add user to database
            String sql = String.format("SELECT password FROM user "
                            + "WHERE username = '%s'", username);
            stmt.executeQuery(sql);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(Map.of(
                "username", username,
                "status", "Login validated! "),
                HttpStatus.OK);
    }
}
