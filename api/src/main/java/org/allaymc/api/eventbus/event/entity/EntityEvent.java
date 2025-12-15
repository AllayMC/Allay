package org.allaymc.api.eventbus.event.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.Event;

/**
 * Represents an event related to an entity. This is the base class for all entity events.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class EntityEvent extends Event {
    protected Entity entity;
}
