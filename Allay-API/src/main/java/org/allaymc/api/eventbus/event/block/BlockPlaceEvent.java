package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2024/8/31
 *
 * @author daoge_cmd
 */
@Getter
public class BlockPlaceEvent extends BlockEvent implements CancellableEvent {

    protected BlockState blockReplaced;
    /**
     * can be null
     */
    protected ItemStack itemUsed;
    /**
     * can be null
     */
    protected EntityPlayer player;
    /**
     * can be null
     */
    protected PlayerInteractInfo interactInfo;

    public BlockPlaceEvent(BlockStateWithPos blockPlaced, BlockState blockReplaced, ItemStack itemUsed, EntityPlayer player, PlayerInteractInfo interactInfo) {
        super(blockPlaced);
        this.blockReplaced = blockReplaced;
        this.itemUsed = itemUsed;
        this.player = player;
        this.interactInfo = interactInfo;
    }
}
