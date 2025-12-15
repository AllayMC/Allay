package org.allaymc.api.eventbus.event.entity;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectInstance;

/**
 * Called when an effect is added to an entity.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
public class EntityEffectAddEvent extends EntityEffectEvent {
    public EntityEffectAddEvent(Entity entity, EffectInstance effect) {
        super(entity, effect);
    }

    public void setEffect(EffectInstance effect) {
        this.effect = effect;
    }
}
