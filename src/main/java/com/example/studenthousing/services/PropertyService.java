package com.example.studenthousing.services;

import com.example.studenthousing.model.Property;
import com.example.studenthousing.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    private EntityManagerFactory entityManagerFactory;
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }
    public PropertyService() {
        // Create the EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("StudentHousing");
    }
    public Page<Property> getProperties(Pageable pageable) {
        return propertyRepository.findAll(pageable);
    }

    public Page<Property> getPropertiesByRentRange(Integer minRent, Integer maxRent, Pageable pageable) {
        return propertyRepository.findByRentBetween(minRent, maxRent, pageable);
    }
    public Page<Property> getPropertiesByCityAndRentRange(String city, Integer minRent, Integer maxRent, Pageable pageable) {
        return propertyRepository.findByCityAndRentBetween(city, minRent, maxRent, pageable);
    }





    public Property newProperty(int id, String externalId, int areaSqm, String city, String coverImageUrl, String furnish, String latitude, String longitude, String postalCode, String propertyType, String rawAvailability, int rent, String rentDetail, String title, int additionalCosts, int deposit, String descriptionNonTranslated, String descriptionTranslated, String energyLabel, String gender, String internet, String isRoomActive, String kitchen, String living, String matchAge, String matchCapacity, String matchGender, String matchLanguages, String matchStatus, String pageDescription, String pageTitle, String pets, int registrationCost, String roommates, String shower, String smokingInside, String toilet) {
        Property p = new Property();
        p.setId(id);
        p.setExternalId(externalId);
        p.setAreaSqm(areaSqm);
        p.setCity(city);
        p.setCoverImageUrl(coverImageUrl);
        p.setFurnish(furnish);
        p.setLatitude(latitude);
        p.setLongitude(longitude);
        p.setPostalCode(postalCode);
        p.setPropertyType(propertyType);
        p.setRawAvailability(rawAvailability);
        p.setRent(rent);
        p.setRentDetail(rentDetail);
        p.setTitle(title);
        p.setAdditionalCosts(additionalCosts);
        p.setDeposit(deposit);
        p.setDescriptionNonTranslated(descriptionNonTranslated);
        p.setDescriptionTranslated(descriptionTranslated);
        p.setEnergyLabel(energyLabel);
        p.setGender(gender);
        p.setInternet(internet);
        p.setIsRoomActive(isRoomActive);
        p.setKitchen(kitchen);
        p.setLiving(living);
        p.setMatchAge(matchAge);
        p.setMatchCapacity(matchCapacity);
        p.setMatchGender(matchGender);
        p.setMatchLanguages(matchLanguages);
        p.setMatchStatus(matchStatus);
        p.setPageDescription(pageDescription);
        p.setPageTitle(pageTitle);
        p.setPets(pets);
        p.setRegistrationCost(registrationCost);
        p.setRoommates(roommates);
        p.setShower(shower);
        p.setSmokingInside(smokingInside);
        p.setToilet(toilet);

        return propertyRepository.save(p);
    }
    public Page<Property> getAllProperties(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return propertyRepository.findAll(pageable);
    }
    public Page<Property> getAllProperties(Pageable pageable) {
        return propertyRepository.findAll(pageable);
    }


    public Optional<Property> getPropertyById(int propertyId) {
        return propertyRepository.findById(propertyId);
    }
    public List<String> getDistinctCities() {
        return propertyRepository.findAllDistinctCities();
    }
    public Page<Property> getPropertiesByCity(String city, Pageable pageable) {
        return propertyRepository.findByCity(city, pageable);
    }

    public Page<Property> getPropertiesById(int id) {
        Pageable pageable = PageRequest.of(0, 1000);
        return propertyRepository.findById(id,pageable);
    }
    public void test() {
        // Implementation of the test method
        System.out.println("Testing PropertyService");
    }


}
