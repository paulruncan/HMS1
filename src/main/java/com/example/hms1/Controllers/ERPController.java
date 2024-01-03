package com.example.hms1.Controllers;

import com.example.hms1.Patients.names;
import com.example.hms1.database;
import com.example.hms1.Patients.patients;
import com.example.hms1.utils.SceneController;
import com.example.hms1.utils.enums.SCENE_IDENTIFIER;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
//import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ERPController extends SceneController implements Initializable {
    @FXML
    protected Text textNume;
    @FXML
    protected Text textPrice;
    @FXML
    protected TextField txtName;
    @FXML
    protected TextField txtId;
    @FXML
    protected TableView<names> patientsTable;
    @FXML
    private TableColumn<names, String> nameCol;
    @FXML
    private TableColumn<names, Integer> idCol;

    ObservableList<names> PatientList = FXCollections.observableArrayList();
    int index = -1;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    //patients patient = null;


    public Text getTextNume() {
        return textNume;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public void setPatientsTable( TableView<names> patientsTable ) {
        this.patientsTable = patientsTable;
    }

    @FXML
    public void goBack() {
        this.changeScene(SCENE_IDENTIFIER.PUBLIV);
    }

    @FXML
    public void onUpdate() {
        System.out.println("da");
        PatientList.clear();
        query = "select * from patients";
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
    public void initialize( URL url, ResourceBundle resourceBundle ) {
        loadDate();
    }

    public void loadDate() {
        connection = database.getConnection();
        onUpdate();
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    @FXML
    public void getSelected() {
        index = patientsTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtName.setText(nameCol.getCellData(index));
        txtId.setText(idCol.getCellData(index).toString());
    }

    public void onCheckPrice() {
        String namev1 = txtName.getText();
        int price = 0;
        query = "select * from patients";
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

    public void onCheck() {
        String namev1 = txtName.getText();
        PatientList.clear();
        query = "select * from patients";
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
}
