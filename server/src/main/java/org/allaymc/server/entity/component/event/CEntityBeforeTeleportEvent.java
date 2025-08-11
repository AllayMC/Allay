package org.allaymc.server.entity.component.event;

import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
public class CEntityBeforeTeleportEvent extends Event {
    public static CEntityBeforeTeleportEvent INSTANCE = new CEntityBeforeTeleportEvent();
}
