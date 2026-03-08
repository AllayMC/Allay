package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.joml.Vector3ic;

/**
 * Called when an entity wakes up from a bed.
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class EntityWakeEvent extends EntityEvent {

    protected final Vector3ic bedPos;

    public EntityWakeEvent(Entity entity, Vector3ic bedPos) {
        super(entity);
        this.bedPos = bedPos;
    }
}
