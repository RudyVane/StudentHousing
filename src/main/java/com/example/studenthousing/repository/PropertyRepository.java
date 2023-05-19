package com.example.studenthousing.repository;

import java.util.List;
import java.util.Optional;

import com.example.studenthousing.model.Property;
import com.example.studenthousing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<Property, Long>, JpaRepository<Property, Long> {


    @Query("SELECT p FROM Property p")
    List<Property> findFirst10Properties();
}