package org.allaymc.api.block.component.event;

import lombok.Getter;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.event.Event;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
@Getter
public class BlockOnPlaceEvent extends Event {
    protected BlockStateWithPos currentBlockState;
    protected BlockState newBlockState;

    /**
     *
     */
    public BlockOnPlaceEvent(
            BlockStateWithPos currentBlockState,
            BlockState newBlockState
    ) {
        this.currentBlockState = currentBlockState;
        this.newBlockState = newBlockState;
    }
}
