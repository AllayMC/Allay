package org.allaymc.api.entity.component.event;

import org.allaymc.api.component.interfaces.ComponentEvent;
import org.allaymc.api.entity.Entity;

public record EntityFallEvent(
        Entity entity,
        float fallDistance
) implements ComponentEvent {
}
