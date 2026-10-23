package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.joml.Vector3ic;

/**
 * {@code LiquidFlowEvent} represents a proposed liquid flow or automatic source formation
 * before its world write. The dimension-thread liquid update honors cancellation for that
 * proposal. {@code block} describes the caller's liquid snapshot; source formation uses the
 * same source and destination position and marks {@code isFormingSource}.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class LiquidFlowEvent extends BlockEvent implements CancellableEvent {
    /**
     * {@code into} identifies the proposed destination and retains the supplied vector
     * reference. Source formation uses the liquid's current position rather than an adjacent
     * destination.
     */
    protected Vector3ic into;

    /**
     * {@code replacedBlockState} carries the state supplied by the flow caller. Ordinary flow
     * supplies the destination state; source formation currently supplies the proposed source
     * state.
     */
    protected BlockState replacedBlockState;

    /**
     * Source-formation proposal flag.
     */
    protected boolean isFormingSource;

    /**
     * Creates an ordinary flow proposal with source formation disabled. The event retains
     * {@code block}, {@code into}, and {@code replacedBlockState} as supplied; construction
     * alone does not dispatch the event or modify the destination.
     */
    public LiquidFlowEvent(Block block, Vector3ic into, BlockState replacedBlockState) {
        this(block, into, replacedBlockState, false);
    }

    /**
     * Creates a flow proposal with an explicit source-formation flag. {@code into} is retained
     * without copying, so callers and handlers must avoid mutating a shared vector while the
     * proposal is being processed.
     */
    public LiquidFlowEvent(Block block, Vector3ic into, BlockState replacedBlockState, boolean isFormingSource) {
        super(block);
        this.into = into;
        this.replacedBlockState = replacedBlockState;
        this.isFormingSource = isFormingSource;
    }
}
