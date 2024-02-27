package org.allaymc.api.world.service;

import org.allaymc.api.entity.Entity;

/**
 * Allay Project 11/12/2023
 *
 * @author Cool_Loong
 */
public interface EntityService {
    void tick();

    default void addEntity(Entity entity) {
        addEntity(entity, () -> {});
    }

    void addEntity(Entity entity, Runnable callback);

    default void removeEntity(Entity entity) {
        removeEntity(entity, () -> {});
    }

    void removeEntity(Entity entity, Runnable callback);
}
