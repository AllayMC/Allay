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
public class CBlockAfterReplacedEvent extends Event {
    protected Block oldBlock;
    protected BlockState newBlockState;
    protected PlayerInteractInfo placementInfo; // Nullable
}
