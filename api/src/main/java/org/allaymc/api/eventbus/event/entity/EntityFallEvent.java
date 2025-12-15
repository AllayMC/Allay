package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when an entity lands on the ground after falling.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.COMPUTE)
public class EntityFallEvent extends EntityEvent implements CancellableEvent {
    protected double fallDistance;

    public EntityFallEvent(Entity entity, double fallDistance) {
        super(entity);
        this.fallDistance = fallDistance;
    }
}
