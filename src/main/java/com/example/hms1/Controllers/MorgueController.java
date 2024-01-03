package com.example.hms1.Controllers;

import com.example.hms1.Patients.bodies;
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
    private TableView<bodies> bodiesTable;
    @FXML
    private TableColumn<bodies, String> nameCol;
    @FXML
    private  TableColumn<bodies,String> organCol;
    @FXML
    private TableColumn<bodies,Integer> idCol;



    ObservableList<bodies> bodiesList = FXCollections.observableArrayList();
    int index =-1;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    public void onAdd( ActionEvent actionEvent ) {
        connection = database.getConnection();
        String sql = "insert into morgue(name,organ) values(?,?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, txtName.getText());
            preparedStatement.setString(2,txtOrgan.getText());
            preparedStatement.execute();
            onUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onUpdate() {
        System.out.println("da");
        bodiesList.clear();
        query = "select * from morgue";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("1");
            while (resultSet.next()) {
                bodiesList.add(new bodies(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getString("organ")));
                bodiesTable.setItems(bodiesList);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onRemove( ActionEvent actionEvent ) {
        connection = database.getConnection();
        String sql = "delete from morgue where id = ?";
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

    public void onEdit( ActionEvent actionEvent ) {
        try{
            connection = database.getConnection();
            String value1 = txtName.getText();
            String value2 = txtId.getText();
            Integer intId = Integer.parseInt(value2);
            String value3 = txtOrgan.getText();
            String sql = "update morgue set organ = '"+value3+"',name = '"+value1+"' where id ="+intId;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            onUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getSelected( MouseEvent mouseEvent ) {
        index = bodiesTable.getSelectionModel().getSelectedIndex();
        if(index <= -1){
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
        query = "select * from morgue";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("1");
            while (resultSet.next()) {
                if(organv1.equals(resultSet.getString("organ")) || namev1.equals(resultSet.getString("name")))
                    bodiesList.add(new bodies(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getString("organ")));
                bodiesTable.setItems(bodiesList);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
