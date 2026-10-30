package org.allaymc.api.eventbus.event.entity;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;

/**
 * {@code EntityDespawnEvent} announces that an entity is about to be removed
 * from a dimension. The entity manager dispatches this notification before
 * waking a sleeping entity and detaching entity, physics, and viewer state;
 * the notification itself does not support cancellation.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class EntityDespawnEvent extends EntityEvent {
    /**
     * Creates the notification carrying the supplied entity for pending entity
     * removal.
     */
    public EntityDespawnEvent(Entity entity) {
        super(entity);
    }
}
