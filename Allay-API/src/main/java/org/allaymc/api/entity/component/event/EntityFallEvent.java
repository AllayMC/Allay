package org.allaymc.api.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;

@Getter
@AllArgsConstructor
public class EntityFallEvent extends Event {
    protected float fallDistance;
}
