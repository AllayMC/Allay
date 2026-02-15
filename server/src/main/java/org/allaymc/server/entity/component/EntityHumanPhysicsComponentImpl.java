package org.allaymc.server.entity.component;

import org.allaymc.api.block.type.BlockTypes;
import org.joml.Vector3d;

/**
 * @author daoge_cmd
 */
public class EntityHumanPhysicsComponentImpl extends EntityPhysicsComponentImpl {

    @Override
    public double getStepHeight() {
        return 0.6;
    }

    @Override
    public Vector3d updateMotion(LiquidState liquidState) {
        if (liquidState.inLiquid() && computeLiquidPhysics()) {
            return updateMotionInLiquid(liquidState);
        }

        var blockStateStandingOn = getBlockStateStandingOn();
        var isOnGround = blockStateStandingOn.getBlockType() != BlockTypes.AIR;
        var slipperinessMultiplier = 1.0;
        // Entity that has liquid motion won't be affected by the friction of the block it stands on
        if (!liquidState.inLiquid() && isOnGround) {
            slipperinessMultiplier = blockStateStandingOn.getBlockStateData().friction();
        }

        // NOTICE: Ground drag factor is always used for human entity even if the entity is in air.
        // This is different to non-human entities.
        var horizontalFactor = slipperinessMultiplier * (1 - getDragFactorOnGround());
        return new Vector3d(
                motion.x() * horizontalFactor,
                (motion.y() - (hasGravity() ? getGravity() : 0f)) * (1 - getDragFactorInAir()),
                motion.z() * horizontalFactor
        );
    }
}
