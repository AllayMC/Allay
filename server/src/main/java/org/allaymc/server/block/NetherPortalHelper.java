package org.allaymc.server.block;

import lombok.Getter;
import org.allaymc.api.block.property.enums.PortalAxis;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.PortalCreateEvent;
import org.allaymc.api.eventbus.event.entity.EntityTeleportEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.api.world.poi.PoiTypes;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.PORTAL_AXIS;

/**
 * {@code NetherPortalHelper} represents a detected rectangular Nether portal
 * interior and supplies frame detection, filling, and interdimension travel. A
 * detected instance retains its dimension, axis, and interior geometry; later
 * calls read or write the world at those coordinates. Detection accepts air, fire,
 * and existing portal blocks inside an obsidian perimeter.
 *
 * <p>
 * Portal travel loads the destination search area before consulting its portal
 * index. {@code teleport} schedules new frame construction on the destination
 * dimension and then schedules the entity transfer through its own scheduler.
 * World writes suppress neighbor updates while a portal is incomplete. The
 * returned future is completed by scheduled callbacks, so callback cancellation or
 * failure can leave it pending; earlier world edits have no rollback.
 *
 * @author daoge_cmd
 */
public class NetherPortalHelper {

    // MIN_WIDTH, MAX_WIDTH, MIN_HEIGHT, and MAX_HEIGHT bound the accepted interior
    // dimensions. Detection validates both measured dimensions before accepting
    // the complete perimeter.
    /**
     * Minimum portal interior width in blocks.
     */
    private static final int MIN_WIDTH = 2;
    /**
     * Maximum portal interior width in blocks.
     */
    private static final int MAX_WIDTH = 21;
    /**
     * Minimum portal interior height in blocks.
     */
    private static final int MIN_HEIGHT = 3;
    /**
     * Maximum portal interior height in blocks.
     */
    private static final int MAX_HEIGHT = 21;

    // SEARCH_CHUNK_RADIUS_TO_NETHER and SEARCH_CHUNK_RADIUS_TO_OVERWORLD determine
    // which destination chunks teleport loads before searching the portal
    // point-of-interest index.
    /**
     * Destination Nether search radius in chunks.
     */
    private static final int SEARCH_CHUNK_RADIUS_TO_NETHER = 1;
    /**
     * Destination Overworld search radius in chunks.
     */
    private static final int SEARCH_CHUNK_RADIUS_TO_OVERWORLD = 8;

    /**
     * {@code PORTAL_CREATE_SEARCH_RADIUS} bounds the vertical candidate search in
     * blocks. {@code findSafeY} returns the clamped target height when no
     * candidate inside this distance has an empty interior.
     */
    private static final int PORTAL_CREATE_SEARCH_RADIUS = 16;

    // dimension and axis locate the portal plane. leftX, leftZ, and bottomY locate
    // its first interior block, while width and height bound position enumeration
    // and filling.
    /**
     * Dimension containing the detected portal frame.
     */
    @Getter
    private final Dimension dimension;
    /**
     * Horizontal axis of the portal interior.
     */
    @Getter
    private final PortalAxis axis;
    /**
     * Lowest portal interior block height.
     */
    private final int bottomY;
    /**
     * Horizontal coordinates of the first interior column.
     */
    private final int leftX, leftZ;
    /**
     * Portal interior width in blocks.
     */
    private final int width;
    /**
     * Portal interior height in blocks.
     */
    private final int height;

    /**
     * Creates a detected frame description from the supplied interior geometry.
     */
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
     * Returns a detected frame containing the supplied position, or {@code null}
     * when both orientations fail validation. The search tests
     * {@code PortalAxis.X} before {@code PortalAxis.Z} and leaves all world blocks
     * unchanged.
     */
    public static NetherPortalHelper detect(Dimension dimension, Vector3ic pos) {
        var result = detectAxis(dimension, pos, PortalAxis.X);
        if (result != null) return result;
        // An invalid X-oriented rectangle leaves the world unchanged, so try the
        // perpendicular orientation before declining this ignition position.
        return detectAxis(dimension, pos, PortalAxis.Z);
    }

    /**
     * Detects a portal interior for one axis and returns its measured geometry.
     * The scan first locates an obsidian floor, then measures an interior width
     * and height within the configured limits. {@code validateFrame} checks the
     * entire perimeter and interior after those measurements, because the first
     * row and column alone cannot establish a complete rectangular frame. Any
     * failed size or block check produces {@code null} without world changes.
     */
    private static NetherPortalHelper detectAxis(Dimension dimension, Vector3ic pos, PortalAxis axis) {
        int x = pos.x(), y = pos.y(), z = pos.z();

        while (y > dimension.getDimensionType().getMinHeight()) {
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

        int leftX = x, leftZ = z;
        // The extra scan position admits the outer obsidian edge.
        for (int i = 0; i < MAX_WIDTH + 1; i++) {
            int nx = axis == PortalAxis.X ? leftX - 1 : leftX;
            int nz = axis == PortalAxis.Z ? leftZ - 1 : leftZ;
            if (dimension.getBlockState(nx, bottomY, nz).getBlockType() == BlockTypes.OBSIDIAN) {
                break;
            }
            leftX = nx;
            leftZ = nz;

            if (i == MAX_WIDTH) return null;
        }

        int leftColX = axis == PortalAxis.X ? leftX - 1 : leftX;
        int leftColZ = axis == PortalAxis.Z ? leftZ - 1 : leftZ;
        if (dimension.getBlockState(leftColX, bottomY, leftColZ).getBlockType() != BlockTypes.OBSIDIAN) {
            return null;
        }

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

        int height = 0;
        // The extra vertical position admits the obsidian ceiling.
        for (int dy = 0; dy < MAX_HEIGHT + 1; dy++) {
            if (dimension.getBlockState(leftX, bottomY + dy, leftZ).getBlockType() == BlockTypes.OBSIDIAN) {
                break;
            }
            height++;
        }
        if (height < MIN_HEIGHT || height > MAX_HEIGHT) return null;

        if (!validateFrame(dimension, axis, leftX, leftZ, bottomY, width, height)) {
            return null;
        }

        return new NetherPortalHelper(dimension, axis, bottomY, leftX, leftZ, width, height);
    }

    /**
     * Returns whether the measured rectangle has obsidian edges and an allowed
     * interior. The perimeter checks exclude corner blocks, and every interior
     * cell must satisfy {@code isPortalInterior} before validation succeeds.
     */
    private static boolean validateFrame(Dimension dimension, PortalAxis axis, int leftX, int leftZ, int bottomY, int width, int height) {
        int dx = axis == PortalAxis.X ? 1 : 0;
        int dz = axis == PortalAxis.Z ? 1 : 0;

        for (int i = 0; i < width; i++) {
            if (dimension.getBlockState(leftX + dx * i, bottomY - 1, leftZ + dz * i).getBlockType() != BlockTypes.OBSIDIAN) {
                return false;
            }
        }

        for (int i = 0; i < width; i++) {
            if (dimension.getBlockState(leftX + dx * i, bottomY + height, leftZ + dz * i).getBlockType() != BlockTypes.OBSIDIAN) {
                return false;
            }
        }

        for (int j = 0; j < height; j++) {
            if (dimension.getBlockState(leftX - dx, bottomY + j, leftZ - dz).getBlockType() != BlockTypes.OBSIDIAN) {
                return false;
            }
        }

        for (int j = 0; j < height; j++) {
            if (dimension.getBlockState(leftX + dx * width, bottomY + j, leftZ + dz * width).getBlockType() != BlockTypes.OBSIDIAN) {
                return false;
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!isPortalInterior(dimension, leftX + dx * i, bottomY + j, leftZ + dz * i)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns whether the position contains air, fire, or a portal block.
     */
    private static boolean isPortalInterior(Dimension dimension, int x, int y, int z) {
        var type = dimension.getBlockState(x, y, z).getBlockType();
        return type == BlockTypes.AIR || type == BlockTypes.FIRE || type == BlockTypes.PORTAL;
    }

    /**
     * Fills the detected interior with portal blocks matching its retained axis.
     * The returned list contains newly allocated mutable coordinate vectors for
     * the attempted writes.
     *
     * <p>
     * Each write updates clients while suppressing neighbor updates, because an
     * incomplete interior could otherwise fail the portal block's support checks.
     * The method ignores individual write results and performs no rollback when a
     * write fails, so the returned positions describe the requested interior
     * rather than verified successful changes.
     */
    public List<Vector3ic> fill() {
        List<Vector3ic> positions = getInteriorPositions();
        var portalState = BlockTypes.PORTAL.getDefaultState().setPropertyValue(PORTAL_AXIS, axis);

        // Suppress neighbor updates until every interior write has been attempted,
        // so portal support checks cannot remove blocks merely because filling is
        // incomplete.
        for (var pos : positions) {

            dimension.setBlockState(pos, portalState, 0, true, false);
        }
        return positions;
    }

    /**
     * Returns newly allocated positions covering this detected portal interior.
     * The mutable list and its mutable coordinate vectors belong to the caller;
     * changing them leaves the retained geometry unchanged.
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
     * Builds a four-block-wide, five-block-tall portal around the supplied lowest
     * interior position. {@code axis} selects the portal plane, and the interior
     * always occupies two columns and three rows.
     *
     * <p>
     * Frame and interior writes suppress neighbor updates so partial construction
     * does not trigger the portal blocks' support checks. Netherrack strips
     * replace the floor on both perpendicular sides after frame construction.
     * Existing blocks can be overwritten, individual write results are ignored,
     * and an interrupted sequence leaves earlier changes in place.
     */
    public static void createPortal(Dimension dimension, Vector3ic pos, PortalAxis axis) {
        int x = pos.x(), y = pos.y(), z = pos.z();
        int dx = axis == PortalAxis.X ? 1 : 0;
        int dz = axis == PortalAxis.Z ? 1 : 0;

        int px = dz; // if axis=X, perpendicular is Z (px=0, pz=1); if axis=Z, perpendicular is X (px=1, pz=0)
        int pz = dx;
        var obsidian = BlockTypes.OBSIDIAN.getDefaultState();
        var netherrack = BlockTypes.NETHERRACK.getDefaultState();
        var portalState = BlockTypes.PORTAL.getDefaultState().setPropertyValue(PORTAL_AXIS, axis);

        // These bounds include the frame around the two-by-three interior. Every
        // write suppresses neighbor updates so incomplete portal cells survive
        // construction.
        for (int i = -1; i <= 2; i++) {
            for (int j = -1; j <= 3; j++) {
                int bx = x + dx * i;
                int by = y + j;
                int bz = z + dz * i;
                if (i == -1 || i == 2 || j == -1 || j == 3) {

                    dimension.setBlockState(bx, by, bz, obsidian, 0, true, false);
                } else {

                    dimension.setBlockState(bx, by, bz, portalState, 0, true, false);
                }
            }
        }

        for (int i = -1; i <= 2; i++) {
            int bx = x + dx * i;
            int bz = z + dz * i;
            int floorY = y - 1;
            dimension.setBlockState(bx + px, floorY, bz + pz, netherrack, 0, true, false);
            dimension.setBlockState(bx - px, floorY, bz - pz, netherrack, 0, true, false);
        }
    }

    /**
     * Finds or creates a destination portal and returns the scheduled teleport
     * result. Callers use a worker such as the server's virtual thread pool
     * because destination chunk loads are joined synchronously before this method
     * returns. Only Overworld-to-Nether and Nether-to-Overworld travel selects a
     * destination; unavailable destinations and cancelled creation requests
     * schedule a {@code false} result.
     *
     * <p>
     * The search scales horizontal coordinates, loads its chunk area, and prefers
     * an indexed portal. A missing portal triggers {@code PortalCreateEvent}
     * before the destination scheduler constructs the fixed frame; that callback
     * then queues the entity teleport through the entity scheduler. Event
     * position-list edits do not alter this fixed construction geometry.
     *
     * <p>
     * Chunk-load failures propagate from this call. Scheduled callback failures or
     * creator invalidation can leave the returned future incomplete, and future
     * cancellation does not cancel queued work. Completion occurs inside the
     * scheduled callback; a continuation attached after completion follows
     * {@code CompletableFuture} continuation rules rather than a fixed thread
     * guarantee.
     */
    public static CompletableFuture<Boolean> teleport(Entity entity) {
        var world = entity.getWorld();
        var currentDim = entity.getDimension();
        var currentDimType = currentDim.getDimensionType();

        Dimension targetDim;
        int searchChunkRadius;
        if (currentDimType == DimensionTypes.OVERWORLD) {
            targetDim = world.getNether();
            searchChunkRadius = SEARCH_CHUNK_RADIUS_TO_NETHER;
        } else if (currentDimType == DimensionTypes.NETHER) {
            targetDim = world.getOverWorld();
            searchChunkRadius = SEARCH_CHUNK_RADIUS_TO_OVERWORLD;
        } else {

            return failOnDimensionThread(entity);
        }

        if (targetDim == null) return failOnDimensionThread(entity);

        var loc = entity.getLocation();
        var targetDimType = targetDim.getDimensionType();
        double targetX = scaleCoordinate(loc.x(), currentDimType, targetDimType);
        double targetZ = scaleCoordinate(loc.z(), currentDimType, targetDimType);
        // Keep the initial search height inside destination dimension bounds.
        int targetY = Math.clamp((int) loc.y(), targetDimType.getMinHeight(), targetDimType.getMaxHeight());

        // Floor negative coordinates into their containing block instead of
        // truncating toward zero.
        int flooredX = (int) Math.floor(targetX);
        int flooredZ = (int) Math.floor(targetZ);

        // Wait for every destination search chunk before querying its portal
        // index; the caller's worker thread absorbs the load wait instead of
        // blocking a dimension tick.
        int cx = flooredX >> 4;
        int cz = flooredZ >> 4;
        for (int dx = -searchChunkRadius; dx <= searchChunkRadius; dx++) {
            for (int dz = -searchChunkRadius; dz <= searchChunkRadius; dz++) {
                targetDim.getChunkManager().getOrLoadChunk(cx + dx, cz + dz).join();
            }
        }

        var poiPos = targetDim.findNearestPoi(PoiTypes.NETHER_PORTAL, flooredX, targetY, flooredZ, searchChunkRadius);
        if (poiPos != null) {

            int bottomY = poiPos.y();
            int minY = targetDim.getDimensionType().getMinHeight();
            while (bottomY > minY && targetDim.getBlockState(poiPos.x(), bottomY - 1, poiPos.z()).getBlockType() == BlockTypes.PORTAL) {
                bottomY--;
            }
            var target = new Location3d(poiPos.x() + 0.5, bottomY, poiPos.z() + 0.5, targetDim);

            // Complete only from the entity scheduler callback so teleport
            // executes through the entity's scheduler after the destination has
            // been selected.
            var future = new CompletableFuture<Boolean>();
            entity.getScheduler().runLater(entity, () -> future.complete(entity.teleport(target, EntityTeleportEvent.Reason.NETHER_PORTAL)));
            return future;
        }

        int safeY = findSafeY(targetDim, flooredX, targetY, flooredZ);
        var createPos = new Vector3i(flooredX, safeY, flooredZ);

        List<Vector3ic> portalBlocks = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                portalBlocks.add(new Vector3i(flooredX + i, safeY + j, flooredZ));
            }
        }
        if (!new PortalCreateEvent(targetDim, portalBlocks, PortalCreateEvent.CreateReason.NETHER_PAIR).call()) {
            return failOnDimensionThread(entity);
        }


        var target = new Location3d(flooredX + 0.5, safeY, flooredZ + 0.5, targetDim);
        var future = new CompletableFuture<Boolean>();
        // Create the destination frame on its dimension scheduler before
        // scheduling the entity transfer; the teleport must observe the attempted
        // frame construction first.
        targetDim.getScheduler().runLater(entity, () -> {
            createPortal(targetDim, createPos, PortalAxis.X);
            entity.getScheduler().runLater(entity, () ->
                    future.complete(entity.teleport(target, EntityTeleportEvent.Reason.NETHER_PORTAL))
            );
        });
        return future;
    }

    /**
     * Queues completion of a new future with {@code false} through the entity
     * scheduler. The future remains pending until that callback runs, including
     * when creator invalidation prevents its execution.
     */
    private static CompletableFuture<Boolean> failOnDimensionThread(Entity entity) {
        var future = new CompletableFuture<Boolean>();
        // Queue failure through the same entity scheduler as successful transfers
        // so continuations already attached before completion observe this
        // scheduled completion path.
        entity.getScheduler().runLater(entity, () -> future.complete(false));
        return future;
    }

    /**
     * Scales a horizontal coordinate between the built-in Overworld and Nether.
     * Travel into the Nether divides by eight, the reverse multiplies by eight,
     * and other dimension pairs retain {@code coord}.
     */
    public static double scaleCoordinate(double coord, DimensionType from, DimensionType to) {
        if (from == DimensionTypes.OVERWORLD && to == DimensionTypes.NETHER) {
            // One Nether block spans eight Overworld horizontal blocks.
            return coord / 8.0;
        } else if (from == DimensionTypes.NETHER && to == DimensionTypes.OVERWORLD) {
            return coord * 8.0;
        }
        return coord;
    }

    /**
     * Returns a height with an empty portal interior near the clamped target.
     * Candidates alternate above and below that height; an unsuccessful bounded
     * search returns the same clamped target even when its interior is occupied.
     */
    private static int findSafeY(Dimension dimension, int x, int y, int z) {
        var dimensionType = dimension.getDimensionType();
        // Reserve vertical space for the entire five-block frame.
        int maxY = dimensionType.getMaxHeight() - 5;
        int minY = dimensionType.getMinHeight() + 1;

        y = Math.clamp(y, minY, maxY);

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

        // No tested height has an empty interior. Keep the clamped target as a
        // construction fallback, allowing createPortal to replace obstructing
        // blocks there.
        return Math.clamp(y, minY, maxY);
    }

    /**
     * Returns whether the two-column, three-row portal interior contains only air.
     * Floor, side, and top-frame positions impose no material constraint because
     * {@code createPortal} replaces those positions during construction.
     */
    private static boolean isSuitableForPortal(Dimension dimension, int x, int y, int z) {

        for (int dx = -1; dx <= 2; dx++) {
            for (int dy = -1; dy <= 3; dy++) {
                var type = dimension.getBlockState(x + dx, y + dy, z).getBlockType();
                if (dy == -1) {

                    continue;
                }
                if (dx == -1 || dx == 2 || dy == 3) {

                    continue;
                }

                if (type != BlockTypes.AIR) {
                    return false;
                }
            }
        }
        return true;
    }
}
