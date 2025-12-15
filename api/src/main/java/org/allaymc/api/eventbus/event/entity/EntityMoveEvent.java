package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3dc;

/**
 * Called when an entity moves from one location to another.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.COMPUTE)
public class EntityMoveEvent extends EntityEvent {
    protected Location3dc from;
    @Setter
    protected Location3dc to;

    public EntityMoveEvent(Entity entity, Location3dc from, Location3dc to) {
        super(entity);
        this.from = from;
        this.to = to;
    }
}
