package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Event triggered when a shelf is used.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class ShelfUseEvent extends BlockEvent implements CancellableEvent {

    /**
     * The entity that is using the shelf.
     */
    protected Entity entity;
    /**
     * The action performed on the shelf.
     */
    protected Action action;

    public ShelfUseEvent(Block block, Entity entity, Action action) {
        super(block);
        this.entity = entity;
        this.action = action;
    }

    public enum Action {
        SINGLE_SWAP,
        MULTI_SWAP
    }
}
