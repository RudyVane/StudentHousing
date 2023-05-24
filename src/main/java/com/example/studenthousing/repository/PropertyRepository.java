package com.example.studenthousing.repository;

import java.util.List;
import java.util.Optional;
import com.example.studenthousing.model.Property;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    @Query("SELECT p FROM Property p ORDER BY p.id ASC")
    List<Property> findFirst10Properties(Pageable pageable);
    Optional<Property> findPropertyById(int id);


}