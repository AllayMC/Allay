package org.allaymc.api.block.data;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * {@code CompassRoseDirection} represents sixteen horizontal directions with block
 * offsets, cardinal-face choices, yaw values, and rotation-property indices. Stored
 * yaw starts at north, while the index table starts at south and proceeds through
 * west, north, and east. Enum declaration order differs from that index order, so
 * ordinal values cannot replace the explicit indices.
 *
 * <p>
 * {@code getClosestFromYaw} adds a half turn before quantizing yaw into the
 * south-first table. For example, north's yaw of {@code 0} degrees selects index
 * {@code 8}, which identifies {@code NORTH}; south's yaw of {@code 180} degrees
 * wraps to index {@code 0}. The default sixteen-direction precision therefore
 * resolves every {@code getYaw} value back to its original direction, while coarser
 * precision selects a neighboring cardinal or diagonal direction.
 *
 * @author joserobjr | Cool_Loong
 */
public enum CompassRoseDirection {
    /**
     * North compass direction.
     */
    NORTH(0, -1, BlockFace.NORTH, 0, 8),
    /**
     * East compass direction.
     */
    EAST(1, 0, BlockFace.EAST, 90, 12),
    /**
     * South compass direction.
     */
    SOUTH(0, 1, BlockFace.SOUTH, 180, 0),
    /**
     * West compass direction.
     */
    WEST(-1, 0, BlockFace.WEST, 270, 4),
    /**
     * North east compass direction.
     */
    NORTH_EAST(NORTH, EAST, BlockFace.NORTH, 45, 10),
    /**
     * North west compass direction.
     */
    NORTH_WEST(NORTH, WEST, BlockFace.WEST, 315, 6),
    /**
     * South east compass direction.
     */
    SOUTH_EAST(SOUTH, EAST, BlockFace.EAST, 135, 14),
    /**
     * South west compass direction.
     */
    SOUTH_WEST(SOUTH, WEST, BlockFace.SOUTH, 225, 2),
    /**
     * West north west compass direction.
     */
    WEST_NORTH_WEST(WEST, NORTH_WEST, BlockFace.WEST, 292.5, 5),
    /**
     * North north west compass direction.
     */
    NORTH_NORTH_WEST(NORTH, NORTH_WEST, BlockFace.NORTH, 337.5, 7),
    /**
     * North north east compass direction.
     */
    NORTH_NORTH_EAST(NORTH, NORTH_EAST, BlockFace.NORTH, 22.5, 9),
    /**
     * East north east compass direction.
     */
    EAST_NORTH_EAST(EAST, NORTH_EAST, BlockFace.EAST, 67.5, 11),
    /**
     * East south east compass direction.
     */
    EAST_SOUTH_EAST(EAST, SOUTH_EAST, BlockFace.EAST, 112.5, 13),
    /**
     * South south east compass direction.
     */
    SOUTH_SOUTH_EAST(SOUTH, SOUTH_EAST, BlockFace.SOUTH, 157.5, 15),
    /**
     * South south west compass direction.
     */
    SOUTH_SOUTH_WEST(SOUTH, SOUTH_WEST, BlockFace.SOUTH, 202.5, 1),
    /**
     * West south west compass direction.
     */
    WEST_SOUTH_WEST(WEST, SOUTH_WEST, BlockFace.WEST, 247.5, 3);

    /**
     * {@code VALUES} orders directions by their rotation-property indices rather
     * than declaration order, allowing yaw quantization to share the same lookup.
     */
    private final static CompassRoseDirection[] VALUES = new CompassRoseDirection[]{
            SOUTH, SOUTH_SOUTH_WEST, SOUTH_WEST, WEST_SOUTH_WEST,
            WEST, WEST_NORTH_WEST, NORTH_WEST, NORTH_NORTH_WEST,
            NORTH, NORTH_NORTH_EAST, NORTH_EAST, EAST_NORTH_EAST,
            EAST, EAST_SOUTH_EAST, SOUTH_EAST, SOUTH_SOUTH_EAST
    };

    /**
     * Horizontal block offset component.
     */
    private final int modX;
    /**
     * Depth block offset component.
     */
    private final int modZ;
    /**
     * Selected neighboring cardinal face.
     */
    private final BlockFace closestBlockFace;
    /**
     * Stored north-zero yaw, degrees.
     */
    private final float yaw;
    /**
     * South-zero rotation property index.
     */
    private final int index;

    /**
     * Creates a direction with explicit coordinate offsets and its stored
     * orientation metadata.
     */
    CompassRoseDirection(int modX, int modZ, BlockFace closestBlockFace, double yaw, int index) {
        this.modX = modX;
        this.modZ = modZ;
        this.closestBlockFace = closestBlockFace;
        this.yaw = (float) yaw;
        this.index = index;
    }

    /**
     * Creates an intermediate direction by adding two existing direction offsets
     * and retaining supplied orientation metadata.
     */
    CompassRoseDirection(CompassRoseDirection face1, CompassRoseDirection face2, BlockFace closestBlockFace, double yaw, int index) {
        this.modX = face1.getModX() + face2.getModX();
        this.modZ = face1.getModZ() + face2.getModZ();
        this.closestBlockFace = closestBlockFace;
        this.yaw = (float) yaw;
        this.index = index;
    }

    /**
     * Returns the direction at a south-zero rotation-property index. Callers must
     * supply an index from {@code 0} through {@code 15}; the lookup throws an array
     * bounds exception for other values.
     */
    public static CompassRoseDirection from(int index) {
        return VALUES[index];
    }

    /**
     * Returns the closest direction after rounding the supplied yaw to the selected
     * precision. Input yaw follows this enum's north-zero convention, with east at
     * {@code 90} degrees and south at {@code 180} degrees. The precision selects
     * four, eight, or sixteen subdivisions before lookup in the common
     * sixteen-entry table.
     *
     * <p>
     * The computation adds {@code 180} degrees because the index table begins at
     * south. North's {@code 0}-degree yaw consequently selects index {@code 8}, and
     * wrapping retains the direction after a complete turn rather than shifting the
     * result to the opposite face.
     */
    public static CompassRoseDirection getClosestFromYaw(double yaw, @NotNull Precision precision) {
        // Offset north-zero yaw into the south-first index table before rounding
        // and wrapping, preserving the represented direction across the two origins.
        return CompassRoseDirection.from((int) Math.round(Math.round((yaw + 180.0) * precision.directions / 360.0) * (16.0 / precision.directions)) & 0x0f);
    }

    /**
     * Returns the closest of all sixteen directions for the supplied yaw in
     * degrees. This overload uses {@code SECONDARY_INTER_CARDINAL} precision and
     * the same north-zero yaw convention returned by {@code getYaw}.
     */
    public static CompassRoseDirection getClosestFromYaw(double yaw) {
        return getClosestFromYaw(yaw, Precision.SECONDARY_INTER_CARDINAL);
    }

    /**
     * Returns the horizontal block displacement stored for this compass direction.
     */
    public int getModX() {
        return modX;
    }

    /**
     * Returns the depth-axis block displacement stored for this compass direction.
     */
    public int getModZ() {
        return modZ;
    }

    /**
     * Returns the stored cardinal face chosen for this direction. Diagonal ties
     * follow the constant definitions, such as {@code NORTH_EAST} choosing {@code
     * NORTH} and {@code SOUTH_EAST} choosing {@code EAST}.
     */
    public BlockFace getClosestBlockFace() {
        return closestBlockFace;
    }

    /**
     * Returns this direction's index in the south-zero rotation-property lookup
     * table.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the compass direction pointing directly opposite this direction
     * horizontally.
     */
    public CompassRoseDirection getOppositeFace() {
        return switch (this) {
            case NORTH -> CompassRoseDirection.SOUTH;
            case SOUTH -> CompassRoseDirection.NORTH;
            case EAST -> CompassRoseDirection.WEST;
            case WEST -> CompassRoseDirection.EAST;
            case NORTH_EAST -> CompassRoseDirection.SOUTH_WEST;
            case NORTH_WEST -> CompassRoseDirection.SOUTH_EAST;
            case SOUTH_EAST -> CompassRoseDirection.NORTH_WEST;
            case SOUTH_WEST -> CompassRoseDirection.NORTH_EAST;
            case WEST_NORTH_WEST -> CompassRoseDirection.EAST_SOUTH_EAST;
            case NORTH_NORTH_WEST -> CompassRoseDirection.SOUTH_SOUTH_EAST;
            case NORTH_NORTH_EAST -> CompassRoseDirection.SOUTH_SOUTH_WEST;
            case EAST_NORTH_EAST -> CompassRoseDirection.WEST_SOUTH_WEST;
            case EAST_SOUTH_EAST -> CompassRoseDirection.WEST_NORTH_WEST;
            case SOUTH_SOUTH_EAST -> CompassRoseDirection.NORTH_NORTH_WEST;
            case SOUTH_SOUTH_WEST -> CompassRoseDirection.NORTH_NORTH_EAST;
            case WEST_SOUTH_WEST -> CompassRoseDirection.EAST_NORTH_EAST;
        };
    }

    /**
     * Returns this direction's yaw in degrees with north at {@code 0}. {@code
     * getClosestFromYaw} uses the same convention; its default sixteen-direction
     * precision resolves this stored value to the original direction.
     */
    public float getYaw() {
        return yaw;
    }

    /**
     * {@code Precision} selects how many compass subdivisions participate in yaw
     * quantization.
     */
    @RequiredArgsConstructor
    public enum Precision {
        /**
         * Four principal compass directions.
         */
        CARDINAL(4),

        /**
         * Eight principal and diagonal directions.
         */
        PRIMARY_INTER_CARDINAL(8),

        /**
         * All sixteen compass directions.
         */
        SECONDARY_INTER_CARDINAL(16);
        /**
         * Available quantization direction count.
         */
        final int directions;
    }
}
