package org.allaymc.api.eventbus.event.entity;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;

/**
 * Called when an entity dies.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class EntityDieEvent extends EntityEvent {
    public EntityDieEvent(Entity entity) {
        super(entity);
    }
}
