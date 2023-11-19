package it.unibo.core.entity;

import it.unibo.core.Position;

import java.util.Collection;

/**
 * Interface representing a simulation world. This world is defined by its dimensions and
 * contains entities such as slimes and patches. It provides methods to interact with
 * these entities and to manage their positions and states within the world.
 */
public interface World {

    /**
     * Returns the width of the simulation world.
     *
     * @return The width of the world.
     */
    int getWidth();

    /**
     * Returns the height of the simulation world.
     *
     * @return The height of the world.
     */
    int getHeight();

    /**
     * Retrieves the current position of a given slime in the world.
     *
     * @param slime The slime whose position is to be retrieved.
     * @return The current position of the specified slime.
     */
    Position getSlimePosition(Slime slime);

    /**
     * Moves a slime to a specified position within the world.
     *
     * @param slime     The slime to be moved.
     * @param position  The new position to which the slime will be moved.
     */
    void moveSlime(Slime slime, Position position);

    /**
     * Returns a collection of all slimes present in the world.
     *
     * @return A collection of {@link Slime} objects representing all slimes in the world.
     */
    Collection<Slime> getSlimes();

    /**
     * Returns a collection of all patches in the world.
     *
     * @return A collection of {@link Patch} objects representing all patches in the world.
     */
    Collection<Patch> getPatches();

    /**
     * Retrieves the neighboring patches around a given patch.
     *
     * @param patch The patch for which neighboring patches are to be retrieved.
     * @return A collection of {@link Patch} objects representing the neighbors of the specified patch.
     */
    Collection<Patch> getNeighborhood(Patch patch);

    /**
     * Gets the patch at a specified position within the world.
     *
     * @param position The position for which the patch is to be retrieved.
     * @return The {@link Patch} at the specified position.
     */
    Patch getPatch(Position position);
}
