package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code ItemFrameUseEvent} represents a proposed item frame interaction with an
 * entity and action. The action distinguishes inserting an item, rotating the
 * displayed item, and dropping it; frame behavior checks cancellation before applying
 * the corresponding item or rotation change.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class ItemFrameUseEvent extends BlockEvent implements CancellableEvent {

    /**
     * {@code entity} identifies the interacting entity and may be {@code null} for a
     * punch without an attributed entity.
     */
    protected Entity entity;
    /**
     * Requested item frame action.
     */
    protected Action action;

    /**
     * Creates an item frame event with its entity and requested action.
     * {@code entity} may be {@code null} when a punch reaching the frame has no
     * responsible entity.
     */
    public ItemFrameUseEvent(Block block, Entity entity, Action action) {
        super(block);
        this.entity = entity;
        this.action = action;
    }

    /**
     * {@code Action} identifies the item or rotation change requested for a frame.
     */
    public enum Action {
        /**
         * Displayed item removal action.
         */
        DROP,
        /**
         * Held item insertion action.
         */
        PUT,
        /**
         * Displayed item rotation action.
         */
        ROTATE
    }
}
