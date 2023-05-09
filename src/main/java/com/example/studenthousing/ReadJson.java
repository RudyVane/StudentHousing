package com.example.studenthousing;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReadJson {
    private static String externalId;
    private static int userId;
    private static int areaSqm;
    private static String city;
    private static String coverImageUrl;

    private static String furnish;

    private static String latitude;
    private static String longitude;
    private static String postalCode;

    private static String propertyType;
    private static String rawAvailability;
    private static int rent;
    private static String rentDetail;
    private static String title;

    private static int additionalCosts;

    private static int deposit;

    private static String descriptionNonTranslated;

    private static String descriptionTranslated;


    private static String energyLabel;
    private static String gender;
    private static String internet;
    private static String isRoomActive;
    private static String kitchen;
    private static String living;
    private static String matchAge;

    private static String matchCapacity;
    private static String matchGender;
    private static String matchLanguages;
    private static String matchStatus;
    private static String pageDescription;
    private static String pageTitle;
    private static String pets;
    private static int registrationCost;
    private static String roommates;
    private static String shower;
    private static String smokingInside;
    private static String toilet;

    public static void main(String[] args) throws ClassNotFoundException, SQLException, JSONException, IOException {
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

            // Specify the path of the JSON file
            String filePath = "C:\\Users\\Gebruiker\\IdeaProjects\\StudentHousing\\src\\main\\java\\com\\example\\studenthousing\\properties.json";

            try {

                // Read the JSON file into a JsonNode object
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(new File(filePath));
                int count = 0;
                for (JsonNode node : jsonNode) {
                    count++;
                    externalId = jsonNode.get("externalId").asText();
                    userId = count;
                    areaSqm = jsonNode.get("areaSqm").asInt();
                    city = jsonNode.get("city").asText();
                    coverImageUrl = jsonNode.get("coverImageUrl").asText();
                    furnish = jsonNode.get("furnish").asText();
                    latitude = jsonNode.get("latitude").asText();
                    longitude = jsonNode.get("longitude").asText();
                    postalCode = jsonNode.get("postalCode").asText();
                    propertyType = jsonNode.get("propertyType").asText();
                    rawAvailability = jsonNode.get("rawAvailability").asText();
                    rent = jsonNode.get("rent").asInt();
                    rentDetail = jsonNode.get("rentDetail").asText();
                    title = jsonNode.get("title").asText();
                    additionalCosts = jsonNode.get("additionalCosts").asInt();
                    deposit = jsonNode.get("deposit").asInt();
                    descriptionNonTranslated = jsonNode.get("descriptionNonTranslated").asText();
                    descriptionTranslated = jsonNode.get("descriptionTranslated").asText();
                    energyLabel = jsonNode.get("energyLabel").asText();
                    gender = jsonNode.get("gender").asText();
                    internet = jsonNode.get("internet").asText();
                    isRoomActive = jsonNode.get("isRoomActive").asText();
                    kitchen = jsonNode.get("kitchen").asText();
                    living = jsonNode.get("living").asText();
                    matchAge = jsonNode.get("matchAge").asText();
                    matchCapacity = jsonNode.get("matchCapacity").asText();
                    matchGender = jsonNode.get("matchGender").asText();
                    matchLanguages = jsonNode.get("matchLanguages").asText();
                    matchStatus = jsonNode.get("matchStatus").asText();
                    pageDescription = jsonNode.get("pageDescription").asText();
                    pageTitle = jsonNode.get("pageTitle").asText();
                    pets = jsonNode.get("pets").asText();
                    registrationCost = jsonNode.get("registrationCost").asInt();
                    roommates = jsonNode.get("roommates").asText();
                    shower = jsonNode.get("shower").asText();
                    smokingInside = jsonNode.get("smokingInside").asText();
                    toilet = jsonNode.get("toilet").asText();


                    // create a connection to the database
                    String sql = "INSERT INTO property "
                            + "(external_id, user_id, area_sqm, city, cover_image_url, furnish, latitude, longitude, postal_code, property_type, raw_availability, rent, rent_detail, title, additional_costs, deposit, description_non_translated, description_translated, energy_label, gender, internet, is_room_active, kitchen, living, match_age, match_capacity, match_gender, match_languages, match_status, page_description, page_title, pets, registration_cost, roommates, shower, smoking_inside, toilet) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/student_housing", "root", psw);
                         PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, externalId);
                        pstmt.setInt(2, userId);
                        pstmt.setInt(3, areaSqm);
                        pstmt.setString(4, city);
                        pstmt.setString(5, coverImageUrl);
                        pstmt.setString(6, furnish);
                        pstmt.setString(7, latitude);
                        pstmt.setString(8, longitude);
                        pstmt.setString(9, postalCode);
                        pstmt.setString(10, propertyType);
                        pstmt.setString(11, rawAvailability);
                        pstmt.setInt(12, rent);
                        pstmt.setString(13, rentDetail);
                        pstmt.setString(14, title);
                        pstmt.setInt(15, additionalCosts);
                        pstmt.setInt(16, deposit);
                        pstmt.setString(17, descriptionNonTranslated);
                        pstmt.setString(18, descriptionTranslated);
                        pstmt.setString(19, energyLabel);
                        pstmt.setString(20, gender);
                        pstmt.setString(21, internet);
                        pstmt.setString(22, isRoomActive);
                        pstmt.setString(23, kitchen);
                        pstmt.setString(24, living);
                        pstmt.setString(25, matchAge);
                        pstmt.setString(26, matchCapacity);
                        pstmt.setString(27, matchGender);
                        pstmt.setString(28, matchLanguages);
                        pstmt.setString(29, matchStatus);
                        pstmt.setString(30, pageDescription);
                        pstmt.setString(31, pageTitle);
                        pstmt.setString(32, pets);
                        pstmt.setInt(33, registrationCost);
                        pstmt.setString(34, roommates);
                        pstmt.setString(35, shower);
                        pstmt.setString(36, smokingInside);
                        pstmt.setString(37, toilet);
                        pstmt.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}


