package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
public class BlockSpreadEvent extends BlockEvent implements CancellableEvent {

    protected BlockStateWithPos spreadBlockState;

    public BlockSpreadEvent(BlockStateWithPos blockState, BlockStateWithPos spreadBlockState) {
        super(blockState);
        this.spreadBlockState = spreadBlockState;
    }
}
