package org.allaymc.api.eventbus.event.entity;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectInstance;

/**
 * {@code EntityEffectAddEvent} carries a proposed status-effect addition or
 * replacement for an entity. Cancellation leaves the effect map unchanged; an
 * accepted event supplies {@code effect} to the living component, and
 * {@code setEffect} lets listeners replace the proposed effect before
 * insertion.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class EntityEffectAddEvent extends EntityEffectEvent {
    /**
     * Creates an addition proposal retaining the supplied entity and effect
     * references. The living component dispatches this payload before
     * inserting its accepted {@code effect} into the active effect map.
     */
    public EntityEffectAddEvent(Entity entity, EffectInstance effect) {
        super(entity, effect);
    }

    /**
     * Replaces the proposed effect reference without copying or validating it.
     * The living component dereferences this value after accepting the event,
     * so listeners must supply a usable, non-{@code null} effect instance.
     */
    public void setEffect(EffectInstance effect) {
        this.effect = effect;
    }
}
