package org.allaymc.api.eventbus.event.server.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.EntityEvent;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3fc;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
public class EntityTeleportEvent extends EntityEvent implements CancellableEvent {
    protected Location3fc from;
    @Setter
    protected Location3f to;

    public EntityTeleportEvent(Entity entity, Location3fc from, Location3f to) {
        super(entity);
        this.from = from;
        this.to = to;
    }
}
