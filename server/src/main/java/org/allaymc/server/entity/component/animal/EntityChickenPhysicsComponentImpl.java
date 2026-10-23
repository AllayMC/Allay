package org.allaymc.server.entity.component.animal;

import org.joml.Vector3d;

/**
 * {@code EntityChickenPhysicsComponentImpl} limits a chicken's downward
 * velocity after ordinary animal motion is calculated. Airborne chickens cannot
 * descend faster than the configured cap, while grounded motion and upward
 * velocity retain the result supplied by the inherited physics calculation.
 */
public class EntityChickenPhysicsComponentImpl extends EntityAnimalPhysicsComponentImpl {

    /**
     * {@code MAX_FALL_SPEED} limits airborne downward motion in blocks per
     * tick; the negative value represents descent and gives chickens their
     * slow-falling behavior.
     */
    protected static final double MAX_FALL_SPEED = -0.08;

    /**
     * Returns inherited motion with excessive airborne descent limited to the
     * chicken fall-speed cap. The result is a mutable vector calculated for
     * this update; grounded motion and upward movement pass through without
     * this extra limit.
     */
    @Override
    public Vector3d updateMotion(LiquidState liquidState) {
        var motion = super.updateMotion(liquidState);
        // Apply the chicken descent cap after inherited physics so gravity and
        // liquid calculations cannot leave a faster downward result while airborne.
        if (!isOnGround() && motion.y < MAX_FALL_SPEED) {
            motion.y = MAX_FALL_SPEED;
        }
        return motion;
    }
}
