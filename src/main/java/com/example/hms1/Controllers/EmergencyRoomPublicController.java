package com.example.hms1.Controllers;

import com.example.hms1.Patients.Names;
import com.example.hms1.Database;
import com.example.hms1.utils.SceneController;
import com.example.hms1.utils.enums.SCENE_IDENTIFIER;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class EmergencyRoomPublicController extends SceneController implements Initializable {
    @FXML
    protected Text textNume;
    @FXML
    protected Text textPrice;
    @FXML
    protected TextField txtName;
    @FXML
    protected TextField txtId;
    @FXML
    protected TableView<Names> patientsTable;
    @FXML
    private TableColumn<Names, String> nameCol;
    @FXML
    private TableColumn<Names, Integer> idCol;

    ObservableList<Names> PatientList = FXCollections.observableArrayList();
    int index = -1;
    String query = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    //Patients patient = null;


    public Text getTextNume() {
        return textNume;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public void setPatientsTable( TableView<Names> patientsTable ) {
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
        Database.updatePublicER(query,preparedStatement,resultSet,PatientList,patientsTable);

    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle ) {
        Database.loadDateERP(nameCol,idCol);
        onUpdate();
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
        try {
            int price1 = Database.checkPricePublicER(query, preparedStatement, resultSet, price, namev1);
            textNume.setText("Nume: " + namev1);
            textPrice.setText("Price: " + price1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void onCheck() {
        String namev1 = txtName.getText();
        PatientList.clear();
        try {
            Database.checkPublicER(query,preparedStatement,resultSet,namev1,PatientList,patientsTable);
            textNume.setText("Nume: " + namev1);
            textPrice.setText("Price:");
        } catch (Exception e){

        }
    }
}
