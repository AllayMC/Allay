package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CBlockOnNeighborUpdateEvent} carries a neighboring block update through a
 * block behavior's companion components. {@code current} identifies the receiving
 * snapshot, {@code neighbor} identifies the changed block, and {@code face} points
 * from the current block toward that neighbor.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CBlockOnNeighborUpdateEvent extends Event {
    /**
     * Block receiving the update.
     */
    private final Block current;
    /**
     * Neighbor causing the update.
     */
    private final Block neighbor;
    /**
     * Direction toward changed neighbor.
     */
    private final BlockFace face;
}
