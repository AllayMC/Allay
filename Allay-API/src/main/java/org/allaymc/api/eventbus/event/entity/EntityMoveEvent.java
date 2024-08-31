package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3fc;

/**
 * Allay Project 2024/8/31
 *
 * @author daoge_cmd
 */
@Getter
public class EntityMoveEvent extends EntityEvent {
    protected Location3fc from;
    @Setter
    protected Location3fc to;

    public EntityMoveEvent(Entity entity, Location3fc from, Location3fc to) {
        super(entity);
        this.from = from;
        this.to = to;
    }
}
