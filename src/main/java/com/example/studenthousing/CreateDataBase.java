package com.example.studenthousing;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class CreateDataBase {


    // getters and setters

    public static void main(String[] args) throws ClassNotFoundException {
        String psw = "";
        try {
            try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Gebruiker\\IdeaProjects\\StudentHousing\\src\\main\\java\\com\\example\\studenthousing\\sqlww.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    psw = line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/student_housing", "root", psw);

            // create a statement object
            Statement stmt = conn.createStatement();

            // create property table if it does not exist
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

            // Create user table if not exists
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS user ("
                    + "user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + "full_name VARCHAR(255),"
                    + "username VARCHAR(255),"
                    + "password VARCHAR(255),"
                    + "registration_date VARCHAR(255),"
                    + "photo_url VARCHAR(255),"
                    + "email VARCHAR(255),"
                    + "telephone VARCHAR(255),"
                    + "age INT,"
                    + "gender VARCHAR(255),"
                    + "role VARCHAR(255),"
                    + "status VARCHAR(255),"
                    + "language VARCHAR(255),"
                    + "max_rent VARCHAR(255),"
                    + "pref_city VARCHAR(255),"
                    + "pref_gender VARCHAR(255),"
                    + "pref_kitchen VARCHAR(255),"
                    + "pref_shower VARCHAR(255),"
                    + "pref_toilet VARCHAR(255),"
                    + "pref_living VARCHAR(255),"
                    + "pref_internet VARCHAR(255),"
                    + "pref_energy_label VARCHAR(255),"
                    + "pref_pets VARCHAR(255),"
                    + "pref_smoking_inside VARCHAR(255),"
                    + "pref_roommates VARCHAR(255),"
                    + "pref_distance_to_zipcode VARCHAR(255),"
                    + "pref_zipcode VARCHAR(255))"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

