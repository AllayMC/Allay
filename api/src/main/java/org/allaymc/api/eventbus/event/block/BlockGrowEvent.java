package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when crops grow.
 *
 * @author daoge_cmd
 */
@Getter
public class BlockGrowEvent extends BlockEvent implements CancellableEvent {
    protected BlockState newBlockState;

    public BlockGrowEvent(BlockStateWithPos blockState, BlockState newBlockState) {
        super(blockState);
        this.newBlockState = newBlockState;
    }
}
