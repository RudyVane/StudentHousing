package com.example.studenthousing.repository;

import java.util.List;
import java.util.Optional;

import com.example.studenthousing.model.Property;
import com.example.studenthousing.model.User;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<Property, Integer> {

    List<Property> findByExternalId(String external_id);
//    List<Property> findByPropertyName(String s);
}