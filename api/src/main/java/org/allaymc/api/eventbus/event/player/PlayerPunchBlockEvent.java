package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerPunchBlockEvent} describes the start of a player's attempt to punch a
 * block face. The input processor dispatches it before the block's punch callback;
 * cancellation suppresses that callback but does not itself cancel the processor's
 * remaining block-breaking setup.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerPunchBlockEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Block receiving the punch attempt.
     */
    protected Block block;
    /**
     * Block face receiving the punch.
     */
    protected BlockFace blockFace;

    /**
     * Creates a {@code PlayerPunchBlockEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerPunchBlockEvent(EntityPlayer player, Block block, BlockFace blockFace) {
        super(player);
        this.block = block;
        this.blockFace = blockFace;
    }
}
