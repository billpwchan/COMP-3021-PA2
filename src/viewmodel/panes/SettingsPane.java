package viewmodel.panes;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import viewmodel.AudioManager;
import viewmodel.Config;
import viewmodel.SceneManager;

/**
 * Represents the settings pane in the game
 */
public class SettingsPane extends BorderPane {
    private VBox leftContainer;
    private Button returnButton;
    private Button toggleSoundFXButton;
    private VBox centerContainer;
    private TextArea infoText;

    /**
     * Instantiate the member components and connect and style them. Also set the callbacks.
     * Hints:
     * Use 20 for the VBox spacing.
     * The text of {@link SettingsPane#toggleSoundFXButton} should depend on {@link AudioManager}
     * Use {@link Config#getAboutText()} for the infoText
     */
    public SettingsPane() {
        this.leftContainer = new VBox(20.0);
        this.returnButton = new Button("Return");
        this.toggleSoundFXButton = new Button(!AudioManager.getInstance().isEnabled() ? "Enabled " + "Sound FX": "Disabled " + "Sound FX");
        this.centerContainer = new VBox(20.0);
        this.infoText = new TextArea(Config.getAboutText());
        this.connectComponents();
        this.styleComponents();
        this.setCallbacks();
    }

    /**
     * Connects the components together (think adding them into another, setting their positions, etc).
     */
    private void connectComponents() {
        this.leftContainer.getChildren().addAll(
                this.returnButton,
                this.toggleSoundFXButton
        );
        this.centerContainer.getChildren().addAll(
                this.infoText
        );
        this.setLeft(this.leftContainer);
        this.setCenter(this.centerContainer);
    }

    /**
     * Apply CSS styling to components.
     * <p>
     * Also set the text area to not be editable, but allow text wrapping.
     */
    private void styleComponents() {
        this.leftContainer.getStyleClass().addAll("big-vbox", "side-menu");
        this.leftContainer.getChildren().stream().filter(Button.class::isInstance).forEach(node -> node.getStyleClass().add("big-button"));
        this.infoText.setEditable(false);
        this.infoText.setWrapText(true);
        this.setPrefHeight(600.0);
        this.centerContainer.getStyleClass().add("big-vbox");
    }

    /**
     * Set the event handler for the 2 buttons.
     * The return button should go to the main menu scene
     */
    private void setCallbacks() {
        this.returnButton.setOnAction(actionEvent -> SceneManager.getInstance().showMainMenuScene());
        this.toggleSoundFXButton.setOnAction(actionEvent -> {
            AudioManager.getInstance().setEnabled(!AudioManager.getInstance().isEnabled());
            this.toggleSoundFXButton.setText((!AudioManager.getInstance().isEnabled() ? "Enabled " : "Disabled ") + "Sound FX");
        });
    }
}
