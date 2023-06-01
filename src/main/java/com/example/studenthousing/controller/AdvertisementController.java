package com.example.studenthousing.controller;

import com.example.studenthousing.model.User;
import com.example.studenthousing.repository.UserRepository;
import com.example.studenthousing.services.UserService;
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
                    HttpStatus.OK);
        }
    }

    @PostMapping("/advertisements")
    public ResponseEntity<?> createAd(@RequestBody User user) {

        // Check the request for correct input
        if (user == null) {
            return new ResponseEntity<>(Map.of("error", "You haven't given a valid advertisement"),
                    HttpStatus.BAD_REQUEST);
        }

        int userID = user.getId();

        // Create new advertisement: update user-info in database, set ad_active to true
        User u = userRepository.findById(userID);

        if (u == null) {
            return new ResponseEntity<>(Map.of("error", "This user does not exist!"),
                    HttpStatus.BAD_REQUEST);
        }

        System.out.printf("Creating ad for user: %s (%s) and email: %s\n", u.getFullName(), u.getUsername(), u.getEmail());
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
        u.setMessage(user.getMessage());
        u.setAdActive(true);

        userRepository.save(u);

        System.out.println("User updated!");
        return new ResponseEntity<>(Map.of("success", "You have added your advertisement"),
                HttpStatus.CREATED);
    }

    @GetMapping("/advertisements/{id}")
    public ResponseEntity<?> editAd(@PathVariable int id) {
        User user = userRepository.findById(id);

        // Check for existing user
        if (user == null) {
            System.out.println("User with id: " + id + " does not exist!");
            return new ResponseEntity<>(Map.of("error", "This user does not exist"),
                    HttpStatus.NOT_FOUND);
        }
        // Return the user
        else {
            System.out.println("Returned advertisement of user " + user.getUsername());
            return new ResponseEntity<>(user,
                    HttpStatus.OK);
        }
    }

    @DeleteMapping("/advertisements/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable int id) {
        User u = userRepository.findById(id);

        // Check for existing user
        if (u == null) {
            System.out.println("User with id: " + id + " does not exist!");
            return new ResponseEntity<>(Map.of("error", "This user does not exist"),
                    HttpStatus.NOT_FOUND);
        } // Return the user
        else {
            // Delete all entries in the database
            u.setFullName(null);
            u.setPhotoURL(null);
            u.setTelephone(null);
            u.setAge(0);
            u.setGender(null);
            u.setRole(null);
            u.setStatus(null);
            u.setLanguage(null);
            u.setMaxRent(0);
            u.setPrefCity(null);
            u.setPrefGender(null);
            u.setPrefKitchen(null);
            u.setPrefShower(null);
            u.setPrefToilet(null);
            u.setPrefLiving(null);
            u.setPrefInternet(null);
            u.setPrefEnergyLabel(null);
            u.setPrefPets(null);
            u.setPrefSmokingInside(null);
            u.setPrefRoommates(null);
            u.setPrefDistanceToZipcode(null);
            u.setPrefZipcode(null);
            u.setMessage(null);

            // Set flag of adActive to false and save
            u.setAdActive(false);
            userRepository.save(u);

            // Show and return success message
            System.out.println("Advertisement deleted of user with id: " + id);
            return new ResponseEntity<>(Map.of(
                    "id", id,
                    "status", "This ad has been deleted with all it's data"),
                    HttpStatus.OK);
        }
    }
}
