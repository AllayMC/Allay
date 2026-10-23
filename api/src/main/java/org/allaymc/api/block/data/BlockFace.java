package org.allaymc.api.block.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.jetbrains.annotations.ApiStatus;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * {@code BlockFace} represents one of a block's six axis-aligned faces and its
 * neighboring-coordinate offset. The constants supply full-face indices in
 * declaration order, horizontal indices in south-west-north-east order, and
 * transformations that orient east-facing geometry around the center of a unit
 * block.
 *
 * @author JukeboxMC | daoge_cmd | Dhaiven
 */
@Getter
@AllArgsConstructor
public enum BlockFace {
    /**
     * Negative vertical axis face.
     */
    DOWN(new Vector3i(0, -1, 0), -1, Axis.Y),
    /**
     * Positive vertical axis face.
     */
    UP(new Vector3i(0, 1, 0), -1, Axis.Y),
    /**
     * Negative depth axis face.
     */
    NORTH(new Vector3i(0, 0, -1), 2, Axis.Z),
    /**
     * Positive depth axis face.
     */
    SOUTH(new Vector3i(0, 0, 1), 0, Axis.Z),
    /**
     * Negative horizontal axis face.
     */
    WEST(new Vector3i(-1, 0, 0), 1, Axis.X),
    /**
     * Positive horizontal axis face.
     */
    EAST(new Vector3i(1, 0, 0), 3, Axis.X);

    /**
     * {@code VALUES} holds the declaration-order faces used by {@code fromIndex}.
     * The array is publicly shared, so callers must leave its entries unchanged to
     * preserve index decoding.
     */
    public static final BlockFace[] VALUES = values();
    /**
     * {@code HORIZONTAL_BLOCK_FACES} supplies south-west-north-east index order.
     * {@code getHorizontalBlockFaces} exposes this same array, so consumers must
     * preserve its entries and ordering.
     */
    private static final BlockFace[] HORIZONTAL_BLOCK_FACES = {SOUTH, WEST, NORTH, EAST};
    /**
     * {@code VERTICAL_BLOCK_FACES} holds the up-down face pair. {@code
     * getVerticalBlockFaces} exposes this same array, so consumers must leave its
     * entries unchanged.
     */
    private static final BlockFace[] VERTICAL_BLOCK_FACES = {UP, DOWN};

    /**
     * {@code offset} holds the adjacent-block displacement shared by generated
     * getters and coordinate operations. Callers must not mutate its underlying
     * vector through a concrete-type cast.
     */
    private final Vector3ic offset;
    /**
     * Horizontal index, or {@code -1} for vertical faces.
     */
    private final int horizontalIndex;
    /**
     * Face normal coordinate axis.
     */
    private final Axis axis;

    /**
     * Returns the face at the supplied declaration-order index. Values outside
     * {@code 0} through {@code 5} return {@code null}; this numbering differs from
     * the horizontal-only index used by block properties.
     */
    public static BlockFace fromIndex(int index) {
        if (index < 0 || index >= VALUES.length) {
            return null;
        }

        return VALUES[index];
    }

    /**
     * Returns the face at a horizontal property index in south-west-north-east
     * order. Values outside {@code 0} through {@code 3} return {@code null}, and
     * vertical faces have no index in this lookup.
     */
    public static BlockFace fromHorizontalIndex(int index) {
        if (index < 0 || index >= HORIZONTAL_BLOCK_FACES.length) {
            return null;
        }

        return HORIZONTAL_BLOCK_FACES[index];
    }

    /**
     * Returns the horizontal block face matching the supplied Minecraft cardinal
     * direction.
     */
    @ApiStatus.Experimental
    public static BlockFace from(MinecraftCardinalDirection direction) {
        return switch (direction) {
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case EAST -> EAST;
            case WEST -> WEST;
        };
    }

    /**
     * Returns the shared horizontal face array in south-west-north-east order.
     * Callers must leave its contents unchanged because horizontal index decoding
     * reads this array directly on subsequent lookups.
     */
    public static BlockFace[] getHorizontalBlockFaces() {
        return HORIZONTAL_BLOCK_FACES;
    }

    /**
     * Returns the shared vertical face array in up-down order. Callers must leave
     * its contents unchanged because other users of this accessor receive the same
     * array rather than a copy.
     */
    public static BlockFace[] getVerticalBlockFaces() {
        return VERTICAL_BLOCK_FACES;
    }

    /**
     * Returns a new coordinate vector one block along this face from the supplied
     * coordinates.
     */
    public Vector3ic offsetPos(int x, int y, int z) {
        return offset.add(x, y, z, new Vector3i());
    }

    /**
     * Returns a new coordinate vector displaced by {@code step} multiples of this
     * face offset.
     */
    public Vector3ic offsetPos(int x, int y, int z, int step) {
        return offset.mul(step, new Vector3i()).add(x, y, z, new Vector3i());
    }

    /**
     * Returns a new vector one block along this face without modifying {@code pos}.
     */
    public Vector3ic offsetPos(Vector3ic pos) {
        return offset.add(pos, new Vector3i());
    }

    /**
     * Returns a new vector displaced by {@code step} face offsets without modifying
     * {@code pos}.
     */
    public Vector3ic offsetPos(Vector3ic pos, int step) {
        return offset.mul(step, new Vector3i()).add(pos, new Vector3i());
    }

    /**
     * Returns a new bounding box oriented from {@code EAST} to this face around the
     * unit-block center. The input box remains unchanged, and reordered minimum and
     * maximum coordinates keep the returned box axis-aligned.
     */
    public AABBd rotateAABB(AABBdc aabb) {
        var c1 = new Vector3d(aabb.minX(), aabb.minY(), aabb.minZ());
        var c2 = new Vector3d(aabb.maxX(), aabb.maxY(), aabb.maxZ());
        var nc1 = rotateVector(c1);
        var nc2 = rotateVector(c2);
        return new AABBd(
                min(nc1.x, nc2.x),
                min(nc1.y, nc2.y),
                min(nc1.z, nc2.z),
                max(nc1.x, nc2.x),
                max(nc1.y, nc2.y),
                max(nc1.z, nc2.z)
        );
    }

    /**
     * Returns a new vector rotated from {@code EAST} to this face about {@code
     * (0.5, 0.5, 0.5)}. The input vector remains unchanged; vertical faces rotate
     * in the horizontal-vertical plane instead of around the vertical axis.
     */
    @SuppressWarnings("SuspiciousNameCombination")
    public Vector3d rotateVector(Vector3dc vec) {
        Vector3d result = new Vector3d(vec);
        // Center unit-block geometry before swapping axes so rotations preserve its
        // placement inside the original block coordinates.
        result.sub(0.5, 0.5, 0.5);

        double temp;
        switch (this) {
            case EAST -> {
                // EAST is the source orientation for the shapes passed to this
                // transformation.
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

        // Restore block-relative coordinates after the rotation around the center.
        return result.add(0.5, 0.5, 0.5);
    }

    /**
     * Returns the face whose adjacent-block offset points in the opposite
     * direction.
     */
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

    /**
     * Returns this face's entity yaw in degrees, with south at {@code 0}. Vertical
     * faces also return {@code 0}, so callers needing a horizontal orientation must
     * check the face separately.
     */
    public double yaw() {
        return switch (this) {
            case WEST -> 90;
            case NORTH -> 180;
            case EAST -> 270;
            // SOUTH or vertical faces
            default -> 0;
        };
    }

    /**
     * Returns the next horizontal face clockwise around the vertical axis. Vertical
     * faces have no horizontal successor and throw {@code IllegalStateException};
     * callers must select a horizontal face before rotating.
     */
    public BlockFace rotateY() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            default -> throw new IllegalStateException("Unable to get Y-rotated face of " + this);
        };
    }

    /**
     * Returns the next horizontal face counterclockwise around the vertical axis.
     * Vertical faces have no horizontal predecessor and throw {@code
     * IllegalStateException}; callers must select a horizontal face before
     * rotating.
     */
    public BlockFace rotateYCCW() {
        return switch (this) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            default -> throw new IllegalStateException("Unable to get counter-clockwise Y-rotated face of " + this);
        };
    }

    /**
     * Returns whether this face points north, south, east, or west.
     */
    public boolean isHorizontal() {
        return this == NORTH || this == EAST || this == SOUTH || this == WEST;
    }

    /**
     * Returns whether this face points along the vertical coordinate axis.
     */
    public boolean isVertical() {
        return this == UP || this == DOWN;
    }

    /**
     * Returns the Minecraft cardinal direction corresponding to this horizontal
     * face. The vertical faces {@code UP} and {@code DOWN} return {@code null},
     * because cardinal direction properties cannot represent vertical orientation.
     */
    @ApiStatus.Experimental
    public MinecraftCardinalDirection toMinecraftCardinalDirection() {
        return switch (this) {
            case NORTH -> MinecraftCardinalDirection.NORTH;
            case SOUTH -> MinecraftCardinalDirection.SOUTH;
            case EAST -> MinecraftCardinalDirection.EAST;
            case WEST -> MinecraftCardinalDirection.WEST;
            default -> null;
        };
    }

    /**
     * {@code Axis} identifies the coordinate axis normal to a block face.
     */
    public enum Axis {
        /**
         * West-east coordinate axis.
         */
        X,
        /**
         * Down-up coordinate axis.
         */
        Y,
        /**
         * North-south coordinate axis.
         */
        Z
    }
}
