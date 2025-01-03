package org.allaymc.api.block.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.jetbrains.annotations.ApiStatus;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Represents a face of a block.
 *
 * @author JukeboxMC | daoge_cmd | Dhaiven
 */
@Getter
@AllArgsConstructor
public enum BlockFace {

    DOWN(new Vector3i(0, -1, 0)),
    UP(new Vector3i(0, 1, 0)),
    NORTH(new Vector3i(0, 0, -1)),
    SOUTH(new Vector3i(0, 0, 1)),
    WEST(new Vector3i(-1, 0, 0)),
    EAST(new Vector3i(1, 0, 0));

    private static final BlockFace[] HORIZONTAL_BLOCK_FACES = {NORTH, EAST, SOUTH, WEST};
    private static final BlockFace[] VERTICAL_BLOCK_FACES = {UP, DOWN};

    private final Vector3ic offset;

    /**
     * Get the block face by index.
     *
     * @param value the index.
     *
     * @return the block face.
     */
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

    /**
     * Get horizontal block faces.
     *
     * @return the horizontal block faces.
     */
    public static BlockFace[] getHorizontalBlockFaces() {
        return HORIZONTAL_BLOCK_FACES;
    }

    /**
     * Get vertical block faces.
     *
     * @return the vertical block faces.
     */
    public static BlockFace[] getVerticalBlockFaces() {
        return VERTICAL_BLOCK_FACES;
    }

    /**
     * Add current block face offset to the given pos.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param z the z coordinate.
     *
     * @return the result pos.
     */
    public Vector3ic offsetPos(int x, int y, int z) {
        return offset.add(x, y, z, new Vector3i());
    }

    /**
     * Add current block face offset to the given pos.
     *
     * @param pos the pos.
     *
     * @return the result pos.
     */
    public Vector3ic offsetPos(Vector3ic pos) {
        return offset.add(pos, new Vector3i());
    }

    /**
     * Rotate the given AABB.
     *
     * @param aabb the AABB.
     *
     * @return the rotated AABB.
     */
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

    /**
     * Rotate the given vector.
     *
     * @param vec the vector.
     *
     * @return the rotated vector.
     */
    @SuppressWarnings("SuspiciousNameCombination")
    public Vector3f rotateVector(Vector3fc vec) {
        Vector3f result = new Vector3f(vec);
        // Translate to rotation point (0.5, 0.5, 0.5)
        result.sub(0.5f, 0.5f, 0.5f);

        float temp;
        switch (this) {
            case EAST -> {
                // No rotation needed as EAST is the default orientation
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

        // Translate back to original point
        result.add(0.5f, 0.5f, 0.5f);

        return result;
    }

    /**
     * Get the opposite block face.
     *
     * @return the opposite block face.
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
     * Rotate this BlockFace around the Y axis clockwise (NORTH =&gt; EAST =&gt; SOUTH =&gt; WEST =&gt; NORTH).
     *
     * @return block face.
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
     * Rotate this BlockFace around the Y axis counter-clockwise (NORTH =&gt; WEST =&gt; SOUTH =&gt; EAST =&gt; NORTH).
     *
     * @return block face.
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
     * Check if this block face is vertical.
     *
     * @return true if vertical, false if not.
     */
    public boolean isHorizontal() {
        return this == NORTH || this == EAST || this == SOUTH || this == WEST;
    }

    /**
     * Get the Minecraft cardinal direction which represents this block face.
     *
     * @return the Minecraft cardinal direction.
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
}
