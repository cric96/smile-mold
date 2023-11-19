package it.unibo.core.behaviour;

import it.unibo.core.entity.World;

/**
 * Represents the behavior of evaporation within a simulated world.
 * This class implements the {@link WorldBehaviour} interface, specifically
 * simulating the process of evaporation by reducing the pheromone levels
 * in each patch of the world based on a defined evaporation rate.
 */
public class Evaporation implements WorldBehaviour {

    private final double evaporationRate;

    /**
     * Constructs an {@code Evaporation} object with a specified evaporation rate.
     *
     * @param evaporationRate The rate at which pheromones evaporate.
     *                        This value is used to calculate the reduction
     *                        in pheromone levels in each update.
     */
    public Evaporation(final double evaporationRate) {
        this.evaporationRate = evaporationRate;
    }

    /**
     * Updates the pheromone levels in each patch of the world, simulating
     * evaporation. This method is called to apply the evaporation behavior
     * to the provided {@code World} object.
     *
     * @param world The world in which evaporation is to be simulated.
     *              This method iterates over each patch in the world,
     *              reducing the pheromone level based on the evaporation rate.
     */
    public void update(World world) {
        world.getPatches().forEach(patch -> {
            double evaporated = patch.getPheromone() * this.evaporationRate;
            patch.setPheromone(evaporated);
        });
    }
}
