package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
public class EntityDamageEvent extends EntityEvent implements CancellableEvent {

    protected DamageContainer damageContainer;

    public EntityDamageEvent(Entity entity, DamageContainer damageContainer) {
        super(entity);
        this.damageContainer = damageContainer;
    }
}
