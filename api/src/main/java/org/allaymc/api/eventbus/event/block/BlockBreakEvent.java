package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.dto.BlockStateWithPos;
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

    public BlockBreakEvent(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        super(blockState);
        this.usedItem = usedItem;
        this.entity = entity;
    }
}
