package org.allaymc.server.entity.component.event;

import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
public class CEntityParallelTickEvent extends Event {
    public static final CEntityParallelTickEvent INSTANCE = new CEntityParallelTickEvent();
}
