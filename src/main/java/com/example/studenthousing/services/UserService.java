package com.example.studenthousing.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.example.studenthousing.model.User;
import com.example.studenthousing.repository.UserRepository;
import com.example.studenthousing.validation.error.UserAlreadyExistException;
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

        User userExists = userRepository.findByUsername(username);
        User emailExists = userRepository.findByEmail(email);
        if (userExists != null) {
            throw new UserAlreadyExistException("Username already exists!");
        } else if (emailExists != null) {
            throw new UserAlreadyExistException("Email address is already used!");
        }

        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(encoder.encode(password));
        return userRepository.save(u);
    }


    public void test() {
        // Save a new user
        User newUser = new User();
        // Create random username
        Random random = new Random();
        String randomUsername = "User" + random.nextInt() + random.nextInt();
        newUser.setUsername(randomUsername);

        String randomEmail = randomUsername + "@test.com";
        newUser.setEmail(randomEmail);
        newUser.setPassword(encoder.encode("eenPassWord"));

        userRepository.save(newUser);

        // Find a user by ID
        Optional<User> result = userRepository.findById(1);
        result.ifPresent(user -> System.out.println(user.getUsername()));

        // Find a user by username
        User userByName = userRepository.findByUsername(randomUsername);
        System.out.printf("\nFind user by name (%s)...\n", randomUsername);
        System.out.println(userByName.getUsername());
        System.out.println(userByName.getEmail());

        // Find a user by email address
        User userByMail = userRepository.findByEmail(randomEmail);
        System.out.printf("\nFind user by email (%s)...\n", randomEmail);
        System.out.println(userByMail.getUsername());
        System.out.println(userByMail.getEmail());

        // List all users
        Iterable<User> iterator = userRepository.findAll();
        System.out.println("\nListing all users...");
        iterator.forEach(user -> System.out.println(user.getUsername()));

        // Count number of users
        long count = userRepository.count();
        System.out.println("Number of users: " + count);
    }

}
