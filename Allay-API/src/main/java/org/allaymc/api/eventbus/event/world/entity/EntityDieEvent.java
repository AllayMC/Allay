package org.allaymc.api.eventbus.event.world.entity;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.EntityEvent;

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
