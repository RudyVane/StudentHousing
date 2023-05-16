package com.example.studenthousing.repository;

import com.example.studenthousing.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByEmail(String email);
    List<User> findByUsername(String username);

    // List<User> findByGender(String gender);

    // methods to do database actions with a user
}
