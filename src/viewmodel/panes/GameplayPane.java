package viewmodel.panes;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Exceptions.InvalidMapException;
import model.LevelManager;
import viewmodel.AudioManager;
import viewmodel.MapRenderer;
import viewmodel.SceneManager;
import viewmodel.customNodes.GameplayInfoPane;

/**
 * Represents the gameplay pane in the game
 */
public class GameplayPane extends BorderPane {

    private final GameplayInfoPane info;
    private VBox canvasContainer;
    private Canvas gamePlayCanvas;
    private HBox buttonBar;
    private Button restartButton;
    private Button quitToMenuButton;

    /**
     * Instantiate the member components and connect and style them. Also set the callbacks.
     * Use 20 for the VBox spacing
     */
    public GameplayPane() {
        this.info = new GameplayInfoPane(
                LevelManager.getInstance().currentLevelNameProperty(),
                LevelManager.getInstance().curGameLevelExistedDurationProperty(),
                LevelManager.getInstance().getGameLevel().numPushesProperty(),
                LevelManager.getInstance().curGameLevelNumRestartsProperty()
        );
        this.canvasContainer = new VBox();
        this.gamePlayCanvas = new Canvas();
        this.buttonBar = new HBox(20.0);
        this.restartButton = new Button("Restart");
        this.quitToMenuButton = new Button("Quit to menu");
        this.connectComponents();
        this.styleComponents();
        this.setCallbacks();
        this.renderCanvas();
    }

    /**
     * Connects the components together (think adding them into another, setting their positions, etc).
     */
    private void connectComponents() {
        this.canvasContainer.getChildren().add(this.gamePlayCanvas);
        this.buttonBar.getChildren().addAll(this.info, this.canvasContainer, this.restartButton, this.quitToMenuButton);
        this.setCenter(this.canvasContainer);
        this.setBottom(this.buttonBar);
        this.canvasContainer.setAlignment(Pos.CENTER);
    }

    /**
     * Apply CSS styling to components.
     */
    private void styleComponents() {
        this.canvasContainer.getStyleClass().add("big-hbox");
        this.buttonBar.getChildren().stream().filter(Button.class::isInstance).forEach(node -> node.getStyleClass().add("big-button"));
        this.buttonBar.getStyleClass().add("bottom-menu");
    }

    /**
     * Set the event handlers for the 2 buttons.
     * <p>
     * Also listens for key presses (w, a, s, d), whichmove the character.
     * <p>
     * Hint: {@link GameplayPane#setOnKeyPressed(EventHandler)}  is needed.
     * You will need to make the move, rerender the canvas, play the sound (if the move was made), and detect
     * for win and deadlock conditions. If win, play the win sound, and do the appropriate action regarding the timers
     * and generating the popups. If deadlock, play the deadlock sound, and do the appropriate action regarding the timers
     * and generating the popups.
     */
    private void setCallbacks() {
        this.restartButton.setOnAction(actionEvent -> this.doRestartAction());
        this.quitToMenuButton.setOnAction(object -> this.doQuitToMenuAction());
        this.setOnKeyPressed(keyInput -> {
            switch (keyInput.getCode()) {
                case W: {
                    LevelManager.getInstance().getGameLevel().makeMove('w');
                    break;
                }
                case A: {
                    LevelManager.getInstance().getGameLevel().makeMove('a');
                    break;
                }
                case S: {
                    LevelManager.getInstance().getGameLevel().makeMove('s');
                    break;
                }
                case D: {
                    LevelManager.getInstance().getGameLevel().makeMove('d');
                }
            }
            AudioManager.getInstance().playMoveSound();
            this.renderCanvas();
            if (LevelManager.getInstance().getGameLevel().isWin()) {
                AudioManager.getInstance().playWinSound();
                this.createLevelClearPopup();
            } else {
                if (LevelManager.getInstance().getGameLevel().isDeadlocked()) {
                    AudioManager.getInstance().playDeadlockSound();
                    this.createDeadlockedPopup();
                }
            }
        });
    }

    /**
     * Called when the tries to quit to menu. Show a popup (see the documentation). If confirmed,
     * do the appropriate action regarding the level timer, level number of restarts, and go to the
     * main menu scene.
     */
    private void doQuitToMenuAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Return to Menu?");
        alert.setContentText("Game progress will be lost.");
        var response = alert.showAndWait();
        if (response.get() == ButtonType.OK) {
            LevelManager.getInstance().resetLevelTimer();
            LevelManager.getInstance().resetNumRestarts();
            SceneManager.getInstance().showMainMenuScene();
        }
    }

    /**
     * Called when the user encounters deadlock. Show a popup (see the documentation).
     * If the user chooses to restart the level, call {@link GameplayPane#doRestartAction()}. Otherwise if they
     * quit to menu, switch to the level select scene, and do the appropriate action regarding
     * the number of restarts.
     */
    private void createDeadlockedPopup() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Level deadlocked!");
        ButtonType returnBtn = new ButtonType("Return");
        ButtonType restartBtn = new ButtonType("Restart");
        alert.getButtonTypes().setAll(returnBtn, restartBtn);
        var response = alert.showAndWait();
        if (response.get() == returnBtn) {
            SceneManager.getInstance().showMainMenuScene();
            LevelManager.getInstance().resetNumRestarts();
            return;
        } else if (response.get() == restartBtn) {
            this.doRestartAction();
        } else {
            return;
        }
        this.renderCanvas();
    }

    /**
     * Called when the user clears the level successfully. Show a popup (see the documentation).
     * If the user chooses to go to the next level, set the new level, rerender, and do the appropriate action
     * regarding the timers and num restarts. If they choose to return, show the level select menu, and do
     * the appropriate action regarding the number of level restarts.
     * <p>
     * Hint:
     * Take care of the edge case for when the user clears the last level. In this case, there shouldn't
     * be an option to go to the next level.
     */
    private void createLevelClearPopup() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Level cleared!");
        ButtonType returnBtn = new ButtonType("Return");
        ButtonType nextLevelBtn = new ButtonType("Next Level");
        if (LevelManager.getInstance().getNextLevelName() != null){
            alert.getButtonTypes().setAll(returnBtn, nextLevelBtn);
        } else {
            alert.getButtonTypes().setAll(returnBtn);
        }
        var response = alert.showAndWait();
        if (response.get() == returnBtn) {
            SceneManager.getInstance().showMainMenuScene();
            LevelManager.getInstance().resetNumRestarts();
        } else if (response.get() == nextLevelBtn) {
            try {
                LevelManager.getInstance().setLevel(LevelManager.getInstance().getNextLevelName());
                this.renderCanvas();
                LevelManager.getInstance().resetNumRestarts();
                LevelManager.getInstance().resetLevelTimer();
                LevelManager.getInstance().startLevelTimer();
            } catch (InvalidMapException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set the current level to the current level name, rerender the canvas, reset and start the timer, and
     * increment the number of restarts
     */
    private void doRestartAction() {
        LevelManager.getInstance().resetLevelTimer();
        LevelManager.getInstance().startLevelTimer();
        LevelManager.getInstance().incrementNumRestarts();
        try {
            LevelManager.getInstance().setLevel(LevelManager.getInstance().currentLevelNameProperty().get());
        } catch (InvalidMapException e) {
            e.printStackTrace();
        }
        this.renderCanvas();
    }

    /**
     * Render the canvas with updated data
     * <p>
     * Hint: {@link MapRenderer}
     */
    private void renderCanvas() {
        MapRenderer.render(this.gamePlayCanvas, LevelManager.getInstance().getGameLevel().getMap().getCells());
    }
}
