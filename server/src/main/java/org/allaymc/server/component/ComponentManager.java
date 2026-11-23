package org.allaymc.server.component;

import org.allaymc.api.eventbus.event.Event;
import org.jetbrains.annotations.ApiStatus;

/**
 * {@code ComponentManager} dispatches internal events synchronously between
 * components belonging to one composed object through its type-level
 * {@link ComponentBlueprint}. Dispatch returns the same event instance, and
 * unchecked exceptions thrown by handlers propagate directly to the caller.
 *
 * @author daoge_cmd
 */
@ApiStatus.Internal
public interface ComponentManager {
    /**
     * Dispatches the supplied internal event synchronously to this owner's
     * precompiled component handlers in descending priority order and returns the
     * same event reference.
     */
    <E extends Event> E callEvent(E event);
}
