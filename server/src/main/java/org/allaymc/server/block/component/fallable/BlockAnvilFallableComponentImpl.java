package org.allaymc.server.block.component.fallable;

import org.allaymc.api.block.component.BlockAnvilBaseComponent;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.world.Sound;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author IWareQ
 */
public class BlockAnvilFallableComponentImpl extends BlockFallableComponentImpl {
    private static final float ANVIL_DAMAGE_CHANCE = 0.05f;

    public BlockAnvilFallableComponentImpl() {
        super(Sound.RANDOM_ANVIL_LAND);
    }

    @Override
    public void onLanded(Location3d location, double fallDistance) {
        fallDistance = Math.round(fallDistance);

        var dimension = location.dimension();
        if (fallDistance > 1 && ThreadLocalRandom.current().nextFloat() < ANVIL_DAMAGE_CHANCE * fallDistance) {
            var anvilState = dimension.getBlockState(location);
            if (anvilState.getBehavior() instanceof BlockAnvilBaseComponent anvilComponent) {
                var newAnvilState = anvilComponent.damage(anvilState);
                dimension.setBlockState(location, newAnvilState);

                if (newAnvilState.getBlockType() == BlockTypes.AIR) {
                    return;
                }
            }
        }

        super.onLanded(location, fallDistance);
    }

    @Override
    public float getDamage(double fallDistance) {
        return (float) Math.min(2f * fallDistance, 40f);
    }
}
