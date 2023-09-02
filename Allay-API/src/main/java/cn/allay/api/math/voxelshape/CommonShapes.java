package cn.allay.api.math.voxelshape;

import cn.allay.api.block.data.BlockFace;

import java.util.EnumMap;
import java.util.Map;

/**
 * Allay Project 2023/9/2
 *
 * @author daoge_cmd
 */
public abstract class CommonShapes {
    private static final VoxelShape UPWARDS_STAIR_SHAPE = VoxelShape
            .builder()
            .solid(0, 0, 0, 1, 1, 1)
            .vacancy(0.5f, 0.5f, 0, 1, 1, 1)
            .build();

    private static final VoxelShape DOWNWARDS_STAIR_SHAPE = VoxelShape
            .builder()
            .solid(0, 0, 0, 1, 1, 1)
            .vacancy(0.5f, 0, 0, 1, 0.5f, 1)
            .build();

    public static final Map<BlockFace, VoxelShape> UPWARDS_STAIR_SHAPES = new EnumMap<>(BlockFace.class);

    public static final Map<BlockFace, VoxelShape> DOWNWARDS_STAIR_SHAPES = new EnumMap<>(BlockFace.class);

    static {
        for (var face : BlockFace.getHorizontal()) {
            UPWARDS_STAIR_SHAPES.put(face, UPWARDS_STAIR_SHAPE.rotate(face));
            DOWNWARDS_STAIR_SHAPES.put(face, DOWNWARDS_STAIR_SHAPE.rotate(face));
        }
    }
}
