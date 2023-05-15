package com.example.studenthousing.services;

// import com.example.studenthousing.controller.PropertyController;
import com.example.studenthousing.StudentHousingApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.studenthousing.model.Property;
import java.util.*;
@Service
public class PropertyService {

    @Autowired
    private StudentHousingApplication properties;

    public ResponseEntity<String> getAllProperties() {
        try {
            // list from all properties from database??
            Map<String, Object> propertyInfo = new HashMap<>();
                propertyInfo.put("external_id", Property.getExternal_id());
                propertyInfo.put("area_sqm", Property.getArea_sqm());
                propertyInfo.put("city", Property.getCity());
                propertyInfo.put("cover_image_url", Property.getCover_image_url());
                propertyInfo.put("property_type", Property.getProperty_type());
                propertyInfo.put("rent", Property.getRent());
                propertyInfo.put("gender", Property.getGender());
                propertyInfo.put("is_room_active", Property.getIs_room_active());
                propertyInfo.put("page_title", Property.getPage_title());
                            
            String propertyInfoJson = objectToJson(propertyInfo);
            if (propertyInfoJson == null) {
                propertyInfoJson = "[]";
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(propertyInfoJson, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving available properties", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public Property getPropertyById(String id) {
        return new Property();
    }
}
