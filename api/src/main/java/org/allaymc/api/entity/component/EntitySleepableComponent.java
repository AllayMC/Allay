package org.allaymc.api.entity.component;

import org.joml.Vector3ic;

/**
 * {@code EntitySleepableComponent} exposes an entity's sleeping state and the
 * bed position associated with it. Sleep and wake operations synchronize
 * entity presentation with bed occupancy through the server implementation,
 * while a {@code null} sleeping position represents an awake entity.
 */
public interface EntitySleepableComponent extends EntityComponent {

    /**
     * Attempts to put the entity to sleep at the supplied bed head position.
     * Already sleeping entities are unchanged, and cancellation of
     * {@code EntitySleepEvent} prevents sleep and occupancy updates.
     */
    void sleep(Vector3ic bedPos);

    /**
     * Wakes a sleeping entity and publishes the corresponding wake event and
     * presentation. An awake entity is unchanged; the server clears its
     * sleeping position before dispatching the wake notification.
     */
    void wake();

    /**
     * Returns whether this component currently has a sleeping bed position.
     */
    default boolean isSleeping() {
        return getSleepingPos() != null;
    }

    /**
     * Returns the stored bed head position, or {@code null} while awake. The
     * server returns its retained position through a read-only vector
     * interface; callers must not cast and mutate that storage.
     */
    Vector3ic getSleepingPos();
}
