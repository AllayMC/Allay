package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
@Getter
public class BlockBreakEvent extends BlockEvent implements CancellableEvent {
    /**
     * Can be null
     */
    protected ItemStack usedItem;
    /**
     * Can be null
     */
    protected Entity entity;

    public BlockBreakEvent(Block block, ItemStack usedItem, Entity entity) {
        super(block);
        this.usedItem = usedItem;
        this.entity = entity;
    }
}
