package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code EntityDamageEvent} exposes a damage attempt before attacker and victim
 * modifiers are applied. Listeners share the original mutable
 * {@code DamageContainer}; cancellation stops later damage processing, although
 * the living component has already recorded the attempt for its damage
 * cooldown.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class EntityDamageEvent extends EntityEvent implements CancellableEvent {

    /**
     * {@code damageContainer} holds the original mutable attempt data.
     * Listeners and the remaining damage pipeline use the same instance, so
     * listener changes affect the amount and knockback processed after
     * dispatch.
     */
    protected DamageContainer damageContainer;

    /**
     * Creates an event retaining the supplied victim and mutable damage
     * container. Neither reference is copied or validated, so the caller must
     * supply the entity and attempt data that subsequent listeners and damage
     * processing should use.
     */
    public EntityDamageEvent(Entity entity, DamageContainer damageContainer) {
        super(entity);
        this.damageContainer = damageContainer;
    }
}
