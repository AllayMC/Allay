package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code BlockPlaceEvent} represents a proposed block state write in a dimension,
 * including replacements initiated without a player. The inherited block describes
 * the incoming state and position, while {@code blockStateReplaced} records the
 * outgoing state; cancellation prevents that write.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class BlockPlaceEvent extends BlockEvent implements CancellableEvent {

    /**
     * Outgoing state before placement.
     */
    protected BlockState blockStateReplaced;
    /**
     * {@code interactInfo} supplies player placement context and may be {@code null}
     * for changes initiated without player interaction.
     */
    protected PlayerInteractInfo interactInfo;

    /**
     * Creates a placement event with the incoming block and outgoing state.
     * {@code interactInfo} may be {@code null} when the dimension write has no
     * associated player interaction context.
     */
    public BlockPlaceEvent(Block blockPlaced, BlockState blockStateReplaced, PlayerInteractInfo interactInfo) {
        super(blockPlaced);
        this.blockStateReplaced = blockStateReplaced;
        this.interactInfo = interactInfo;
    }
}
