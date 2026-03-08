package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a block spreads to another location. Examples include grass spreading to nearby dirt blocks.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.DIMENSION)
public class BlockSpreadEvent extends BlockEvent implements CancellableEvent {

    protected Block spreadBlock;

    public BlockSpreadEvent(Block block, Block spreadBlock) {
        super(block);
        this.spreadBlock = spreadBlock;
    }
}
