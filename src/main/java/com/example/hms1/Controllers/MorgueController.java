package com.example.hms1.Controllers;

import com.example.hms1.Patients.Bodies;
import com.example.hms1.Database;
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
        Database.addMorgue(preparedStatement,txtName,txtOrgan);
        onUpdate();
    }

    public void onUpdate() {
        System.out.println("da");
        bodiesList.clear();
        Database.updateMorgue(query,preparedStatement,resultSet,bodiesList,bodiesTable);
    }

    public void onRemove( ActionEvent actionEvent ) {
        Database.removeMorgue(preparedStatement,txtId);
        onUpdate();
    }

    public void goBack( ActionEvent actionEvent ) {
        this.changeScene(SCENE_IDENTIFIER.MEDICPAGE);
    }

    public void onEdit( ActionEvent actionEvent ) {
        try {
            Database.editMorgue(preparedStatement,txtName,txtId,txtOrgan);
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
        Database.loadDataMorgue(nameCol,organCol,idCol);
        onUpdate();
    }

    public void onCheck() {
        String organv1 = txtOrgan.getText();
        String namev1 = txtName.getText();
        System.out.println("da");
        bodiesList.clear();
        Database.checkMorgue(query,preparedStatement,resultSet,namev1,organv1,bodiesList,bodiesTable);
    }
}
