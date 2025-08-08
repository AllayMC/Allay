package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.block.dto.Block;

/**
 * @author daoge_cmd
 */
public class BlockFallEvent extends BlockEvent {
    public BlockFallEvent(Block block) {
        super(block);
    }
}
