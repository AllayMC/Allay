package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CBlockAfterReplacedEvent} carries the outgoing block's completed replacement
 * to companion components. {@code oldBlock} identifies the previous state and
 * position after {@code newBlockState} has been stored, so cleanup handlers retain
 * access to the block that owned their behavior.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CBlockAfterReplacedEvent extends Event {
    /**
     * Outgoing snapshot before replacement.
     */
    protected Block oldBlock;
    /**
     * Stored state after replacement.
     */
    protected BlockState newBlockState;
    /**
     * {@code placementInfo} supplies player context for the replacement and may be
     * {@code null} when no player initiated the dimension write.
     */
    protected PlayerInteractInfo placementInfo;
}
