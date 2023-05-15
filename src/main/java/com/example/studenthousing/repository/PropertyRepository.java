package com.example.studenthousing.repository;

import java.util.List;
import java.util.Optional;

import com.example.studenthousing.model.Property;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<Property, Integer> {
    Optional<Property> findById(Integer Id);

//    List<Property> findByPropertyName(String s);
}