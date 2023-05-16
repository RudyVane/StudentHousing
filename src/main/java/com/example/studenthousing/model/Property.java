package com.example.studenthousing.model;

import javax.persistence.*;

@Entity
@Table(name="property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="external_id")
    private String externalId;
    @Column(name="user_id")
    private int userId;
    @Column(name="area_sqm")
    private int areaSqm;
    @Column(name="city")
    private String city;
    @Column(name="cover_image_url")
    private String coverImageUrl;
    @Column(name="furnish")

    private String furnish;
    @Column(name="latitude")
    private String latitude;
    @Column(name="longitude")
    private String longitude;
    @Column(name="postal_code")
    private String postalCode;
    @Column(name="property_type")
    private String propertyType;
    @Column(name="raw_availability")
    private String rawAvailability;
    @Column(name="rent")
    private int rent;
    @Column(name="rent_detail")
    private String rentDetail;
    @Column(name="title")
    private String title;
    @Column(name="additional_costs")
    private int additionalCosts;
    @Column(name="deposit")
    private int deposit;
    @Column(name="description_non_translated")
    private String descriptionNonTranslated;
    @Column(name="description_translated")

    private String descriptionTranslated;
    @Column(name="energy_label")
    private String energyLabel;
    @Column(name="gender")
    private String gender;
    @Column(name="internet")
    private String internet;
    @Column(name="is_room_active")
    private String isRoomActive;
    @Column(name="kitchen")
    private String kitchen;
    @Column(name="living")
    private String living;
    @Column(name="match_age")
    private String matchAge;
    @Column(name="match_capacity")
    private String matchCapacity;
    @Column(name="match_gender")
    private String matchGender;
    @Column(name="match_languages")
    private String matchLanguages;
    @Column(name="match_status")
    private String matchStatus;
    @Column(name="page_description")
    private String pageDescription;
    @Column(name="page_title")
    private String pageTitle;
    @Column(name="pets")
    private String pets;
    @Column(name="registration_costs")
    private int registrationCost;
    @Column(name="roommates")
    private String roommates;
    @Column(name="shower")
    private String shower;
    @Column(name="smoking_inside")
    private String smokingInside;
    @Column(name="toilet")
    private String toilet;
    public Property() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAreaSqm() {
        return areaSqm;
    }

    public void setAreaSqm(int areaSqm) {
        this.areaSqm = areaSqm;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getFurnish() {
        return furnish;
    }

    public void setFurnish(String furnish) {
        this.furnish = furnish;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getRawAvailability() {
        return rawAvailability;
    }

    public void setRawAvailability(String rawAvailability) {
        this.rawAvailability = rawAvailability;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getRentDetail() {
        return rentDetail;
    }

    public void setRentDetail(String rentDetail) {
        this.rentDetail = rentDetail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAdditionalCosts() {
        return additionalCosts;
    }

    public void setAdditionalCosts(int additionalCosts) {
        this.additionalCosts = additionalCosts;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getDescriptionNonTranslated() {
        return descriptionNonTranslated;
    }

    public void setDescriptionNonTranslated(String descriptionNonTranslated) {
        this.descriptionNonTranslated = descriptionNonTranslated;
    }

    public String getDescriptionTranslated() {
        return descriptionTranslated;
    }

    public void setDescriptionTranslated(String descriptionTranslated) {
        this.descriptionTranslated = descriptionTranslated;
    }

    public String getEnergyLabel() {
        return energyLabel;
    }

    public void setEnergyLabel(String energyLabel) {
        this.energyLabel = energyLabel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getIsRoomActive() {
        return isRoomActive;
    }

    public void setIsRoomActive(String isRoomActive) {
        this.isRoomActive = isRoomActive;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String getLiving() {
        return living;
    }

    public void setLiving(String living) {
        this.living = living;
    }

    public String getMatchAge() {
        return matchAge;
    }

    public void setMatchAge(String matchAge) {
        this.matchAge = matchAge;
    }

    public String getMatchCapacity() {
        return matchCapacity;
    }

    public void setMatchCapacity(String matchCapacity) {
        this.matchCapacity = matchCapacity;
    }

    public String getMatchGender() {
        return matchGender;
    }

    public void setMatchGender(String matchGender) {
        this.matchGender = matchGender;
    }

    public String getMatchLanguages() {
        return matchLanguages;
    }

    public void setMatchLanguages(String matchLanguages) {
        this.matchLanguages = matchLanguages;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getPageDescription() {
        return pageDescription;
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPets() {
        return pets;
    }

    public void setPets(String pets) {
        this.pets = pets;
    }

    public int getRegistrationCost() {
        return registrationCost;
    }

    public void setRegistrationCost(int registrationCost) {
        this.registrationCost = registrationCost;
    }

    public String getRoommates() {
        return roommates;
    }

    public void setRoommates(String roommates) {
        this.roommates = roommates;
    }

    public String getShower() {
        return shower;
    }

    public void setShower(String shower) {
        this.shower = shower;
    }

    public String getSmokingInside() {
        return smokingInside;
    }

    public void setSmokingInside(String smokingInside) {
        this.smokingInside = smokingInside;
    }

    public String getToilet() {
        return toilet;
    }

    public void setToilet(String toilet) {
        this.toilet = toilet;
    }

    @Override
    public String toString() {
        return "Property [id=" + id + ", external_id=" + externalId + "]";
    }


}
