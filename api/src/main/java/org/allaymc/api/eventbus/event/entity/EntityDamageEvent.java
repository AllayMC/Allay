package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when an entity takes damage.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class EntityDamageEvent extends EntityEvent implements CancellableEvent {

    protected DamageContainer damageContainer;

    public EntityDamageEvent(Entity entity, DamageContainer damageContainer) {
        super(entity);
        this.damageContainer = damageContainer;
    }
}
