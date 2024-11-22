package org.allaymc.api.component.interfaces;

import org.allaymc.api.eventbus.event.Event;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author daoge_cmd
 */
@ApiStatus.Internal
public interface ComponentManager {
    <E extends Event> E callEvent(E event);
}
