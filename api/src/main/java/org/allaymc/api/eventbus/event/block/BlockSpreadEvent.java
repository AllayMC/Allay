package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code BlockSpreadEvent} represents a block spreading from a source to a proposed
 * target snapshot. The inherited block identifies the source, while
 * {@code spreadBlock} describes the target state; cancellation prevents the caller
 * from applying that spread attempt.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.DIMENSION)
public class BlockSpreadEvent extends BlockEvent implements CancellableEvent {

    /**
     * {@code spreadBlock} holds the proposed target snapshot. Grass spreading reads
     * its block state after dispatch while retaining the originally selected target
     * coordinates.
     */
    protected Block spreadBlock;

    /**
     * Creates a spread event with its source and proposed target block.
     */
    public BlockSpreadEvent(Block block, Block spreadBlock) {
        super(block);
        this.spreadBlock = spreadBlock;
    }
}
