package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerPickBlockEvent extends PlayerEvent implements CancellableEvent {
    protected BlockStateWithPos clickedBlock;
    protected boolean addUserData;
    @Setter
    protected ItemStack itemBlock;

    public PlayerPickBlockEvent(EntityPlayer player, BlockStateWithPos clickedBlock, boolean addUserData, ItemStack itemBlock) {
        super(player);
        this.clickedBlock = clickedBlock;
        this.addUserData = addUserData;
        this.itemBlock = itemBlock;
    }
}
