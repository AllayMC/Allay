package org.allaymc.api.eventbus.event.world.entity;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.EntityEvent;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class EntityFallEvent extends EntityEvent implements CancellableEvent {
    protected float fallDistance;

    public EntityFallEvent(Entity entity, float fallDistance) {
        super(entity);
        this.fallDistance = fallDistance;
    }
}
