package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@Getter
public abstract class BlockEvent extends Event {
    protected BlockStateWithPos blockState;

    public BlockEvent(BlockStateWithPos blockState) {
        this.blockState = blockState;
    }
}
