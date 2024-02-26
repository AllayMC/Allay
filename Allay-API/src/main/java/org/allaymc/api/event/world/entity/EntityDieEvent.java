package org.allaymc.api.event.world.entity;

import org.allaymc.api.entity.Entity;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class EntityDieEvent extends EntityEvent {
    public EntityDieEvent(Entity entity) {
        super(entity);
    }
}
