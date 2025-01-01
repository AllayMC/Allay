package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Represent the hardening of a liquid.
 *
 * @author daoge_cmd
 */
@Getter
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

    public LiquidHardenEvent(BlockStateWithPos blockState, BlockState cause, BlockState hardenedBlockState) {
        super(blockState);
        this.cause = cause;
        this.hardenedBlockState = hardenedBlockState;
    }
}
