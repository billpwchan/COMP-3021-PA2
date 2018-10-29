package viewmodel.panes;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import viewmodel.LevelEditorCanvas;
import viewmodel.SceneManager;
import viewmodel.customNodes.NumberTextField;

import static viewmodel.LevelEditorCanvas.Brush;

/**
 * Represents the level editor in the game
 */
public class LevelEditorPane extends BorderPane {
    private final LevelEditorCanvas levelEditor = new LevelEditorCanvas(5, 5);
    private VBox leftContainer = new VBox(20.0);
    private Button returnButton = new Button("Return");
    private Label rowText = new Label("Rows");
    private NumberTextField rowField = new NumberTextField("5");
    private Label colText = new Label("Columns");
    private NumberTextField colField = new NumberTextField("5");
    private BorderPane rowBox = new BorderPane(); //holds the rowText and rowField side by side
    private BorderPane colBox = new BorderPane(); //holds the colText and colField side by side
    private Button newGridButton = new Button("New Grid");
    private ObservableList<Brush> brushList = FXCollections.observableArrayList(Brush.values());
    private ListView<Brush> selectedBrush = new ListView<>();
    private Button saveButton = new Button("Save");
    private VBox centerContainer = new VBox(20.0);

    /**
     * Instantiate the member components and connect and style them. Also set the callbacks.
     * <p>
     * Hints: {@link LevelEditorPane#rowField} and {@link LevelEditorPane#colField} should be initialized to "5".
     * {@link LevelEditorPane#levelEditor} should be initialized to 5 rows and 5 columns.
     * {@link LevelEditorPane#brushList} should be initialized with all values of the {@link Brush} enum.
     * Use 20 for VBox spacing
     */
    public LevelEditorPane() {
        leftContainer = new VBox(20.0);
        returnButton = new Button("Return");
        rowText = new Label("Rows");
        rowField = new NumberTextField("5");
        colText = new Label("Columns");
        colField = new NumberTextField("5");
        rowBox = new BorderPane(); //holds the rowText and rowField side by side
        colBox = new BorderPane(); //holds the colText and colField side by side
        newGridButton = new Button("New Grid");
        brushList = FXCollections.observableArrayList(Brush.values());
        selectedBrush = new ListView<>();
        saveButton = new Button("Save");
        centerContainer = new VBox(20.0);
        this.connectComponents();
        this.styleComponents();
        this.setCallbacks();
    }

    /**
     * Connects the components together (think adding them into another, setting their positions, etc). Reference
     * the other classes in the {@link javafx.scene.layout.Pane} package.
     * <p>
     * Also sets {@link LevelEditorPane#selectedBrush}'s items, and selects the first.
     */
    private void connectComponents() {
        this.rowBox.setLeft(this.rowText);
        this.rowBox.setRight(this.rowField);
        this.colBox.setLeft(this.colText);
        this.colBox.setRight(this.colField);
        this.leftContainer.getChildren().addAll(
            this.returnButton,
                this.rowBox,
                this.colBox,
                this.newGridButton,
                this.selectedBrush,
                this.saveButton
        );
        this.centerContainer.getChildren().add(this.levelEditor);
        this.selectedBrush.setItems(this.brushList);
        this.selectedBrush.getSelectionModel().selectFirst();
        this.setLeft(this.leftContainer);
        this.setCenter(this.centerContainer);
    }

    /**
     * Apply CSS styling to components.
     */
    private void styleComponents() {
        this.leftContainer.getStyleClass().addAll("big-vbox", "side-menu");
        this.leftContainer.getChildren().stream().filter(Button.class::isInstance).forEach(node -> node.getStyleClass().add("big-button"));
        this.selectedBrush.prefHeightProperty().bind(Bindings.size(this.brushList).multiply(30));
        this.centerContainer.getStyleClass().add("big-vbox");
        this.centerContainer.setAlignment(Pos.CENTER);
    }

    /**
     * Sets the event handlers for the 3 buttons and 1 canvas.
     * <p>
     * Hints:
     * The save button should save the current LevelEditorCanvas to file.
     * The new grid button should change the LevelEditorCanvas size based on the entered values
     * The return button should switch back to the main menu scene
     * The LevelEditorCanvas, upon mouse click, should call {@link LevelEditorCanvas#setTile(Brush, double, double)},
     * passing in the currently selected brush and mouse click coordinates
     */
    private void setCallbacks() {
        this.levelEditor.setOnMouseClicked(mouseEvent -> this.levelEditor.setTile(this.selectedBrush.getSelectionModel().getSelectedItem(), mouseEvent.getX(), mouseEvent.getY()));
        this.returnButton.setOnAction(actionEvent -> SceneManager.getInstance().showMainMenuScene());
        this.saveButton.setOnAction(actionEvent -> this.levelEditor.saveToFile());
        this.newGridButton.setOnAction(actionEvent -> this.levelEditor.changeSize(this.rowField.getValue(), this.colField.getValue()));
    }
}
