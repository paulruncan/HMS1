package com.example.hms1.Controllers;

import com.example.hms1.utils.enums.SCENE_IDENTIFIER;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.hms1.utils.SceneController;

public class HelloController extends SceneController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        this.changeScene(SCENE_IDENTIFIER.MAINPAGE);
    }
}