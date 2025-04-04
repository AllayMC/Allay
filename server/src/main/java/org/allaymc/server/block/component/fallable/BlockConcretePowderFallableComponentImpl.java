package org.allaymc.server.block.component.fallable;

import org.allaymc.api.block.component.BlockConcretePowderBaseComponent;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.world.Sound;

/**
 * @author IWareQ
 */
public class BlockConcretePowderFallableComponentImpl extends BlockFallableComponentImpl {
    public BlockConcretePowderFallableComponentImpl() {
        super(Sound.LAND_SAND);
    }

    @Override
    public void onLanded(Location3d location, double fallDistance, BlockState blockState) {
        var dimension = location.dimension();
        if (blockState.getBehavior() instanceof BlockConcretePowderBaseComponent concretePowderBaseComponent) {
            if (concretePowderBaseComponent.hasAdjacentWater(dimension, MathUtils.floor(location))) {
                dimension.setBlockState(location, concretePowderBaseComponent.getSolidBlock().getDefaultState());
                return;
            }
        }

        super.onLanded(location, fallDistance, blockState);
    }
}
