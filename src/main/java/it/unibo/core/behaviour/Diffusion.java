package it.unibo.core.behaviour;

import it.unibo.core.entity.World;

/**
 * Represents the behavior of diffusion within a simulated world.
 * This class implements the {@link WorldBehaviour} interface, specifically
 * simulating the process of diffusion by distributing pheromone levels
 * from each patch to its neighboring patches based on a defined diffusion rate.
 */
public class Diffusion implements WorldBehaviour {
    private static final double DEFAULT_DIFFUSION_THRESHOLD = 1;

    private final double diffusionRate;

    /**
     * Constructs a {@code Diffusion} object with a specified diffusion rate.
     *
     * @param diffusionRate The rate at which pheromones diffuse to neighboring patches.
     *                      This value is used to calculate the amount of pheromones
     *                      distributed from each patch to its neighbors.
     */
    public Diffusion(double diffusionRate) {
        this.diffusionRate = diffusionRate;
    }

    /**
     * Updates the pheromone levels in each patch of the world and its neighbors,
     * simulating diffusion. This method applies the diffusion behavior to the
     * provided {@code World} object.
     *
     * @param world The world in which diffusion is to be simulated.
     *              This method iterates over each patch in the world.
     *              If a patch has a pheromone level of 1 or more, it distributes
     *              a portion of its pheromones (based on the diffusion rate) to
     *              each of its neighboring patches.
     */
    @Override
    public void update(World world) {
        world.getPatches().forEach(patch -> {
            if (patch.getPheromone() < DEFAULT_DIFFUSION_THRESHOLD) return; // No pheromones to diffuse
            double diffused = patch.getPheromone() * this.diffusionRate;
            world.getNeighborhood(patch).forEach(neighbour -> {
                neighbour.setPheromone(neighbour.getPheromone() + diffused);
            });
        });
    }
}
