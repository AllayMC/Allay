package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CBlockAfterPlacedEvent} carries the incoming block's completed placement to
 * companion components. {@code oldBlock} retains the outgoing snapshot while
 * {@code newBlockState} describes the stored replacement, allowing handlers to
 * inspect the previous block without rereading it from the dimension.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CBlockAfterPlacedEvent extends Event {
    /**
     * Outgoing snapshot before placement.
     */
    protected Block oldBlock;
    /**
     * Incoming state after placement.
     */
    protected BlockState newBlockState;
    /**
     * {@code placementInfo} supplies player context for the replacement and may be
     * {@code null} when no player initiated the dimension write.
     */
    protected PlayerInteractInfo placementInfo;
}
