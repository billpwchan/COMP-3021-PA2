package viewmodel;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import model.Map.Cell;
import model.Map.Occupant.Crate;
import model.Map.Occupiable.DestTile;
import model.Map.Occupiable.Occupiable;

import java.net.URISyntaxException;

/**
 * Renders maps onto canvases
 */
public class MapRenderer {
    private static Image wall = null;
    private static Image crateOnTile = null;
    private static Image crateOnDest = null;

    private static Image playerOnTile = null;
    private static Image playerOnDest = null;

    private static Image dest = null;
    private static Image tile = null;

    static {
        try {
            wall = new Image(MapRenderer.class.getResource("/assets/images/wall.png").toURI().toString());
            crateOnTile = new Image(MapRenderer.class.getResource("/assets/images/crateOnTile.png").toURI().toString());
            crateOnDest = new Image(MapRenderer.class.getResource("/assets/images/crateOnDest.png").toURI().toString());
            playerOnTile = new Image(MapRenderer.class.getResource("/assets/images/playerOnTile.png").toURI().toString());
            playerOnDest = new Image(MapRenderer.class.getResource("/assets/images/playerOnDest.png").toURI().toString());
            dest = new Image(MapRenderer.class.getResource("/assets/images/dest.png").toURI().toString());
            tile = new Image(MapRenderer.class.getResource("/assets/images/tile.png").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Render the map onto the canvas. This method can be used in Level Editor
     * <p>
     * Hint: set the canvas height and width as a multiple of the rows and cols
     *
     * @param canvas The canvas to be rendered onto
     * @param map    The map holding the current state of the game
     */
    static void render(Canvas canvas, LevelEditorCanvas.Brush[][] map) {
        int rowNum = map.length;
        int colNum = map.length > 0 ? map[0].length : 0;
        canvas.setHeight((double) (rowNum << 5));
        canvas.setWidth((double) (colNum << 5));
        var graphicsContext = canvas.getGraphicsContext2D();
        for (var i = 0; i < rowNum; i++) {
            for (var j = 0; j < colNum; j++) {
                Image image = null;
                switch (map[i][j].getRep()) {
                    case '.': {
                        image = tile;
                        break;
                    }
                    case '@': {
                        image = playerOnTile;
                        break;
                    }
                    case '&': {
                        image = playerOnDest;
                        break;
                    }
                    case 'c': {
                        image = crateOnTile;
                        break;
                    }
                    case '$': {
                        image = crateOnDest;
                        break;
                    }
                    case '#': {
                        image = wall;
                        break;
                    }
                    case 'C': {
                        image = dest;
                        break;
                    }
                }
                graphicsContext.drawImage(image, (double)(j << 5), (double)(i << 5));
            }
        }
    }


    /**
     * Render the map onto the canvas. This method can be used in GamePlayPane and LevelSelectPane
     * <p>
     * Hint: set the canvas height and width as a multiple of the rows and cols
     *
     * @param canvas The canvas to be rendered onto
     * @param map    The map holding the current state of the game
     */
    public static void render(Canvas canvas, Cell[][] map) {
        int rowNum = map.length;
        int colNum = map.length > 0 ? map[0].length : 0;
        canvas.setHeight((double) (rowNum * Config.LEVEL_EDITOR_TILE_SIZE));
        canvas.setWidth((double) (colNum * Config.LEVEL_EDITOR_TILE_SIZE));
        var graphicsContext = canvas.getGraphicsContext2D();
        for (var i = 0; i < rowNum; i++) {
            for (var j = 0; j < colNum; j++) {
                Image image = null;
                if (!(map[i][j] instanceof Occupiable)) {
                    image = wall;
                } else {
                    if (!((Occupiable) map[i][j]).getOccupant().isPresent()) {
                        if (map[i][j] instanceof DestTile) {
                            image = dest;
                        } else {
                            image = tile;
                        }
                    } else {
                        if (((Occupiable) map[i][j]).getOccupant().get() instanceof Crate) {
                            image = map[i][j] instanceof DestTile ? crateOnDest : crateOnTile;
                        } else {
                            image = map[i][j] instanceof DestTile ? playerOnDest : playerOnTile;
                        }
                    }
                }
                graphicsContext.drawImage(image, (double) (j << 5), (double) (i << 5), Config.LEVEL_EDITOR_TILE_SIZE, Config.LEVEL_EDITOR_TILE_SIZE);
            }
        }
    }
}
