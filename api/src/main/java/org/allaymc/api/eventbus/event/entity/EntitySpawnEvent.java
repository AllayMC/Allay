package org.allaymc.api.eventbus.event.entity;

import org.allaymc.api.entity.Entity;

/**
 * @author daoge_cmd
 */
public class EntitySpawnEvent extends EntityEvent {
    public EntitySpawnEvent(Entity entity) {
        super(entity);
    }
}
