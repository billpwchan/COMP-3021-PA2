package main;

import javafx.application.Application;
import javafx.stage.Stage;
import viewmodel.SceneManager;

/**
 * Main application
 */
public class SokobanApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SceneManager.getInstance().setStage(primaryStage);
        SceneManager.getInstance().showMainMenuScene();
    }
}
