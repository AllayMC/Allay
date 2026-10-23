package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;

/**
 * {@code BlockFallEvent} represents the start of a block transition into a falling
 * entity. Falling components dispatch it before adding the entity and removing the
 * original block; this event supplies a notification without implementing the
 * cancellable event contract.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class BlockFallEvent extends BlockEvent {
    /**
     * Creates a falling notification for the block leaving its position.
     */
    public BlockFallEvent(Block block) {
        super(block);
    }
}
