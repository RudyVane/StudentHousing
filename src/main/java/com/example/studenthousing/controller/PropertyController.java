package com.example.studenthousing.controller;

import com.example.studenthousing.model.Property;
import com.example.studenthousing.repository.PropertyRepository;
import com.example.studenthousing.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/property")
    public String registerGET() {
        return "It works!";
    }

    // Using POST to /register will add a user to the user-table
    @PostMapping("/property")
    public ResponseEntity<?> register(@RequestBody Property property) {

        // Check the request for correct input
        if (property == null) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }

        // Check if all parameters are correctly given, else show error message
        if (property.getExternalId() == null ) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }

        // Create new property via PropertyService
        Property newProperty = propertyService.newProperty(
                property.getExternalId(),
                property.getAreaSqm(),
                property.getCity());

        return new ResponseEntity<>(Map.of(
                "external_id", newProperty.getExternalId(),
                "area_sqm", newProperty.getAreaSqm(),
                "city", newProperty.getCity(),
                "status", "Property created!"),
                HttpStatus.OK);
    }
}
