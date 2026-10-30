package org.allaymc.server.entity.component.item;

import org.allaymc.server.entity.component.EntityPhysicsComponentImpl;

/**
 * {@code EntityTntPhysicsComponentImpl} supplies primed TNT gravity and ground
 * drag. TNT bypasses ordinary liquid buoyancy and drag while the shared
 * physics engine can still add liquid flow motion, and ordinary entity pushing
 * does not affect its motion.
 *
 * @author daoge_cmd
 */
public class EntityTntPhysicsComponentImpl extends EntityPhysicsComponentImpl {

    /**
     * Returns TNT downward acceleration in blocks per tick squared.
     */
    @Override
    public double getGravity() {
        return 0.04;
    }

    /**
     * Returns the fraction of TNT motion removed by ground drag.
     */
    @Override
    public double getDragFactorOnGround() {
        return 0.3;
    }

    /**
     * Returns {@code false} to keep TNT on its gravity-based motion update in
     * liquids. The separate liquid-flow phase can still add flow motion before
     * movement, because this override does not disable that phase.
     */
    @Override
    public boolean computeLiquidPhysics() {
        return false;
    }

    /**
     * Returns {@code false} to suppress ordinary entity pushing for primed
     * TNT.
     */
    @Override
    public boolean computeEntityCollisionMotion() {
        return false;
    }
}
