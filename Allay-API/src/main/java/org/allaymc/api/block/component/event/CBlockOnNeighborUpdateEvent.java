package org.allaymc.api.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.data.BlockFace;
import org.allaymc.api.block.BlockStateWithPos;
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
