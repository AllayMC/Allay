package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code EntityEffectEvent} supplies the shared entity and effect payload for
 * cancellable effect changes. The living component decides whether to add or
 * remove an effect after dispatch, while the event retains the supplied
 * mutable {@code EffectInstance} directly for listener inspection.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class EntityEffectEvent extends EntityEvent implements CancellableEvent {
    /**
     * {@code effect} retains the supplied mutable effect instance. The
     * generated getter exposes that same object, so listener mutations are
     * visible to the caller and other listeners.
     */
    protected EffectInstance effect;

    /**
     * Creates an effect-change payload retaining the entity and effect
     * references. The effect is not copied, so changes to the supplied mutable
     * instance remain visible through this event.
     */
    public EntityEffectEvent(Entity entity, EffectInstance effect) {
        super(entity);
        this.effect = effect;
    }
}
