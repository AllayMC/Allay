package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.joml.Vector3ic;

/**
 * {@code LiquidHardenEvent} represents a proposed solid replacement caused by liquid
 * interaction or lava's basalt conditions. The dimension-thread hardening caller honors
 * cancellation before its block write and fizz sound. Handlers may replace {@code
 * hardenedBlockState}; the target coordinates are caller-selected and shared through the
 * retained position reference.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class LiquidHardenEvent extends BlockEvent implements CancellableEvent {
    /**
     * {@code cause} holds the interacting liquid state when available. Lava's
     * surrounding-block basalt check can supply {@code null} when no neighboring water caused
     * the proposal.
     */
    protected BlockState cause;
    /**
     * {@code hardenedBlockState} is the proposed replacement consumed after dispatch. Handlers
     * must supply a usable block state because hardening passes this value directly to the
     * dimension.
     */
    @Setter
    protected BlockState hardenedBlockState;
    /**
     * {@code hardenedBlockPosition} identifies the replacement location and retains the
     * supplied vector reference. The getter exposes that reference, so callers and handlers
     * must keep its coordinates stable until hardening finishes consuming them.
     */
    protected Vector3ic hardenedBlockPosition;

    /**
     * Creates a hardening proposal with its interacting state, replacement, and target
     * position. {@code cause} may be {@code null} for a basalt proposal; the constructor
     * retains all supplied references without changing the world.
     */
    public LiquidHardenEvent(Block block, BlockState cause, BlockState hardenedBlockState, Vector3ic hardenedBlockPosition) {
        super(block);
        this.cause = cause;
        this.hardenedBlockState = hardenedBlockState;
        this.hardenedBlockPosition = hardenedBlockPosition;
    }
}
