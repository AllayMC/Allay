package org.allaymc.api.math.voxelshape;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;

import java.util.EnumMap;
import java.util.Map;

/**
 * VoxelShapes contains some useful voxel shapes.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class VoxelShapes {

    // Stairs
    private static final BlockFace[] STAIR_DIRECTION_VALUE_TO_BLOCK_FACE = {BlockFace.EAST, BlockFace.WEST, BlockFace.SOUTH, BlockFace.NORTH};
    private static final Map<BlockFace, VoxelShape> UPWARDS_STAIR_SHAPES = new EnumMap<>(BlockFace.class);
    private static final Map<BlockFace, VoxelShape> DOWNWARDS_STAIR_SHAPES = new EnumMap<>(BlockFace.class);

    private static final VoxelShape UPWARDS_STAIR_SHAPE = VoxelShape.builder()
            .solid(0, 0, 0, 1, 1, 1)
            .vacancy(0, 0.5f, 0, 0.5f, 1, 1)
            .build();
    private static final VoxelShape DOWNWARDS_STAIR_SHAPE = VoxelShape.builder()
            .solid(0, 0, 0, 1, 1, 1)
            .vacancy(0, 0, 0, 0.5f, 0.5f, 1)
            .build();

    static {
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            UPWARDS_STAIR_SHAPES.put(face, UPWARDS_STAIR_SHAPE.rotate(face));
            DOWNWARDS_STAIR_SHAPES.put(face, DOWNWARDS_STAIR_SHAPE.rotate(face));
        }
    }

    public static VoxelShape buildStairShape(BlockState stairBlockState) {
        var isDownwards = stairBlockState.getPropertyValue(BlockPropertyTypes.UPSIDE_DOWN_BIT);
        var face = STAIR_DIRECTION_VALUE_TO_BLOCK_FACE[stairBlockState.getPropertyValue(BlockPropertyTypes.WEIRDO_DIRECTION)];
        return isDownwards ? DOWNWARDS_STAIR_SHAPES.get(face) : UPWARDS_STAIR_SHAPES.get(face);
    }

    public static VoxelShape buildLiquidShape(BlockState liquidBlockState) {
        if (!(liquidBlockState.getBehavior() instanceof BlockLiquidBaseComponent liquidBaseComponent)) {
            throw new IllegalArgumentException("The liquidBlockState must implement BlockLiquidBaseComponent!");
        }
        return VoxelShape.builder()
                .solid(0, 0, 0, 1, 0.125f * BlockLiquidBaseComponent.getDepth(liquidBlockState), 1)
                .build();
    }
}
