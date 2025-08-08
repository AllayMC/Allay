package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CBlockOnNeighborUpdateEvent extends Event {
    private final Block current;
    private final Block neighbor;
    private final BlockFace face;
}
