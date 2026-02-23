package org.allaymc.api.entity.ai.behavior;

import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * Executes a behavior's logic each tick while active.
 *
 * @author daoge_cmd
 */
public interface BehaviorExecutor {

    /**
     * Execute this behavior for the given entity.
     *
     * @param entity the entity executing the behavior
     * @return {@code true} if execution should continue, {@code false} to stop
     */
    boolean execute(EntityIntelligent entity);

    /**
     * Called when this behavior is interrupted by a higher priority behavior.
     *
     * @param entity the entity
     */
    default void onInterrupt(EntityIntelligent entity) {
    }

    /**
     * Called when this behavior starts executing.
     *
     * @param entity the entity
     */
    default void onStart(EntityIntelligent entity) {
    }

    /**
     * Called when this behavior stops executing normally (not interrupted).
     *
     * @param entity the entity
     */
    default void onStop(EntityIntelligent entity) {
    }
}
