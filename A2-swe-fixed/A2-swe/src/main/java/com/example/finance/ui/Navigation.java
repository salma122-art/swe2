package com.example.finance.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Tiny navigation helper for swapping scenes on a single primary stage.
 */
public class Navigation {

    private static Stage stage;

    private Navigation() {
        // utility class
    }

    public static void init(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void go(Scene scene, String title) {
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
