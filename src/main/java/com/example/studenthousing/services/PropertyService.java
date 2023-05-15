package com.example.studenthousing.services;

import java.util.List;
import java.util.Optional;

import com.example.studenthousing.model.Property;
import com.example.studenthousing.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public void test() {
        // Save a new Property
        Property newProperty = new Property();
        newProperty.setExternalId("JPA-Property1");

        propertyRepository.save(newProperty);

        // Find a Property by ID
        Optional<Property> result = propertyRepository.findById(1);
        result.ifPresent(property -> System.out.println());

        // Find a Property by PropertyName
        System.out.println("\nFind Property by name (JPA-Property1)...");
        List<Property> properties = propertyRepository.findByPropertyName("JPA-Property1");
        properties.forEach(property -> System.out.println(property));

        // List all Properties
        System.out.println("\nListing all Properties...");
        Iterable<Property> iterator = propertyRepository.findAll();
        iterator.forEach(property -> System.out.println(property));

        // Count number of Properties
        long count = propertyRepository.count();
        System.out.println("Number of Properties: " + count);
    }

    public Object getPropertyById(int id) {
        return propertyRepository.findById(id);
    }

    public Object getAllProperties() {
        return propertyRepository.findAll();
    }
}

