package com.example.studenthousing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadDatabaseTest {
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
           // stmt.executeUpdate("SELECT * FROM property("





        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}