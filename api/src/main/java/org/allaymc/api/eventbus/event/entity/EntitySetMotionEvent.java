package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.joml.Vector3dc;

/**
 * {@code EntitySetMotionEvent} carries a proposed entity velocity for motion
 * assignment. Cancellation prevents the assignment or player motion packet;
 * accepted events supply the listener-adjusted {@code motion}, which the
 * ordinary physics component copies into its stored velocity after dispatch.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.DIMENSION)
@CallerThread(ThreadType.COMPUTE)
public class EntitySetMotionEvent extends EntityEvent implements CancellableEvent {
    /**
     * {@code motion} retains the proposed velocity reference in blocks per
     * tick. Listeners may replace it, and the accepting physics or player
     * handler reads the final reference after dispatch.
     */
    protected Vector3dc motion;

    /**
     * Creates a motion proposal retaining the supplied velocity reference
     * without copying. The constructor does not reject {@code null} or
     * nonfinite components; listeners must leave a usable vector for the
     * accepting motion handler.
     */
    public EntitySetMotionEvent(Entity entity, Vector3dc motion) {
        super(entity);
        this.motion = motion;
    }
}
