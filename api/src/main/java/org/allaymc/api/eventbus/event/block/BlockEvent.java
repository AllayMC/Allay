package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@Getter
public abstract class BlockEvent extends Event {
    protected Block block;

    public BlockEvent(Block block) {
        this.block = block;
    }
}
