package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code BellRingEvent} represents a bell ringing request with its trigger,
 * optional source entity, and animation direction. The bell component dispatches
 * this event after validating the hit direction, and cancellation prevents the
 * block entity from receiving the ring request.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class BellRingEvent extends BlockEvent implements CancellableEvent {
    /**
     * Trigger reported for this ringing request.
     */
    protected RingCause cause;
    /**
     * {@code entity} identifies the player or projectile that triggered the ring.
     * Redstone requests use {@code null} because no source entity participates.
     */
    protected Entity entity;
    /**
     * Animation direction: {@code 0} north, {@code 1} east, {@code 2} south, or
     * {@code 3} west.
     */
    protected int direction;

    /**
     * Creates a bell ringing request retaining its block, trigger, and optional
     * source entity. {@code direction} uses the bell animation encoding;
     * construction stores the supplied value without validating the attachment or
     * direction.
     */
    public BellRingEvent(Block block, RingCause cause, Entity entity, int direction) {
        super(block);
        this.cause = cause;
        this.entity = entity;
        this.direction = direction;
    }

    /**
     * {@code RingCause} identifies the trigger reported for a bell ringing
     * request. Event producers select the applicable value so listeners can
     * distinguish player, projectile, redstone, item, and unclassified requests.
     */
    public enum RingCause {
        /**
         * A player interacted with the bell.
         */
        PLAYER_INTERACTION,
        /**
         * A projectile hit the bell.
         */
        PROJECTILE,
        /**
         * The bell was activated by redstone.
         */
        REDSTONE,
        /**
         * A dropped item collided with the bell.
         */
        DROPPED_ITEM,
        /**
         * Unclassified bell ringing trigger.
         */
        UNKNOWN
    }
}
