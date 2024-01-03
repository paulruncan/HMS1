package com.example.hms1;

import com.example.hms1.utils.ApplicationHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        ApplicationHandler.getInstance().startApplication(stage);
    }

    public static void main(String[] args) {
        launch();
    }
    ////sdasdasdasdasd
}