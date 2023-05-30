package com.example.studenthousing.repository;

import com.example.studenthousing.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    User findByUsername(String username);
    User findById(int id);

    List<User> findByAdActiveTrue();

    // List<User> findByGender(String gender);

    // methods to do database actions with a user
}
