package com.example.studenthousing.controller;

import com.example.studenthousing.model.User;
import com.example.studenthousing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/account")
    public Object getUser(@RequestParam(required = false) Integer id) {
        if (id == null) {
            // Handle case when no parameter is provided
            System.out.println("Returning standard user(78)");
            return userRepository.findById(78);
        } else {
            // Try to find a user with that ID (there is no guarantee that such user exists)
            if (userRepository.findById(id) == null) {
                System.out.println("Returning standard user(78)");
                return userRepository.findById(78);
            } else {
                System.out.println("Returning user with id " + id);
                return userRepository.findById(id);
            }
        }
    }
}
