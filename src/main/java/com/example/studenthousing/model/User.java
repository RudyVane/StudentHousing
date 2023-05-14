package com.example.studenthousing.model;

import java.time.LocalDateTime;

public class User {

    private int userID;
    private String fullName;
    private String username;
    private String password;
    private LocalDateTime registrationDate;
    private String photoURL;
    private String email;
    private String telephone;
    private int age;
    private String gender;
    private String role;
    private String status;
    private String language;
    private int maxRent;
    private String prefCity;
    private String prefGender;
    private String prefKitchen;
    private String prefShower;
    private String prefToilet;
    private String prefLiving;
    private String prefInternet;
    private String prefEnergyLabel;
    private String prefPets;
    private String prefSmokingInside;
    private String prefRoommates;
    private String prefDistanceToZipcode;
    private String prefZipcode;

    public User(int userID, String fullName, String username, String password, LocalDateTime registrationDate,
                String photoURL, String email, String telephone, int age, String gender, String role,
                String status, String language, int maxRent, String prefCity, String prefGender,
                String prefKitchen, String prefShower, String prefToilet, String prefLiving, String prefInternet,
                String prefEnergyLabel, String prefPets, String prefSmokingInside, String prefRoommates,
                String prefDistanceToZipcode, String prefZipcode) {
        this.userID = userID;
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


}
