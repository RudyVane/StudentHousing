package com.example.studenthousing.controller;

import com.example.studenthousing.model.User;
import com.example.studenthousing.repository.UserRepository;
import com.example.studenthousing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginGET() {
        return "This is the login page";
    }

    // Using POST to /register will add a user to the user-table
    @PostMapping("/login")
    public ResponseEntity<?> register(@RequestBody User user) {

        // Check the request for correct input
        if (user == null) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }

        // Check if all parameters are correctly given, else show error message
        if (user.getUsername() == null || user.getPassword() == null) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }

        // DEBUG print the new user
//        System.out.println("Username: " + username);
//        System.out.println("Password: " + password);

        // Validate user's login


        // TO DO:
        // Move database calls to UserRepository
        // Use selected password to validate the inputted password against
        // Respond accordingly

        return new ResponseEntity<>(Map.of(
                "username", user.getUsername(),
                "status", "Login validated! "),
                HttpStatus.OK);
    }
}
