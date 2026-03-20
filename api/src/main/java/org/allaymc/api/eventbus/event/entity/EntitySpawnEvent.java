package org.allaymc.api.eventbus.event.entity;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;

/**
 * Called when an entity is spawned into a dimension.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class EntitySpawnEvent extends EntityEvent {
    public EntitySpawnEvent(Entity entity) {
        super(entity);
    }
}
