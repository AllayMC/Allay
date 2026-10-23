package org.allaymc.server.block.component.rail;

import org.allaymc.api.block.data.BlockFace;

import java.util.Arrays;
import java.util.List;

/**
 * {@code RailOrientation} maps rail direction metadata to two horizontal connection
 * faces and an optional ascent face. Declaration order defines the metadata encoding:
 * values {@code 0} through {@code 5} cover straight and ascending rails, and values
 * {@code 6} through {@code 9} describe normal-rail curves. Connection accessors expose
 * shared enum storage, which callers must leave unchanged.
 */
public enum RailOrientation {
    /**
     * Flat north-south rail connection.
     */
    STRAIGHT_NORTH_SOUTH(BlockFace.NORTH, BlockFace.SOUTH),
    /**
     * Flat east-west rail connection.
     */
    STRAIGHT_EAST_WEST(BlockFace.EAST, BlockFace.WEST),
    /**
     * East-west rail ascending east.
     */
    ASCENDING_EAST(BlockFace.EAST, BlockFace.WEST, BlockFace.EAST),
    /**
     * East-west rail ascending west.
     */
    ASCENDING_WEST(BlockFace.EAST, BlockFace.WEST, BlockFace.WEST),
    /**
     * North-south rail ascending north.
     */
    ASCENDING_NORTH(BlockFace.NORTH, BlockFace.SOUTH, BlockFace.NORTH),
    /**
     * North-south rail ascending south.
     */
    ASCENDING_SOUTH(BlockFace.NORTH, BlockFace.SOUTH, BlockFace.SOUTH),
    /**
     * Flat south-east rail curve.
     */
    CURVED_SOUTH_EAST(BlockFace.SOUTH, BlockFace.EAST),
    /**
     * Flat south-west rail curve.
     */
    CURVED_SOUTH_WEST(BlockFace.SOUTH, BlockFace.WEST),
    /**
     * Flat north-west rail curve.
     */
    CURVED_NORTH_WEST(BlockFace.NORTH, BlockFace.WEST),
    /**
     * Flat north-east rail curve.
     */
    CURVED_NORTH_EAST(BlockFace.NORTH, BlockFace.EAST);

    /**
     * {@code VALUES} caches declaration order for metadata lookup, so {@code byMeta}
     * resolves numeric rail directions without creating another enum array.
     */
    private static final RailOrientation[] VALUES = values();

    /**
     * {@code connectingDirections} holds both horizontal endpoints. Array and list
     * accessors expose this same storage, so consumers must not replace its elements.
     */
    private final BlockFace[] connectingDirections;
    /**
     * Ascent face, or {@code null} for a flat orientation.
     */
    private final BlockFace ascendingDirection;

    /**
     * Creates a flat orientation connecting the two supplied horizontal faces.
     */
    RailOrientation(BlockFace dir1, BlockFace dir2) {
        this(dir1, dir2, null);
    }

    /**
     * Creates an orientation with two endpoints and an optional ascending face. A
     * {@code null} {@code ascendingDirection} marks a flat rail, while the endpoint
     * array is retained for connection queries.
     */
    RailOrientation(BlockFace dir1, BlockFace dir2, BlockFace ascendingDirection) {
        this.connectingDirections = new BlockFace[]{dir1, dir2};
        this.ascendingDirection = ascendingDirection;
    }

    /**
     * Returns the orientation encoded by declaration-order metadata. Values outside the
     * enum range select {@code STRAIGHT_NORTH_SOUTH}, giving rail-state consumers a
     * defined orientation even when the numeric metadata is invalid.
     */
    public static RailOrientation byMeta(int meta) {
        // Unknown metadata cannot index the enum table; downstream connection
        // checks continue with the default north-south endpoints instead of failing lookup.
        if (meta < 0 || meta >= VALUES.length) {
            return STRAIGHT_NORTH_SOUTH;
        }
        return VALUES[meta];
    }

    /**
     * Returns a flat orientation along the supplied horizontal face's axis. Vertical
     * faces select the default {@code STRAIGHT_NORTH_SOUTH} orientation, while
     * {@code null} cannot be switched and causes {@code NullPointerException}.
     */
    public static RailOrientation straight(BlockFace face) {
        return switch (face) {
            case NORTH, SOUTH -> STRAIGHT_NORTH_SOUTH;
            case EAST, WEST -> STRAIGHT_EAST_WEST;
            // A vertical face supplies no horizontal axis; use the same default
            // orientation as invalid metadata so callers still receive usable endpoints.
            default -> STRAIGHT_NORTH_SOUTH;
        };
    }

    /**
     * Returns the rail orientation ascending toward the supplied horizontal face.
     * Vertical faces select the default {@code ASCENDING_EAST} orientation, while
     * {@code null} cannot be switched and causes {@code NullPointerException}.
     */
    public static RailOrientation ascending(BlockFace face) {
        return switch (face) {
            case EAST -> ASCENDING_EAST;
            case WEST -> ASCENDING_WEST;
            case NORTH -> ASCENDING_NORTH;
            case SOUTH -> ASCENDING_SOUTH;
            // Vertical faces cannot describe a rail ascent; this fallback supplies
            // east-west endpoints and a concrete ascending direction to subsequent checks.
            default -> ASCENDING_EAST;
        };
    }

    /**
     * Returns the flat curve connecting two horizontal perpendicular faces in either
     * order. Equal, opposite, vertical, or {@code null} faces have no matching curve
     * and return {@code null}.
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
     * Returns a straight orientation for opposite faces and a matching curve otherwise.
     * Unsupported curve pairs return {@code null}; opposite vertical faces use
     * the north-south default from {@code straight}, and {@code f1} must be non-{@code null}.
     */
    public static RailOrientation straightOrCurved(BlockFace f1, BlockFace f2) {
        if (f1.opposite() == f2) {
            return straight(f1);
        }
        return curved(f1, f2);
    }

    /**
     * Returns the declaration-order metadata value identifying this rail orientation.
     */
    public int getMeta() {
        return ordinal();
    }

    /**
     * Returns the shared array containing this orientation's two horizontal endpoints.
     * Callers must not mutate its elements because connection queries and other callers
     * observe the same enum-owned storage.
     */
    public BlockFace[] connectingDirections() {
        return connectingDirections;
    }

    /**
     * Returns the face toward which the rail rises, or {@code null} for flat rails. The
     * horizontal endpoints remain available separately for both flat and ascending
     * orientations.
     */
    public BlockFace ascendingDirection() {
        return ascendingDirection;
    }

    /**
     * Returns whether this metadata value belongs to the normal-rail curve range.
     */
    public boolean isCurved() {
        return ordinal() >= 6;
    }

    /**
     * Returns whether this orientation has a non-{@code null} ascent face.
     */
    public boolean isAscending() {
        return ascendingDirection != null;
    }

    /**
     * Returns a fixed-size list backed by this orientation's endpoint array. Callers
     * must not replace list elements because the list and {@code connectingDirections}
     * share storage with the enum instance.
     */
    public List<BlockFace> connectingDirectionsList() {
        return Arrays.asList(connectingDirections);
    }

    /**
     * Returns whether either declared horizontal endpoint matches the supplied face. A
     * {@code null} or vertical face returns {@code false}, since all declared rail
     * endpoints are non-{@code null} horizontal faces.
     */
    public boolean hasConnection(BlockFace face) {
        return connectingDirections[0] == face || connectingDirections[1] == face;
    }
}
