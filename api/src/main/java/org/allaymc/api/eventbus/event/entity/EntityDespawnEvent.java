package org.allaymc.api.eventbus.event.entity;

import org.allaymc.api.entity.Entity;

/**
 * @author daoge_cmd
 */
public class EntityDespawnEvent extends EntityEvent {
    public EntityDespawnEvent(Entity entity) {
        super(entity);
    }
}
