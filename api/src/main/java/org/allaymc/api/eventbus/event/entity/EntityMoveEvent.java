package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3dc;

/**
 * {@code EntityMoveEvent} carries source and proposed destination references
 * for an entity location update. The movement path uses the listener-adjusted
 * {@code to} location after dispatch, subject to immobility handling; this
 * event does not implement the cancellation contract despite the movement path
 * checking its dispatch result.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.COMPUTE)
public class EntityMoveEvent extends EntityEvent {
    /**
     * {@code from} retains the source location reference supplied to the
     * event. The getter exposes the same object; listeners must preserve the
     * source and keep any accepted destination usable for the location update.
     */
    protected Location3dc from;
    /**
     * {@code to} retains the proposed destination reference supplied to the
     * event. The getter exposes the same object; listeners must preserve the
     * source and keep any accepted destination usable for the location update.
     */
    @Setter
    protected Location3dc to;

    /**
     * Creates a movement payload retaining both supplied location references
     * directly. Replacing {@code to} changes the proposed movement
     * destination, while mutation of a mutable source object affects the
     * original reference as well.
     */
    public EntityMoveEvent(Entity entity, Location3dc from, Location3dc to) {
        super(entity);
        this.from = from;
        this.to = to;
    }
}
