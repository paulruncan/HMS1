package com.example.hms1;

import com.example.hms1.Patients.patients;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class database {

    // Connection databaseLink = null;
    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/hospital";
        String username = "postgres";
        String password = "";

        try {
            Connection databaseLink = DriverManager.getConnection(url, username, password);
            System.out.println("da");
            return databaseLink;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //   return databaseLink;
    }

    public static ObservableList<patients> getDataPatients() {
        Connection conn = getConnection();
        ObservableList<patients> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from patients");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new patients(rs.getString("name"), rs.getString("medicine"), Integer.parseInt(rs.getString("price")), Integer.parseInt(rs.getString("id"))));

            }
        } catch (Exception e) {

        }
        return null;
    }
}
