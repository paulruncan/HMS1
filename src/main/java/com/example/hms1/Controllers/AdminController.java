package com.example.hms1.Controllers;

import com.example.hms1.Patients.Medics;
import com.example.hms1.Database;
import com.example.hms1.utils.SceneController;
import com.example.hms1.utils.enums.SCENE_IDENTIFIER;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
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
    private TableView<Medics> medicsTable;
    @FXML
    private TableColumn<Medics, String> usernameCol;
    @FXML
    private TableColumn<Medics, String> passwordCol;
    @FXML
    private TableColumn<Medics, Integer> idCol;


    ObservableList<Medics> medicsList = FXCollections.observableArrayList();
    int index = -1;
    String query = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle ) {
        Database.loadDataAdmin(usernameCol,passwordCol,idCol);
        onUpdate();
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
            Database.editAdmin(preparedStatement,txtUsername,txtId,txtPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        onUpdate();
    }

    public void goBack( ActionEvent actionEvent ) {
        this.changeScene(SCENE_IDENTIFIER.MEDIC);
    }

    public void onRemove( ActionEvent actionEvent ) {
        Database.removeAdmin(preparedStatement,txtId);
        onUpdate();
    }

    public void onAdd( ActionEvent actionEvent ) {
        Database.addAdmin(preparedStatement,txtUsername,txtPassword);
        onUpdate();
    }

    public void onUpdate() {
        System.out.println("da");
        medicsList.clear();
        Database.updateAdmin(query,preparedStatement,resultSet,medicsList,medicsTable);
    }
}
