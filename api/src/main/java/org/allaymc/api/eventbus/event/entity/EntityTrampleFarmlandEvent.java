package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a farmland is trampled by an entity.
 *
 * @author daoge_cmd
 */
@Getter
public class EntityTrampleFarmlandEvent extends EntityEvent implements CancellableEvent {

    protected BlockStateWithPos blockState;

    public EntityTrampleFarmlandEvent(Entity entity, BlockStateWithPos blockState) {
        super(entity);
        this.blockState = blockState;
    }
}
