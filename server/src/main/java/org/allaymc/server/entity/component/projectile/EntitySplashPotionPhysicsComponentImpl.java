package org.allaymc.server.entity.component.projectile;

/**
 * {@code EntitySplashPotionPhysicsComponentImpl} selects ordinary
 * splash-potion duration handling for the shared potion impact path. A full
 * base duration multiplier leaves distance attenuation to the parent
 * calculation, which also dispatches per-target splash events and handles
 * water interactions.
 *
 * @author daoge_cmd
 */
public class EntitySplashPotionPhysicsComponentImpl extends EntityPotionPhysicsComponentImpl {

    /**
     * Returns the full base-duration factor for ordinary splash potion
     * effects.
     */
    @Override
    protected float getDurationMultiplier() {
        return 1.0f;
    }
}
