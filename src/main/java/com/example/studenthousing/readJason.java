package com.example.studenthousing;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class readJason {
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
    private static int registrationCosts;
    private static String roommates;
    private static String shower;
    private static String smokingInside;
    private static String toilet;

    public static void main(String[] args) throws ClassNotFoundException, SQLException, JSONException, IOException {
        String psw = ""; //read password from external file
        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(readJason.class.getClassLoader().getResourceAsStream("sqlww.txt")))) {

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
            String filename = "properties.json";

            try (BufferedReader br = new BufferedReader(new InputStreamReader(readJason.class.getClassLoader().getResourceAsStream(filename)))) {

                String line; // read Json file and iterate over all lines
                Gson gson = new Gson();
                int count = 0;
                while ((line = br.readLine()) != null) {
                    count++;
                    JsonElement element = gson.fromJson(line, JsonElement.class);
                    JsonObject node = element.getAsJsonObject();
                    try { //get only needed keys
                        userId = count; //this is not in the Json file, but we use it in the database, so we add them here
                        externalId = node.get("externalId").getAsString();
                        // get integers and strings, check for exceptions using class getIntValue and class GetStrinGvalue
                        areaSqm = getIntValue(node, "areaSqm", 0); //if exception is thrown, set to 0
                        city = getStringValue(node, "city", "");  //if exception is thrown, set to ""
                        coverImageUrl = getStringValue(node, "coverImageUrl", "");
                        furnish = getStringValue(node, "furnish", "");
                        latitude = getStringValue(node, "latitude", "");
                        longitude = getStringValue(node, "longitude", "");
                        postalCode = getStringValue(node, "postalCode", "");
                        propertyType = getStringValue(node, "propertyType", "");
                        rawAvailability = getStringValue(node, "rawAvailability", "");
                        rent = getIntValue(node, "rent", 0);
                        rentDetail = getStringValue(node, "rentDetail", "");
                        title = getStringValue(node, "title", "");
                        additionalCosts = getIntValue(node, "additionalCosts", 0);
                        deposit = getIntValue(node, "deposit", 0);
                        descriptionNonTranslated = getStringValue(node, "descriptionNonTranslated", "");
                        descriptionTranslated = getStringValue(node, "descriptionTranslated", "");
                        energyLabel = getStringValue(node, "energyLabel", "");
                        gender = getStringValue(node, "gender", "");
                        internet = getStringValue(node, "internet", "");
                        isRoomActive = getStringValue(node, "isRoomActive", "");
                        kitchen = getStringValue(node, "kitchen", "");
                        living = getStringValue(node, "living", "");
                        matchAge = getStringValue(node, "matchAge", "");
                        matchCapacity = getStringValue(node, "matchCapacity", "");
                        matchGender = getStringValue(node, "matchGender", "");
                        matchLanguages = getStringValue(node, "matchLanguages", "");
                        matchStatus = getStringValue(node, "matchStatus", "");
                        pageDescription = getStringValue(node, "pageDescription", "");
                        pageTitle = getStringValue(node, "pageTitle", "");
                        pets = getStringValue(node, "pets", "");
                        registrationCosts = getIntValue(node, "registrationCosts", 0);
                        roommates = getStringValue(node, "roommates", "");
                        shower = getStringValue(node, "shower", "");
                        smokingInside = getStringValue(node, "smokingInside", "");
                        toilet = getStringValue(node, "toilet", "");


                    }
                    catch (NumberFormatException e) {
                        // Handle the exception, if not handled by getIntValue()
                        System.err.println("Error: Invalid number format");

                    }
                    catch (NullPointerException e) {
                        // handle the JsonNull exception here, if not handled by getIntValue() or getStringValue()
                        System.out.println("A JsonNull exception occurred: " + e.getMessage());

                    }

                    // create a connection to the database
                    String sql = "INSERT INTO property "
                            + "(external_id, user_id, area_sqm, city, cover_image_url, furnish, latitude, longitude, postal_code, property_type, raw_availability, rent, rent_detail, title, additional_costs, deposit, description_non_translated, description_translated, energy_label, gender, internet, is_room_active, kitchen, living, match_age, match_capacity, match_gender, match_languages, match_status, page_description, page_title, pets, registration_costs, roommates, shower, smoking_inside, toilet) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/student_housing", "root", psw);
                         PreparedStatement pstmt = conn.prepareStatement(sql)) { // set the values
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
                        pstmt.setInt(33, registrationCosts);
                        pstmt.setString(34, roommates);
                        pstmt.setString(35, shower);
                        pstmt.setString(36, smokingInside);
                        pstmt.setString(37, toilet);
                        pstmt.executeUpdate(); // fill the database
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
    private static String getStringValue(JsonObject node, String name, String defaultValue) { //handle JsonNull exceptions
        if (node.get(name) != null && !node.get(name).isJsonNull()) {
            return node.get(name).getAsString();
        }
        return defaultValue;
    }

    private static int getIntValue(JsonObject node, String name, int defaultValue) { // handle NumberFormat Exceptions (NA)
        if (node.get(name) != null && !node.get(name).isJsonNull()) {
            return node.get(name).getAsInt();
        }
        return defaultValue;
    }

}


