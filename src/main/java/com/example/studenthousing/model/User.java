package com.example.studenthousing.model;

import com.example.studenthousing.validation.ValidEmail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name="user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;
    @Column(name="full_name")
    private String fullName;
    @Column(name="username", nullable = false, unique = true)
    private String username;
    @Column(name="password")
    private String password;
    @Transient
    private String matching_password;
    @Column(name="registration_date")
    private LocalDateTime registrationDate;
    @Column(name="photo_url")
    private String photoURL;
    @Column(name="email")
    @ValidEmail
    private String email;
    @Column(name="telephone")
    private String telephone;
    @Column(name="age")
    private int age;
    @Column(name="gender")
    private String gender;
    @Column(name="role")
    private String role;
    @Column(name="status")
    private String status;
    @Column
    private String language;
    @Column(name="max_rent")
    private int maxRent;
    @Column(name="pref_city")
    private String prefCity;
    @Column(name="pref_gender")
    private String prefGender;
    @Column(name="pref_kitchen")
    private String prefKitchen;
    @Column(name="pref_shower")
    private String prefShower;
    @Column(name="pref_toilet")
    private String prefToilet;
    @Column(name="pref_living")
    private String prefLiving;
    @Column(name="pref_internet")
    private String prefInternet;
    @Column(name="pref_energy_label")
    private String prefEnergyLabel;
    @Column(name="pref_pets")
    private String prefPets;
    @Column(name="pref_smoking_inside")
    private String prefSmokingInside;
    @Column(name="pref_roommates")
    private String prefRoommates;
    @Column(name="pref_distance_to_zipcode")
    private String prefDistanceToZipcode;
    @Column(name="pref_zipcode")
    private String prefZipcode;

    public User(int user_id, String fullName, String username, String password, LocalDateTime registrationDate,
                String photoURL, String email, String telephone, int age, String gender, String role,
                String status, String language, int maxRent, String prefCity, String prefGender,
                String prefKitchen, String prefShower, String prefToilet, String prefLiving, String prefInternet,
                String prefEnergyLabel, String prefPets, String prefSmokingInside, String prefRoommates,
                String prefDistanceToZipcode, String prefZipcode) {
        this.user_id = user_id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
        this.photoURL = photoURL;
        this.email = email;
        this.telephone = telephone;
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.status = status;
        this.language = language;
        this.maxRent = maxRent;
        this.prefCity = prefCity;
        this.prefGender = prefGender;
        this.prefKitchen = prefKitchen;
        this.prefShower = prefShower;
        this.prefToilet = prefToilet;
        this.prefLiving = prefLiving;
        this.prefInternet = prefInternet;
        this.prefEnergyLabel = prefEnergyLabel;
        this.prefPets = prefPets;
        this.prefSmokingInside = prefSmokingInside;
        this.prefRoommates = prefRoommates;
        this.prefDistanceToZipcode = prefDistanceToZipcode;
        this.prefZipcode = prefZipcode;
    }

    public User() {
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public String getMatching_password() {
        return matching_password;
    }

    public void setMatching_password(String matching_password) {
        this.matching_password = matching_password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getMaxRent() {
        return maxRent;
    }

    public void setMaxRent(int maxRent) {
        this.maxRent = maxRent;
    }

    public String getPrefCity() {
        return prefCity;
    }

    public void setPrefCity(String prefCity) {
        this.prefCity = prefCity;
    }

    public String getPrefGender() {
        return prefGender;
    }

    public void setPrefGender(String prefGender) {
        this.prefGender = prefGender;
    }

    public String getPrefKitchen() {
        return prefKitchen;
    }

    public void setPrefKitchen(String prefKitchen) {
        this.prefKitchen = prefKitchen;
    }

    public String getPrefShower() {
        return prefShower;
    }

    public void setPrefShower(String prefShower) {
        this.prefShower = prefShower;
    }

    public String getPrefToilet() {
        return prefToilet;
    }

    public void setPrefToilet(String prefToilet) {
        this.prefToilet = prefToilet;
    }

    public String getPrefLiving() {
        return prefLiving;
    }

    public void setPrefLiving(String prefLiving) {
        this.prefLiving = prefLiving;
    }

    public String getPrefInternet() {
        return prefInternet;
    }

    public void setPrefInternet(String prefInternet) {
        this.prefInternet = prefInternet;
    }

    public String getPrefEnergyLabel() {
        return prefEnergyLabel;
    }

    public void setPrefEnergyLabel(String prefEnergyLabel) {
        this.prefEnergyLabel = prefEnergyLabel;
    }

    public String getPrefPets() {
        return prefPets;
    }

    public void setPrefPets(String prefPets) {
        this.prefPets = prefPets;
    }

    public String getPrefSmokingInside() {
        return prefSmokingInside;
    }

    public void setPrefSmokingInside(String prefSmokingInside) {
        this.prefSmokingInside = prefSmokingInside;
    }

    public String getPrefRoommates() {
        return prefRoommates;
    }

    public void setPrefRoommates(String prefRoommates) {
        this.prefRoommates = prefRoommates;
    }

    public String getPrefDistanceToZipcode() {
        return prefDistanceToZipcode;
    }

    public void setPrefDistanceToZipcode(String prefDistanceToZipcode) {
        this.prefDistanceToZipcode = prefDistanceToZipcode;
    }

    public String getPrefZipcode() {
        return prefZipcode;
    }

    public void setPrefZipcode(String prefZipcode) {
        this.prefZipcode = prefZipcode;
    }
}
