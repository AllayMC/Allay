package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class EntityFallEvent extends EntityEvent implements CancellableEvent {
    protected float fallDistance;

    public EntityFallEvent(Entity entity, float fallDistance) {
        super(entity);
        this.fallDistance = fallDistance;
    }
}
