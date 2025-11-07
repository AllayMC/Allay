package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a farmland is trampled by an entity.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.COMPUTE)
public class EntityTrampleFarmlandEvent extends EntityEvent implements CancellableEvent {

    protected Block block;

    public EntityTrampleFarmlandEvent(Entity entity, Block block) {
        super(entity);
        this.block = block;
    }
}
