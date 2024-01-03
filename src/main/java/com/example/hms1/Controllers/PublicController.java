package com.example.hms1.Controllers;

import com.example.hms1.utils.SceneController;
import com.example.hms1.utils.enums.SCENE_IDENTIFIER;
import javafx.event.ActionEvent;

public class PublicController extends SceneController {
    public void onERButton() {
        this.changeScene(SCENE_IDENTIFIER.ERP);
    }

    public void onICButton() {
        this.changeScene(SCENE_IDENTIFIER.ICP);
    }

    public void onMorgueButton() {
        this.changeScene(SCENE_IDENTIFIER.MORGUEP);
    }

    public void onBackToLog() {
        this.changeScene(SCENE_IDENTIFIER.MAINPAGE);
    }
}
