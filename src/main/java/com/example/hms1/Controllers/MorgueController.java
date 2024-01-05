package com.example.hms1.Controllers;

import com.example.hms1.Patients.Bodies;
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

public class MorgueController extends SceneController implements Initializable {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtOrgan;
    @FXML
    private TableView<Bodies> bodiesTable;
    @FXML
    private TableColumn<Bodies, String> nameCol;
    @FXML
    private TableColumn<Bodies, String> organCol;
    @FXML
    private TableColumn<Bodies, Integer> idCol;


    ObservableList<Bodies> bodiesList = FXCollections.observableArrayList();
    int index = -1;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    public void onAdd( ActionEvent actionEvent ) {
        connection = database.getConnection();
        database.addMorgue(preparedStatement,connection,txtName,txtOrgan);
        onUpdate();
    }

    public void onUpdate() {
        System.out.println("da");
        bodiesList.clear();
        database.updateMorgue(query,preparedStatement,connection,resultSet,bodiesList,bodiesTable);
    }

    public void onRemove( ActionEvent actionEvent ) {
        connection = database.getConnection();
        database.removeMorgue(preparedStatement,connection,txtId);
        onUpdate();
    }

    public void goBack( ActionEvent actionEvent ) {
        this.changeScene(SCENE_IDENTIFIER.MEDICPAGE);
    }

    public void onEdit( ActionEvent actionEvent ) {
        try {
            connection = database.getConnection();
            database.editMorgue(preparedStatement,connection,txtName,txtId,txtOrgan);
            onUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSelected( MouseEvent mouseEvent ) {
        index = bodiesTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtName.setText(nameCol.getCellData(index));
        txtId.setText(idCol.getCellData(index).toString());
        txtOrgan.setText(organCol.getCellData(index));
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle ) {
        loadData();
    }

    private void loadData() {
        connection = database.getConnection();
        onUpdate();
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        organCol.setCellValueFactory(new PropertyValueFactory<>("organ"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    public void onCheck() {
        String organv1 = txtOrgan.getText();
        String namev1 = txtName.getText();
        System.out.println("da");
        bodiesList.clear();
        database.checkMorgue(query,preparedStatement,connection,resultSet,namev1,organv1,bodiesList,bodiesTable);
    }
}
