package org.allaymc.api.entity.component.event;

import org.allaymc.api.component.interfaces.ComponentEvent;

public record EntityFallEvent(
        float fallDistance
) implements ComponentEvent {
}
