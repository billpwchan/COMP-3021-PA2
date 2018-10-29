package model.Map;

import model.Exceptions.InvalidMapException;
import model.Exceptions.InvalidNumberOfPlayersException;
import model.Exceptions.UnknownElementException;
import model.Map.Occupant.Crate;
import model.Map.Occupant.Player;
import model.Map.Occupiable.DestTile;
import model.Map.Occupiable.Occupiable;
import model.Map.Occupiable.Tile;
import viewmodel.LevelEditorCanvas;

import java.util.ArrayList;

/**
 * A class holding a the 2D array of cells, representing the world map
 */
public class Map {
    private Cell[][] cells;
    private ArrayList<DestTile> destTiles = new ArrayList<>();
    private ArrayList<Crate> crates = new ArrayList<>();

    private Player player;

    /**
     * This function instantiates and initializes cells, destTiles, crates to the correct map elements (e.g. the # char
     * means a wall, @ the player, etc).
     *
     * @param rows The number of rows in the map
     * @param cols The number of columns in the map
     * @param rep  The 2d char array read from the map text file
     * @throws InvalidMapException Throw the correct exception when necessary. There should only be 1 player.
     */
    public void initialize(int rows, int cols, char[][] rep) throws InvalidMapException {
        cells = new Cell[rows][cols];
        destTiles = new ArrayList<>();
        crates = new ArrayList<>();
        player = null;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                LevelEditorCanvas.Brush b = LevelEditorCanvas.Brush.fromChar(rep[r][c]);
                if (b == null)
                    throw new UnknownElementException("Unknown char: " + rep[r][c]);

                switch (b) {
                    case TILE:
                        cells[r][c] = new Tile();
                        break;
                    case PLAYER_ON_TILE:
                        if (player == null) {
                            player = new Player(r, c);
                            Tile t = new Tile();
                            cells[r][c] = t;
                            t.setOccupant(player);
                        } else {
                            throw new InvalidNumberOfPlayersException(">1 players found!");
                        }
                        break;
                    case PLAYER_ON_DEST:
                        if (player == null) {
                            player = new Player(r, c);
                            DestTile t = new DestTile();
                            destTiles.add(t);
                            cells[r][c] = t;
                            t.setOccupant(player);
                        } else {
                            throw new InvalidNumberOfPlayersException(">1 players found!");
                        }
                        break;
                    case CRATE_ON_TILE:
                        Crate crate = new Crate(r, c);
                        crates.add(crate);
                        Tile t = new Tile();
                        cells[r][c] = t;
                        t.setOccupant(crate);
                        break;
                    case CRATE_ON_DEST:
                        Crate crate2 = new Crate(r, c);
                        crates.add(crate2);
                        DestTile t2 = new DestTile();
                        destTiles.add(t2);
                        cells[r][c] = t2;
                        t2.setOccupant(crate2);
                        break;
                    case WALL:
                        cells[r][c] = new Wall();
                        break;
                    case DEST:
                        DestTile d = new DestTile();
                        cells[r][c] = d;
                        destTiles.add(d);
                        break;
                }
            }
        }

        if (null == player)
            throw new InvalidNumberOfPlayersException("0 players found!");
    }

    public ArrayList<DestTile> getDestTiles() {
        return destTiles;
    }

    public ArrayList<Crate> getCrates() {
        return crates;
    }

    public Cell[][] getCells() {
        return cells;
    }

    /**
     * Attempts to move the player in the specified direction. Note that the player only has the strength to push
     * one crate. It cannot push 2 or more crates simultaneously.
     *
     * @param d The direction the player wants to move
     * @return Whether the move was successful
     */
    public boolean movePlayer(Direction d) {
        int curR = player.getR();
        int curC = player.getC();

        int newR = curR;
        int newC = curC;

        switch (d) {
            case UP:
                newR--;
                break;
            case DOWN:
                newR++;
                break;
            case LEFT:
                newC--;
                break;
            case RIGHT:
                newC++;
                break;
        }

        if (isValid(newR, newC)) {
            if (isOccupiableAndNotOccupiedWithCrate(newR, newC)) {
                ((Occupiable) cells[curR][curC]).removeOccupant();
                ((Occupiable) cells[newR][newC]).setOccupant(player);
                player.setPos(newR, newC);
                return true;
            } else if (cells[newR][newC] instanceof Occupiable) {
                if (moveCrate((Crate) ((Occupiable) cells[newR][newC]).getOccupant().get(), d)) {
                    ((Occupiable) cells[curR][curC]).removeOccupant();
                    ((Occupiable) cells[newR][newC]).setOccupant(player);
                    player.setPos(newR, newC);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Attempts to move the crate into the specified direction by 1 cell. Will only succeed if the destination
     * implements the occupiable interface and is not currently occupied.
     *
     * @param c The crate to be moved
     * @param d The desired direction to move the crate in
     * @return Whether or not the move was successful
     */
    private boolean moveCrate(Crate c, Direction d) {
        int curR = c.getR();
        int curC = c.getC();

        int newR = curR;
        int newC = curC;

        switch (d) {
            case UP:
                newR--;
                break;
            case DOWN:
                newR++;
                break;
            case LEFT:
                newC--;
                break;
            case RIGHT:
                newC++;
                break;
        }

        if (isOccupiableAndNotOccupiedWithCrate(newR, newC)) {
            ((Occupiable) cells[curR][curC]).removeOccupant();
            ((Occupiable) cells[newR][newC]).setOccupant(c);
            c.setPos(newR, newC);
            return true;
        } else {
            return false;
        }
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && r < cells.length && c >= 0 && c < cells[0].length;
    }

    /**
     * @param r The row coordinate
     * @param c The column coordinate
     * @return Whether or not the specified location on the grid is a location which implements Occupiable,
     * yet does not currently have a crate in it. Will return false if out of bounds.
     */
    public boolean isOccupiableAndNotOccupiedWithCrate(int r, int c) {
        if (!isValid(r, c)) {
            return false;
        }

        return cells[r][c] instanceof Occupiable &&
                (!((Occupiable) cells[r][c]).getOccupant().isPresent()
                        || !(((Occupiable) cells[r][c]).getOccupant().get() instanceof Crate));
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
