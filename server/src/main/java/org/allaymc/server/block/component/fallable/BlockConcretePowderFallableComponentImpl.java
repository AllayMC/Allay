package org.allaymc.server.block.component.fallable;

import org.allaymc.api.block.component.BlockConcretePowderBaseComponent;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.world.sound.SoundNames;

/**
 * {@code BlockConcretePowderFallableComponentImpl} hardens landed concrete powder
 * beside liquid and otherwise uses ordinary falling-block placement. The hardening
 * branch uses the powder's solid default state and skips the inherited landing sound,
 * while the shared adjacency helper also accepts lava as a liquid.
 *
 * @author IWareQ
 */
public class BlockConcretePowderFallableComponentImpl extends BlockFallableComponentImpl {
    /**
     * Creates concrete powder falling behavior with the sand landing sound.
     */
    public BlockConcretePowderFallableComponentImpl() {
        super(SoundNames.LAND_SAND);
    }

    /**
     * Places solid concrete when the landed state exposes powder hardening and
     * adjacent liquid is found. Otherwise inherited landing places the supplied state
     * and plays the sand sound; the hardening branch returns without that sound.
     */
    @Override
    public void onLanded(Location3d location, double fallDistance, BlockState blockState) {
        var dimension = location.dimension();
        if (blockState.getBehavior() instanceof BlockConcretePowderBaseComponent concretePowderBaseComponent) {
            if (concretePowderBaseComponent.hasAdjacentWater(dimension, MathUtils.floor(location))) {
                dimension.setBlockState(location, concretePowderBaseComponent.getSolidBlock().getDefaultState());
                return;
            }
        }

        // Without the powder capability or adjacent liquid, ordinary landing retains
        // the supplied falling state and its configured sand landing sound.
        super.onLanded(location, fallDistance, blockState);
    }
}
