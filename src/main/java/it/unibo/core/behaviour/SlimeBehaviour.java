package it.unibo.core.behaviour;

import it.unibo.core.entity.Slime;

/**
 * Represents a behavior specifically for a {@code Slime} entity.
 * This interface extends the {@link Behaviour} interface, specializing it
 * for application to instances of {@code Slime}.
 *
 * Implementations of this interface will define behavior logic that can be
 * applied to a {@code Slime} object through the {@code update} method
 * inherited from the {@link Behaviour} interface.
 */
public interface SlimeBehaviour extends Behaviour<Slime> { }

