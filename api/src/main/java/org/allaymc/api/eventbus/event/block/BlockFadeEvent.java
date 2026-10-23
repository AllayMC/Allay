package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code BlockFadeEvent} represents a proposed block transformation such as melting,
 * extinguishing, or oxidation. Cancellation guards the proposed transition, while
 * callers that read {@code getNewBlockState} can apply a replacement state selected
 * by an event listener.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.DIMENSION)
public class BlockFadeEvent extends BlockEvent implements CancellableEvent {
    /**
     * {@code newBlockState} holds the proposed replacement state. Listeners can
     * replace it for event callers that consult {@code getNewBlockState} after
     * dispatch.
     */
    protected BlockState newBlockState;

    /**
     * Creates a block fade event with the proposed replacement state.
     */
    public BlockFadeEvent(Block block, BlockState newBlockState) {
        super(block);
        this.newBlockState = newBlockState;
    }
}
