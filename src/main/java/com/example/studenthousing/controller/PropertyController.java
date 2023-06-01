package com.example.studenthousing.controller;

import com.example.studenthousing.model.Property;
import com.example.studenthousing.repository.PropertyRepository;
import com.example.studenthousing.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @GetMapping(value = "/properties", produces = { MediaType.APPLICATION_JSON_VALUE, "text/csv" })
    public ResponseEntity<?> getPropertyList(
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "json") String format,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "rent") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(required = false) Integer minRent,
            @RequestParam(required = false) Integer maxRent) {

        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<Property> properties;

        if (minRent != null && maxRent != null) {
            if (city != null) {
                properties = propertyService.getPropertiesByCityAndRentRange(city, minRent, maxRent, pageable);
            } else {
                properties = propertyService.getPropertiesByRentRange(minRent, maxRent, pageable);
            }
        } else if (city != null) {
            properties = propertyService.getPropertiesByCity(city, pageable);
        } else {
            properties = propertyService.getAllProperties(pageable);
        }

        if (format.equalsIgnoreCase("csv")) {
            String csvData = convertPropertiesToCSV(properties.getContent());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=properties.csv");
            return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(properties, HttpStatus.OK);
        }
    }


    private String convertPropertiesToCSV(List<Property> properties) {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("id,external_id,area_sqm,city,cover_image_url,furnish,latitude,longitude,postal_code,property_type,raw_availability,rent,rent_detail,title,additional_costs,deposit,description_non_translated,description_translated,energy_label,gender,internet,is_room_active,kitchen,living,match_age,match_capacity,match_gender,match_languages,match_status,page_description,page_title,pets,registration_costs,roommates,shower,smoking_inside,toilet\n");
        for (Property property : properties) {
            csvBuilder.append(property.getId()).append(",")
                    .append(property.getExternalId()).append(",")
                    .append(property.getAreaSqm()).append(",")
                    .append(property.getCity()).append(",")
                    .append(property.getCoverImageUrl()).append(",")
                    .append(property.getFurnish()).append(",")
                    .append(property.getLatitude()).append(",")
                    .append(property.getLongitude()).append(",")
                    .append(property.getPostalCode()).append(",")
                    .append(property.getPropertyType()).append(",")
                    .append(property.getRawAvailability()).append(",")
                    .append(property.getRent()).append(",")
                    .append(property.getRentDetail()).append(",")
                    .append(property.getTitle()).append(",")
                    .append(property.getAdditionalCosts()).append(",")
                    .append(property.getDeposit()).append(",")
                    .append(property.getDescriptionNonTranslated()).append(",")
                    .append(property.getDescriptionTranslated()).append(",")
                    .append(property.getEnergyLabel()).append(",")
                    .append(property.getGender()).append(",")
                    .append(property.getInternet()).append(",")
                    .append(property.getIsRoomActive()).append(",")
                    .append(property.getKitchen()).append(",")
                    .append(property.getLiving()).append(",")
                    .append(property.getMatchAge()).append(",")
                    .append(property.getMatchCapacity()).append(",")
                    .append(property.getMatchGender()).append(",")
                    .append(property.getMatchLanguages()).append(",")
                    .append(property.getMatchStatus()).append(",")
                    .append(property.getPageDescription()).append(",")
                    .append(property.getPageTitle()).append(",")
                    .append(property.getPets()).append(",")
                    .append(property.getRegistrationCost()).append(",")
                    .append(property.getRoommates()).append(",")
                    .append(property.getShower()).append(",")
                    .append(property.getSmokingInside()).append(",")
                    .append(property.getToilet()).append("\n");
        }
        return csvBuilder.toString();
    }


    @GetMapping("/properties/distinct-cities")
    public ResponseEntity<List<String>> getDistinctCities() {
        List<String> cities = propertyService.getDistinctCities();
        return ResponseEntity.ok(cities);
    }



    @GetMapping("/properties/{id}")
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
    @PostMapping("/properties")
    public ResponseEntity<?> register (@RequestBody Property property){
        if (property == null) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.BAD_REQUEST);
        }
//        if (property.getExternalId() == null) {
//            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
//                    HttpStatus.BAD_REQUEST);
//        }
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

        propertyRepository.save(newProperty);
        System.out.println("New Property added! ID: " + newProperty.getId() + " in " + newProperty.getCity());

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

        // return new ResponseEntity<>(responseMap, HttpStatus.OK);
        return new ResponseEntity<>(Map.of(
                "Success", "New property added",
                "ID:", newProperty.getId(),
                "City:", newProperty.getCity()
        ), HttpStatus.OK);
    }
}

