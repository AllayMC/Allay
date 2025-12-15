package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Represents an event related to an entity's effect. This is the base class for all entity effect events.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class EntityEffectEvent extends EntityEvent implements CancellableEvent {
    protected EffectInstance effect;

    public EntityEffectEvent(Entity entity, EffectInstance effect) {
        super(entity);
        this.effect = effect;
    }
}
