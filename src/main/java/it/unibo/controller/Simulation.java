package it.unibo.controller;

import it.unibo.core.behaviour.SlimeBehaviour;
import it.unibo.core.behaviour.WorldBehaviour;
import it.unibo.core.entity.World;

import java.util.List;


/**
 * Represents a simulation environment that manages the interactions and behaviors of various elements within a virtual world.
 * This class encapsulates the logic for updating the state of the world and its inhabitants, as well as notifying observers about changes.
 * It maintains a collection of behaviors that are applied to the world and its slimes, allowing for dynamic and customizable simulations.
 *
 * <p>Key components of the simulation include:</p>
 * <ul>
 *     <li>A {@link World} instance that represents the simulation environment.</li>
 *     <li>A list of {@link WorldBehaviour} instances that define behaviors applicable to the world.</li>
 *     <li>A list of {@link SlimeBehaviour} instances that define behaviors applicable to the slimes in the world.</li>
 *     <li>A list of {@link SimulationObserver} instances that are notified of changes in the world.</li>
 * </ul>
 */
public class Simulation {
    private final World world;
    private final List<WorldBehaviour> worldBehaviours;
    private final List<SlimeBehaviour> behaviours;
    private final List<SimulationObserver> observers;

    /**
     * Constructs a Simulation instance with specified world, behaviors, and observers.
     *
     * @param world            The {@link World} instance representing the simulation environment.
     * @param worldBehaviours  A list of {@link WorldBehaviour} instances defining behaviors for the world.
     * @param behaviours       A list of {@link SlimeBehaviour} instances defining behaviors for the slimes.
     * @param observers        A list of {@link SimulationObserver} instances to be notified about world changes.
     */
    public Simulation(
            final World world,
            final List<WorldBehaviour> worldBehaviours,
            final List<SlimeBehaviour> behaviours,
            final List<SimulationObserver> observers
    ) {
        this.world = world;
        this.worldBehaviours = worldBehaviours;
        this.behaviours = behaviours;
        this.observers = observers;
    }

    /**
     * Updates the state of the simulation. This method applies each behavior to its respective target (world or slimes)
     * and notifies observers of any changes to the world.
     *
     * <p>Steps performed in the update process:</p>
     * <ul>
     *     <li>Applies each {@link WorldBehaviour} to the world.</li>
     *     <li>Applies each {@link SlimeBehaviour} to every slime in the world.</li>
     *     <li>Notifies each {@link SimulationObserver} of changes to the world.</li>
     * </ul>
     */
    public void update() {
        this.worldBehaviours.forEach(behaviour -> behaviour.update(this.world));
        this.world.getSlimes().forEach(slime -> {
            this.behaviours.forEach(behaviour -> behaviour.update(slime));
        });
        this.observers.forEach(observer -> observer.onWorldChanges(this.world));
    }
}
