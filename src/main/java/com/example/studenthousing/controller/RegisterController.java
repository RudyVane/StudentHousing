package com.example.studenthousing.controller;

import com.example.studenthousing.model.User;
import com.example.studenthousing.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    // Using POST to /register will add a user to the user-table
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        // Check the request for correct input
        if (user == null) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }

        // Check if all parameters are correctly given, else show error message
        if (user.getUsername() == null || user.getEmail() == null || user.getPassword() == null) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }

        // Create new user, save to db
        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        userRepository.save(u);

        return new ResponseEntity<>(Map.of(
                "username", user.getUsername(),
                "email", user.getEmail(),
                "status", "Account created!"),
                HttpStatus.OK);
    }
}
