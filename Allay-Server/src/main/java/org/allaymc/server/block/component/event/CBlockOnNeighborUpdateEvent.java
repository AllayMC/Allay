package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CBlockOnNeighborUpdateEvent extends Event {
    private final BlockStateWithPos current;
    private final BlockStateWithPos neighbor;
    private final BlockFace face;
}
