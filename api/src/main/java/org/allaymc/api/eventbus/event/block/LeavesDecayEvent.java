package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
public class LeavesDecayEvent extends BlockEvent implements CancellableEvent {
    public LeavesDecayEvent(BlockStateWithPos blockState) {
        super(blockState);
    }
}
