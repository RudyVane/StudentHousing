package com.example.studenthousing.controller;

import com.example.studenthousing.model.User;
import com.example.studenthousing.repository.UserRepository;
import com.example.studenthousing.services.UserService;
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
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerGET() {
        return "It works!";
    }

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

        // Create new user via UserService
        User newUser = userService.registerNewUser(
                user.getUsername(),
                user.getEmail(),
                user.getPassword());

        return new ResponseEntity<>(Map.of(
                "username", newUser.getUsername(),
                "email", newUser.getEmail(),
                "id", newUser.getUser_id(),
                "password", newUser.getPassword(),
                "status", "Account created!"),
                HttpStatus.OK);
    }
}
