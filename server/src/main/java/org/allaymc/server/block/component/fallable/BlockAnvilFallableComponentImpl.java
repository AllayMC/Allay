package org.allaymc.server.block.component.fallable;

import org.allaymc.api.block.component.BlockAnvilBaseComponent;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.world.sound.SoundNames;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockAnvilFallableComponentImpl} applies falling-anvil wear and impact
 * damage before delegating surviving landings to the shared fallable component.
 * The landing wear chance increases with rounded fall distance; a transition to
 * air ends the landing without placing an anvil or playing the shared landing
 * sound.
 *
 * @author IWareQ
 */
public class BlockAnvilFallableComponentImpl extends BlockFallableComponentImpl {
    /**
     * {@code ANVIL_DAMAGE_CHANCE} is the wear probability per rounded
     * fall-distance block. {@code onLanded} multiplies this factor by the distance
     * for falls longer than one block.
     */
    private static final float ANVIL_DAMAGE_CHANCE = 0.05f;

    /**
     * Creates an anvil fallable component using the anvil landing sound.
     */
    public BlockAnvilFallableComponentImpl() {
        super(SoundNames.RANDOM_ANVIL_LAND);
    }

    /**
     * Rounds the fall distance and probabilistically advances anvil wear. Falls
     * longer than one rounded block can damage the anvil; a resulting air state
     * suppresses shared placement and landing sound. A surviving state delegates
     * to the shared landing path.
     */
    @Override
    public void onLanded(Location3d location, double fallDistance, BlockState blockState) {
        // Wear uses the nearest whole fall-distance block rather than fractional
        // travel.
        fallDistance = Math.round(fallDistance);

        if (fallDistance > 1 && ThreadLocalRandom.current().nextFloat() < ANVIL_DAMAGE_CHANCE * fallDistance) {
            if (blockState.getBehavior() instanceof BlockAnvilBaseComponent anvilComponent) {
                var newAnvilState = anvilComponent.damage(blockState);
                if (newAnvilState.getBlockType() == BlockTypes.AIR) {
                    return;
                }

                blockState = newAnvilState;
            }
        }

        super.onLanded(location, fallDistance, blockState);
    }

    /**
     * Returns twice the fall distance capped at forty damage points. The
     * calculation does not round or clamp negative input; callers supply the
     * nonnegative distance for the falling entity.
     */
    @Override
    public float calculateDamage(double fallDistance) {
        // Cap the two-damage-per-block impact at forty damage points.
        return (float) Math.min(2f * fallDistance, 40f);
    }
}
