package com.example.studenthousing.services;

import java.util.List;
import java.util.Optional;

import com.example.studenthousing.model.User;
import com.example.studenthousing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public User registerNewUser(String username, String email, String password) {
        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(encoder.encode(password));
        return userRepository.save(u);
    }

    public void test() {
        // Save a new user
        User newUser = new User();
        newUser.setUsername("JPA-user1");
        newUser.setPassword("eenPassWord");

        userRepository.save(newUser);

        // Find a user by ID
        Optional<User> result = userRepository.findById(1);
        result.ifPresent(user -> System.out.println(user.getUsername()));

        // Find a user by username
        System.out.println("\nFind user by name (JPA-user1)...");
        List<User> users = userRepository.findByUsername("JPA-user1");
        users.forEach(user -> System.out.println(user.getUsername()));

        // List all users
        System.out.println("\nListing all users...");
        Iterable<User> iterator = userRepository.findAll();
        iterator.forEach(user -> System.out.println(user.getUsername()));

        // Count number of users
        long count = userRepository.count();
        System.out.println("Number of users: " + count);
    }

}
