package com.example.studenthousing.controller;

import com.example.studenthousing.model.User;
import com.example.studenthousing.services.UserService;
import com.example.studenthousing.validation.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class RegisterController {
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
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        // Check if all parameters are correctly given, else show error message
        if (user.getUsername() == null || user.getEmail() == null || user.getPassword() == null) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        // Check if the passwords are identical
        if (!user.getPassword().equals(user.getMatching_password())) {
            return new ResponseEntity<>(Map.of("error", "The passwords are not identical"),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        // Create new user via UserService
        User newUser = null;
        try {
            newUser = userService.registerNewUser(
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Display message to user about username or email already existing
            if (e instanceof UserAlreadyExistException) {
                return new ResponseEntity<>(Map.of(
                        "error", e.getMessage()),
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }
            // Other errors are most probably an invalid email, thus show this message
            // This does not mean other errors are not possible! Can do with improvement.
            // Tried to catch other exceptions or instanceof exceptions, but couldn't get it working
            else {
                return new ResponseEntity<>(Map.of(
                        "error", "Invalid email!"),
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }

        // Upon successful registering, show the newly registered user
        System.out.println("New user created: " + newUser.getUsername());
        return new ResponseEntity<>(Map.of(
                "username", newUser.getUsername(),
                "email", newUser.getEmail(),
                "id", newUser.getUser_id(),
                "status", "Account created!"),
                HttpStatus.OK);
    }
}
