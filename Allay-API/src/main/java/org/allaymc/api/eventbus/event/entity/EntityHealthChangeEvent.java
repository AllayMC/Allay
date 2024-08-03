package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@Getter
public class EntityHealthChangeEvent extends EntityEvent implements CancellableEvent {

    protected float oldHealth;
    @Setter
    protected float newHealth;

    public EntityHealthChangeEvent(Entity entity, float oldHealth, float newHealth) {
        super(entity);
        this.oldHealth = oldHealth;
        this.newHealth = newHealth;
    }
}
