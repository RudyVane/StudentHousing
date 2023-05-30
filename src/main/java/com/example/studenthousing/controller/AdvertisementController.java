package com.example.studenthousing.controller;

import com.example.studenthousing.model.User;
import com.example.studenthousing.repository.UserRepository;
import com.example.studenthousing.services.UserService;
import com.example.studenthousing.validation.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AdvertisementController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/advertisements")
    public ResponseEntity<?> getAds() {
        List<User> users = userService.findByAdActiveTrue();

        // Check for empty list
        if (users.isEmpty()) {
            System.out.println("Empty list of advertisements found");
            return new ResponseEntity<>(Map.of("error", "There are no advertisements to show"),
                    HttpStatus.NOT_FOUND);
        }
        // Return list of user-advertisements
        else {
            System.out.println("List of advertisements returned");
            return new ResponseEntity<>(users,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("/advertisements")
    public ResponseEntity<?> putAd(@RequestBody User user) {

        // Check the request for correct input
        if (user == null) {
            return new ResponseEntity<>(Map.of("error", "You have not given the correct input"),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        // Create new advertisement: update user-info in database, set ad_active to true
        User u = userRepository.findById(78);
        System.out.printf("Selected user with username: %s and email: %s\n", u.getUsername(), u.getEmail());
        u.setFullName(user.getFullName());
        u.setPhotoURL(user.getPhotoURL());
        u.setTelephone(user.getTelephone());
        u.setAge(user.getAge());
        u.setGender(user.getGender());
        u.setRole(user.getRole());
        u.setStatus(user.getStatus());
        u.setFullName(user.getFullName());
        u.setStatus(user.getStatus());
        u.setLanguage(user.getLanguage());
        u.setMaxRent(user.getMaxRent());
        u.setPrefCity(user.getPrefCity());
        u.setPrefGender(user.getPrefGender());
        u.setPrefKitchen(user.getPrefKitchen());
        u.setPrefShower(user.getPrefShower());
        u.setPrefToilet(user.getPrefToilet());
        u.setPrefLiving(user.getPrefLiving());
        u.setPrefInternet(user.getPrefInternet());
        u.setPrefEnergyLabel(user.getPrefEnergyLabel());
        u.setPrefPets(user.getPrefPets());
        u.setPrefSmokingInside(user.getPrefSmokingInside());
        u.setPrefRoommates(user.getPrefRoommates());
        u.setPrefDistanceToZipcode(user.getPrefDistanceToZipcode());
        u.setPrefZipcode(user.getPrefZipcode());
        u.setAdActive(true);

        userRepository.save(u);

        System.out.println("User updated!");
        return new ResponseEntity<>(Map.of("success", "You have added your advertisement"),
                HttpStatus.CREATED);
    }

}
