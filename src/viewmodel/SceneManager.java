package viewmodel;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.LevelManager;
import viewmodel.panes.*;

/**
 * Handles scene changing logic
 */
public class SceneManager {
    private static final SceneManager ourInstance = new SceneManager();
    private final Scene mainMenuScene;
    private final Scene levelSelectScene;
    private final Scene levelEditorScene;
    private final Scene settingsScene;
    private Stage stage;

    private SceneManager() {
        mainMenuScene = new Scene(new MainMenuPane(), Config.WIDTH / 2, Config.HEIGHT);
        levelSelectScene = new Scene(new LevelSelectPane(), Config.WIDTH, Config.HEIGHT);
        levelEditorScene = new Scene(new LevelEditorPane(), Config.WIDTH, Config.HEIGHT);
        settingsScene = new Scene(new SettingsPane(), Config.WIDTH, Config.HEIGHT);

        mainMenuScene.getStylesheets().add(Config.CSS_STYLES);
        levelSelectScene.getStylesheets().add(Config.CSS_STYLES);
        levelEditorScene.getStylesheets().add(Config.CSS_STYLES);
        settingsScene.getStylesheets().add(Config.CSS_STYLES);
    }

    public static SceneManager getInstance() {
        return ourInstance;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void showScene(Scene scene) {
        if (stage == null)
            return;

        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    public void showMainMenuScene() {
        showScene(mainMenuScene);
    }

    public void showLevelSelectMenuScene() {
//        LevelManager.getInstance().loadLevelNamesFromDisk();
        showScene(levelSelectScene);
    }

    public void showLevelEditorScene() {
        showScene(levelEditorScene);
    }

    public void showSettingsMenuScene() {
        showScene(settingsScene);
    }

    public void showGamePlayScene() {
        Scene gameplayScene = new Scene(new GameplayPane(), Config.WIDTH, Config.HEIGHT);
        gameplayScene.getStylesheets().add(Config.CSS_STYLES);
        showScene(gameplayScene);
    }
}
