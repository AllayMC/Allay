package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CBlockAfterReplacedEvent extends Event {
    protected BlockStateWithPos oldBlockState;
    protected BlockState newBlockState;
    protected PlayerInteractInfo placementInfo; // Can be null
}
