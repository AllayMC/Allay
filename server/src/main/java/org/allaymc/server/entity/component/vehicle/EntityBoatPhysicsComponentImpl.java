package org.allaymc.server.entity.component.vehicle;

import org.allaymc.api.entity.component.EntityBoatBaseComponent;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityPhysicsComponentImpl;
import org.joml.Vector3d;

/**
 * Server-authoritative boat movement and buoyancy.
 */
public class EntityBoatPhysicsComponentImpl extends EntityPhysicsComponentImpl {
    @Dependency
    protected EntityBoatBaseComponent boatComponent;

    @Override
    public double getGravity() {
        return 0.04;
    }

    @Override
    public double getDragFactorOnGround() {
        return 0.5;
    }

    @Override
    public double getDragFactorInAir() {
        return 0.01;
    }

    @Override
    public Vector3d updateMotion(LiquidState liquidState) {
        if (liquidState.inWater()) {
            return new Vector3d(
                    motion.x() * 0.9,
                    Math.clamp((motion.y() - getGravity() + 0.08) * 0.9, -0.1, 0.1),
                    motion.z() * 0.9
            );
        }
        if (liquidState.inLava()) {
            return new Vector3d(motion.x() * 0.5, (motion.y() - getGravity()) * 0.5, motion.z() * 0.5);
        }
        return super.updateMotion(liquidState);
    }

    @Override
    public void afterApplyMotion() {
        super.afterApplyMotion();
        boatComponent.updatePassengerPositions();
    }

    @Override
    public boolean hasGravity() {
        return true;
    }

    @Override
    public double getStepHeight() {
        return 0.0;
    }
}
