package model.Map.Occupiable;

import model.Map.Cell;
import model.Map.Occupant.Occupant;

import java.util.Optional;

/**
 * A floor tile that can be occupied by a player or crates
 */
public class Tile extends Cell implements Occupiable {

    private Occupant occupant;

    @Override
    public void removeOccupant() {
        this.occupant = null;
    }

    @Override
    public Optional<Occupant> getOccupant() {
        return Optional.ofNullable(occupant);
    }

    @Override
    public void setOccupant(Occupant o) {
        this.occupant = o;
    }
}