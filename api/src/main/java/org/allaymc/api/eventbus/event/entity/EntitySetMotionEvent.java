package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.joml.Vector3dc;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.UNKNOWN)
public class EntitySetMotionEvent extends EntityEvent implements CancellableEvent {
    protected Vector3dc motion;

    public EntitySetMotionEvent(Entity entity, Vector3dc motion) {
        super(entity);
        this.motion = motion;
    }
}
