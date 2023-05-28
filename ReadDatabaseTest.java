package com.example.studenthousing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;

public class ReadDatabaseTest {
    public static void main(String[] args) {
        String psw = "";

        try {
            // Read the password from the file
            try (InputStream inputStream = ReadDatabaseTest.class.getResourceAsStream("/sqlww.txt")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = br.readLine()) != null) {
                    psw = line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/student_housing", "root", psw);

            // Create a statement object
            Statement stmt = conn.createStatement();

            // Execute the query to fetch the first 10 properties
            ResultSet rs = stmt.executeQuery("SELECT * FROM property LIMIT 10");

            // Process the result set
            while (rs.next()) {
                // Retrieve the property data from the result set
                int id = rs.getInt("id");
                String externalId = rs.getString("external_id");
                // Retrieve other properties as needed

                // Print or process the property data
                System.out.println("Property ID: " + id);
                System.out.println("External ID: " + externalId);
                // Print or process other properties as needed
            }

            // Close the result set, statement, and connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
