package it.unibo.core.behaviour;

import it.unibo.core.Position;
import it.unibo.core.entity.Patch;
import it.unibo.core.entity.Slime;
import it.unibo.core.entity.World;

import java.util.*;

/**
 * Represents the aggregation behavior of a slime entity in response to pheromone concentrations.
 * This behavior is defined by the slime's attraction to areas of high pheromone concentration.
 * When the concentration on a patch exceeds a certain threshold, the slime moves towards the patch
 * with the highest concentration. Otherwise, it moves randomly.
 *
 * This class implements the {@link SlimeBehaviour} interface, providing specific behavior logic
 * for slime entities.
 */
public class Aggregation implements SlimeBehaviour {
    /*
     * Threshold for making a decision based on probability, rather than pheromone concentration.
     */
    private static final double probabilityThreshold = 0.5;

    private final int seed;
    private final double threshold;
    private final double pheromoneDropped;
    private final Random random;

    /**
     * Constructs an {@code Aggregation} object with specified parameters.
     *
     * @param seed             The seed for the random number generator, ensuring reproducible behavior.
     * @param threshold        The pheromone concentration threshold for directional movement.
     * @param pheromoneDropped The amount of pheromone dropped by the slime after each movement.
     */
    public Aggregation(final int seed, final double threshold, final double pheromoneDropped) {
        this.seed = seed;
        this.threshold = threshold;
        this.pheromoneDropped = pheromoneDropped;
        this.random = new Random(this.seed);
    }

    /**
     * Updates the movement and pheromone-dropping behavior of a slime entity.
     * The slime moves towards the area with the highest pheromone concentration
     * if the concentration on its current patch is above a certain threshold and
     * a probabilistic condition is met. Otherwise, it moves randomly.
     * After moving, the slime drops a specified amount of pheromones on the patch.
     *
     * @param slime The slime entity whose behavior is being updated.
     */
    @Override
    public void update(Slime slime) {
        final World world = slime.getWorld();
        final Position position = world.getSlimePosition(slime);
        final Patch patch = world.getPatch(position);
        final Collection<Patch> neighborhood = world.getNeighborhood(patch);
        final boolean followHigh = this.random.nextDouble() < probabilityThreshold;
        // if the slime is on a patch with a high concentration of pheromones, it moves towards the highest concentration of pheromones
        if(patch.getPheromone() >= this.threshold && followHigh) {
            final Patch max = neighborhood.stream().max(Comparator.comparingDouble(Patch::getPheromone)).get();
            // random perturbation
            world.moveSlime(slime, max.getPosition());
        } else {
            // otherwise, it moves randomly
            var shuffle = new ArrayList<>(neighborhood);
            Collections.shuffle(shuffle, this.random);
            world.moveSlime(slime, shuffle.get(0).getPosition());
        }
        // the slime drops pheromones on the patch it is on
        patch.setPheromone(patch.getPheromone() + this.pheromoneDropped);

    }
}
