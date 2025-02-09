package org.allaymc.api.math.voxelshape;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.type.BlockState;

/**
 * VoxelShapes contains some useful voxel shapes.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class VoxelShapes {
    public static VoxelShape buildLiquidShape(BlockState liquidBlockState) {
        if (!(liquidBlockState.getBehavior() instanceof BlockLiquidBaseComponent)) {
            throw new IllegalArgumentException("The liquidBlockState must implement BlockLiquidBaseComponent!");
        }
        return VoxelShape.builder()
                .solid(0, 0, 0, 1, 0.125f * BlockLiquidBaseComponent.getDepth(liquidBlockState), 1)
                .build();
    }
}
