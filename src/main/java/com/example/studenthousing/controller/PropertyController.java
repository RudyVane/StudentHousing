package com.example.studenthousing.controller;

import com.example.studenthousing.model.Property;
import com.example.studenthousing.repository.PropertyRepository;
import com.example.studenthousing.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/property")
    public ResponseEntity<String> registerGET() {

        return ResponseEntity.ok("It works!");
    }

    // Check the request for correct input
    @PostMapping("/property")
    public ResponseEntity<?> register (@RequestBody Property property){
        if (property == null) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }
        if (property.getExternalId() == null) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }
        Property newProperty = propertyService.newProperty(
                property.getExternalId(), property.getAreaSqm(), property.getCity(),
                property.getCoverImageUrl(), property.getFurnish(), property.getLatitude(),
                property.getLongitude(), property.getPostalCode(), property.getPropertyType(),
                property.getRawAvailability(), property.getRent(), property.getRentDetail(),
                property.getTitle(), property.getAdditionalCosts(), property.getDeposit(),
                property.getDescriptionNonTranslated(), property.getDescriptionTranslated(),
                property.getEnergyLabel(), property.getGender(), property.getInternet(),
                property.getIsRoomActive(), property.getKitchen(), property.getLiving(),
                property.getMatchAge(), property.getMatchCapacity(), property.getMatchGender(),
                property.getMatchLanguages(), property.getMatchStatus(), property.getPageDescription(),
                property.getPageTitle(), property.getPets(), property.getRegistrationCost(),
                property.getRoommates(), property.getShower(), property.getSmokingInside(),
                property.getToilet()
        );
        // Create a HashMap to store the property details
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("external_id", newProperty.getExternalId());
        responseMap.put("area_sqm", newProperty.getAreaSqm());
        responseMap.put("city", newProperty.getCity());
        responseMap.put("cover_image_url", newProperty.getCoverImageUrl());
        responseMap.put("furnish", newProperty.getFurnish());
        responseMap.put("latitude", newProperty.getLatitude());
        responseMap.put("longitude", newProperty.getLongitude());
        responseMap.put("postal_code", newProperty.getPostalCode());
        responseMap.put("property_type", newProperty.getPropertyType());
        responseMap.put("raw_availability", newProperty.getRawAvailability());
        responseMap.put("rent", newProperty.getRent());
        responseMap.put("rent_detail", newProperty.getRentDetail());
        responseMap.put("title", newProperty.getTitle());
        responseMap.put("additional_costs", newProperty.getAdditionalCosts());
        responseMap.put("deposit", newProperty.getDeposit());
        responseMap.put("description_non_translated", newProperty.getDescriptionNonTranslated());
        responseMap.put("description_translated", newProperty.getDescriptionTranslated());
        responseMap.put("energy_label", newProperty.getEnergyLabel());
        responseMap.put("gender", newProperty.getGender());
        responseMap.put("internet", newProperty.getInternet());
        responseMap.put("is_room_active", newProperty.getIsRoomActive());
        responseMap.put("kitchen", newProperty.getKitchen());
        responseMap.put("living", newProperty.getLiving());
        responseMap.put("match_age", newProperty.getMatchAge());
        responseMap.put("match_capacity", newProperty.getMatchCapacity());
        responseMap.put("match_gender", newProperty.getMatchGender());
        responseMap.put("match_languages", newProperty.getMatchLanguages());
        responseMap.put("match_status", newProperty.getMatchStatus());
        responseMap.put("page_description", newProperty.getPageDescription());
        responseMap.put("page_title", newProperty.getPageTitle());
        responseMap.put("pets", newProperty.getPets());
        responseMap.put("registration_costs", newProperty.getRegistrationCost());
        responseMap.put("roommates", newProperty.getRoommates());
        responseMap.put("shower", newProperty.getShower());
        responseMap.put("smoking_inside", newProperty.getSmokingInside());
        responseMap.put("toilet", newProperty.getToilet());
        responseMap.put("status", "Property created!");

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}

