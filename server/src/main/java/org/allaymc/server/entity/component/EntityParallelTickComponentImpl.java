package org.allaymc.server.entity.component;

import org.allaymc.api.entity.component.EntityParallelTickComponent;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.event.CEntityParallelTickEvent;

/**
 * {@code EntityParallelTickComponentImpl} forwards an entity's parallel tick
 * entry point to its component event manager. The dimension entity manager
 * invokes this phase on compute workers and joins those callbacks before
 * ordinary entity ticks, allowing components to separate parallel computation
 * from sequential actions.
 *
 * @author daoge_cmd
 */
public class EntityParallelTickComponentImpl implements EntityParallelTickComponent {

    /**
     * {@code IDENTIFIER} names this component implementation for provider
     * lookup and replacement when entity types assemble their component sets.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_parallel_tick_component");

    /**
     * Injected event manager for this entity's component handlers.
     */
    @Manager
    protected ComponentManager manager;

    /**
     * Dispatches the shared parallel-tick event to this entity's component
     * handlers.
     */
    @Override
    public void parallelTick() {
        manager.callEvent(CEntityParallelTickEvent.INSTANCE);
    }
}
