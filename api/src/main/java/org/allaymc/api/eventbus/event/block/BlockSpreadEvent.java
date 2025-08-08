package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
public class BlockSpreadEvent extends BlockEvent implements CancellableEvent {

    protected Block spreadBlock;

    public BlockSpreadEvent(Block block, Block spreadBlock) {
        super(block);
        this.spreadBlock = spreadBlock;
    }
}
