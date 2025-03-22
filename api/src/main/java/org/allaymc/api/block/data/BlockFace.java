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
     * Retrieves the horizontal block faces.
     *
     * @return the horizontal block faces.
     */
    public static BlockFace[] getHorizontalBlockFaces() {
        return HORIZONTAL_BLOCK_FACES;
    }

    /**
     * Retrieves the vertical block faces.
     *
     * @return the vertical block faces.
     */
    public static BlockFace[] getVerticalBlockFaces() {
        return VERTICAL_BLOCK_FACES;
    }

    /**
     * Converts a {@link MinecraftCardinalDirection} to a corresponding {@link BlockFace}.
     *
     * @param direction the Minecraft cardinal direction to convert.
     *
     * @return the corresponding {@link BlockFace}.
     */
    @ApiStatus.Experimental
    public static BlockFace fromMinecraftCardinalDirection(MinecraftCardinalDirection direction) {
        return switch (direction) {
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case EAST -> EAST;
            case WEST -> WEST;
        };
    }

    /**
     * Adds the current block face offset to the specified coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     *
     * @return a new Vector3ic representing the resulting position
     */
    public Vector3ic offsetPos(int x, int y, int z) {
        return offset.add(x, y, z, new Vector3i());
    }

    /**
     * Adds the current block face offset to the specified position.
     *
     * @param pos the position to offset
     *
     * @return a new Vector3ic representing the resulting position
     */
    public Vector3ic offsetPos(Vector3ic pos) {
        return offset.add(pos, new Vector3i());
    }

    /**
     * Rotates the given AABB based on this block face.
     *
     * @param aabb the axis-aligned bounding box to rotate
     *
     * @return a new AABBd representing the rotated bounding box
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
     * Rotates the given vector based on this block face.
     *
     * @param vec the vector to rotate
     *
     * @return a new Vector3d representing the rotated vector
     */
    @SuppressWarnings("SuspiciousNameCombination")
    public Vector3d rotateVector(Vector3dc vec) {
        Vector3d result = new Vector3d(vec);
        // Translate to rotation point (0.5, 0.5, 0.5)
        result.sub(0.5, 0.5, 0.5);

        double temp;
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
        return result.add(0.5, 0.5, 0.5);
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

    /**
     * Rotates this block face clockwise around the Y axis (NORTH => EAST => SOUTH => WEST => NORTH).
     *
     * @return the rotated {@link BlockFace}
     *
     * @throws IllegalStateException if this face is {@link #UP} or {@link #DOWN}
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
     * Rotates this block face counter-clockwise around the Y axis (NORTH => WEST => SOUTH => EAST => NORTH).
     *
     * @return the rotated {@link BlockFace}
     *
     * @throws IllegalStateException if this face is {@link #UP} or {@link #DOWN}
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
     * Checks if this block face is horizontal.
     *
     * @return {@code true} if this face is {@link #NORTH}, {@link #EAST}, {@link #SOUTH}, or {@link #WEST}, {@code false} otherwise
     */
    public boolean isHorizontal() {
        return this == NORTH || this == EAST || this == SOUTH || this == WEST;
    }

    /**
     * Checks if this block face is vertical.
     *
     * @return {@code true} if this face is {@link #UP} or {@link #DOWN}, {@code false} otherwise
     */
    public boolean isVertical() {
        return this == UP || this == DOWN;
    }

    /**
     * Converts this block face to its corresponding Minecraft cardinal direction.
     *
     * @return the corresponding {@link MinecraftCardinalDirection}, or {@code null} if not applicable
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
