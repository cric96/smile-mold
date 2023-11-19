package it.unibo.core.behaviour;

import it.unibo.core.entity.World;

/**
 * Represents a behavior specific to a {@code World} entity.
 * This interface extends the {@link Behaviour} interface, specializing it
 * for use with {@code World} type entities.
 *
 * Implementations of this interface will define the behavior logic to be
 * applied to a {@code World} instance through the {@code update} method
 * inherited from {@link Behaviour}.
 */
public interface WorldBehaviour extends Behaviour<World> { }

