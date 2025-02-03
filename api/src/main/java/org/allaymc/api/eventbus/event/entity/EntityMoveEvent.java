package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3dc;

/**
 * @author daoge_cmd
 */
@Getter
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
