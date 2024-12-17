package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.block.dto.BlockStateWithPos;

/**
 * @author daoge_cmd
 */
public class BlockFallEvent extends BlockEvent {
    public BlockFallEvent(BlockStateWithPos blockState) {
        super(blockState);
    }
}
