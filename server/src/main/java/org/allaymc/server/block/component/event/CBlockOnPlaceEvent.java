package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CBlockOnPlaceEvent} carries an incoming block's preplacement context to
 * companion components. {@code currentBlock} still describes the outgoing stored
 * block, while {@code newBlockState} identifies the incoming state before the
 * dimension writes it; placement handlers can initialize related block entity data.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CBlockOnPlaceEvent extends Event {
    /**
     * Outgoing stored block snapshot.
     */
    protected Block currentBlock;
    /**
     * Proposed incoming block state.
     */
    protected BlockState newBlockState;
    /**
     * {@code placementInfo} supplies player context for the replacement and may be
     * {@code null} when no player initiated the dimension write.
     */
    protected PlayerInteractInfo placementInfo;
}
