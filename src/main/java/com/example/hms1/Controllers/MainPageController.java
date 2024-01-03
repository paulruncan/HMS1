package com.example.hms1.Controllers;

import com.example.hms1.utils.SceneController;
import com.example.hms1.utils.enums.SCENE_IDENTIFIER;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class MainPageController extends SceneController {
    @FXML
    private Text randomText;
    @FXML
    protected void onMedicButtonClick(){this.changeScene(SCENE_IDENTIFIER.MEDIC);}

    public void onPublicButton() {
        this.changeScene(SCENE_IDENTIFIER.PUBLIV);
    }
}
