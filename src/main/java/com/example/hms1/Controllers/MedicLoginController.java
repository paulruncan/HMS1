package com.example.hms1.Controllers;

import com.example.hms1.database;
import com.example.hms1.utils.enums.SCENE_IDENTIFIER;
import javafx.fxml.FXML;
import com.example.hms1.utils.SceneController;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MedicLoginController extends SceneController{
    @FXML
    private Text credentialsText;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onGoBackButton(){
        this.changeScene(SCENE_IDENTIFIER.MAINPAGE);
    }
    @FXML
    protected void onLogInButton(){
        if(!usernameField.getText().isBlank() && !passwordField.getText().isBlank())
        {
            credentialsText.setText("You tried!");
            int valid = validateLogin();
            System.out.println(valid);
            if(valid == 2)
            {
                this.changeScene(SCENE_IDENTIFIER.ADMIN);
            }
            if(valid == 1)
            {
                this.changeScene(SCENE_IDENTIFIER.MEDICPAGE);
            }
        }
        else {
            credentialsText.setText("Please fill up all fields!");
        }
        usernameField.setText("");
        passwordField.setText("");
    }

    protected int validateLogin() {
        database connectNow= new database();
        Connection connection = connectNow.getConnection();

        String verifyLogin = " select count(1) from accounts where username = '" + usernameField.getText()+ "' and passwords = '" + passwordField.getText() + "'";
        String checkIfAdmin = " select username from accounts where username = '" + usernameField.getText()+ "' and passwords = '" + passwordField.getText() + "'";
        try{
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            ResultSet queryResultAdmin = statement1.executeQuery(checkIfAdmin);

            while(queryResult.next() && queryResultAdmin.next()){
                if(queryResult.getInt(1)==1){
                    credentialsText.setText("Enter credentials!");
                    if(queryResultAdmin.getString(1).equals("admin"))
                        return 2;
                    return 1;
                }
                else{
                    return 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
