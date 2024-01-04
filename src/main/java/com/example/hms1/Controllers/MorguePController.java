package com.example.hms1.Controllers;

import com.example.hms1.Patients.Names;

public class MorguePController extends EmergencyRoomPublicController {

    @Override
    public void onCheck() {
        String namev1 = txtName.getText();
        PatientList.clear();
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

    @Override
    public void onUpdate() {
        System.out.println("da");
        PatientList.clear();
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

