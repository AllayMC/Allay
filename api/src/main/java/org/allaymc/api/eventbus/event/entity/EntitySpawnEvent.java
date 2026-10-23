package org.allaymc.api.eventbus.event.entity;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;

/**
 * {@code EntitySpawnEvent} announces an entity's pending insertion into a
 * dimension. The entity manager dispatches this notification before adding
 * runtime membership, physics participation, and viewer presentation, so
 * receiving it does not establish that every later spawn step has already
 * completed.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class EntitySpawnEvent extends EntityEvent {
    /**
     * Creates the notification carrying the supplied entity for pending entity
     * insertion.
     */
    public EntitySpawnEvent(Entity entity) {
        super(entity);
    }
}
