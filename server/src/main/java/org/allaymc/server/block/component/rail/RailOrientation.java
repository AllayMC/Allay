package org.allaymc.server.block.component.rail;

import org.allaymc.api.block.data.BlockFace;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the orientation/shape of a rail block, mapping rail_direction
 * property values (0-9) to their connecting directions.
 *
 * <p>Values 0-5 are used by all rail types (straight and ascending).
 * Values 6-9 are curve orientations, only available for normal rails.</p>
 */
public enum RailOrientation {
    STRAIGHT_NORTH_SOUTH(BlockFace.NORTH, BlockFace.SOUTH),
    STRAIGHT_EAST_WEST(BlockFace.EAST, BlockFace.WEST),
    ASCENDING_EAST(BlockFace.EAST, BlockFace.WEST, BlockFace.EAST),
    ASCENDING_WEST(BlockFace.EAST, BlockFace.WEST, BlockFace.WEST),
    ASCENDING_NORTH(BlockFace.NORTH, BlockFace.SOUTH, BlockFace.NORTH),
    ASCENDING_SOUTH(BlockFace.NORTH, BlockFace.SOUTH, BlockFace.SOUTH),
    CURVED_SOUTH_EAST(BlockFace.SOUTH, BlockFace.EAST),
    CURVED_SOUTH_WEST(BlockFace.SOUTH, BlockFace.WEST),
    CURVED_NORTH_WEST(BlockFace.NORTH, BlockFace.WEST),
    CURVED_NORTH_EAST(BlockFace.NORTH, BlockFace.EAST);

    private static final RailOrientation[] VALUES = values();

    private final BlockFace[] connectingDirections;
    private final BlockFace ascendingDirection;

    RailOrientation(BlockFace dir1, BlockFace dir2) {
        this(dir1, dir2, null);
    }

    RailOrientation(BlockFace dir1, BlockFace dir2, BlockFace ascendingDirection) {
        this.connectingDirections = new BlockFace[]{dir1, dir2};
        this.ascendingDirection = ascendingDirection;
    }

    /**
     * Gets the orientation for the given rail_direction metadata value.
     *
     * @param meta the rail_direction property value (0-9)
     * @return the corresponding orientation, or {@code STRAIGHT_NORTH_SOUTH} if out of range
     */
    public static RailOrientation byMeta(int meta) {
        if (meta < 0 || meta >= VALUES.length) {
            return STRAIGHT_NORTH_SOUTH;
        }
        return VALUES[meta];
    }

    /**
     * Returns a straight orientation for the given face's axis.
     * NORTH or SOUTH → STRAIGHT_NORTH_SOUTH, EAST or WEST → STRAIGHT_EAST_WEST.
     */
    public static RailOrientation straight(BlockFace face) {
        return switch (face) {
            case NORTH, SOUTH -> STRAIGHT_NORTH_SOUTH;
            case EAST, WEST -> STRAIGHT_EAST_WEST;
            default -> STRAIGHT_NORTH_SOUTH;
        };
    }

    /**
     * Returns an ascending orientation going up toward the given face.
     */
    public static RailOrientation ascending(BlockFace face) {
        return switch (face) {
            case EAST -> ASCENDING_EAST;
            case WEST -> ASCENDING_WEST;
            case NORTH -> ASCENDING_NORTH;
            case SOUTH -> ASCENDING_SOUTH;
            default -> ASCENDING_EAST;
        };
    }

    /**
     * Returns the curved orientation connecting the two given faces.
     *
     * @return the curved orientation, or {@code null} if no curve matches
     */
    public static RailOrientation curved(BlockFace f1, BlockFace f2) {
        for (RailOrientation o : new RailOrientation[]{CURVED_SOUTH_EAST, CURVED_SOUTH_WEST, CURVED_NORTH_WEST, CURVED_NORTH_EAST}) {
            if ((o.connectingDirections[0] == f1 && o.connectingDirections[1] == f2) ||
                (o.connectingDirections[0] == f2 && o.connectingDirections[1] == f1)) {
                return o;
            }
        }
        return null;
    }

    /**
     * Returns a straight orientation if the two faces are opposite, or a curved
     * orientation if they are perpendicular. For ascending, use {@link #ascending(BlockFace)}.
     *
     * @return the best orientation connecting the two faces, or {@code null} if no match
     */
    public static RailOrientation straightOrCurved(BlockFace f1, BlockFace f2) {
        if (f1.opposite() == f2) {
            return straight(f1);
        }
        return curved(f1, f2);
    }

    public int getMeta() {
        return ordinal();
    }

    public BlockFace[] connectingDirections() {
        return connectingDirections;
    }

    /**
     * Returns the direction in which this rail ascends, or {@code null} if flat.
     */
    public BlockFace ascendingDirection() {
        return ascendingDirection;
    }

    public boolean isCurved() {
        return ordinal() >= 6;
    }

    public boolean isAscending() {
        return ascendingDirection != null;
    }

    /**
     * Returns the list of connecting faces for this orientation.
     */
    public List<BlockFace> connectingDirectionsList() {
        return Arrays.asList(connectingDirections);
    }

    /**
     * Checks if this orientation connects to the given face.
     */
    public boolean hasConnection(BlockFace face) {
        return connectingDirections[0] == face || connectingDirections[1] == face;
    }
}
