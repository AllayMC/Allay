package org.allaymc.api.eventbus.event.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class EntityEvent extends Event {
    protected Entity entity;
}
