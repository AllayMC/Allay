package org.allaymc.api.eventbus.event.entity;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;

/**
 * {@code EntityDieEvent} announces that a living entity has reached its death
 * processing. The living component dispatches this notification before
 * internal death handlers, drops, and the transition to {@code DEAD};
 * listeners receive the entity reference but cannot cancel death through this
 * event.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class EntityDieEvent extends EntityEvent {
    /**
     * Creates the notification carrying the supplied entity for entity death
     * processing.
     */
    public EntityDieEvent(Entity entity) {
        super(entity);
    }
}
