package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
public class EntityFallEvent extends EntityEvent implements CancellableEvent {
    protected double fallDistance;

    public EntityFallEvent(Entity entity, double fallDistance) {
        super(entity);
        this.fallDistance = fallDistance;
    }
}
