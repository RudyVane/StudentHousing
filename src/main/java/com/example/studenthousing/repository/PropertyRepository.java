package com.example.studenthousing.repository;

import java.util.List;
import java.util.Optional;
import com.example.studenthousing.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    @Query("SELECT p FROM Property p ORDER BY p.id ASC")
    List<Property> findFirst10Properties(Pageable pageable);
    Optional<Property> findPropertyById(int id);
    @Query("SELECT DISTINCT p.city FROM Property p ORDER BY p.city asc")
    List<String> findAllDistinctCities();
    @Query("SELECT p FROM Property p WHERE p.city = :city")
    Page findByCity(@Param("city") String city, Pageable pageable);

    @Query("SELECT p FROM Property p ORDER BY p.id ASC")
    Page findById(@Param("id") Integer id, Pageable pageable);
}