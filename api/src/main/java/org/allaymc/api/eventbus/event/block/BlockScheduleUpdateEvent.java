package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a block receives a scheduled tick update.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class BlockScheduleUpdateEvent extends BlockEvent implements CancellableEvent {
    public BlockScheduleUpdateEvent(Block block) {
        super(block);
    }
}
