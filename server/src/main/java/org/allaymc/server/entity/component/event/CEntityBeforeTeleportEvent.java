package org.allaymc.server.entity.component.event;

import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CEntityBeforeTeleportEvent} notifies an entity's components before an
 * accepted teleport changes its location. The base component sends the shared
 * payload after the public teleport event succeeds, allowing physics handlers
 * to clear motion without carrying the destination in this internal event.
 *
 * @author daoge_cmd
 */
public class CEntityBeforeTeleportEvent extends Event {
    /**
     * {@code INSTANCE} supplies the shared payload for component-manager
     * dispatch. The event carries no operation-specific data, and its type
     * does not permit cancellation through the inherited event API.
     */
    public static CEntityBeforeTeleportEvent INSTANCE = new CEntityBeforeTeleportEvent();
}
