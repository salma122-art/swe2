package com.example.finance;

import com.example.finance.ui.LoginScreen;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

/**
 * Application entry point. Launches the JavaFX login screen.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ensureDataFolder();
        new LoginScreen().start(stage);
    }

    private static void ensureDataFolder() {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
