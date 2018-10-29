package model.Map.Occupiable;

import model.Map.Occupant.Crate;

/**
 * A destination tile. To win the game, we must push the crate with the corresponding ID onto this tile
 */
public class DestTile extends Tile {

    public DestTile() {
    }

    public boolean isCompleted() {
        return this.getOccupant().isPresent() && this.getOccupant().get() instanceof Crate;
    }
}
