package viewmodel;

/**
 * Holds constants
 */
public class Config {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final int LIST_CELL_HEIGHT = 30;

    public static final int LEVEL_EDITOR_TILE_SIZE = 32;
    public static final String CSS_STYLES = Config.class.getResource("/assets/css/styles.css").toString();

    public static String getAboutText() {
        return "Controls:\n" +
                "w: up\n" +
                "a: left\n" +
                "s: down\n" +
                "d: right\n\n" +
                "Instructions:\n" +
                "The objective of Sokoban is to push the all of the crates onto the destination tiles. This is done by moving the player next to a crate and pushing it.";
    }
}
