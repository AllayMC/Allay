package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
public class BlockPlaceEvent extends BlockEvent implements CancellableEvent {

    protected BlockState blockStateReplaced;
    /**
     * can be null
     */
    protected Entity entity;
    /**
     * can be null
     */
    protected PlayerInteractInfo interactInfo;

    public BlockPlaceEvent(Block blockPlaced, BlockState blockStateReplaced, Entity entity, PlayerInteractInfo interactInfo) {
        super(blockPlaced);
        this.blockStateReplaced = blockStateReplaced;
        this.entity = entity;
        this.interactInfo = interactInfo;
    }
}
