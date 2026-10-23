package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CBlockAfterNeighborLayerReplaceEvent} carries another block layer's
 * completed replacement to companion block components. {@code currentBlock} describes
 * the changed layer's old snapshot and {@code newBlockState} describes its
 * replacement, while the receiving component belongs to the neighboring layer at the
 * same position.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CBlockAfterNeighborLayerReplaceEvent extends Event {
    /**
     * Changed layer's old snapshot.
     */
    protected Block currentBlock;
    /**
     * Changed layer's incoming state.
     */
    protected BlockState newBlockState;
    /**
     * {@code placementInfo} supplies player context for the replacement and may be
     * {@code null} when no player initiated the dimension write.
     */
    protected PlayerInteractInfo placementInfo;
}
