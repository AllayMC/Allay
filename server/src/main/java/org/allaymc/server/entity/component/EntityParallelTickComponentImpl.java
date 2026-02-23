package org.allaymc.server.entity.component;

import org.allaymc.api.entity.component.EntityParallelTickComponent;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.event.CEntityParallelTickEvent;

/**
 * @author daoge_cmd
 */
public class EntityParallelTickComponentImpl implements EntityParallelTickComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_parallel_tick_component");

    @Manager
    protected ComponentManager manager;

    @Override
    public void parallelTick() {
        manager.callEvent(CEntityParallelTickEvent.INSTANCE);
    }
}
