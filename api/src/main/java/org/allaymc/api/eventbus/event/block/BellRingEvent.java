package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a bell is rung.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class BellRingEvent extends BlockEvent implements CancellableEvent {
    /**
     * The cause of the bell ringing.
     */
    protected RingCause cause;
    /**
     * The entity that caused the bell to ring, if applicable. Can be {@code null}.
     */
    protected Entity entity;
    /**
     * The direction from which the bell was struck (0-3).
     */
    protected int direction;

    public BellRingEvent(Block block, RingCause cause, Entity entity, int direction) {
        super(block);
        this.cause = cause;
        this.entity = entity;
        this.direction = direction;
    }

    /**
     * The cause of a bell ringing.
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
         * Unknown cause.
         */
        UNKNOWN
    }
}
