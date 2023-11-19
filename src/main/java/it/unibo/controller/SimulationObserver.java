package it.unibo.controller;

import it.unibo.core.entity.World;

/**
 * This interface represents an observer for simulation changes.
 * Implementers of this interface are notified whenever there are changes
 * in the simulation world.
 *
 * The notification is done through the {@code onWorldChanges} method,
 * which provides the updated state of the simulation world.
 */
public interface SimulationObserver {

    /**
     * Called when there are changes in the simulation world.
     *
     * Implementers of this method will receive the updated state of the world
     * and can perform actions or updates based on the new state.
     *
     * @param world The current state of the simulation world after changes.
     *              This object is expected to encapsulate all relevant
     *              details about the simulation world's current state.
     */
    void onWorldChanges(final World world);
}
