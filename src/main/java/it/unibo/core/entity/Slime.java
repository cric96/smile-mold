package it.unibo.core.entity;

/**
 * Represents a slime entity within a simulation world.
 * This class encapsulates the relationship of a slime with its surrounding world,
 * providing access to the world in which the slime exists.
 *
 * Instances of this class are expected to interact with the world, possibly
 * influenced by behaviors defined in implementations of {@link it.unibo.core.behaviour.SlimeBehaviour}.
 */
public class Slime {

    /**
     * The world in which this slime entity exists.
     * This reference is used to interact with the simulation world and
     * to obtain contextual information necessary for the slime's behavior.
     */
    private final World world;

    /**
     * Constructs a {@code Slime} with a reference to the world it inhabits.
     *
     * @param world The simulation world to which this slime belongs.
     *              This world is used as the context for various behaviors
     *              and interactions of the slime.
     */
    public Slime(final World world) {
        this.world = world;
    }

    /**
     * Returns the world in which this slime entity exists.
     *
     * @return The {@link World} object representing the simulation world
     *         of this slime.
     */
    public World getWorld() {
        return this.world;
    }
}
