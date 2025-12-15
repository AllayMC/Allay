package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a block is broken.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.ANY)
public class BlockBreakEvent extends BlockEvent implements CancellableEvent {
    /**
     * The item used to break the block. Can be {@code null}.
     */
    protected ItemStack usedItem;
    /**
     * The entity breaks the block. Can be {@code null}.
     */
    protected Entity entity;

    public BlockBreakEvent(Block block, ItemStack usedItem, Entity entity) {
        super(block);
        this.usedItem = usedItem;
        this.entity = entity;
    }
}
