package org.allaymc.api.world.service;

import org.allaymc.api.entity.Entity;

/**
 * Allay Project 11/12/2023
 *
 * @author Cool_Loong
 */
public interface EntityService {
    default void addEntity(Entity entity) {
        addEntity(entity, () -> {});
    }

    /**
     * NOTICE: Before call this method, you should make sure the chunk which this entity will be spawned in has been loaded!
     *
     * @param entity   The entity pending to be spawned
     * @param callback Called after the entity been pawned
     */
    void addEntity(Entity entity, Runnable callback);

    default void removeEntity(Entity entity) {
        removeEntity(entity, () -> {});
    }

    void removeEntity(Entity entity, Runnable callback);
}
