package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code BlockNeighborUpdateEvent} represents a pending neighbor-driven update of a
 * block snapshot. The neighboring block and relative face identify the source of the
 * update, while the previous neighbor state is optional; cancellation suppresses that
 * update callback.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class BlockNeighborUpdateEvent extends BlockEvent implements CancellableEvent {
    /**
     * Neighbor causing the update.
     */
    protected Block neighbor;
    /**
     * Direction from block to neighbor.
     */
    protected BlockFace blockFace;
    /**
     * {@code oldNeighborState} holds the neighbor's previous state and may be
     * {@code null} when the update source does not supply it.
     */
    protected BlockState oldNeighborState;

    /**
     * Creates a neighbor update event with the relative face and old neighbor state.
     * {@code oldNeighborState} may be {@code null} when the previous state is unknown
     * to the update source.
     */
    public BlockNeighborUpdateEvent(Block block, Block neighbor, BlockFace blockFace, BlockState oldNeighborState) {
        super(block);
        this.neighbor = neighbor;
        this.blockFace = blockFace;
        this.oldNeighborState = oldNeighborState;
    }
}
