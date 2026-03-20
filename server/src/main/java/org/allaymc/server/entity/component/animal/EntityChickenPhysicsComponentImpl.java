package org.allaymc.server.entity.component.animal;

import org.joml.Vector3d;

/**
 * Chicken physics: clamp downward speed to simulate slow fall.
 */
public class EntityChickenPhysicsComponentImpl extends EntityAnimalPhysicsComponentImpl {

    protected static final double MAX_FALL_SPEED = -0.08;

    @Override
    public Vector3d updateMotion(LiquidState liquidState) {
        var motion = super.updateMotion(liquidState);
        if (!isOnGround() && motion.y < MAX_FALL_SPEED) {
            motion.y = MAX_FALL_SPEED;
        }
        return motion;
    }
}
