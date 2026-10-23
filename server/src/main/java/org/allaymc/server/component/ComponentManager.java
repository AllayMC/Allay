package org.allaymc.server.component;

import org.allaymc.api.eventbus.event.Event;
import org.jetbrains.annotations.ApiStatus;

/**
 * {@code ComponentManager} dispatches events between components belonging to one
 * composed object.
 * Components receive their owner's manager during initialization and use its
 * event bus to notify registered sibling components. Dispatch returns the same
 * event object; asynchronous handlers may continue processing it after return.
 *
 * @author daoge_cmd
 */
@ApiStatus.Internal
public interface ComponentManager {
    /**
     * Dispatches the supplied event to this owner's registered component
     * handlers. The returned reference is the same event, including changes
     * already made synchronously; asynchronous handlers are not awaited before
     * the call returns.
     */
    <E extends Event> E callEvent(E event);
}
