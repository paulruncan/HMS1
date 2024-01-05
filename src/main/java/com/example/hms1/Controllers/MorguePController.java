package com.example.hms1.Controllers;

import com.example.hms1.Patients.Names;
import com.example.hms1.database;

public class MorguePController extends EmergencyRoomPublicController {

    @Override
    public void onCheck() {
        String namev1 = txtName.getText();
        PatientList.clear();
        database.checkMorguePublic(query,preparedStatement,connection,resultSet,namev1,PatientList,patientsTable);
    }

    @Override
    public void onUpdate() {
        System.out.println("da");
        PatientList.clear();
        database.updateMorguePublic(query,preparedStatement,connection,resultSet,PatientList,patientsTable);
    }
}

