package com.example.hms1.Controllers;

import com.example.hms1.Database;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class IntensiveCarePublicController extends EmergencyRoomPublicController {

    @FXML
    private TextField daysText;

    @Override
    public void onUpdate() {
        System.out.println("da");
        PatientList.clear();
        Database.updateICP(query,preparedStatement,resultSet,PatientList,patientsTable);
    }

    @Override
    public void onCheck() {
        String namev1 = txtName.getText();
        PatientList.clear();
        try {
            Database.checkICP(query,preparedStatement,resultSet,namev1,PatientList,patientsTable);
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
        try {
            int price1= Database.checkPriceICP(query,preparedStatement,resultSet,namev1,price);
            textNume.setText("Nume: " + namev1);
            textPrice.setText("Price: " + price1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCheckDays() {
        String namev1 = txtName.getText();
        int days = Integer.parseInt(daysText.getText());
        int price = 0;
        try {
            int price1 = Database.checkDaysICP(query,preparedStatement,resultSet,namev1,price,days);
                textNume.setText("Nume: " + namev1);
                textPrice.setText("Price: " + price1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
