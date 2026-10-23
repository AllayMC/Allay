package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code BlockScheduleUpdateEvent} represents a due scheduled update for the supplied
 * block snapshot. Chunk ticking dispatches the event before invoking the block
 * behavior's scheduled update hook, and cancellation prevents the hook from running
 * for that due update.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class BlockScheduleUpdateEvent extends BlockEvent implements CancellableEvent {
    /**
     * Creates a scheduled tick event for the supplied block snapshot.
     */
    public BlockScheduleUpdateEvent(Block block) {
        super(block);
    }
}
