package org.allaymc.api.eventbus.event.entity;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;

/**
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
public class EntitySpawnEvent extends EntityEvent {
    public EntitySpawnEvent(Entity entity) {
        super(entity);
    }
}
