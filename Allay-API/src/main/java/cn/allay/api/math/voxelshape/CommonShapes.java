package cn.allay.api.math.voxelshape;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.data.VanillaBlockPropertyTypes;

import java.util.EnumMap;
import java.util.Map;

import static cn.allay.api.block.data.BlockFace.getBlockFaceByStairDirectionValue;

/**
 * Allay Project 2023/9/2
 *
 * @author daoge_cmd
 */
public abstract class CommonShapes {
    private static final VoxelShape UPWARDS_STAIR_SHAPE = VoxelShape
            .builder()
            .solid(0, 0, 0, 1, 1, 1)
            .vacancy(0, 0.5f, 0, 0.5f, 1, 1)
            .build();

    private static final VoxelShape DOWNWARDS_STAIR_SHAPE = VoxelShape
            .builder()
            .solid(0, 0, 0, 1, 1, 1)
            .vacancy(0, 0, 0, 0.5f, 0.5f, 1)
            .build();

    public static final Map<BlockFace, VoxelShape> UPWARDS_STAIR_SHAPES = new EnumMap<>(BlockFace.class);

    public static final Map<BlockFace, VoxelShape> DOWNWARDS_STAIR_SHAPES = new EnumMap<>(BlockFace.class);

    static {
        for (var face : BlockFace.getHorizontal()) {
            UPWARDS_STAIR_SHAPES.put(face, UPWARDS_STAIR_SHAPE.rotate(face));
            DOWNWARDS_STAIR_SHAPES.put(face, DOWNWARDS_STAIR_SHAPE.rotate(face));
        }
    }

    public static VoxelShape buildStairShape(BlockState stairBlockState) {
        var isDownwards = stairBlockState.getPropertyValue(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT);
        var face = getBlockFaceByStairDirectionValue(stairBlockState.getPropertyValue(VanillaBlockPropertyTypes.WEIRDO_DIRECTION));
        return isDownwards ? DOWNWARDS_STAIR_SHAPES.get(face) : UPWARDS_STAIR_SHAPES.get(face);
    }
}
