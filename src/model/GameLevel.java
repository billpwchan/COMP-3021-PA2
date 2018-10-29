package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Exceptions.InvalidMapException;
import model.Map.Map;
import model.Map.Occupant.Crate;
import model.Map.Occupiable.DestTile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that loads, stores, modifies, and keeps track of the game map win/deadlock condition. Also keeps tracks
 * of information about this current level, e.g. how many moves the player has made.
 */
public class GameLevel {

    private final IntegerProperty numPushes = new SimpleIntegerProperty(0);
    private Map map;

    public IntegerProperty numPushesProperty() {
        return numPushes;
    }

    public Map getMap() {
        return map;
    }

    /**
     * Loads and reads the map line by line, instantiates and initializes map
     *
     * @param filename the map text filename
     * @throws InvalidMapException when the map is invalid
     */
    public void loadMap(String filename) throws InvalidMapException {
        File f = new File(filename);
        try (Scanner reader = new Scanner(f)) {
            int numRows = reader.nextInt();
            int numCols = reader.nextInt();
            reader.nextLine();

            char[][] rep = new char[numRows][numCols];
            for (int r = 0; r < numRows; r++) {
                String row = reader.nextLine();
                for (int c = 0; c < numCols; c++) {
                    rep[r][c] = row.charAt(c);
                }
            }

            map = new Map();
            map.initialize(numRows, numCols, rep);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Whether or not the win condition has been satisfied
     */
    public boolean isWin() {
        return map.getDestTiles().stream().allMatch(DestTile::isCompleted);
    }

    /**
     * When no crates can be moved but the game is not won, then deadlock has occurred
     *
     * @return Whether deadlock has occurred
     */
    public boolean isDeadlocked() {
        for (Crate c : map.getCrates()) {
            boolean canMoveLR = map.isOccupiableAndNotOccupiedWithCrate(c.getR(), c.getC() - 1)
                    && map.isOccupiableAndNotOccupiedWithCrate(c.getR(), c.getC() + 1);
            boolean canMoveUD = map.isOccupiableAndNotOccupiedWithCrate(c.getR() - 1, c.getC()) &&
                    map.isOccupiableAndNotOccupiedWithCrate(c.getR() + 1, c.getC());
            if (canMoveLR || canMoveUD)
                return false;
        }
        return true;
    }

    /**
     * @param c The char corresponding to a move from the user
     *          w: up
     *          a: left
     *          s: down
     *          d: right
     * @return Whether or not the move was successful
     */
    public boolean makeMove(char c) {
        boolean madeMove = false;
        switch (c) {
            case 'w':
                madeMove = map.movePlayer(Map.Direction.UP);
                break;
            case 'a':
                madeMove = map.movePlayer(Map.Direction.LEFT);
                break;
            case 's':
                madeMove = map.movePlayer(Map.Direction.DOWN);
                break;
            case 'd':
                madeMove = map.movePlayer(Map.Direction.RIGHT);
                break;
        }
        if (madeMove) {
            numPushes.setValue(numPushes.getValue() + 1);
        }
        return madeMove;
    }
}
