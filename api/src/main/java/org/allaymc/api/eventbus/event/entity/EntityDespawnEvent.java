package org.allaymc.api.eventbus.event.entity;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;

/**
 * Called when an entity is despawned from a dimension.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class EntityDespawnEvent extends EntityEvent {
    public EntityDespawnEvent(Entity entity) {
        super(entity);
    }
}
