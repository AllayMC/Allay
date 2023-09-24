package cn.allay.api.block.data;

import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Allay Project 2023/8/11
 *
 * @author JukeboxMC | daoge_cmd
 */
public enum BlockFace {

    DOWN(-1, new Vector3i(0, -1, 0)),
    UP(-1, new Vector3i(0, 1, 0)),
    NORTH(2, new Vector3i(0, 0, -1)),
    SOUTH(0, new Vector3i(0, 0, 1)),
    WEST(1, new Vector3i(-1, 0, 0)),
    EAST(3, new Vector3i(1, 0, 0));

    private final int horizontalIndex;
    private final Vector3ic offset;

    BlockFace(int horizontalIndex, Vector3ic offset) {
        this.horizontalIndex = horizontalIndex;
        this.offset = offset;
    }

    public Vector3ic getOffset() {
        return offset;
    }

    public Vector3ic offsetPos(int x, int y, int z) {
        return offset.add(x, y, z, new Vector3i());
    }

    public Vector3ic offsetPos(Vector3ic pos) {
        return offset.add(pos, new Vector3i());
    }

    public AABBf rotateAABB(AABBfc aabb) {
        var c1 = new Vector3f(aabb.minX(), aabb.minY(), aabb.minZ());
        var c2 = new Vector3f(aabb.maxX(), aabb.maxY(), aabb.maxZ());
        var nc1 = rotateVector(c1);
        var nc2 = rotateVector(c2);
        return new AABBf(
                min(nc1.x, nc2.x),
                min(nc1.y, nc2.y),
                min(nc1.z, nc2.z),
                max(nc1.x, nc2.x),
                max(nc1.y, nc2.y),
                max(nc1.z, nc2.z)
        );
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public Vector3f rotateVector(Vector3fc vec) {
        Vector3f result = new Vector3f(vec);
        // 平移到旋转点 (0.5, 0.5, 0.5)
        result.sub(0.5f, 0.5f, 0.5f);

        float temp;
        switch (this) {
            case EAST -> {
                // 不需要旋转，因为 EAST 是默认朝向
            }
            case NORTH -> {
                temp = result.x;
                result.x = result.z;
                result.z = -temp;
            }
            case SOUTH -> {
                temp = result.x;
                result.x = -result.z;
                result.z = temp;
            }
            case WEST -> {
                result.x = -result.x;
                result.z = -result.z;
            }
            case UP -> {
                temp = result.x;
                result.x = -result.y;
                result.y = temp;
            }
            case DOWN -> {
                temp = result.x;
                result.x = result.y;
                result.y = -temp;
            }
        }

        // 平移回原点
        result.add(0.5f, 0.5f, 0.5f);

        return result;
    }

    public int getHorizontalIndex() {
        return horizontalIndex;
    }

    public BlockFace opposite() {
        return switch (this) {
            case DOWN -> UP;
            case UP -> DOWN;
            case NORTH -> SOUTH;
            case WEST -> EAST;
            case EAST -> WEST;
            default -> NORTH;
        };
    }

    public static BlockFace fromId(int value) {
        return switch (value) {
            case 0 -> BlockFace.DOWN;
            case 1 -> BlockFace.UP;
            case 2 -> BlockFace.NORTH;
            case 3 -> BlockFace.SOUTH;
            case 4 -> BlockFace.WEST;
            case 5 -> BlockFace.EAST;
            default -> null;
        };
    }

    public static BlockFace[] getHorizontal() {
        return new BlockFace[]{
                NORTH,
                EAST,
                SOUTH,
                WEST
        };
    }

    public static BlockFace[] getVertical() {
        return new BlockFace[]{
                UP,
                DOWN
        };
    }

    public boolean isHorizontal() {
        return this == NORTH || this == EAST || this == SOUTH || this == WEST;
    }

    public static final BlockFace[] STAIR_DIRECTION_VALUE_TO_BLOCK_FACE =
            new BlockFace[]{
                    BlockFace.EAST, BlockFace.WEST,
                    BlockFace.SOUTH, BlockFace.NORTH};

    public static BlockFace getBlockFaceByStairDirectionValue(int value) {
        return STAIR_DIRECTION_VALUE_TO_BLOCK_FACE[value];
    }

    public int toStairDirectionValue() {
        return switch (this) {
            case EAST -> 0;
            case WEST -> 1;
            case SOUTH -> 2;
            case NORTH -> 3;
            default -> throw new IllegalStateException("Unexpected value: " + this);
        };
    }
}
