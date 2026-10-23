package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.joml.Vector3ic;

/**
 * {@code EntityWakeEvent} announces the previous bed position when a sleeping
 * entity wakes. The sleeping component clears its stored sleeping position
 * before dispatch, then releases bed occupancy and broadcasts the wake
 * animation; listeners cannot cancel this notification.
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class EntityWakeEvent extends EntityEvent {

    /**
     * {@code bedPos} retains the supplied bed head position without copying.
     * The generated getter exposes the same reference, so listeners must avoid
     * mutating a vector shared with the caller.
     */
    protected final Vector3ic bedPos;

    /**
     * Creates the bed-state payload retaining its entity and position
     * references. The position is not copied here; any defensive copy required
     * for stored sleeping state belongs to the sleeping component.
     */
    public EntityWakeEvent(Entity entity, Vector3ic bedPos) {
        super(entity);
        this.bedPos = bedPos;
    }
}
