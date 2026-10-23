package org.allaymc.api.eventbus.event.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code EntityEvent} provides the entity reference shared by entity-related
 * event payloads. Specialized subclasses add the proposed change or
 * notification data, while this base class retains the supplied entity
 * directly and exposes it through the generated getter during event dispatch.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class EntityEvent extends Event {
    /**
     * Entity reference associated with this event payload.
     */
    protected Entity entity;
}
