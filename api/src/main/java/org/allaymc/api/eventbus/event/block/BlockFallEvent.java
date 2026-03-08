package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;

/**
 * Called when a block begins to fall due to gravity. Examples include sand, gravel, and anvils.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class BlockFallEvent extends BlockEvent {
    public BlockFallEvent(Block block) {
        super(block);
    }
}
