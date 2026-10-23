package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code EntityFallEvent} carries the fall distance used when an entity lands.
 * Cancellation suppresses internal fall handling and the standing block's
 * landing callback, while the physics component resets accumulated fall
 * distance either way; accepted events pass the listener-adjusted distance to
 * internal handlers.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.DIMENSION)
public class EntityFallEvent extends EntityEvent implements CancellableEvent {
    /**
     * {@code fallDistance} records the proposed landing distance in blocks.
     * Internal fall handlers receive its listener-adjusted value when the
     * physics component accepts this event.
     */
    protected double fallDistance;

    /**
     * Creates a landing proposal with the entity and fall distance in blocks.
     */
    public EntityFallEvent(Entity entity, double fallDistance) {
        super(entity);
        this.fallDistance = fallDistance;
    }
}
