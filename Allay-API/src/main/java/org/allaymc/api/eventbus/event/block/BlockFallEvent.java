package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.block.dto.BlockStateWithPos;

/**
 * Allay Project 2024/8/8
 *
 * @author daoge_cmd
 */
public class BlockFallEvent extends BlockEvent {
    public BlockFallEvent(BlockStateWithPos block) {
        super(block);
    }
}
