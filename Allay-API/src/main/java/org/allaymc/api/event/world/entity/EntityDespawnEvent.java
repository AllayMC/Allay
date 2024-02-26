package org.allaymc.api.event.world.entity;

import org.allaymc.api.entity.Entity;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class EntityDespawnEvent extends EntityEvent {
    public EntityDespawnEvent(Entity entity) {
        super(entity);
    }
}
