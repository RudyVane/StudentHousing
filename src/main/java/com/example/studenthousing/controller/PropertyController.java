package com.example.studenthousing.controller;

import com.example.studenthousing.model.Property;
import com.example.studenthousing.repository.PropertyRepository;
import com.example.studenthousing.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
//@RequestMapping("/studenthousing")
@CrossOrigin

public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyService propertyService;
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
    @GetMapping("/property")
    public ResponseEntity<Page<Property>> getPropertyList(@RequestParam(required = false) String city) {
        Page<Property> properties;
        if (city != null) {
            properties = propertyService.getPropertiesByCity(city);

        } else {
            properties = propertyService.getProperties();
        }
        return ResponseEntity.ok(properties);
    }
    @GetMapping("/property/distinct-cities")
    public ResponseEntity<List<String>> getDistinctCities() {
        List<String> cities = propertyService.getDistinctCities();
        return ResponseEntity.ok(cities);
    }

   /* @GetMapping("/property/find-all-ids")
    public ResponseEntity<List<Property>> findAllIds() {
        List<Property> propertyIds = propertyService.findById();
        return ResponseEntity.ok(propertyIds);
    }*/

    @GetMapping("/property/{id}")
    public ResponseEntity<?> getPropertiesById(@PathVariable("id") int id) {
        Page<Property> propertyOptional = propertyService.getPropertiesById(id);
        if (propertyOptional.isEmpty()) {
            return new ResponseEntity<>(Map.of("error", "Property not found"), HttpStatus.NOT_FOUND);
        }

        Property property = (Property) propertyOptional.get();

        // Create a HashMap to store the property details
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("id", property.getId());
        responseMap.put("external_id", property.getExternalId());
        responseMap.put("area_sqm", property.getAreaSqm());
        responseMap.put("city", property.getCity());
        responseMap.put("cover_image_url", property.getCoverImageUrl());
        responseMap.put("furnish", property.getFurnish());
        responseMap.put("latitude", property.getLatitude());
        responseMap.put("longitude", property.getLongitude());
        responseMap.put("postal_code", property.getPostalCode());
        responseMap.put("property_type", property.getPropertyType());
        responseMap.put("raw_availability", property.getRawAvailability());
        responseMap.put("rent", property.getRent());
        responseMap.put("rent_detail", property.getRentDetail());
        responseMap.put("title", property.getTitle());
        responseMap.put("additional_costs", property.getAdditionalCosts());
        responseMap.put("deposit", property.getDeposit());
        responseMap.put("description_non_translated", property.getDescriptionNonTranslated());
        responseMap.put("description_translated", property.getDescriptionTranslated());
        responseMap.put("energy_label", property.getEnergyLabel());
        responseMap.put("gender", property.getGender());
        responseMap.put("internet", property.getInternet());
        responseMap.put("is_room_active", property.getIsRoomActive());
        responseMap.put("kitchen", property.getKitchen());
        responseMap.put("living", property.getLiving());
        responseMap.put("match_age", property.getMatchAge());
        responseMap.put("match_capacity", property.getMatchCapacity());
        responseMap.put("match_gender", property.getMatchGender());
        responseMap.put("match_languages", property.getMatchLanguages());
        responseMap.put("match_status", property.getMatchStatus());
        responseMap.put("page_description", property.getPageDescription());
        responseMap.put("page_title", property.getPageTitle());
        responseMap.put("pets", property.getPets());
        responseMap.put("registration_costs", property.getRegistrationCost());
        responseMap.put("roommates", property.getRoommates());
        responseMap.put("shower", property.getShower());
        responseMap.put("smoking_inside", property.getSmokingInside());
        responseMap.put("toilet", property.getToilet());
        responseMap.put("status", "Property retrieved!");

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
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
                property.getId(),property.getExternalId(), property.getAreaSqm(), property.getCity(),
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
        responseMap.put("id", property.getId());
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

