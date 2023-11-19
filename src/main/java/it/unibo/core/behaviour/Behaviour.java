package it.unibo.core.behaviour;

/**
 * Represents a behavior that can be applied to an entity of type {@code E}.
 * This interface defines a single method {@code update}, which encapsulates
 * the behavior logic to be applied to the entity.
 *
 * @param <E> The type of entity to which this behavior can be applied.
 */
public interface Behaviour<E> {

    /**
     * Updates the state or performs an action on the given entity based on this behavior.
     * This method is intended to encapsulate the core logic of the behavior and apply it
     * to the specified entity.
     *
     * @param entity The entity to which the behavior is applied. This entity should be
     *               of type {@code E}, and the method will manipulate or use this entity
     *               according to the defined behavior.
     */
    void update(E entity);
}
