package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a block receives a random tick update. Random tick updates are used for
 * things like crop growth and leaf decay.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class BlockRandomUpdateEvent extends BlockEvent implements CancellableEvent {
    public BlockRandomUpdateEvent(Block block) {
        super(block);
    }
}
