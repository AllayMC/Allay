package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code ShelfUseEvent} represents a proposed shelf item exchange with an interacting
 * entity. The action distinguishes a single slot exchange from a powered shelf group
 * exchange, and shelf behavior checks cancellation before modifying the relevant
 * shelf and player inventories.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class ShelfUseEvent extends BlockEvent implements CancellableEvent {

    /**
     * Entity interacting with shelf.
     */
    protected Entity entity;
    /**
     * Requested shelf exchange action.
     */
    protected Action action;

    /**
     * Creates a shelf interaction event containing its entity and exchange action.
     */
    public ShelfUseEvent(Block block, Entity entity, Action action) {
        super(block);
        this.entity = entity;
        this.action = action;
    }

    /**
     * {@code Action} identifies the scope of a requested shelf item exchange and
     * distinguishes one clicked slot from a powered shelf group.
     */
    public enum Action {
        /**
         * Single shelf slot exchange.
         */
        SINGLE_SWAP,
        /**
         * Powered shelf group exchange.
         */
        MULTI_SWAP
    }
}
