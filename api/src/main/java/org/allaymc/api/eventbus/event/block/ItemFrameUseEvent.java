package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Event triggered when an item frame is used.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class ItemFrameUseEvent extends BlockEvent implements CancellableEvent {

    /**
     * The entity that is using the item frame.
     */
    protected Entity entity;
    /**
     * The action performed on the item frame.
     */
    protected Action action;

    public ItemFrameUseEvent(Block block, Entity entity, Action action) {
        super(block);
        this.entity = entity;
        this.action = action;
    }

    public enum Action {
        DROP,
        PUT,
        ROTATE
    }
}
