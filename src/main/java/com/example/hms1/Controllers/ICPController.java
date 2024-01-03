package com.example.hms1.Controllers;

import com.example.hms1.Patients.names;
import com.example.hms1.utils.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ICPController extends ERPController {

    @FXML
    private TextField daysText;

    @Override
    public void onUpdate() {
        System.out.println("da");
        PatientList.clear();
        query = "select * from patientsIC";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("1");
            while (resultSet.next()) {
                PatientList.add(new names(resultSet.getInt("id"), resultSet.getString("name")));
                patientsTable.setItems(PatientList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCheck() {
        String namev1 = txtName.getText();
        PatientList.clear();
        query = "select * from patientsIC";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (namev1.equals(resultSet.getString("name")))
                    PatientList.add(new names(resultSet.getInt("id"), resultSet.getString("name")));
                patientsTable.setItems(PatientList);
            }
            textNume.setText("Nume: " + namev1);
            textPrice.setText("Price:");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCheckPrice() {

        String namev1 = txtName.getText();
        int price = 0;
        query = "select * from patientsIC ";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (namev1.equals(resultSet.getString("name")))
                    price += resultSet.getInt("price");
            }
            textNume.setText("Nume: " + namev1);
            textPrice.setText("Price: " + price);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCheckDays() {
        String namev1 = txtName.getText();
        int days = Integer.parseInt(daysText.getText());
        int price = 0;
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
                textNume.setText("Nume: " + namev1);
                textPrice.setText("Price: " + price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
