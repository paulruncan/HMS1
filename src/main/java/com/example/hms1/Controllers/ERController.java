package com.example.hms1.Controllers;

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
//import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ERController extends SceneController implements Initializable {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtMedicine;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtId;
    @FXML
    private TableView<patients> patientsTable;
    @FXML
    private TableColumn<patients, String> nameCol;

    @FXML
    private TableColumn<patients, String> medicineCol;
    @FXML
    private TableColumn<patients, Integer> priceCol;
    @FXML
    private TableColumn<patients, Integer> idCol;

    ObservableList<patients> PatientList = FXCollections.observableArrayList();
    int index = -1;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    //patients patient = null;
    @FXML
    public void goBack() {
        this.changeScene(SCENE_IDENTIFIER.MEDICPAGE);
    }

    @FXML
    public void onAdd() {
        connection = database.getConnection();
        String sql = "insert into patients(name,medicine,price) values(?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, txtName.getText());
            preparedStatement.setString(2, txtMedicine.getText());
            Integer valuePrice = Integer.parseInt(txtPrice.getText());
            preparedStatement.setInt(3, valuePrice);
            preparedStatement.execute();
            onUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                PatientList.add(new patients(resultSet.getString("name"), resultSet.getString("medicine"), resultSet.getInt("price"), resultSet.getInt("id")));
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
        medicineCol.setCellValueFactory(new PropertyValueFactory<>("medicine"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    @FXML
    public void onEdit() {
        try {
            connection = database.getConnection();
            String value1 = txtName.getText();
            String value2 = txtMedicine.getText();
            String value3 = txtPrice.getText();
            Integer intVal = Integer.parseInt(value3);
            String value4 = txtId.getText();
            Integer intProspect = Integer.parseInt(value4);
            String sql = "update patients set medicine = '" + value2 + "',price=" + value3 + ",name = '" + value1 + "' where id =" + intProspect;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
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
        connection = database.getConnection();
        String sql = "delete from patients where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(txtId.getText()));
            preparedStatement.execute();
            onUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
