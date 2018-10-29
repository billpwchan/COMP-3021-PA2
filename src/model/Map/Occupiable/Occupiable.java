package model.Map.Occupiable;

import model.Map.Occupant.Occupant;

import java.util.Optional;

/**
 * An interface for objects which can be occupied by crates or the player
 */
public interface Occupiable {
    /**
     * @return Returns the Optional instance potentially bearing an Occupant
     */
    Optional<Occupant> getOccupant();

    /**
     * @param o The occupant to set in this position. Ignores whether or not the Occupiable instance is occupied or not.
     */
    void setOccupant(Occupant o);

    /**
     * Removes the occupant from the Occupiable instance, or does nothing if it's not occupied
     */
    void removeOccupant();
}
