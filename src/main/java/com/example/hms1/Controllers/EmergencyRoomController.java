package com.example.hms1.Controllers;

import com.example.hms1.Patients.Patients;
import com.example.hms1.Database;
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
//import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class EmergencyRoomController extends SceneController implements Initializable {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtMedicine;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtId;
    @FXML
    private TableView<Patients> patientsTable;
    @FXML
    private TableColumn<Patients, String> nameCol;

    @FXML
    private TableColumn<Patients, String> medicineCol;
    @FXML
    private TableColumn<Patients, Integer> priceCol;
    @FXML
    private TableColumn<Patients, Integer> idCol;

    ObservableList<Patients> PatientList = FXCollections.observableArrayList();
    int index = -1;
    String query = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    //Patients patient = null;
    @FXML
    public void goBack() {
        this.changeScene(SCENE_IDENTIFIER.MEDICPAGE);
    }

    @FXML
    public void onAdd() {
        Database.addER(txtName,txtMedicine,txtPrice);
        onUpdate();
    }

    @FXML
    public void onUpdate() {
        System.out.println("da");
        PatientList.clear();
        Database.updateER(query,preparedStatement,resultSet,PatientList,patientsTable);
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle ) {
        Database.loadDateER(nameCol,medicineCol,priceCol,idCol);
        onUpdate();
    }

    @FXML
    public void onEdit() {
        try {
            Database.editER(txtName, txtMedicine, txtPrice, txtId, preparedStatement);
            onUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void getSelected() {
        index = patientsTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtName.setText(nameCol.getCellData(index));
        txtMedicine.setText(medicineCol.getCellData(index));
        txtPrice.setText(priceCol.getCellData(index).toString());
        txtId.setText(idCol.getCellData(index).toString());
    }

    public void onRemove( ActionEvent actionEvent ) {

        Database.removeER(preparedStatement,txtId);
        onUpdate();
    }
}
