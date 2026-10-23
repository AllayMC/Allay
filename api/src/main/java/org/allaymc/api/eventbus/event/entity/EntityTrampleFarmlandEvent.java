package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code EntityTrampleFarmlandEvent} carries the farmland block selected for
 * trampling by an entity's landing. The farmland handler dispatches this event
 * after its fall and size checks; cancellation prevents that handler from
 * replacing the selected block with dirt.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class EntityTrampleFarmlandEvent extends EntityEvent implements CancellableEvent {

    /**
     * Farmland block selected by the landing interaction.
     */
    protected Block block;

    /**
     * Creates a trampling proposal carrying the entity and selected farmland
     * block.
     */
    public EntityTrampleFarmlandEvent(Entity entity, Block block) {
        super(entity);
        this.block = block;
    }
}
