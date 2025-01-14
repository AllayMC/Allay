package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
@Getter
public class PlayerBlockPickEvent extends PlayerEvent implements CancellableEvent {
    protected BlockStateWithPos clickedBlock;
    @Setter
    protected boolean includeBlockEntity;
    @Setter
    protected ItemStack itemBlock;

    public PlayerBlockPickEvent(EntityPlayer player, BlockStateWithPos clickedBlock, boolean includeBlockEntity, ItemStack itemBlock) {
        super(player);
        this.clickedBlock = clickedBlock;
        this.includeBlockEntity = includeBlockEntity;
        this.itemBlock = itemBlock;
    }
}
