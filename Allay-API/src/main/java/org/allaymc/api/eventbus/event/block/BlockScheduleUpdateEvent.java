package org.allaymc.api.eventbus.event.block;

import org.allaymc.api.block.BlockStateWithPos;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Allay Project 2024/8/8
 *
 * @author daoge_cmd
 */
public class BlockScheduleUpdateEvent extends BlockEvent implements CancellableEvent {
    public BlockScheduleUpdateEvent(BlockStateWithPos block) {
        super(block);
    }
}
