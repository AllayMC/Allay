package org.allaymc.api.world.service;

import org.allaymc.api.entity.Entity;

/**
 * Allay Project 11/12/2023
 *
 * @author Cool_Loong
 */
public interface EntityUpdateService {
    void tick();

    void addEntity(Entity entity);

    void removeEntity(Entity entity);
}
