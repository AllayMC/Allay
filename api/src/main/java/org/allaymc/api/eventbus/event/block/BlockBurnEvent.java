package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code BlockBurnEvent} represents a fire-driven attempt to destroy the supplied
 * block snapshot. Fire behavior checks cancellation before replacing the target with
 * air, while its separate TNT priming branch can still run after cancellation.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class BlockBurnEvent extends BlockEvent implements CancellableEvent {
    /**
     * Creates a fire burn event for the supplied block snapshot.
     */
    public BlockBurnEvent(Block block) {
        super(block);
    }
}
