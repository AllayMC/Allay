package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when fire destroys a block.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class BlockBurnEvent extends BlockEvent implements CancellableEvent {
    public BlockBurnEvent(Block block) {
        super(block);
    }
}
