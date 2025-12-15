package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when crops grow.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class BlockGrowEvent extends BlockEvent implements CancellableEvent {
    protected BlockState newBlockState;

    public BlockGrowEvent(Block block, BlockState newBlockState) {
        super(block);
        this.newBlockState = newBlockState;
    }
}
