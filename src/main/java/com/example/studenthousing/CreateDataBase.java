package com.example.studenthousing;
import java.sql.*;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class CreateDataBase {

}


class Property {
    private String externalId;

    private int areaSqm;
    private String city;
    private String coverImageUrl;

    private String furnish;

    private String latitude;
    private String longitude;
    private String postalCode;

    private String propertyType;
    private String rawAvailability;
    private int rent;
    private String rentDetail;


    private String title;

    private int additionalCosts;

    private int deposit;

    private String descriptionNonTranslated;

    private String descriptionTranslated;


    private String energyLabel;
    private String gender;
    private String internet;
    private String isRoomActive;
    private String kitchen;
    private String living;
    private String matchAge;

    private String matchCapacity;
    private String matchGender;
    private String matchLanguages;
    private String matchStatus;
    private String pageDescription;
    private String pageTitle;
    private String pets;
    private int registrationCost;
    private String roommates;
    private String shower;
    private String smokingInside;
    private String toilet;

    // getters and setters

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            // load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/student_housing", "root", "");

            // create a statement object
            Statement stmt = conn.createStatement();

            // create the table if it does not exist
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS property ("
                    + "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + "external_id VARCHAR(255),"
                    + "user_id INT,"
                    + "area_sqm INT,"
                    + "city VARCHAR(255),"
                    + "cover_image_url VARCHAR(255),"
                    + "furnish VARCHAR(255),"
                    + "latitude VARCHAR(255),"
                    + "longitude VARCHAR(255),"
                    + "postal_code VARCHAR(255),"
                    + "property_type VARCHAR(255),"
                    + "raw_availability VARCHAR(255),"
                    + "rent INT,"
                    + "rent_detail VARCHAR(255),"
                    + "title VARCHAR(255),"
                    + "additional_costs INT,"
                    + "deposit INT,"
                    + "description_non_translated TEXT,"
                    + "description_translated TEXT,"
                    + "energy_label VARCHAR(255),"
                    + "gender VARCHAR(255),"
                    + "internet VARCHAR(255),"
                    + "is_room_active VARCHAR(255),"
                    + "kitchen VARCHAR(255),"
                    + "living VARCHAR(255),"
                    + "match_age VARCHAR(255),"
                    + "match_capacity VARCHAR(255),"
                    + "match_gender VARCHAR(255),"
                    + "match_languages VARCHAR(255),"
                    + "match_status VARCHAR(255),"
                    + "page_description TEXT,"
                    + "page_title VARCHAR(255),"
                    + "pets VARCHAR(255),"
                    + "registration_cost INT,"
                    + "roommates VARCHAR(255),"
                    + "shower VARCHAR(255),"
                    + "smoking_inside VARCHAR(255),"
                    + "toilet VARCHAR(255))"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

