package viewmodel.panes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import model.Exceptions.InvalidMapException;
import model.LevelManager;
import model.Map.Cell;
import viewmodel.MapRenderer;
import viewmodel.SceneManager;

import java.io.File;

import static java.lang.System.getProperty;

/**
 * Represents the main menu in the game
 */
public class LevelSelectPane extends BorderPane {
    private VBox leftContainer;
    private Button returnButton;
    private Button playButton;
    private Button chooseMapDirButton;
    private ListView<String> levelsListView;
    private VBox centerContainer;
    private Canvas levelPreview;

    /**
     * Instantiate the member components and connect and style them. Also set the callbacks.
     * Use 20 for VBox spacing
     */
    public LevelSelectPane() {
        this.leftContainer = new VBox(20.0);
        this.returnButton = new Button("Return");
        this.playButton = new Button("Play");
        this.chooseMapDirButton = new Button("Choose map directory");
        this.levelsListView = new ListView<>();
        this.centerContainer = new VBox(20.0);
        this.levelPreview = new Canvas();
        this.connectComponents();
        this.styleComponents();
        this.setCallbacks();
    }

    /**
     * Connects the components together (think adding them into another, setting their positions, etc). Reference
     * the other classes in the {@link javafx.scene.layout.Pane} package.
     */
    private void connectComponents() {
        //TODO

        this.leftContainer.getChildren().addAll(
                this.returnButton,
                this.chooseMapDirButton,
                this.returnButton
        );
        this.centerContainer.getChildren().add(this.levelPreview);

    }

    /**
     * Apply CSS styling to components. Also sets the {@link LevelSelectPane#playButton} to be disabled.
     */
    private void styleComponents() {
        this.setLeft(this.leftContainer);
        this.setCenter(this.centerContainer);
        this.leftContainer.getChildren().stream().filter(Button.class::isInstance).forEach(node -> node.getStyleClass().add("big-button"));
        this.leftContainer.getStyleClass().addAll("side-menu", "big-vbox");
        this.centerContainer.getStyleClass().add("big-vbox");
        this.levelsListView.setPrefSize(150.0, 300.0);
        this.centerContainer.setAlignment(Pos.CENTER);
        this.playButton.setDisable(true);
    }

    /**
     * Set the event handlers for the 3 buttons and listview.
     * <p>
     * Hints:
     * The return button should show the main menu scene
     * The chooseMapDir button should prompt the user to choose the map directory, and load the levels
     * The play button should set the current level based on the current level name (see LevelManager), show
     * the gameplay scene, and start the level timer.
     * The listview, based on which item was clicked, should set the current level (see LevelManager), render the
     * preview (see {@link MapRenderer#render(Canvas, Cell[][])}}, and set the play button to enabled.
     */
    private void setCallbacks() {
        this.returnButton.setOnAction(actionEvent -> SceneManager.getInstance().showMainMenuScene());
        this.chooseMapDirButton.setOnAction(actionEvent -> {
            DirectoryChooser dir = new DirectoryChooser();
            dir.setTitle("Load map directory");
            dir.setInitialDirectory(new File(getProperty("user.dir")));

        });
        this.levelsListView.getSelectionModel().selectedItemProperty().addListener((obj, str1, str2) -> {
            try {
                if (str == null) {
                    this.playButton.setDisable(true);
                }

            } catch (InvalidMapException ex) {
                ex.printStackTrace();
                return;
            }
        });
    }

    /**
     * Popup a DirectoryChooser window to ask the user where the map folder is stored.
     * Update the LevelManager's map directory afterwards, and potentially
     * load the levels from disk using LevelManager (if the user didn't cancel out the window)
     */
    private void promptUserForMapDirectory() {
        //TODO
    }
}
