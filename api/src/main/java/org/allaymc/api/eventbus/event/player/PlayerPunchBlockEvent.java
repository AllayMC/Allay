package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a player punches a block.
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerPunchBlockEvent extends PlayerEvent implements CancellableEvent {
    /**
     * The block being punched.
     */
    protected Block block;
    /**
     * The face of the block being punched.
     */
    protected BlockFace blockFace;

    public PlayerPunchBlockEvent(EntityPlayer player, Block block, BlockFace blockFace) {
        super(player);
        this.block = block;
        this.blockFace = blockFace;
    }
}
