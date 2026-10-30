package org.allaymc.server.entity.component.humanlike;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.server.entity.component.EntityPhysicsComponentImpl;
import org.joml.Vector3d;

/**
 * {@code EntityHumanPhysicsComponentImpl} calculates humanlike motion with a
 * six-tenths-block step height and a horizontal drag rule shared between ground
 * and air. Liquid motion delegates to the inherited liquid calculation when
 * liquid physics is enabled, preserving its buoyancy and liquid-specific drag
 * handling.
 *
 * <p>
 * For other motion, the supporting block contributes friction only when the
 * entity is outside liquid and has a non-air support block. Horizontal velocity
 * always uses the ground drag factor, including airborne movement. Vertical
 * velocity separately applies optional gravity and the air drag factor. Keeping
 * these factors separate preserves humanlike air movement while still allowing
 * supporting blocks to change horizontal retention on the ground.
 *
 * @author daoge_cmd
 */
public class EntityHumanPhysicsComponentImpl extends EntityPhysicsComponentImpl {

    /**
     * Returns the humanlike step height of {@code 0.6} blocks. The inherited
     * collision solver uses this height when attempting to climb an obstacle
     * without an explicit jump, alongside the ordinary block-collision rules.
     */
    @Override
    public double getStepHeight() {
        return 0.6;
    }

    /**
     * Calculates humanlike velocity using liquid state, support friction, drag,
     * and gravity. Enabled liquid physics delegates to
     * {@code updateMotionInLiquid}; otherwise the method returns a fresh vector
     * without replacing the stored {@code motion}.
     *
     * <p>
     * A non-air support block contributes friction only outside liquids.
     * Horizontal retention then uses {@code getDragFactorOnGround} even in the
     * air, while vertical retention uses {@code getDragFactorInAir} after
     * optional gravity. This separation keeps the humanlike airborne horizontal
     * rule independent of the vertical drag and liquid-buoyancy calculations.
     */
    @Override
    public Vector3d updateMotion(LiquidState liquidState) {
        if (liquidState.inLiquid() && computeLiquidPhysics()) {
            return updateMotionInLiquid(liquidState);
        }

        var blockStateStandingOn = getBlockStateStandingOn();
        var isOnGround = blockStateStandingOn.getBlockType() != BlockTypes.AIR;
        var slipperinessMultiplier = 1.0;
        // A liquid state suppresses support friction even when liquid physics is
        // disabled, so the non-liquid path does not apply a block's friction underwater.
        if (!liquidState.inLiquid() && isOnGround) {
            slipperinessMultiplier = blockStateStandingOn.getBlockStateData().friction();
        }

        // Preserve humanlike horizontal retention while airborne by using ground
        // drag here; vertical motion below still uses the independent air drag.
        var horizontalFactor = slipperinessMultiplier * (1 - getDragFactorOnGround());
        return new Vector3d(
                motion.x() * horizontalFactor,
                (motion.y() - (hasGravity() ? getGravity() : 0f)) * (1 - getDragFactorInAir()),
                motion.z() * horizontalFactor
        );
    }
}
