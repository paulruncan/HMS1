package com.example.hms1;

import com.example.hms1.Patients.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.*;

public class database {

    // Connection databaseLink = null;
    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/hospital";
        String username = "postgres";
        String password = "cenago11";

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

    public static ObservableList<Patients> getDataPatients() {
        Connection conn = getConnection();
        ObservableList<Patients> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from patients");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Patients(rs.getString("name"), rs.getString("medicine"), Integer.parseInt(rs.getString("price")), Integer.parseInt(rs.getString("id"))));

            }
        } catch (Exception e) {

        }
        return null;
    }

    public static void addER( Connection connection, TextField txtName, TextField txtMedicine, TextField txtPrice ) {
        PreparedStatement preparedStatement;
        String sql = "insert into patients(name,medicine,price) values(?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, txtName.getText());
            preparedStatement.setString(2, txtMedicine.getText());
            Integer valuePrice = Integer.parseInt(txtPrice.getText());
            preparedStatement.setInt(3, valuePrice);
            preparedStatement.execute();
            // onUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateER( String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, ObservableList<Patients> PatientList, TableView<Patients> patientsTable ) {
        query = "select * from patients";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("1");
            while (resultSet.next()) {
                PatientList.add(new Patients(resultSet.getString("name"), resultSet.getString("medicine"), resultSet.getInt("price"), resultSet.getInt("id")));
                patientsTable.setItems(PatientList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editER( Connection connection, TextField txtName, TextField txtMedicine, TextField txtPrice, TextField txtId, PreparedStatement preparedStatement ) {
        try {
            String value1 = txtName.getText();
            String value2 = txtMedicine.getText();
            String value3 = txtPrice.getText();
            Integer intVal = Integer.parseInt(value3);
            String value4 = txtId.getText();
            Integer intProspect = Integer.parseInt(value4);
            String sql = "update patients set medicine = '" + value2 + "',price=" + value3 + ",name = '" + value1 + "' where id =" + intProspect;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeER( PreparedStatement preparedStatement, Connection connection, TextField txtId ) {
        String sql = "delete from patients where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(txtId.getText()));
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeAdmin( PreparedStatement preparedStatement, Connection connection, TextField txtId ) {
        String sql = "delete from accounts where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(txtId.getText()));
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addAdmin( PreparedStatement preparedStatement, Connection connection, TextField txtUsername, TextField txtPassword ) {
        String sql = "insert into accounts(username,passwords) values(?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, txtUsername.getText());
            preparedStatement.setString(2, txtPassword.getText());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateAdmin( String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, ObservableList<Medics> medicsList, TableView<Medics> medicsTable ) {
        query = "select * from accounts";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("1");
            while (resultSet.next()) {
                medicsList.add(new Medics(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("passwords")));
                medicsTable.setItems(medicsList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editAdmin( PreparedStatement preparedStatement, Connection connection, TextField txtUsername, TextField txtId, TextField txtPassword ) {
        try {
            String value1 = txtUsername.getText();
            String value2 = txtId.getText();
            Integer intId = Integer.parseInt(value2);
            String value3 = txtPassword.getText();
            String sql = "update accounts set passwords = '" + value3 + "',username = '" + value1 + "' where id =" + intId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatePublicER( String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, ObservableList<Names> PatientList, TableView<Names> patientsTable ) {
        query = "select * from patients";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("1");
            while (resultSet.next()) {
                PatientList.add(new Names(resultSet.getInt("id"), resultSet.getString("name")));
                patientsTable.setItems(PatientList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int checkPricePublicER( String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, int price, String namev1 ) {
        query = "select * from patients";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (namev1.equals(resultSet.getString("name")))
                    price += resultSet.getInt("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    public static void checkPublicER( String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, String namev1, ObservableList<Names> PatientList, TableView<Names> patientsTable ) {
        query = "select * from patients";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (namev1.equals(resultSet.getString("name")))
                    PatientList.add(new Names(resultSet.getInt("id"), resultSet.getString("name")));
                patientsTable.setItems(PatientList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addIC( PreparedStatement preparedStatement, Connection connection, TextField txtName, TextField txtMedicine, TextField txtPrice, TextField txtProspect, Text txt ) {
        String sql = "insert into patientsIC(name,medicine,price,prospect) values(?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, txtName.getText());
            preparedStatement.setString(2, txtMedicine.getText());
            Integer valuePrice = Integer.parseInt(txtPrice.getText());
            Integer valueProspect = Integer.parseInt(txtProspect.getText());
            preparedStatement.setInt(3, valuePrice);
            preparedStatement.setInt(4, valueProspect);
            if (valueProspect == 1 || valueProspect == 101 || valueProspect == 111)
                preparedStatement.execute();
            else txt.setText("Invalid Prospect");
            //onUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateIC( String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, ObservableList<PatientsIntensiveCare> PatientICList, TableView<PatientsIntensiveCare> patientsICTable ) {
        query = "select * from patientsic";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("1");
            while (resultSet.next()) {
                PatientICList.add(new PatientsIntensiveCare(resultSet.getString("name"), resultSet.getString("medicine"), resultSet.getInt("price"), resultSet.getInt("prospect"), resultSet.getInt("id")));
                patientsICTable.setItems(PatientICList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeIC( PreparedStatement preparedStatement, Connection connection, TextField txtId ) {
        String sql = "delete from patientsIC where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(txtId.getText()));
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editIC( PreparedStatement preparedStatement, Connection connection, Text txt, TextField txtName, TextField txtMedicine, TextField txtPrice, TextField txtProspect, TextField txtId ) {
        try {
            String value1 = txtName.getText();
            String value2 = txtMedicine.getText();
            String value3 = txtPrice.getText();
            Integer intVal = Integer.parseInt(value3);
            String value4 = txtProspect.getText();
            Integer intProspect = Integer.parseInt(value4);
            String value5 = txtId.getText();
            Integer intId = Integer.parseInt(value5);
            String sql = "update patientsIC set medicine = '" + value2 + "',price=" + value3 + ",prospect=" + value4 + ", name = '" + value1 + "' where id =" + intId;
            preparedStatement = connection.prepareStatement(sql);
            if (intProspect == 1 || intProspect == 101 || intProspect == 111)
                preparedStatement.execute();
            else txt.setText("invalid prospect(edit)");
            // onUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateICP(String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, ObservableList<Names>PatientList, TableView<Names>patientsTable){
        query = "select * from patientsIC";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("1");
            while (resultSet.next()) {
                PatientList.add(new Names(resultSet.getInt("id"), resultSet.getString("name")));
                patientsTable.setItems(PatientList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkICP(String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, String namev1, ObservableList<Names>PatientList, TableView<Names>patientsTable){
        query = "select * from patientsIC";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (namev1.equals(resultSet.getString("name")))
                    PatientList.add(new Names(resultSet.getInt("id"), resultSet.getString("name")));
                patientsTable.setItems(PatientList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int checkPriceICP(String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, String namev1, int price){
        query = "select * from patientsIC ";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (namev1.equals(resultSet.getString("name")))
                    price += resultSet.getInt("price");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    public static int checkDaysICP(String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, String namev1, int price , int days){
        query = "select * from patientsIC";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (namev1.equals(resultSet.getString("name"))) {
                    if (resultSet.getInt("prospect") == 1)
                        price += days * 1 * resultSet.getInt("price");
                    else if (resultSet.getInt("prospect") == 101)
                        price += days * 2 * resultSet.getInt("price");
                    else price += days * 3 * resultSet.getInt("price");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }
    public static void addMorgue(PreparedStatement preparedStatement, Connection connection, TextField txtName, TextField txtOrgan){
        String sql = "insert into morgue(name,organ) values(?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, txtName.getText());
            preparedStatement.setString(2, txtOrgan.getText());
            preparedStatement.execute();
            //onUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateMorgue(String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, ObservableList<Bodies>bodiesList, TableView<Bodies>bodiesTable){
        query = "select * from morgue";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("1");
            while (resultSet.next()) {
                bodiesList.add(new Bodies(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("organ")));
                bodiesTable.setItems(bodiesList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void removeMorgue(PreparedStatement preparedStatement, Connection connection, TextField txtId){
        String sql = "delete from morgue where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(txtId.getText()));
            preparedStatement.execute();
            //onUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void editMorgue(PreparedStatement preparedStatement, Connection connection, TextField txtName, TextField txtId, TextField txtOrgan){
        try {
            String value1 = txtName.getText();
            String value2 = txtId.getText();
            Integer intId = Integer.parseInt(value2);
            String value3 = txtOrgan.getText();
            String sql = "update morgue set organ = '" + value3 + "',name = '" + value1 + "' where id =" + intId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkMorgue(String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, String namev1, String organv1, ObservableList<Bodies>bodiesList, TableView<Bodies> bodiesTable){
        query = "select * from morgue";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("1");
            while (resultSet.next()) {
                if (organv1.equals(resultSet.getString("organ")) || namev1.equals(resultSet.getString("name")))
                    bodiesList.add(new Bodies(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("organ")));
                bodiesTable.setItems(bodiesList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkMorguePublic(String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, String namev1, ObservableList<Names>PatientList,TableView<Names>patientsTable){
        query = "select * from morgue";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (namev1.equals(resultSet.getString("name")))
                    PatientList.add(new Names(resultSet.getInt("id"), resultSet.getString("name")));
                patientsTable.setItems(PatientList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateMorguePublic(String query, PreparedStatement preparedStatement, Connection connection, ResultSet resultSet, ObservableList<Names>PatientList, TableView<Names>patientsTable){
        query = "select * from morgue";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("1");
            while (resultSet.next()) {
                PatientList.add(new Names(resultSet.getInt("id"), resultSet.getString("name")));
                patientsTable.setItems(PatientList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
