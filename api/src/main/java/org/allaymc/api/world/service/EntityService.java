package org.allaymc.api.world.service;

import org.allaymc.api.entity.Entity;

/**
 * EntityService is responsible for adding, removing and ticking entities.
 *
 * @author Cool_Loong
 */
public interface EntityService {
    default void addEntity(Entity entity) {
        addEntity(entity, () -> {});
    }

    /**
     * Add an entity to the world.
     * <p>
     * The entity won't be added to the world immediately.
     * It will be added to the world in the next tick, and
     * the callback will be called.
     *
     * @param entity   The entity pending to be spawned
     * @param callback The callback to be called after the entity is added to the world
     */
    void addEntity(Entity entity, Runnable callback);

    default void removeEntity(Entity entity) {
        removeEntity(entity, () -> {});
    }

    /**
     * Remove an entity from the world.
     * <p>
     * The entity won't be removed from the world immediately.
     * It will be removed from the world in the next tick, and
     * the callback will be called.
     *
     * @param entity   The entity pending to be despawned
     * @param callback The callback to be called after the entity is removed from the world
     */
    void removeEntity(Entity entity, Runnable callback);
}
