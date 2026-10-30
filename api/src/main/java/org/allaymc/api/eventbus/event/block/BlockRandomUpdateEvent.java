package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code BlockRandomUpdateEvent} represents a random tick selected for the supplied
 * block snapshot. Chunk ticking dispatches the event before the block behavior
 * receives its random update callback, allowing cancellation to suppress that
 * selected update without changing the block itself.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class BlockRandomUpdateEvent extends BlockEvent implements CancellableEvent {
    /**
     * Creates a random tick event for the supplied block snapshot.
     */
    public BlockRandomUpdateEvent(Block block) {
        super(block);
    }
}
