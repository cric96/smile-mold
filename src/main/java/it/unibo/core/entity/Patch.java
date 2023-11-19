package it.unibo.core.entity;

import it.unibo.core.Position;

/**
 * Represents a patch in the simulation world, characterized by its position
 * and a pheromone level. The pheromone level indicates the concentration of
 * pheromones at this patch, which is a factor in the behavior of certain entities
 * like slimes.
 *
 * The pheromone level is constrained to be within the range of 0.0 to 1.0.
 */
public class Patch {

    private final Position position;

    private double pheromone;

    /**
     * Constructs a {@code Patch} with a specific position and initial pheromone level.
     *
     * @param position  The position of the patch in the simulation world.
     * @param pheromone The initial level of pheromones at this patch.
     *                  Must be between 0.0 and 1.0.
     * @throws IllegalArgumentException if the pheromone level is not within the range 0.0 to 1.0.
     */
    public Patch(final Position position, final double pheromone) {
        if (pheromone < 0.0 || pheromone > 1.0) {
            throw new IllegalArgumentException("Pheromone must be between 0.0 and 1.0");
        }
        this.position = position;
        this.pheromone = pheromone;
    }

    /**
     * Returns the position of this patch.
     *
     * @return The position of this patch in the simulation world.
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Returns the current level of pheromones at this patch.
     *
     * @return The current pheromone level.
     */
    public double getPheromone() {
        return this.pheromone;
    }

    /**
     * Sets the level of pheromones at this patch.
     *
     * @param pheromone The new pheromone level to set.
     *                  Should be within the range of 0.0 to 1.0.
     */
    public void setPheromone(double pheromone) {
        this.pheromone = pheromone;
    }
}
