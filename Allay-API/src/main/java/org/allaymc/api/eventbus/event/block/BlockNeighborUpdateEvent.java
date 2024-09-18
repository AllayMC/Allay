package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
public class BlockNeighborUpdateEvent extends BlockEvent implements CancellableEvent {
    protected BlockStateWithPos neighborBlock;
    protected BlockFace blockFace;

    public BlockNeighborUpdateEvent(BlockStateWithPos block, BlockStateWithPos neighborBlock, BlockFace blockFace) {
        super(block);
        this.neighborBlock = neighborBlock;
        this.blockFace = blockFace;
    }
}
