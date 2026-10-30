package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.joml.Vector3ic;

/**
 * {@code EntitySleepEvent} carries a proposed bed head position when an awake
 * entity attempts sleep. Cancellation prevents storing the sleeping position,
 * changing bed occupancy, and broadcasting sleep state; the event retains the
 * supplied position reference for listeners during dispatch.
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class EntitySleepEvent extends EntityEvent implements CancellableEvent {

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
    public EntitySleepEvent(Entity entity, Vector3ic bedPos) {
        super(entity);
        this.bedPos = bedPos;
    }
}
