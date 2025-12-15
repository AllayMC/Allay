package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * BlockPlaceEvent is called when a block is placed in the dimension.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.ANY)
public class BlockPlaceEvent extends BlockEvent implements CancellableEvent {

    /**
     * The block state being replaced by the new block.
     */
    protected BlockState blockStateReplaced;
    /**
     * The interact info of this placement. Can be {@code null} if the block is not placed by an entity.
     */
    protected PlayerInteractInfo interactInfo;

    public BlockPlaceEvent(Block blockPlaced, BlockState blockStateReplaced, PlayerInteractInfo interactInfo) {
        super(blockPlaced);
        this.blockStateReplaced = blockStateReplaced;
        this.interactInfo = interactInfo;
    }
}
