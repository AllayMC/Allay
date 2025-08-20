package org.allaymc.server.entity.component;

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
    public boolean computeEntityCollisionMotion() {
        return false;
    }
}
