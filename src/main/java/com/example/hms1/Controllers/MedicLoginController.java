package com.example.hms1.Controllers;

import com.example.hms1.Database;
import com.example.hms1.utils.enums.SCENE_IDENTIFIER;
import javafx.fxml.FXML;
import com.example.hms1.utils.SceneController;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MedicLoginController extends SceneController {
    @FXML
    private Text credentialsText;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onGoBackButton() {
        this.changeScene(SCENE_IDENTIFIER.MAINPAGE);
    }

    @FXML
    protected void onLogInButton() {
        if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {
            credentialsText.setText("You tried!");
            int valid = Database.validateLogin(credentialsText,usernameField,passwordField);
            System.out.println(valid);
            if (valid == 2) {
                this.changeScene(SCENE_IDENTIFIER.ADMIN);
            }
            if (valid == 1) {
                this.changeScene(SCENE_IDENTIFIER.MEDICPAGE);
            }
        } else {
            credentialsText.setText("Please fill up all fields!");
        }
        usernameField.setText("");
        passwordField.setText("");
    }

}
