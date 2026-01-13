package org.allaymc.server.entity.component.projectile;

/**
 * @author daoge_cmd
 */
public class EntitySplashPotionPhysicsComponentImpl extends EntityPotionPhysicsComponentImpl {

    @Override
    protected float getDurationMultiplier() {
        return 1.0f;
    }
}
