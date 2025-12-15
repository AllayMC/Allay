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
 * Represent the hardening of a liquid.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class LiquidHardenEvent extends BlockEvent implements CancellableEvent {
    /**
     * Represent the liquid that cause the hardening.
     */
    protected BlockState cause;
    /**
     * Represent the harden block state which will replaced the liquid being hardened.
     */
    @Setter
    protected BlockState hardenedBlockState;
    /**
     * The position where the hardened block state will be placed.
     */
    protected Vector3ic hardenedBlockPosition;

    public LiquidHardenEvent(Block block, BlockState cause, BlockState hardenedBlockState, Vector3ic hardenedBlockPosition) {
        super(block);
        this.cause = cause;
        this.hardenedBlockState = hardenedBlockState;
        this.hardenedBlockPosition = hardenedBlockPosition;
    }
}
