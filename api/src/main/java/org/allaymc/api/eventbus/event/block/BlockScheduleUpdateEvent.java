package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
public class BlockScheduleUpdateEvent extends BlockEvent implements CancellableEvent {
    public BlockScheduleUpdateEvent(BlockStateWithPos blockState) {
        super(blockState);
    }
}
