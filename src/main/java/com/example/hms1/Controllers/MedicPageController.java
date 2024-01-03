package com.example.hms1.Controllers;

import com.example.hms1.utils.SceneController;
import com.example.hms1.utils.enums.SCENE_IDENTIFIER;
import javafx.fxml.FXML;

public class MedicPageController extends SceneController {
    @FXML
    protected void onERButton() {
        this.changeScene(SCENE_IDENTIFIER.ER);
    }

    @FXML
    protected void onBackToLog() {
        this.changeScene(SCENE_IDENTIFIER.MEDIC);
    }

    @FXML
    protected void onICButton() {
        this.changeScene(SCENE_IDENTIFIER.IC);
    }

    @FXML
    protected void onMorgueButton() {
        this.changeScene(SCENE_IDENTIFIER.MORGUE);
    }
}
