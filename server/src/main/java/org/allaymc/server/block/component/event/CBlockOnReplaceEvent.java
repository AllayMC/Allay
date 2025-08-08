package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CBlockOnReplaceEvent extends Event {
    protected Block currentBlock;
    protected BlockState newBlockState;
    protected PlayerInteractInfo placementInfo; // Nullable
}
