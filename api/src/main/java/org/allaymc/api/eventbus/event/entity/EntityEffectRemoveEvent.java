package org.allaymc.api.eventbus.event.entity;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectInstance;

/**
 * {@code EntityEffectRemoveEvent} carries the active effect proposed for
 * removal from an entity. Cancellation preserves that effect in the living
 * component's effect map; an accepted event is followed by map removal, the
 * effect's removal hook, and viewer updates.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class EntityEffectRemoveEvent extends EntityEffectEvent {
    /**
     * Creates a removal proposal retaining the supplied active effect
     * reference. Listener changes affect that shared instance, while
     * cancellation is interpreted by the living component before it removes
     * the map entry.
     */
    public EntityEffectRemoveEvent(Entity entity, EffectInstance effect) {
        super(entity, effect);
    }
}
