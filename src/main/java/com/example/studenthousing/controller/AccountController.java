package com.example.studenthousing.controller;

import com.example.studenthousing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/account")
    public Object getUser(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return userRepository.findById(id);
        } else {
            // Handle case when no parameter is provided
            return userRepository.findById(78);
        }
    }
}
