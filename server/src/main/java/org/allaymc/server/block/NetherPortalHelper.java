package org.allaymc.server.block;

import lombok.Getter;
import org.allaymc.api.block.property.enums.PortalAxis;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.PortalCreateEvent;
import org.allaymc.api.eventbus.event.entity.EntityTeleportEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.poi.PoiTypes;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.PORTAL_AXIS;

/**
 * Utility class for nether portal frame detection, portal block filling,
 * coordinate scaling, portal search, portal creation, and teleportation.
 *
 * @author daoge_cmd
 */
public class NetherPortalHelper {

    // Nether portal interior size constraints (inclusive)
    private static final int MIN_WIDTH = 2;
    private static final int MAX_WIDTH = 21;
    private static final int MIN_HEIGHT = 3;
    private static final int MAX_HEIGHT = 21;

    // Bedrock Edition portal search ranges (in chunks)
    private static final int SEARCH_CHUNK_RADIUS_TO_NETHER = 1;    // 3×3 chunks
    private static final int SEARCH_CHUNK_RADIUS_TO_OVERWORLD = 8;  // 17×17 chunks
    // Search radius (in blocks) for finding a safe Y to create a new portal
    private static final int PORTAL_CREATE_SEARCH_RADIUS = 16;

    @Getter
    private final Dimension dimension;
    @Getter
    private final PortalAxis axis;
    private final int bottomY;
    private final int leftX, leftZ;  // leftmost column position
    private final int width;
    private final int height;

    private NetherPortalHelper(Dimension dimension, PortalAxis axis, int bottomY, int leftX, int leftZ, int width, int height) {
        this.dimension = dimension;
        this.axis = axis;
        this.bottomY = bottomY;
        this.leftX = leftX;
        this.leftZ = leftZ;
        this.width = width;
        this.height = height;
    }

    /**
     * Try to detect a valid nether portal frame at the given position.
     * Tests both X and Z axis orientations.
     *
     * @return a NetherPortalHelper instance if valid, null otherwise
     */
    public static NetherPortalHelper detect(Dimension dimension, Vector3ic pos) {
        var result = detectAxis(dimension, pos, PortalAxis.X);
        if (result != null) return result;
        return detectAxis(dimension, pos, PortalAxis.Z);
    }

    private static NetherPortalHelper detectAxis(Dimension dimension, Vector3ic pos, PortalAxis axis) {
        int x = pos.x(), y = pos.y(), z = pos.z();

        // Scan down to find the bottom (obsidian floor)
        while (y > dimension.getDimensionInfo().minHeight()) {
            var below = dimension.getBlockState(x, y - 1, z);
            if (below.getBlockType() == BlockTypes.OBSIDIAN) {
                break;
            }
            if (!isPortalInterior(dimension, x, y - 1, z)) {
                return null;
            }
            y--;
        }
        int bottomY = y;

        // Scan left to find the left column
        int leftX = x, leftZ = z;
        for (int i = 0; i < MAX_WIDTH + 1; i++) {
            int nx = axis == PortalAxis.X ? leftX - 1 : leftX;
            int nz = axis == PortalAxis.Z ? leftZ - 1 : leftZ;
            if (dimension.getBlockState(nx, bottomY, nz).getBlockType() == BlockTypes.OBSIDIAN) {
                break;
            }
            leftX = nx;
            leftZ = nz;
            // If we went too far without finding obsidian, it's invalid
            if (i == MAX_WIDTH) return null;
        }
        // Verify left column is actually obsidian
        int leftColX = axis == PortalAxis.X ? leftX - 1 : leftX;
        int leftColZ = axis == PortalAxis.Z ? leftZ - 1 : leftZ;
        if (dimension.getBlockState(leftColX, bottomY, leftColZ).getBlockType() != BlockTypes.OBSIDIAN) {
            return null;
        }

        // Measure width (interior) by scanning right
        int width = 0;
        int cx = leftX, cz = leftZ;
        for (int i = 0; i < MAX_WIDTH + 1; i++) {
            if (dimension.getBlockState(cx, bottomY, cz).getBlockType() == BlockTypes.OBSIDIAN) {
                break;
            }
            width++;
            cx += axis == PortalAxis.X ? 1 : 0;
            cz += axis == PortalAxis.Z ? 1 : 0;
        }
        if (width < MIN_WIDTH || width > MAX_WIDTH) return null;

        // Measure height (interior) by scanning up from bottom
        int height = 0;
        for (int dy = 0; dy < MAX_HEIGHT + 1; dy++) {
            if (dimension.getBlockState(leftX, bottomY + dy, leftZ).getBlockType() == BlockTypes.OBSIDIAN) {
                break;
            }
            height++;
        }
        if (height < MIN_HEIGHT || height > MAX_HEIGHT) return null;

        // Validate the full frame
        if (!validateFrame(dimension, axis, leftX, leftZ, bottomY, width, height)) {
            return null;
        }

        return new NetherPortalHelper(dimension, axis, bottomY, leftX, leftZ, width, height);
    }

    private static boolean validateFrame(Dimension dimension, PortalAxis axis, int leftX, int leftZ, int bottomY, int width, int height) {
        int dx = axis == PortalAxis.X ? 1 : 0;
        int dz = axis == PortalAxis.Z ? 1 : 0;

        // Check bottom row (obsidian)
        for (int i = 0; i < width; i++) {
            if (dimension.getBlockState(leftX + dx * i, bottomY - 1, leftZ + dz * i).getBlockType() != BlockTypes.OBSIDIAN) {
                return false;
            }
        }

        // Check top row (obsidian)
        for (int i = 0; i < width; i++) {
            if (dimension.getBlockState(leftX + dx * i, bottomY + height, leftZ + dz * i).getBlockType() != BlockTypes.OBSIDIAN) {
                return false;
            }
        }

        // Check left column (obsidian)
        for (int j = 0; j < height; j++) {
            if (dimension.getBlockState(leftX - dx, bottomY + j, leftZ - dz).getBlockType() != BlockTypes.OBSIDIAN) {
                return false;
            }
        }

        // Check right column (obsidian)
        for (int j = 0; j < height; j++) {
            if (dimension.getBlockState(leftX + dx * width, bottomY + j, leftZ + dz * width).getBlockType() != BlockTypes.OBSIDIAN) {
                return false;
            }
        }

        // Check interior (must be air, fire, or portal)
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!isPortalInterior(dimension, leftX + dx * i, bottomY + j, leftZ + dz * i)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isPortalInterior(Dimension dimension, int x, int y, int z) {
        var type = dimension.getBlockState(x, y, z).getBlockType();
        return type == BlockTypes.AIR || type == BlockTypes.FIRE || type == BlockTypes.PORTAL;
    }

    /**
     * Fill the interior with portal blocks.
     * <p>
     * Uses {@code update=false} to suppress neighbor updates during filling,
     * preventing partially-filled portal blocks from breaking themselves.
     *
     * @return list of portal block positions
     */
    public List<Vector3ic> fill() {
        List<Vector3ic> positions = getInteriorPositions();
        var portalState = BlockTypes.PORTAL.getDefaultState().setPropertyValue(PORTAL_AXIS, axis);

        for (var pos : positions) {
            // send=true (update clients), update=false (no neighbor updates during fill)
            dimension.setBlockState(pos, portalState, 0, true, false);
        }
        return positions;
    }

    /**
     * Compute the interior positions without placing any blocks.
     *
     * @return list of interior positions
     */
    public List<Vector3ic> getInteriorPositions() {
        List<Vector3ic> positions = new ArrayList<>();
        int dx = axis == PortalAxis.X ? 1 : 0;
        int dz = axis == PortalAxis.Z ? 1 : 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int px = leftX + dx * i;
                int py = bottomY + j;
                int pz = leftZ + dz * i;
                positions.add(new Vector3i(px, py, pz));
            }
        }
        return positions;
    }

    /**
     * Build a standard 4-wide 5-tall obsidian portal frame and fill it with portal blocks.
     * Places netherrack platforms on both perpendicular sides at the floor level
     * so the portal is not left floating without a walkable surface.
     * <p>
     * Uses {@code update=false} to suppress neighbor updates during construction,
     * preventing partially-filled portal blocks from breaking themselves.
     */
    public static void createPortal(Dimension dimension, Vector3ic pos, PortalAxis axis) {
        int x = pos.x(), y = pos.y(), z = pos.z();
        int dx = axis == PortalAxis.X ? 1 : 0;
        int dz = axis == PortalAxis.Z ? 1 : 0;
        // Perpendicular direction
        int px = dz; // if axis=X, perpendicular is Z (px=0, pz=1); if axis=Z, perpendicular is X (px=1, pz=0)
        int pz = dx;
        var obsidian = BlockTypes.OBSIDIAN.getDefaultState();
        var netherrack = BlockTypes.NETHERRACK.getDefaultState();
        var portalState = BlockTypes.PORTAL.getDefaultState().setPropertyValue(PORTAL_AXIS, axis);

        // Build frame: 4 wide × 5 tall (interior is 2×3)
        for (int i = -1; i <= 2; i++) {
            for (int j = -1; j <= 3; j++) {
                int bx = x + dx * i;
                int by = y + j;
                int bz = z + dz * i;
                if (i == -1 || i == 2 || j == -1 || j == 3) {
                    // Frame
                    dimension.setBlockState(bx, by, bz, obsidian, 0, true, false);
                } else {
                    // Interior — no neighbor updates to avoid cascade
                    dimension.setBlockState(bx, by, bz, portalState, 0, true, false);
                }
            }
        }

        // Place netherrack platforms on both sides at floor level
        for (int i = -1; i <= 2; i++) {
            int bx = x + dx * i;
            int bz = z + dz * i;
            int floorY = y - 1;
            dimension.setBlockState(bx + px, floorY, bz + pz, netherrack, 0, true, false);
            dimension.setBlockState(bx - px, floorY, bz - pz, netherrack, 0, true, false);
        }
    }

    /**
     * Teleport an entity through a nether portal.
     * Handles overworld/nether coordinate scaling and portal search/creation.
     * <p>
     * This method is expected to be called from a virtual thread. The actual teleport
     * is scheduled on the entity's dimension thread to ensure thread safety.
     *
     * @return a future that completes with {@code true} if the teleport succeeded, {@code false} otherwise
     */
    public static CompletableFuture<Boolean> teleport(Entity entity) {
        var world = entity.getWorld();
        var currentDim = entity.getDimension();
        var currentDimInfo = currentDim.getDimensionInfo();

        // Determine target dimension and search radius
        Dimension targetDim;
        int searchChunkRadius;
        if (currentDimInfo == DimensionInfo.OVERWORLD) {
            targetDim = world.getNether();
            searchChunkRadius = SEARCH_CHUNK_RADIUS_TO_NETHER;
        } else if (currentDimInfo == DimensionInfo.NETHER) {
            targetDim = world.getOverWorld();
            searchChunkRadius = SEARCH_CHUNK_RADIUS_TO_OVERWORLD;
        } else {
            // Cannot use nether portal from The End
            return failOnDimensionThread(entity);
        }

        if (targetDim == null) return failOnDimensionThread(entity);

        var loc = entity.getLocation();
        double targetX = scaleCoordinate(loc.x(), currentDimInfo, targetDim.getDimensionInfo());
        double targetZ = scaleCoordinate(loc.z(), currentDimInfo, targetDim.getDimensionInfo());
        int targetY = Math.clamp((int) loc.y(), targetDim.getDimensionInfo().minHeight(), targetDim.getDimensionInfo().maxHeight());

        // Use Math.floor to correctly handle negative coordinates (truncation rounds toward zero)
        int flooredX = (int) Math.floor(targetX);
        int flooredZ = (int) Math.floor(targetZ);

        // Load all chunks within the search radius (already on virtual thread, .join() is fine)
        int cx = flooredX >> 4;
        int cz = flooredZ >> 4;
        for (int dx = -searchChunkRadius; dx <= searchChunkRadius; dx++) {
            for (int dz = -searchChunkRadius; dz <= searchChunkRadius; dz++) {
                targetDim.getChunkManager().getOrLoadChunk(cx + dx, cz + dz).join();
            }
        }

        // Search for existing portal using POI index
        var poiPos = targetDim.findNearestPoi(PoiTypes.NETHER_PORTAL, flooredX, targetY, flooredZ, searchChunkRadius);
        if (poiPos != null) {
            // Find the bottom of the portal column
            int bottomY = poiPos.y();
            int minY = targetDim.getDimensionInfo().minHeight();
            while (bottomY > minY && targetDim.getBlockState(poiPos.x(), bottomY - 1, poiPos.z()).getBlockType() == BlockTypes.PORTAL) {
                bottomY--;
            }
            var target = new Location3d(poiPos.x() + 0.5, bottomY, poiPos.z() + 0.5, targetDim);
            // Schedule teleport on the entity's current dimension thread
            var future = new CompletableFuture<Boolean>();
            entity.getScheduler().runLater(entity, () -> future.complete(entity.teleport(target, EntityTeleportEvent.Reason.NETHER_PORTAL)));
            return future;
        }

        // No portal found — create one
        int safeY = findSafeY(targetDim, flooredX, targetY, flooredZ);
        var createPos = new Vector3i(flooredX, safeY, flooredZ);

        // Compute portal interior positions and fire event before mutating the world
        List<Vector3ic> portalBlocks = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                portalBlocks.add(new Vector3i(flooredX + i, safeY + j, flooredZ));
            }
        }
        if (!new PortalCreateEvent(targetDim, portalBlocks, PortalCreateEvent.CreateReason.NETHER_PAIR).call()) {
            return failOnDimensionThread(entity);
        }

        // Schedule portal creation on the target dimension thread (BlockPlaceEvent contract),
        // then teleport on the entity's current dimension thread (EntityTeleportEvent contract)
        var target = new Location3d(flooredX + 0.5, safeY, flooredZ + 0.5, targetDim);
        var future = new CompletableFuture<Boolean>();
        targetDim.getScheduler().runLater(entity, () -> {
            createPortal(targetDim, createPos, PortalAxis.X);
            entity.getScheduler().runLater(entity, () ->
                    future.complete(entity.teleport(target, EntityTeleportEvent.Reason.NETHER_PORTAL))
            );
        });
        return future;
    }

    /**
     * Return a future that completes with {@code false} on the entity's dimension thread,
     * ensuring that {@code thenAccept} callbacks run on the correct thread.
     */
    private static CompletableFuture<Boolean> failOnDimensionThread(Entity entity) {
        var future = new CompletableFuture<Boolean>();
        entity.getScheduler().runLater(entity, () -> future.complete(false));
        return future;
    }

    /**
     * Scale a coordinate between dimensions (overworld ×8 = nether, nether ÷8 = overworld).
     */
    public static double scaleCoordinate(double coord, DimensionInfo from, DimensionInfo to) {
        if (from == DimensionInfo.OVERWORLD && to == DimensionInfo.NETHER) {
            return coord / 8.0;
        } else if (from == DimensionInfo.NETHER && to == DimensionInfo.OVERWORLD) {
            return coord * 8.0;
        }
        return coord;
    }

    /**
     * Find a safe Y coordinate to build a portal at.
     */
    private static int findSafeY(Dimension dimension, int x, int y, int z) {
        var dimInfo = dimension.getDimensionInfo();
        int maxY = dimInfo.maxHeight() - 5;  // Need room for the 5-tall frame
        int minY = dimInfo.minHeight() + 1;

        // Try the target Y first
        y = Math.clamp(y, minY, maxY);

        // Search within a limited range for Bedrock behavior
        for (int offset = 0; offset <= PORTAL_CREATE_SEARCH_RADIUS; offset++) {
            int checkY = y + offset;
            if (checkY <= maxY && isSuitableForPortal(dimension, x, checkY, z)) {
                return checkY;
            }
            checkY = y - offset;
            if (checkY >= minY && isSuitableForPortal(dimension, x, checkY, z)) {
                return checkY;
            }
        }

        // Fallback: just use the original Y
        return Math.clamp(y, minY, maxY);
    }

    private static boolean isSuitableForPortal(Dimension dimension, int x, int y, int z) {
        // Check that a 4×5 area is clear (or close to it)
        for (int dx = -1; dx <= 2; dx++) {
            for (int dy = -1; dy <= 3; dy++) {
                var type = dimension.getBlockState(x + dx, y + dy, z).getBlockType();
                if (dy == -1) {
                    // Bottom row: needs solid ground or we can place obsidian
                    continue;
                }
                if (dx == -1 || dx == 2 || dy == 3) {
                    // Frame edges: we'll replace these anyway
                    continue;
                }
                // Interior should be air
                if (type != BlockTypes.AIR) {
                    return false;
                }
            }
        }
        return true;
    }
}
