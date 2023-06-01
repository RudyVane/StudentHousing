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
        if (id == null) {
            // Handle case when no parameter is provided
            System.out.println("Returning standard user(79)");
            return userRepository.findById(79);
        } else {
            // Try to find a user with that ID (there is no guarantee that such user exists)
            if (userRepository.findById(id) == null) {
                System.out.println("Returning standard user(79)");
                return userRepository.findById(79);
            } else {
                System.out.println("Returning user with id " + id);
                return userRepository.findById(id);
            }
        }
    }

//    @PostMapping("/account")
//    public ResponseEntity<?> createAd(@RequestBody User user) {
//        // When no id has been given, redirect back to /advertisements
//        if (user == null) {
//            return new ResponseEntity<>(Map.of("error", "You haven't given a valid advertisement"),
//                    HttpStatus.BAD_REQUEST);
//        }
//
//        // Select the given user by ID
//        User u = userRepository.findById(user.getId());
//        // Update all fields in the db to show the correct info
//        System.out.printf("Selected user: %s, matching with inputted user: %s, with id: %d\n",
//                u.getUsername(), user.getUsername(), u.getId());
//
//        return new ResponseEntity<>(Map.of(
//                "status", "Advertisement created!"),
//                HttpStatus.OK);
//
//    }
}
