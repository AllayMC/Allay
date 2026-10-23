package org.allaymc.server.entity.component.event;

import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CEntityParallelTickEvent} signals the parallel entity tick phase to
 * component handlers without per-tick data. A shared singleton is dispatched
 * through each entity's own component manager, while the dimension manager
 * joins those parallel callbacks before sending ordinary entity tick events.
 *
 * @author daoge_cmd
 */
public class CEntityParallelTickEvent extends Event {
    /**
     * {@code INSTANCE} supplies the shared payload for component-manager
     * dispatch. The event carries no operation-specific data, and its type
     * does not permit cancellation through the inherited event API.
     */
    public static final CEntityParallelTickEvent INSTANCE = new CEntityParallelTickEvent();
}
