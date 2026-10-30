package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code LiquidDecayEvent} represents a proposed depth reduction or removal of an unsupported
 * liquid block. The dimension-thread liquid update dispatches this event before changing the
 * world; cancellation skips that decay write and ends the current liquid update.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class LiquidDecayEvent extends BlockEvent implements CancellableEvent {

    /**
     * {@code newBlockState} holds the proposed liquid state; {@code null} tells the liquid
     * update to remove the liquid completely.
     */
    protected BlockState newBlockState;

    /**
     * Creates a decay proposal for the supplied liquid block snapshot. {@code newBlockState}
     * is retained as supplied and may be {@code null} when the decay removes the liquid
     * completely.
     */
    public LiquidDecayEvent(Block block, BlockState newBlockState) {
        super(block);
        this.newBlockState = newBlockState;
    }
}
