package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntitySplashPotion;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PotionSplashEvent} represents one living target considered for splash
 * potion effects. Splash handling dispatches a separate event for each
 * eligible target, and cancellation skips that target while processing
 * continues for other nearby living entities.
 *
 * @author zernix2077
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PotionSplashEvent extends EntityEvent implements CancellableEvent {

    /**
     * {@code target} retains the living entity considered for this
     * application. Cancellation prevents this target from receiving the
     * effects in the current splash pass.
     */
    protected EntityLiving target;

    /**
     * Creates a splash application event retaining the potion and target
     * references. The event does not apply effects itself; the splash handler
     * checks cancellation before adding effects to the target.
     */
    public PotionSplashEvent(EntitySplashPotion splashPotion, EntityLiving target) {
        super(splashPotion);
        this.target = target;
    }

    /**
     * Returns the splash potion entity retained as this event source.
     */
    @Override
    public EntitySplashPotion getEntity() {
        return (EntitySplashPotion) super.getEntity();
    }
}
