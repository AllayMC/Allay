package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
@Getter
public class BlockBreakEvent extends BlockEvent implements CancellableEvent {
    protected Dimension dimension;
    protected Vector3ic blockPos;
    /**
     * Can be null
     */
    protected ItemStack usedItem;
    /**
     * Can be null
     */
    protected EntityPlayer player;

    public BlockBreakEvent(BlockStateWithPos block, ItemStack usedItem, EntityPlayer player) {
        super(block);
        this.usedItem = usedItem;
        this.player = player;
    }
}
