package viewmodel.panes;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import viewmodel.SceneManager;

/**
 * Represents the main menu in the game
 */
public class MainMenuPane extends BorderPane {

    private VBox container;
    private Label title;
    private Button playButton;
    private Button levelEditorButton;
    private Button settingsButton;
    private Button quitButton;

    /**
     * Instantiate the member components and connect and style them. Also set the callbacks.
     */
    public MainMenuPane() {
        this.container = new VBox();
        this.title = new Label("Sokoban");
        this.playButton = new Button("Play");
        this.levelEditorButton = new Button("Level Editor");
        this.settingsButton = new Button("About / Settings");
        this.quitButton = new Button("Quit");
        this.connectComponents();
        this.styleComponents();
        this.setCallbacks();
    }

    /**
     * Connects the components together (think adding them into another, setting their positions, etc).
     * Use this for reference.
     */
    private void connectComponents() {
        container.getChildren().addAll(
                title,
                playButton,
                levelEditorButton,
                settingsButton,
                quitButton
        );
        this.setCenter(container);
    }

    /**
     * Apply CSS styling to components.
     */
    private void styleComponents() {
        this.setCenter(this.container);
        this.title.setStyle("-fx-font-size: 50;");
        this.container.getStyleClass().add("big-vbox");
        this.container.getChildren().stream().filter(Button.class::isInstance).forEach(node -> node.getStyleClass().add("big-button"));
    }

    /**
     * Set the event handlers for the 4 buttons, 3 of which switch to different scene, and 1 of which exits the program.
     */
    private void setCallbacks() {
        this.playButton.setOnAction(actionEvent -> SceneManager.getInstance().showGamePlayScene());
        this.levelEditorButton.setOnAction(actionEvent -> SceneManager.getInstance().showLevelEditorScene());
        this.settingsButton.setOnAction(actionEvent -> SceneManager.getInstance().showSettingsMenuScene());
        this.quitButton.setOnAction(actionEvent -> Platform.exit());
    }
}
