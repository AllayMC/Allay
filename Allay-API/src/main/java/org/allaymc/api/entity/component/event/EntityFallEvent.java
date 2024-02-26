package org.allaymc.api.entity.component.event;

import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;

@Getter
public class EntityFallEvent extends Event {
    protected float fallDistance;

    public EntityFallEvent(
            float fallDistance
    ) {
        this.fallDistance = fallDistance;
    }
}
