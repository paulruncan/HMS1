package com.example.hms1.Controllers;

import com.example.hms1.Patients.bodies;
import com.example.hms1.Patients.medics;
import com.example.hms1.database;
import com.example.hms1.utils.SceneController;
import com.example.hms1.utils.enums.SCENE_IDENTIFIER;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AdminController extends SceneController implements Initializable {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TableView<medics> medicsTable;
    @FXML
    private TableColumn<medics, String> usernameCol;
    @FXML
    private TableColumn<medics, String> passwordCol;
    @FXML
    private TableColumn<medics, Integer> idCol;


    ObservableList<medics> medicsList = FXCollections.observableArrayList();
    int index = -1;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle ) {
        loadData();
    }

    private void loadData() {
        connection = database.getConnection();
        onUpdate();
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    public void getSelected( MouseEvent mouseEvent ) {
        index = medicsTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtUsername.setText(usernameCol.getCellData(index));
        txtId.setText(idCol.getCellData(index).toString());
        txtPassword.setText(passwordCol.getCellData(index));
    }

    public void onEdit( ActionEvent actionEvent ) {
        try {
            connection = database.getConnection();
            String value1 = txtUsername.getText();
            String value2 = txtId.getText();
            Integer intId = Integer.parseInt(value2);
            String value3 = txtPassword.getText();
            String sql = "update accounts set passwords = '" + value3 + "',username = '" + value1 + "' where id =" + intId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            onUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goBack( ActionEvent actionEvent ) {
        this.changeScene(SCENE_IDENTIFIER.MEDIC);
    }

    public void onRemove( ActionEvent actionEvent ) {
        connection = database.getConnection();
        String sql = "delete from accounts where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(txtId.getText()));
            preparedStatement.execute();
            onUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAdd( ActionEvent actionEvent ) {

        connection = database.getConnection();
        String sql = "insert into accounts(username,passwords) values(?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, txtUsername.getText());
            preparedStatement.setString(2, txtPassword.getText());
            preparedStatement.execute();
            onUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUpdate() {
        System.out.println("da");
        medicsList.clear();
        query = "select * from accounts";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("1");
            while (resultSet.next()) {
                medicsList.add(new medics(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("passwords")));
                medicsTable.setItems(medicsList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
