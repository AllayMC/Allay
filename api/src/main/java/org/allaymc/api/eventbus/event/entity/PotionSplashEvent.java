package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntitySplashPotion;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called before a splash potion applies effects to a target entity.
 *
 * @author zernix2077
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PotionSplashEvent extends EntityEvent implements CancellableEvent {

    /**
     * The target entity that would receive splash potion effects.
     */
    protected EntityLiving target;

    public PotionSplashEvent(EntitySplashPotion splashPotion, EntityLiving target) {
        super(splashPotion);
        this.target = target;
    }

    @Override
    public EntitySplashPotion getEntity() {
        return (EntitySplashPotion) super.getEntity();
    }
}
