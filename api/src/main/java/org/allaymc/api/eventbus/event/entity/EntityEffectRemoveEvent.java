package org.allaymc.api.eventbus.event.entity;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectInstance;

/**
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
public class EntityEffectRemoveEvent extends EntityEffectEvent {
    public EntityEffectRemoveEvent(Entity entity, EffectInstance effect) {
        super(entity, effect);
    }
}
