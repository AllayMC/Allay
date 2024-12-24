package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
public class BlockRandomUpdateEvent extends BlockEvent implements CancellableEvent {
    public BlockRandomUpdateEvent(BlockStateWithPos blockState) {
        super(blockState);
    }
}
