package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.Event;
import org.jetbrains.annotations.Nullable;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CBlockAfterNeighborLayerReplaceEvent extends Event {
    protected BlockStateWithPos currentBlockState;
    protected BlockState newBlockState;
    @Nullable
    protected PlayerInteractInfo placementInfo;
}
