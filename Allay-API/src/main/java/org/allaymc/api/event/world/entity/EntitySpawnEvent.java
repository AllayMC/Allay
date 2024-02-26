package org.allaymc.api.event.world.entity;

import org.allaymc.api.entity.Entity;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class EntitySpawnEvent extends EntityEvent {

    public EntitySpawnEvent(Entity entity) {
        super(entity);
    }
}
