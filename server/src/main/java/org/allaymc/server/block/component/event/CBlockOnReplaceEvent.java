package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CBlockOnReplaceEvent} carries an outgoing block's prereplacement context to
 * companion components. {@code currentBlock} describes the still-stored old block and
 * {@code newBlockState} identifies its planned replacement, allowing handlers to
 * release old block entity state before incoming placement handlers run.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CBlockOnReplaceEvent extends Event {
    /**
     * Outgoing stored block snapshot.
     */
    protected Block currentBlock;
    /**
     * Planned replacement block state.
     */
    protected BlockState newBlockState;
    /**
     * {@code placementInfo} supplies player context for the replacement and may be
     * {@code null} when no player initiated the dimension write.
     */
    protected PlayerInteractInfo placementInfo;
}
