package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
public class BlockNeighborUpdateEvent extends BlockEvent implements CancellableEvent {
    protected Block neighbor;
    protected BlockFace blockFace;

    public BlockNeighborUpdateEvent(Block block, Block neighbor, BlockFace blockFace) {
        super(block);
        this.neighbor = neighbor;
        this.blockFace = blockFace;
    }
}
