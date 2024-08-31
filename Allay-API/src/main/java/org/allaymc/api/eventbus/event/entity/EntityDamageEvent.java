package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Allay Project 2024/8/31
 *
 * @author daoge_cmd
 */
@Getter
public class EntityDamageEvent extends EntityEvent implements CancellableEvent {

    protected DamageContainer damage;

    public EntityDamageEvent(Entity entity, DamageContainer damage) {
        super(entity);
        this.damage = damage;
    }
}
