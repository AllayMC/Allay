package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
public class BlockFadeEvent extends BlockEvent implements CancellableEvent {
    protected BlockState newBlockState;

    public BlockFadeEvent(BlockStateWithPos block, BlockState newBlockState) {
        super(block);
        this.newBlockState = newBlockState;
    }
}
