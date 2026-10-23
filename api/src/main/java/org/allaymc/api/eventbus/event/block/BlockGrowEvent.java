package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code BlockGrowEvent} represents a proposed growth transition for a crop or other
 * growing block. Its block identifies the current snapshot, and {@code newBlockState}
 * describes the proposed growth result; cancellation lets callers stop that growth
 * attempt.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class BlockGrowEvent extends BlockEvent implements CancellableEvent {
    /**
     * Proposed block growth state.
     */
    protected BlockState newBlockState;

    /**
     * Creates a growth event containing the current block and proposed state.
     */
    public BlockGrowEvent(Block block, BlockState newBlockState) {
        super(block);
        this.newBlockState = newBlockState;
    }
}
