package org.allaymc.server.component.interfaces;

import org.allaymc.api.eventbus.event.Event;
import org.jetbrains.annotations.ApiStatus;

/**
 * ComponentManager is responsible for implementing communication between components using event bus.
 *
 * @author daoge_cmd
 */
@ApiStatus.Internal
public interface ComponentManager {
    <E extends Event> E callEvent(E event);
}
