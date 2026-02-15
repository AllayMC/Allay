package org.allaymc.server.entity.component.tnt;

import org.allaymc.server.entity.component.EntityPhysicsComponentImpl;

/**
 * @author daoge_cmd
 */
public class EntityTntPhysicsComponentImpl extends EntityPhysicsComponentImpl {

    @Override
    public double getGravity() {
        return 0.04;
    }

    @Override
    public double getDragFactorOnGround() {
        return 0.3;
    }

    @Override
    public boolean computeLiquidPhysics() {
        // TNT always applies gravity even in water (no buoyancy/drag), matching vanilla behavior.
        // It still receives flow pushing via computeLiquidMotion().
        return false;
    }

    @Override
    public boolean computeEntityCollisionMotion() {
        return false;
    }
}
