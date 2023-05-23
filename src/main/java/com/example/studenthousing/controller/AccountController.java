package com.example.studenthousing.controller;

import com.example.studenthousing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/account")
    public Principal user(Principal user) {
        return user;
    }
}
