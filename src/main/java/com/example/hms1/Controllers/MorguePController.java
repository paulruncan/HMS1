package com.example.hms1.Controllers;

import com.example.hms1.Database;

public class MorguePController extends EmergencyRoomPublicController {

    @Override
    public void onCheck() {
        String namev1 = txtName.getText();
        PatientList.clear();
        Database.checkMorguePublic(query,preparedStatement,resultSet,namev1,PatientList,patientsTable);
    }

    @Override
    public void onUpdate() {
        System.out.println("da");
        PatientList.clear();
        Database.updateMorguePublic(query,preparedStatement,resultSet,PatientList,patientsTable);
    }
}

