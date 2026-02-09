package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.joml.Vector3ic;

/**
 * Called when an entity attempts to sleep in a bed.
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class EntitySleepEvent extends EntityEvent implements CancellableEvent {

    protected final Vector3ic bedPos;

    public EntitySleepEvent(Entity entity, Vector3ic bedPos) {
        super(entity);
        this.bedPos = bedPos;
    }
}
