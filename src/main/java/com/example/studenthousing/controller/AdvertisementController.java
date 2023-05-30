package com.example.studenthousing.controller;

import com.example.studenthousing.model.User;
import com.example.studenthousing.services.UserService;
import com.example.studenthousing.validation.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AdvertisementController {
    @Autowired
    private UserService userService;

    @GetMapping("/advertisements")
    public ResponseEntity<?> getAds() {
        List<User> users = userService.findByAdActiveTrue();

        // Check for empty list
        if (users.isEmpty()) {
            System.out.println("Empty list of advertisements found");
            return new ResponseEntity<>(Map.of("error", "There are no advertisements to show"),
                    HttpStatus.NOT_FOUND);
        }
        // Return list of user-advertisements
        else {
            System.out.println("List of advertisements returned");
            return new ResponseEntity<>(users,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
