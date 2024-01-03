package com.example.hms1.Controllers;

import com.example.hms1.Patients.patients;
import com.example.hms1.Patients.patientsIC;
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
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ICController extends SceneController implements Initializable {

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
    private TableView<patientsIC> patientsICTable;
    @FXML
    private TableColumn<patientsIC, String> nameCol;

    @FXML
    private  TableColumn<patientsIC,String > medicineCol;
    @FXML
    private TableColumn<patientsIC,Integer> priceCol;
    @FXML
    private TableColumn<patientsIC,Integer> prospectCol;
    @FXML
    private TableColumn<patientsIC,Integer> idCol;

    ObservableList<patientsIC> PatientICList = FXCollections.observableArrayList();
    int index =-1;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;


    @FXML
    protected void onAdd(){
        connection = database.getConnection();
        String sql = "insert into patientsIC(name,medicine,price,prospect) values(?,?,?,?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, txtName.getText());
            preparedStatement.setString(2,txtMedicine.getText());
            Integer valuePrice = Integer.parseInt(txtPrice.getText());
            Integer valueProspect = Integer.parseInt(txtProspect.getText());
            preparedStatement.setInt(3, valuePrice);
            preparedStatement.setInt(4, valueProspect);
            if(valueProspect == 1 || valueProspect == 101 || valueProspect == 111)
            preparedStatement.execute();
            else txt.setText("Invalid Prospect");
            onUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    };

    public void onUpdate() {
        System.out.println("da");
        PatientICList.clear();
        query = "select * from patientsic";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("1");
            while (resultSet.next()) {
                PatientICList.add(new patientsIC(resultSet.getString("name"), resultSet.getString("medicine"), resultSet.getInt("price"),resultSet.getInt("prospect"),resultSet.getInt("id")));
                patientsICTable.setItems(PatientICList);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onRemove( ActionEvent actionEvent ) {
        connection = database.getConnection();
        String sql = "delete from patientsIC where id = ?";
        try{
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(txtId.getText()));
            preparedStatement.execute();
            onUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void goBack( ActionEvent actionEvent ) {
        this.changeScene(SCENE_IDENTIFIER.MEDICPAGE);
    }
    @FXML
    public void onEdit( ActionEvent actionEvent ) {
        try{
            connection = database.getConnection();
            String value1 = txtName.getText();
            String value2 = txtMedicine.getText();
            String value3 = txtPrice.getText();
            Integer intVal = Integer.parseInt(value3);
            String value4 = txtProspect.getText();
            Integer intProspect = Integer.parseInt(value4);
            String value5 = txtId.getText();
            Integer intId = Integer.parseInt(value5);
            String sql = "update patientsIC set medicine = '"+value2+"',price="+value3+",prospect="+value4+", name = '"+value1+"' where id ="+intId;
            preparedStatement = connection.prepareStatement(sql);
            if(intProspect == 1 || intProspect == 101 || intProspect == 111)
            preparedStatement.execute();
            else txt.setText("invalid prospect(edit)");
            onUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void getSelected( MouseEvent mouseEvent ) {
        index = patientsICTable.getSelectionModel().getSelectedIndex();
        if(index <= -1){
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
        loadData();
    }
    public void loadData(){
        connection = database.getConnection();
        onUpdate();
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        medicineCol.setCellValueFactory(new PropertyValueFactory<>("medicine"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        prospectCol.setCellValueFactory(new PropertyValueFactory<>("prospect"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    }


}
