package com.example.studenthousing;
import com.example.studenthousing.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import com.example.studenthousing.services.PropertyService;
@RestController
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, "text/csv"})
    public ResponseEntity<?> getAllProperties(@RequestParam(defaultValue = "json") String format) {
        List<Property> properties = (List<Property>) propertyService.getAllProperties();

        if (format.equalsIgnoreCase("csv")) {
            // Convert properties to CSV format
            String csvData = convertToCsv(properties);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Disposition", "attachment; filename=properties.csv");
            return ResponseEntity.ok().headers(headers).contentType(MediaType.TEXT_PLAIN).body(csvData);
        } else {
            // Return properties in JSON format
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(properties);
        }
    }

    // Other methods for handling other HTTP methods and endpoints can be added here




    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, "text/csv"})
    public ResponseEntity<?> getPropertyById(@PathVariable("id") String id, @RequestParam(defaultValue = "json") String format) {
        Property property = propertyService.getPropertyById(id);

        if (property == null) {
            // If property is not found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        }

        if (format.equalsIgnoreCase("csv")) {
            // Convert property to CSV format
            String csvData = convertToCsv(Collections.singletonList(property));
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Disposition", "attachment; filename=property_" + id + ".csv");
            return ResponseEntity.ok().headers(headers).contentType(MediaType.TEXT_PLAIN).body(csvData);
        } else {
            // Return property in JSON format
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(property);
        }
    }

    // Helper method to convert List<Property> to CSV format
    private String convertToCsv(List<Property> properties) {
        // Implementation of CSV conversion logic
        // ...
        return "this,will,be,a,csv,file";
    }

    // Other methods for handling other HTTP methods and endpoints can be added here

}
