package com.example.hms1.utils;

import com.example.hms1.utils.enums.SCENE_IDENTIFIER;

public class SceneController {
    public void changeScene( SCENE_IDENTIFIER newScene ) {
        ApplicationHandler.getInstance().changeScene(newScene);
    }

    public void closeApplication() {
        ApplicationHandler.getInstance().closeApplication();
    }
}
