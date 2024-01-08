package com.example.hms1.Controllers;

import com.example.hms1.Patients.PatientsIntensiveCare;
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
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class IntensiveCareController extends SceneController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtMedicine;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtProspect;
    @FXML
    private Text txt;
    @FXML
    private TextField txtId;

    @FXML
    private TableView<PatientsIntensiveCare> patientsICTable;
    @FXML
    private TableColumn<PatientsIntensiveCare, String> nameCol;

    @FXML
    private TableColumn<PatientsIntensiveCare, String> medicineCol;
    @FXML
    private TableColumn<PatientsIntensiveCare, Integer> priceCol;
    @FXML
    private TableColumn<PatientsIntensiveCare, Integer> prospectCol;
    @FXML
    private TableColumn<PatientsIntensiveCare, Integer> idCol;

    ObservableList<PatientsIntensiveCare> PatientICList = FXCollections.observableArrayList();
    int index = -1;
    String query = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;


    @FXML
    protected void onAdd() {
        Database.addIC(preparedStatement,txtName,txtMedicine,txtPrice,txtProspect,txt);
        onUpdate();
    }

    ;

    public void onUpdate() {
        System.out.println("da");
        PatientICList.clear();
        Database.updateIC(query,preparedStatement,resultSet,PatientICList,patientsICTable);
    }

    public void onRemove( ActionEvent actionEvent ) {
        Database.removeIC(preparedStatement,  txtId);
        onUpdate();
    }

    public void goBack( ActionEvent actionEvent ) {
        this.changeScene(SCENE_IDENTIFIER.MEDICPAGE);
    }

    @FXML
    public void onEdit( ActionEvent actionEvent ) {
        try {
            Database.editIC(preparedStatement,txt,txtName,txtMedicine,txtPrice,txtProspect,txtId);
        } catch (Exception e) {
        }
        onUpdate();
    }

    @FXML
    public void getSelected( MouseEvent mouseEvent ) {
        index = patientsICTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtName.setText(nameCol.getCellData(index));
        txtMedicine.setText(medicineCol.getCellData(index));
        txtPrice.setText(priceCol.getCellData(index).toString());
        txtProspect.setText(prospectCol.getCellData(index).toString());
        txtId.setText(idCol.getCellData(index).toString());
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle ) {
        Database.loadDataIC(nameCol,medicineCol,priceCol,prospectCol,idCol);
        onUpdate();
    }



}
