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
    @Query("SELECT p FROM Property p WHERE p.isRoomActive = 'true' ORDER BY p.id ASC")
    List<Property> findFirst10Properties(Pageable pageable);

    Optional<Property> findPropertyById(int id);

    @Query("SELECT DISTINCT p.city FROM Property p WHERE p.isRoomActive = 'true' ORDER BY p.city ASC ")
    List<String> findAllDistinctCities();

//    @Query("SELECT p FROM Property p WHERE p.city = :city AND p.isRoomActive = 'true' ORDER BY p.rent ASC ")
    Page<Property> findByCity(@Param("city") String city, Pageable pageable);

    @Query("SELECT p FROM Property p WHERE p.id = :id AND p.isRoomActive = 'true' ")
    Page<Property> findById(@Param("id") Integer id, Pageable pageable);

    Page<Property> findByRentBetween(int minRent, int maxRent, Pageable pageable);
    Page<Property> findByCityAndRentBetween(String city, int minRent, int maxRent, Pageable pageable);

}
