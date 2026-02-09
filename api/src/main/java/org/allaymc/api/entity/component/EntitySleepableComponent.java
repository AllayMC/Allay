package org.allaymc.api.entity.component;

import org.joml.Vector3ic;

/**
 * Component for entities that can sleep in a bed (e.g., players, villagers).
 */
public interface EntitySleepableComponent extends EntityComponent {

    /**
     * Make the entity sleep at the given bed position.
     *
     * @param bedPos the position of the bed's head block
     */
    void sleep(Vector3ic bedPos);

    /**
     * Wake the entity up from sleep.
     */
    void wake();

    /**
     * Check if the entity is currently sleeping.
     *
     * @return {@code true} if the entity is sleeping
     */
    default boolean isSleeping() {
        return getSleepingPos() != null;
    }

    /**
     * Get the position of the bed that the entity is sleeping in.
     *
     * @return the bed position, or {@code null} if not sleeping
     */
    Vector3ic getSleepingPos();
}
