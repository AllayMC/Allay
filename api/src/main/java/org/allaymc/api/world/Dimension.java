package org.allaymc.api.world;

import org.allaymc.api.block.action.BlockAction;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityXpOrb;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.world.LightningStrikeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.player.Player;
import org.allaymc.api.primitiveshape.PrimitiveShape;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.api.utils.function.QuadConsumer;
import org.allaymc.api.utils.function.TriFunction;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.light.LightEngine;
import org.allaymc.api.world.manager.BlockUpdateManager;
import org.allaymc.api.world.manager.ChunkManager;
import org.allaymc.api.world.manager.EntityManager;
import org.allaymc.api.world.particle.Particle;
import org.allaymc.api.world.poi.PoiType;
import org.allaymc.api.world.sound.Sound;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.Unmodifiable;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.joml.primitives.AABBdc;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.allaymc.api.block.component.BlockLiquidBaseComponent.isSource;
import static org.allaymc.api.block.type.BlockTypes.AIR;

/**
 * {@code Dimension} exposes the blocks, entities, managers, and spatial
 * operations belonging to one world dimension. Dimension coordinates are
 * distinct from chunk-local and section-local indices, and default helpers
 * translate between them while retaining the caller's requested origin.
 * Ordinary lookups use loaded chunks and return their documented absence
 * values; only explicit loading helpers wait for chunk availability. Callers
 * coordinate simulation mutations with dimension execution and use managers
 * for their respective lifecycle work.
 *
 * <p>
 * Bulk operations split regions at chunk and section boundaries, preserving
 * relative array positions for cells even when a chunk is missing or a height
 * lies outside the dimension. Collision queries extend the lower search
 * boundary to find shapes taller than their owning block, so consumers must
 * restore that shifted origin when interpreting array indices. Direct section
 * writes intentionally bypass the event-aware placement path, while liquid
 * helpers select between the foreground and extra layer according to the
 * existing block's containment rules.
 *
 * @author daoge_cmd | Cool_Loong
 */
public interface Dimension extends TaskCreator {

    /**
     * {@code PAIR_LIQUID_NOT_FOUND} represents no selected liquid: its layer
     * is {@code -1} and its block state is {@code null}.
     */
    Pair<Integer, BlockState> PAIR_LIQUID_NOT_FOUND = new Pair<>(-1, null);

    /**
     * Returns the manager responsible for this dimension's chunk availability.
     */
    ChunkManager getChunkManager();

    /**
     * Returns the manager responsible for scheduled and neighboring block
     * updates.
     */
    BlockUpdateManager getBlockUpdateManager();

    /**
     * Returns the manager responsible for this dimension's entity lifecycle.
     */
    EntityManager getEntityManager();

    /**
     * Registers a shape with this dimension and presents it to current
     * players. Later viewer updates use the retained shape instance; callers
     * coordinate registration changes with dimension presentation work.
     */
    void addPrimitiveShape(PrimitiveShape primitiveShape);

    /**
     * Unregisters a shape from this dimension and its current player viewers.
     * The operation affects the retained shape instance, and later viewer
     * callbacks can fail after membership changes have occurred.
     */
    void removePrimitiveShape(PrimitiveShape primitiveShape);

    /**
     * Returns an unmodifiable view backed by this dimension's shape
     * membership. Shape instances remain shared, and later registrations or
     * removals are visible through the same returned collection view.
     */
    @UnmodifiableView
    Set<PrimitiveShape> getPrimitiveShapes();

    /**
     * Removes current player viewers from all retained dimension shapes before
     * clearing membership. Earlier viewer removals remain applied if a later
     * presentation failure interrupts the operation before it completes.
     */
    void removeAllPrimitiveShapes();

    /**
     * Returns the engine supplying illumination queries for this dimension.
     */
    LightEngine getLightEngine();

    /**
     * Returns the registered identity and vertical bounds of this dimension.
     */
    DimensionType getDimensionType();

    /**
     * Returns the world that owns this dimension's shared metadata.
     */
    World getWorld();

    /**
     * Returns the scheduler advanced during this dimension's tick execution.
     */
    Scheduler getScheduler();

    /**
     * Returns the generator attached to this dimension's chunk pipeline.
     */
    WorldGenerator getWorldGenerator();

    /**
     * Returns the entity manager's unmodifiable live view keyed by runtime ID.
     * Entity references remain shared, and callers coordinate iteration with
     * membership changes rather than assuming a stable snapshot.
     */
    @UnmodifiableView
    default Map<Long, Entity> getEntities() {
        return getEntityManager().getEntities();
    }

    /**
     * Returns the current size of this dimension's entity membership map.
     */
    default int getEntityCount() {
        return getEntities().size();
    }

    /**
     * Returns an unmodifiable view backed by this dimension's player
     * membership. Player references remain shared, and later joins or
     * departures can become visible through the same returned set.
     */
    @UnmodifiableView
    Set<Player> getPlayers();

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses layer {@code 0}, client updates enabled, neighbor updates
     * enabled, block behavior enabled, no player interaction context. Floating
     * coordinates are floored toward negative infinity before chunk access.
     * The delegated result reports acceptance or failure of the placement
     * path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3dc pos, BlockState blockState) {
        return setBlockState(pos, blockState, 0);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses client updates enabled, neighbor updates enabled, block
     * behavior enabled, no player interaction context. Floating coordinates
     * are floored toward negative infinity before chunk access. The delegated
     * result reports acceptance or failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3dc pos, BlockState blockState, int layer) {
        // Floor negative fractions into the containing block without changing the caller's vector.
        pos = pos.floor(new Vector3d());
        return setBlockState((int) pos.x(), (int) pos.y(), (int) pos.z(), blockState, layer);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses layer {@code 0}, client updates enabled, neighbor updates
     * enabled, block behavior enabled, no player interaction context. Floating
     * coordinates are floored toward negative infinity before chunk access.
     * The delegated result reports acceptance or failure of the placement
     * path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(double x, double y, double z, BlockState blockState) {
        return setBlockState(x, y, z, blockState, 0);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses client updates enabled, neighbor updates enabled, block
     * behavior enabled, no player interaction context. Floating coordinates
     * are floored toward negative infinity before chunk access. The delegated
     * result reports acceptance or failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(double x, double y, double z, BlockState blockState, int layer) {
        // Floor each coordinate so negative fractions select their containing block.
        return setBlockState((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z), blockState, layer);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses layer {@code 0}, client updates enabled, neighbor updates
     * enabled, block behavior enabled, no player interaction context. The
     * delegated result reports acceptance or failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState) {
        return setBlockState(x, y, z, blockState, 0, true, true);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses layer {@code 0}, client updates enabled, neighbor updates
     * enabled, block behavior enabled, no player interaction context. The
     * delegated result reports acceptance or failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3ic pos, BlockState blockState) {
        return setBlockState(pos, blockState, 0, true, true);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses client updates enabled, neighbor updates enabled, block
     * behavior enabled, no player interaction context. The delegated result
     * reports acceptance or failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        return setBlockState(x, y, z, blockState, layer, true, true);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses client updates enabled, neighbor updates enabled, block
     * behavior enabled, no player interaction context. The delegated result
     * reports acceptance or failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3ic pos, BlockState blockState, int layer) {
        return setBlockState(pos, blockState, layer, true, true);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses neighbor updates enabled, block behavior enabled, no
     * player interaction context. The delegated result reports acceptance or
     * failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send) {
        return setBlockState(x, y, z, blockState, layer, send, true);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses neighbor updates enabled, block behavior enabled, no
     * player interaction context. The delegated result reports acceptance or
     * failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3ic pos, BlockState blockState, int layer, boolean send) {
        return setBlockState(pos, blockState, layer, send, true);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses block behavior enabled, no player interaction context. The
     * delegated result reports acceptance or failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3ic pos, BlockState blockState, int layer, boolean send, boolean update) {
        return setBlockState(pos.x(), pos.y(), pos.z(), blockState, layer, send, update);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses block behavior enabled, no player interaction context. The
     * delegated result reports acceptance or failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send, boolean update) {
        return setBlockState(x, y, z, blockState, layer, send, update, true, null);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses no player interaction context. The delegated result
     * reports acceptance or failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3ic pos, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior) {
        return setBlockState(pos.x(), pos.y(), pos.z(), blockState, layer, send, update, callBlockBehavior);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses no player interaction context. The delegated result
     * reports acceptance or failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior) {
        return setBlockState(x, y, z, blockState, layer, send, update, callBlockBehavior, null);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses layer {@code 0}, client updates enabled, neighbor updates
     * enabled, block behavior enabled. The delegated result reports acceptance
     * or failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState, PlayerInteractInfo placementInfo) {
        return setBlockState(x, y, z, blockState, 0, true, true, true, placementInfo);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload uses client updates enabled, neighbor updates enabled, block
     * behavior enabled. The delegated result reports acceptance or failure of
     * the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState, int layer, PlayerInteractInfo placementInfo) {
        return setBlockState(x, y, z, blockState, layer, true, true, true, placementInfo);
    }

    /**
     * Requests a block replacement at the supplied dimension coordinates. This
     * overload unpacks the supplied vector and forwards every behavior flag
     * and interaction context to the event-aware placement operation. The
     * delegated result reports acceptance or failure of the placement path.
     *
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean,
     *     boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3ic pos, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior, PlayerInteractInfo placementInfo) {
        return setBlockState(pos.x(), pos.y(), pos.z(), blockState, layer, send, update, callBlockBehavior, placementInfo);
    }

    /**
     * Replaces a block in a loaded chunk after accepted placement-event
     * dispatch. Missing chunks and cancellation return {@code false}; callers
     * supply a valid layer and height. {@code send} controls client update
     * recording, {@code update} controls neighboring notifications, and {@code
     * callBlockBehavior} selects placement and replacement hooks.
     *
     * <p>
     * The event-aware implementation removes old block entity state before
     * creating the replacement when behavior is enabled, then writes the
     * requested state and updates main-layer point-of-interest membership. A
     * later hook failure does not roll back preceding changes. {@code
     * placementInfo} may be {@code null} when no player interaction caused the
     * request.
     */
    boolean setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior, PlayerInteractInfo placementInfo);

    /**
     * Returns the block state at the supplied dimension position. This
     * overload selects the main layer {@code 0}. Floating coordinates are
     * floored toward negative infinity before lookup. The delegated lookup
     * uses air for unavailable chunks or heights outside the dimension.
     *
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(Vector3dc pos) {
        return getBlockState(pos, 0);
    }

    /**
     * Returns the block state at the supplied dimension position. Floating
     * coordinates are floored toward negative infinity before lookup. The
     * delegated lookup uses air for unavailable chunks or heights outside the
     * dimension.
     *
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(Vector3dc pos, int layer) {
        // Floor negative fractions into the containing block without changing the caller's vector.
        pos = pos.floor(new Vector3d());
        return getBlockState((int) pos.x(), (int) pos.y(), (int) pos.z(), layer);
    }

    /**
     * Returns the block state at the supplied dimension position. This
     * overload selects the main layer {@code 0}. Floating coordinates are
     * floored toward negative infinity before lookup. The delegated lookup
     * uses air for unavailable chunks or heights outside the dimension.
     *
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(double x, double y, double z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * Returns the block state at the supplied dimension position. Floating
     * coordinates are floored toward negative infinity before lookup. The
     * delegated lookup uses air for unavailable chunks or heights outside the
     * dimension.
     *
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(double x, double y, double z, int layer) {
        // Floor each coordinate so negative fractions select their containing block.
        return getBlockState((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z), layer);
    }

    /**
     * Returns the block state at the supplied dimension position. This
     * overload selects the main layer {@code 0}. The delegated lookup uses air
     * for unavailable chunks or heights outside the dimension.
     *
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(Vector3ic pos) {
        return getBlockState(pos, 0);
    }

    /**
     * Returns the selected-layer state at the supplied dimension block
     * position. The delegated lookup returns air for unavailable chunks or
     * heights outside the dimension and does not request loading.
     */
    default BlockState getBlockState(Vector3ic pos, int layer) {
        return getBlockState(pos.x(), pos.y(), pos.z(), layer);
    }

    /**
     * Returns the block state at the supplied dimension position. This
     * overload selects the main layer {@code 0}. The delegated lookup uses air
     * for unavailable chunks or heights outside the dimension.
     *
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(int x, int y, int z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * Returns a loaded block state at dimension coordinates in the selected
     * layer. An out-of-range height or missing chunk returns {@code
     * AIR.getDefaultState()} without loading data; callers supply a valid
     * layer.
     */
    default BlockState getBlockState(int x, int y, int z, int layer) {
        // Queries outside available block storage use air without requesting a load,
        // allowing neighboring checks to continue with a concrete state while
        // leaving absent or out-of-range cells unmaterialized.
        if (y < this.getDimensionType().getMinHeight() || y > getDimensionType().getMaxHeight()) {
            return AIR.getDefaultState();
        }

        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return AIR.getDefaultState();
        }

        return chunk.getBlockState(x & 15, y, z & 15, layer);
    }

    /**
     * Visits loaded block cells covered by the floored minimum and ceiled
     * maximum of a box. The consumer receives dimension coordinates; unloaded
     * or out-of-range cells are omitted through the region traversal.
     *
     * @see #forEachBlockStates(int, int, int, int, int, int, int,
     *     QuadConsumer)
     */
    default void forEachBlockStates(AABBdc aabb, int layer, QuadConsumer<Integer, Integer, Integer, BlockState> blockStateConsumer) {
        // Outward rounding covers every block cell touched by fractional box bounds.
        var maxX = (int) Math.ceil(aabb.maxX());
        var maxY = (int) Math.ceil(aabb.maxY());
        var maxZ = (int) Math.ceil(aabb.maxZ());
        var minX = (int) Math.floor(aabb.minX());
        var minY = (int) Math.floor(aabb.minY());
        var minZ = (int) Math.floor(aabb.minZ());
        forEachBlockStates(minX, minY, minZ, maxX - minX, maxY - minY, maxZ - minZ, layer, blockStateConsumer);
    }

    /**
     * Visits non-{@code null} cells from a region read and supplies their
     * dimension coordinates to the consumer. Invalid extents cause no
     * callbacks, and missing chunks or out-of-range heights remain omitted.
     * Section read operations finish before consumer invocation.
     */
    default void forEachBlockStates(
            int x, int y, int z,
            @Range(from = 1, to = Integer.MAX_VALUE) int sizeX,
            @Range(from = 1, to = Integer.MAX_VALUE) int sizeY,
            @Range(from = 1, to = Integer.MAX_VALUE) int sizeZ,
            int layer, QuadConsumer<Integer, Integer, Integer, BlockState> blockStateConsumer) {
        var blockStates = getBlockStates(x, y, z, sizeX, sizeY, sizeZ, layer);
        if (blockStates == null) {
            return;
        }

        for (int offsetX = 0, length0 = blockStates.length; offsetX < length0; offsetX++) {
            var sub1 = blockStates[offsetX];
            for (int offsetY = 0, length1 = sub1.length; offsetY < length1; offsetY++) {
                var sub2 = sub1[offsetY];
                for (int offsetZ = 0, length2 = sub2.length; offsetZ < length2; offsetZ++) {
                    var blockState = sub2[offsetZ];
                    if (blockState == null) {
                        continue;
                    }

                    blockStateConsumer.accept(x + offsetX, y + offsetY, z + offsetZ, blockState);
                }
            }
        }
    }

    /**
     * Returns a new region array indexed by offsets from the supplied origin.
     * Any nonpositive extent returns {@code null}; missing chunks and heights
     * outside the dimension leave {@code null} cells rather than shifting
     * later data or substituting air. Callers choose extents whose allocation
     * and coordinate sums are valid.
     *
     * <p>
     * The traversal clips each chunk and section to the requested region, then
     * subtracts the original coordinates when writing result indices. Each
     * section read uses its own block read operation, so the completed array
     * is not an atomic snapshot of the whole region and block-state values
     * remain shared references.
     */
    default BlockState[][][] getBlockStates(int x, int y, int z, int sizeX, int sizeY, int sizeZ, int layer) {
        if (sizeX < 1 || sizeY < 1 || sizeZ < 1) {
            return null;
        }

        var blockStates = new BlockState[sizeX][sizeY][sizeZ];
        var dimensionType = getDimensionType();
        // Split at chunk and section boundaries, then retain the original region
        // origin for result or supplier coordinates. Clipping local ranges avoids
        // assigning a neighboring chunk's cells to the wrong region offsets.
        var startX = x >> 4;
        var endX = (x + sizeX - 1) >> 4;
        var startY = y >> 4;
        var endY = (y + sizeY - 1) >> 4;
        var startZ = z >> 4;
        var endZ = (z + sizeZ - 1) >> 4;
        for (int chunkX = startX; chunkX <= endX; chunkX++) {
            var cX = chunkX << 4;
            var localStartX = Math.max(x - cX, 0);
            var localEndX = Math.min(x + sizeX - cX, 16);

            for (int chunkZ = startZ; chunkZ <= endZ; chunkZ++) {
                var cZ = chunkZ << 4;
                var localStartZ = Math.max(z - cZ, 0);
                var localEndZ = Math.min(z + sizeZ - cZ, 16);

                var chunk = getChunkManager().getChunk(chunkX, chunkZ);
                if (chunk == null) {
                    // Keep missing chunk cells at their original array offsets as
                    // null, so later loaded cells retain their spatial correspondence
                    // instead of compacting the requested region.
                    continue;
                }

                for (int sectionY = startY; sectionY <= endY; sectionY++) {
                    if (sectionY < dimensionType.minSectionY() || sectionY > dimensionType.maxSectionY()) {
                        continue;
                    }

                    var cY = sectionY << 4;
                    var localStartY = Math.max(y - cY, 0);
                    var localEndY = Math.min(y + sizeY - cY, 16);

                    // Read one section under its block lock. Later sections can
                    // change independently, so this callback protects borrowed section
                    // access without promising a whole-region snapshot.
                    chunk.applyOperationInSection(sectionY, section -> {
                        for (int localX = localStartX; localX < localEndX; localX++) {
                            for (int localY = localStartY; localY < localEndY; localY++) {
                                for (int localZ = localStartZ; localZ < localEndZ; localZ++) {
                                    var globalX = cX + localX;
                                    var globalY = cY + localY;
                                    var globalZ = cZ + localZ;
                                    var blockState = section.getBlockState(localX, localY, localZ, layer);
                                    blockStates[globalX - x][globalY - y][globalZ - z] = blockState;
                                }
                            }
                        }
                    }, OperationType.READ, OperationType.NONE);
                }
            }
        }

        return blockStates;
    }

    /**
     * Writes supplied states directly into loaded sections within a region.
     * Nonpositive extents perform no work, missing chunks and out-of-range
     * sections are skipped, and a {@code null} supplier result leaves that
     * cell unchanged. The supplier receives dimension coordinates while the
     * selected section's block write operation is held.
     *
     * <p>
     * Chunk and section clipping preserve the supplied region origin across
     * local index conversions. Direct section mutation updates palettes
     * without the dimension placement events, block behavior,
     * point-of-interest maintenance, or normal chunk change callbacks. Callers
     * requiring those effects use {@code setBlockState}; a failure here leaves
     * preceding section writes applied.
     */
    default void setBlockStates(int x, int y, int z, int sizeX, int sizeY, int sizeZ, int layer, TriFunction<Integer, Integer, Integer, BlockState> blockStateSupplier) {
        if (sizeX < 1 || sizeY < 1 || sizeZ < 1) {
            return;
        }

        var dimensionType = getDimensionType();
        // Split at chunk and section boundaries, then retain the original region
        // origin for result or supplier coordinates. Clipping local ranges avoids
        // assigning a neighboring chunk's cells to the wrong region offsets.
        var startX = x >> 4;
        var endX = (x + sizeX - 1) >> 4;
        var startY = y >> 4;
        var endY = (y + sizeY - 1) >> 4;
        var startZ = z >> 4;
        var endZ = (z + sizeZ - 1) >> 4;
        for (int chunkX = startX; chunkX <= endX; chunkX++) {
            var cX = chunkX << 4;
            var localStartX = Math.max(x - cX, 0);
            var localEndX = Math.min(x + sizeX - cX, 16);

            for (int chunkZ = startZ; chunkZ <= endZ; chunkZ++) {
                var cZ = chunkZ << 4;
                var localStartZ = Math.max(z - cZ, 0);
                var localEndZ = Math.min(z + sizeZ - cZ, 16);

                var chunk = getChunkManager().getChunk(chunkX, chunkZ);
                if (chunk == null) {
                    // Skip missing chunks without loading them or calling the supplier;
                    // bulk section mutation must not invent data for unavailable regions
                    // while applying writes to other loaded chunks.
                    continue;
                }

                for (int sectionY = startY; sectionY <= endY; sectionY++) {
                    if (sectionY < dimensionType.minSectionY() || sectionY > dimensionType.maxSectionY()) {
                        continue;
                    }

                    var cY = sectionY << 4;
                    var localStartY = Math.max(y - cY, 0);
                    var localEndY = Math.min(y + sizeY - cY, 16);

                    // Hold this section's block write lock across supplier evaluation
                    // and direct palette mutation. Other sections remain independent,
                    // and failures preserve writes already applied before the callback exits.
                    chunk.applyOperationInSection(sectionY, section -> {
                        for (int localX = localStartX; localX < localEndX; localX++) {
                            for (int localY = localStartY; localY < localEndY; localY++) {
                                for (int localZ = localStartZ; localZ < localEndZ; localZ++) {
                                    var globalX = cX + localX;
                                    var globalY = cY + localY;
                                    var globalZ = cZ + localZ;
                                    var blockState = blockStateSupplier.apply(globalX, globalY, globalZ);
                                    if (blockState != null) {
                                        section.setBlockState(localX, localY, localZ, blockState, layer);
                                    }
                                }
                            }
                        }
                    }, OperationType.WRITE, OperationType.NONE);
                }
            }
        }
    }

    /**
     * Updates a block property through the loaded-chunk property helper. This
     * overload selects main layer {@code 0}. Coordinates and property values
     * are forwarded without dimension placement events; missing chunks remain
     * unchanged.
     *
     * @see #updateBlockProperty(BlockPropertyType, Object, int, int, int, int)
     */
    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, int x, int y, int z) {
        updateBlockProperty(propertyType, value, x, y, z, 0);
    }

    /**
     * Updates a block property through the loaded-chunk property helper. This
     * overload selects main layer {@code 0}. Coordinates and property values
     * are forwarded without dimension placement events; missing chunks remain
     * unchanged.
     *
     * @see #updateBlockProperty(BlockPropertyType, Object, Vector3ic, int)
     */
    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, Vector3ic pos) {
        updateBlockProperty(propertyType, value, pos, 0);
    }

    /**
     * Updates a selected-layer property through the loaded-chunk property
     * helper at dimension coordinates. The direct chunk path bypasses
     * dimension placement events and behavior hooks; a missing chunk remains
     * unchanged.
     */
    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, Vector3ic pos, int layer) {
        updateBlockProperty(propertyType, value, pos.x(), pos.y(), pos.z(), layer);
    }

    /**
     * Replaces one property through the loaded chunk's block setter when its
     * state identity changes. Missing chunks perform no work; this path
     * bypasses dimension placement events and behavior hooks while retaining
     * normal chunk change callbacks.
     */
    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, int x, int y, int z, int layer) {
        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return;
        }

        var xIndex = x & 15;
        var zIndex = z & 15;
        var oldBlockState = chunk.getBlockState(xIndex, y, zIndex, layer);

        var newBlockState = oldBlockState.setPropertyValue(propertyType, value);
        if (oldBlockState == newBlockState) {
            return;
        }

        chunk.setBlockState(xIndex, y, zIndex, newBlockState, layer);
    }

    /**
     * Returns colliding block states in the box with the extra lower search
     * row. This overload selects main layer {@code 0}. Collision filtering
     * remains enabled, and the full query supplies its shifted Y origin and
     * no-collision {@code null} result.
     *
     * @see #getCollidingBlockStates(AABBdc, int, boolean)
     */
    default BlockState[][][] getCollidingBlockStates(AABBdc aabb) {
        return getCollidingBlockStates(aabb, 0);
    }

    /**
     * Returns colliding block states in the box with the extra lower search
     * row. Collision filtering remains enabled, and the full query supplies
     * its shifted Y origin and no-collision {@code null} result.
     *
     * @see #getCollidingBlockStates(AABBdc, int, boolean)
     */
    default BlockState[][][] getCollidingBlockStates(AABBdc aabb, int layer) {
        return getCollidingBlockStates(aabb, layer, false);
    }

    /**
     * Returns region-indexed states intersecting the original box, or {@code
     * null} when no collision exists. {@code ignoreCollision} instead includes
     * every loaded cell and returns the array even when no shapes intersect.
     * Callers provide finite, ordered bounds suitable for array allocation.
     *
     * <p>
     * The search starts at {@code floor(aabb.minY()) - 1} to include tall
     * shapes extending above their owning block. Result indices therefore use
     * that shifted Y origin, not the unexpanded box minimum. X and Z origins
     * use their floored minima; missing or filtered cells remain {@code null},
     * and shape intersection still uses the original box.
     */
    default BlockState[][][] getCollidingBlockStates(AABBdc aabb, int layer, boolean ignoreCollision) {
        // Outward rounding covers every block cell touched by fractional box bounds.
        var maxX = (int) Math.ceil(aabb.maxX());
        var maxY = (int) Math.ceil(aabb.maxY());
        var maxZ = (int) Math.ceil(aabb.maxZ());
        var minX = (int) Math.floor(aabb.minX());
        // Include the lower row because tall collision shapes can extend into the
        // query from a block below it; retain this shifted origin when filling
        // result indices rather than shifting the intersection box itself.
        var minY = (int) Math.floor(aabb.minY()) - 1;
        var minZ = (int) Math.floor(aabb.minZ());
        var blockStates = new BlockState[maxX - minX][maxY - minY][maxZ - minZ];
        AtomicBoolean notEmpty = new AtomicBoolean(false);
        forEachBlockStates(minX, minY, minZ, maxX - minX, maxY - minY, maxZ - minZ, layer, (globalX, globalY, globalZ, blockState) -> {
            var blockStateData = blockState.getBlockStateData();
            if (ignoreCollision) {
                blockStates[globalX - minX][globalY - minY][globalZ - minZ] = blockState;
            } else if (blockStateData.hasCollision() && blockStateData.collisionShape().translate(globalX, globalY, globalZ).intersectsAABB(aabb)) {
                blockStates[globalX - minX][globalY - minY][globalZ - minZ] = blockState;
                notEmpty.set(true);
            }
        });
        return ignoreCollision || notEmpty.get() ? blockStates : null;
    }

    /**
     * Queues neighboring block updates on every face except those supplied in
     * {@code ignoreFaces}. A {@code null} or empty exclusion array skips no
     * faces; the originating block state is passed as unknown.
     *
     * @see #updateAroundIgnoreFace(Vector3ic, BlockFace...)
     */
    default void updateAroundIgnoreFace(int x, int y, int z, BlockFace... ignoreFaces) {
        updateAroundIgnoreFace(new Vector3i(x, y, z), ignoreFaces);
    }

    /**
     * Queues neighboring block updates on every face except those supplied in
     * {@code ignoreFaces}. A {@code null} or empty exclusion array skips no
     * faces; the originating block state is passed as unknown.
     *
     * <p>
     * The update queue retains {@code pos} as a changed-neighbor reference.
     * Callers must leave its coordinates unchanged until the queued updates
     * have been processed.
     */
    default void updateAroundIgnoreFace(Vector3ic pos, BlockFace... ignoreFaces) {
        for (var face : BlockFace.VALUES) {
            if (ignoreFaces != null && ignoreFaces.length > 0) {
                var ignore = Arrays.stream(ignoreFaces).anyMatch(ignoreFace -> ignoreFace == face);
                if (ignore) continue;
            }

            updateAtFace(pos, face);
        }
    }

    /**
     * Queues block updates for all six neighbors of the supplied position.
     * This overload reports no known previous source state. The block update
     * manager determines when the queued behavior runs.
     *
     * @see #updateAround(Vector3ic, BlockState)
     */
    default void updateAround(int x, int y, int z) {
        updateAround(x, y, z, null);
    }

    /**
     * Queues block updates for all six neighbors of the supplied position. The
     * previous source state is forwarded to every neighbor and may be {@code
     * null} when unknown. The block update manager determines when the queued
     * behavior runs.
     *
     * @see #updateAround(Vector3ic, BlockState)
     */
    default void updateAround(int x, int y, int z, BlockState oldBlockState) {
        for (var face : BlockFace.VALUES) updateAtFace(x, y, z, face, oldBlockState);
    }

    /**
     * Queues block updates for all six neighbors of the supplied position.
     * This overload reports no known previous source state. The block update
     * manager determines when the queued behavior runs.
     *
     * <p>
     * The update queue retains {@code pos} as a changed-neighbor reference.
     * Callers must leave its coordinates unchanged until the queued updates
     * have been processed.
     */
    default void updateAround(Vector3ic pos) {
        updateAround(pos, null);
    }

    /**
     * Queues block updates for all six neighbors of the supplied position. The
     * previous source state is forwarded to every neighbor and may be {@code
     * null} when unknown. The block update manager determines when the queued
     * behavior runs.
     *
     * <p>
     * The update queue retains {@code pos} as a changed-neighbor reference.
     * Callers must leave its coordinates unchanged until the queued updates
     * have been processed.
     */
    default void updateAround(Vector3ic pos, BlockState oldBlockState) {
        for (var face : BlockFace.VALUES) updateAtFace(pos, face, oldBlockState);
    }

    /**
     * Queues first- and second-order neighboring updates around the supplied
     * dimension coordinates. The vector overload excludes the immediate return
     * face during its second-order pass, and manager processing remains
     * deferred.
     *
     * @see #updateAllAround(Vector3ic)
     */
    default void updateAllAround(int x, int y, int z) {
        updateAllAround(new Vector3i(x, y, z));
    }

    /**
     * Queues updates for immediate neighbors and then neighbors of those
     * neighbors. The first pass addresses all six faces of the original
     * position. Each second-order pass excludes the face pointing back to that
     * original position, rather than treating the source as a new outward
     * target.
     *
     * <p>
     * The block update manager receives the requests and determines execution
     * timing. Different paths can still reach the same second-order position,
     * so this helper does not itself deduplicate the complete neighborhood or
     * guarantee one callback per destination.
     *
     * <p>
     * The update queue retains {@code pos} as a changed-neighbor reference.
     * Callers must leave its coordinates unchanged until the queued updates
     * have been processed.
     */
    default void updateAllAround(Vector3ic pos) {
        // Queue immediate neighbors before expanding outward. Excluding the return
        // face in each expansion avoids reporting the original position as an
        // outward second-order target through that same path.
        updateAround(pos);
        for (var face : BlockFace.VALUES) {
            var neighborPos = face.offsetPos(pos);
            updateAroundIgnoreFace(neighborPos, face.opposite());
        }
    }

    /**
     * Queues an update for the neighbor on the supplied face. The prior source
     * state is unknown in this overload. The manager receives the opposite
     * face so direction remains relative to the receiving block.
     */
    default void updateAtFace(int x, int y, int z, BlockFace face) {
        updateAtFace(x, y, z, face, null);
    }

    /**
     * Queues an update for the neighbor on the supplied face. The supplied
     * prior source state may be {@code null} when unknown. The manager
     * receives the opposite face so direction remains relative to the
     * receiving block.
     */
    default void updateAtFace(int x, int y, int z, BlockFace face, BlockState oldBlockState) {
        updateAtFace(new Vector3i(x, y, z), face, oldBlockState);
    }

    /**
     * Queues an update for the neighbor on the supplied face. The prior source
     * state is unknown in this overload. The manager receives the opposite
     * face so direction remains relative to the receiving block.
     *
     * <p>
     * The update queue retains {@code pos} as a changed-neighbor reference.
     * Callers must leave its coordinates unchanged until the queued updates
     * have been processed.
     */
    default void updateAtFace(Vector3ic pos, BlockFace face) {
        updateAtFace(pos, face, null);
    }

    /**
     * Queues an update for the neighbor on the supplied face. The supplied
     * prior source state may be {@code null} when unknown. The manager
     * receives the opposite face so direction remains relative to the
     * receiving block.
     *
     * <p>
     * The update queue retains {@code pos} as a changed-neighbor reference.
     * Callers must leave its coordinates unchanged until the queued updates
     * have been processed.
     */
    default void updateAtFace(Vector3ic pos, BlockFace face, BlockState oldBlockState) {
        // The receiving neighbor sees the source on its opposite face. Preserve
        // that receiver-relative direction so behavior does not interpret the
        // source as lying on the wrong side.
        var offsetPos = face.offsetPos(pos);
        getBlockUpdateManager().neighborBlockUpdate(offsetPos, pos, face.opposite(), oldBlockState);
    }

    /**
     * Returns a new six-element array of main-layer neighbor block
     * descriptions. Entries follow {@code BlockFace.VALUES} order and use
     * ordinary lookup fallbacks, so missing chunk data appears as air.
     */
    default Block[] getNeighborsBlocks(Vector3ic pos) {
        return getNeighborsBlocks(pos.x(), pos.y(), pos.z());
    }

    /**
     * Returns a new six-element array of main-layer neighbor block
     * descriptions. Entries follow {@code BlockFace.VALUES} order and use
     * ordinary lookup fallbacks, so missing chunk data appears as air.
     */
    default Block[] getNeighborsBlocks(int x, int y, int z) {
        var result = new Block[6];
        for (int i = 0; i < BlockFace.VALUES.length; i++) {
            var offsetPos = BlockFace.VALUES[i].offsetPos(x, y, z);
            var neighborBlockState = getBlockState(offsetPos.x(), offsetPos.y(), offsetPos.z(), 0);
            result[i] = new Block(neighborBlockState, new Position3i(offsetPos, this), 0);
        }
        return result;
    }

    /**
     * Returns whether the supplied height lies within the inclusive dimension
     * bounds.
     */
    default boolean isYInRange(double y) {
        return y >= getDimensionType().getMinHeight() && y <= getDimensionType().getMaxHeight();
    }

    /**
     * Returns whether the height is valid and the selected horizontal chunk is
     * loaded. Horizontal doubles are truncated toward zero before shifting, so
     * negative fractional coordinates can select a different chunk from
     * floor-based block lookup.
     */
    default boolean isInWorld(double x, double y, double z) {
        // Truncation maps a horizontal value such as -0.5 to block zero before chunk selection.
        return isYInRange(y) && getChunkManager().isChunkLoaded((int) x >> 4, (int) z >> 4);
    }

    /**
     * Returns whether the box's minimum and maximum corners pass {@code
     * isInWorld}. Intermediate chunks are not checked, so a positive result
     * does not establish that every chunk overlapped by a large box is loaded.
     */
    default boolean isAABBInWorld(AABBdc aabb) {
        return isInWorld(aabb.maxX(), aabb.maxY(), aabb.maxZ()) && isInWorld(aabb.minX(), aabb.minY(), aabb.minZ());
    }

    /**
     * Returns the block entity at the supplied dimension coordinates without
     * loading its chunk. A missing chunk or absent block entity produces
     * {@code null}; callers provide a valid dimension height.
     */
    default BlockEntity getBlockEntity(int x, int y, int z) {
        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return null;
        }

        return chunk.getBlockEntity(x & 15, y, z & 15);
    }

    /**
     * Returns the block entity at the supplied dimension coordinates without
     * loading its chunk. A missing chunk or absent block entity produces
     * {@code null}; callers provide a valid dimension height.
     *
     * @see #getBlockEntity(int, int, int)
     */
    default BlockEntity getBlockEntity(Vector3ic pos) {
        return getBlockEntity(pos.x(), pos.y(), pos.z());
    }

    /**
     * Collects loaded chunks' block entities into a new map using their
     * existing position keys. The collector currently returns mutable map
     * storage despite the annotation; entity references remain shared.
     * Duplicate chunk-local keys across different chunks throw {@code
     * IllegalStateException} rather than merging entries.
     */
    @Unmodifiable
    default Map<Integer, BlockEntity> getBlockEntities() {
        return getChunkManager().getLoadedChunks().stream()
                .flatMap(chunk -> chunk.getBlockEntities().entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Returns the total current block entity count across loaded chunks.
     */
    default int getBlockEntityCount() {
        return getChunkManager().getLoadedChunks().stream().mapToInt(chunk -> chunk.getBlockEntities().size()).sum();
    }

    /**
     * Presents a block action to actual player loaders of the position's
     * loaded chunk. Missing chunks produce no presentation, and the operation
     * does not change the simulated block or load storage.
     *
     * @see #addBlockAction(int, int, int, BlockAction)
     */
    default void addBlockAction(Vector3ic pos, BlockAction action) {
        addBlockAction(pos.x(), pos.y(), pos.z(), action);
    }

    /**
     * Presents a block action to actual player loaders of the position's
     * loaded chunk. Missing chunks produce no presentation, and the operation
     * does not change the simulated block or load storage.
     */
    default void addBlockAction(int x, int y, int z, BlockAction action) {
        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk != null) {
            chunk.forEachChunkLoaders(loader -> {
                if (loader instanceof EntityPlayer player && player.isActualPlayer()) {
                    player.getController().viewBlockAction(new Vector3i(x, y, z), action);
                }
            });
        }
    }

    /**
     * Presents a particle to actual player loaders of the selected loaded
     * chunk. Missing chunks produce no presentation; fractional positions
     * retain their visual coordinates while chunk selection truncates
     * horizontal values toward zero.
     *
     * @see #addParticle(double, double, double, Particle)
     */
    default void addParticle(Vector3ic pos, Particle particle) {
        addParticle(pos.x(), pos.y(), pos.z(), particle);
    }

    /**
     * Presents a particle to actual player loaders of the selected loaded
     * chunk. Missing chunks produce no presentation; fractional positions
     * retain their visual coordinates while chunk selection truncates
     * horizontal values toward zero.
     *
     * @see #addParticle(double, double, double, Particle)
     */
    default void addParticle(Vector3dc pos, Particle particle) {
        addParticle(pos.x(), pos.y(), pos.z(), particle);
    }

    /**
     * Presents a particle to actual player loaders of the selected loaded
     * chunk. Missing chunks produce no presentation; fractional positions
     * retain their visual coordinates while chunk selection truncates
     * horizontal values toward zero.
     */
    default void addParticle(double x, double y, double z, Particle particle) {
        // Truncation selects block zero for negative fractional positions between -1 and zero.
        var chunk = getChunkManager().getChunkByDimensionPos((int) x, (int) z);
        if (chunk != null) {
            chunk.forEachChunkLoaders(loader -> {
                if (loader instanceof EntityPlayer player && player.isActualPlayer()) {
                    player.getController().viewParticle(particle, new Vector3d(x, y, z));
                }
            });
        }
    }

    /**
     * Presents a sound to actual player loaders of the selected loaded chunk.
     * This overload enables distance-based attenuation. Missing chunks receive
     * no presentation; fractional horizontal coordinates are truncated for
     * chunk selection.
     *
     * @see #addSound(double, double, double, Sound, boolean)
     */
    default void addSound(Vector3dc pos, Sound sound) {
        addSound(pos, sound, true);
    }

    /**
     * Presents a sound to actual player loaders of the selected loaded chunk.
     * The supplied {@code relative} flag controls distance attenuation.
     * Missing chunks receive no presentation; fractional horizontal
     * coordinates are truncated for chunk selection.
     *
     * @see #addSound(double, double, double, Sound, boolean)
     */
    default void addSound(Vector3dc pos, Sound sound, boolean relative) {
        addSound(pos.x(), pos.y(), pos.z(), sound, relative);
    }

    /**
     * Presents a sound to actual player loaders of the selected loaded chunk.
     * This overload enables distance-based attenuation. Missing chunks receive
     * no presentation; fractional horizontal coordinates are truncated for
     * chunk selection.
     *
     * @see #addSound(double, double, double, Sound, boolean)
     */
    default void addSound(Vector3ic pos, Sound sound) {
        addSound(pos, sound, true);
    }

    /**
     * Presents a sound to actual player loaders of the selected loaded chunk.
     * The supplied {@code relative} flag controls distance attenuation.
     * Missing chunks receive no presentation; fractional horizontal
     * coordinates are truncated for chunk selection.
     *
     * @see #addSound(double, double, double, Sound, boolean)
     */
    default void addSound(Vector3ic pos, Sound sound, boolean relative) {
        addSound(pos.x(), pos.y(), pos.z(), sound, relative);
    }

    /**
     * Presents a sound to actual player loaders of the selected loaded chunk.
     * This overload enables distance-based attenuation. Missing chunks receive
     * no presentation; fractional horizontal coordinates are truncated for
     * chunk selection.
     *
     * @see #addSound(double, double, double, Sound, boolean)
     */
    default void addSound(double x, double y, double z, Sound sound) {
        addSound(x, y, z, sound, true);
    }

    /**
     * Presents a sound to actual player loaders of the selected loaded chunk.
     * The supplied {@code relative} flag controls distance attenuation.
     * Missing chunks receive no presentation; fractional horizontal
     * coordinates are truncated for chunk selection.
     */
    default void addSound(double x, double y, double z, Sound sound, boolean relative) {
        // Truncation selects block zero for negative fractional positions between -1 and zero.
        var chunk = getChunkManager().getChunkByDimensionPos((int) x, (int) z);
        if (chunk != null) {
            chunk.forEachChunkLoaders(loader -> {
                if (loader instanceof EntityPlayer player && player.isActualPlayer()) {
                    player.getController().viewSound(sound, new Vector3d(x, y, z), relative);
                }
            });
        }
    }

    /**
     * Requests an item drop with ten-tick pickup delay and randomized
     * horizontal motion. The initial vertical motion is {@code 0.2}, and
     * horizontal components lie from {@code -0.1} inclusive to {@code 0.1}
     * exclusive.
     *
     * @see #dropItem(ItemStack, Vector3dc, Vector3dc, int)
     */
    default void dropItem(ItemStack itemStack, Vector3dc pos) {
        var rand = ThreadLocalRandom.current();
        dropItem(itemStack, pos, new Vector3d(rand.nextDouble(0.2) - 0.1, 0.2, rand.nextDouble(0.2) - 0.1));
    }

    /**
     * Requests an item drop using the supplied motion and a ten-tick pickup
     * delay. The full operation retains the supplied stack after clearing its
     * network identity and delegates entity admission to the manager.
     *
     * @see #dropItem(ItemStack, Vector3dc, Vector3dc, int)
     */
    default void dropItem(ItemStack itemStack, Vector3dc pos, Vector3dc motion) {
        dropItem(itemStack, pos, motion, 10);
    }

    /**
     * Creates an item entity and requests its addition using the supplied
     * motion and pickup delay in entity ticks. The item setter retains the
     * supplied stack and clears its network identity; admission is queued
     * rather than awaited.
     */
    default void dropItem(ItemStack itemStack, Vector3dc pos, Vector3dc motion, int pickupDelay) {
        var entityItem = EntityTypes.ITEM.createEntity(
                EntityInitInfo.builder()
                        .dimension(this)
                        .pos(pos)
                        .motion(motion)
                        .build()
        );
        entityItem.setItemStack(itemStack);
        entityItem.setPickupDelay(pickupDelay);
        getEntityManager().addEntity(entityItem);
    }

    /**
     * Splits a positive experience amount into canonical orb sizes and
     * requests each drop. Nonpositive amounts produce no drops; each resulting
     * orb follows the ordinary randomized-motion and pickup-delay defaults.
     */
    default void splitAndDropXpOrb(Vector3dc pos, int xp) {
        for (var split : EntityXpOrb.splitIntoOrbSizes(xp)) {
            dropXpOrb(pos, split);
        }
    }

    /**
     * Requests an experience orb with randomized upward and horizontal motion.
     * The delegated overload uses a ten-tick pickup delay, and the manager
     * handles admission rather than completing it synchronously here.
     */
    default void dropXpOrb(Vector3dc pos, int xp) {
        var rand = ThreadLocalRandom.current();
        var motion = new Vector3d(
                (rand.nextDouble() * 0.2 - 0.1) * 2,
                rand.nextDouble() * 0.4,
                (rand.nextDouble() * 0.2 - 0.1) * 2
        );
        dropXpOrb(pos, xp, motion);
    }

    /**
     * Requests an experience orb with the supplied value and motion using a
     * ten-tick pickup delay. The full operation assigns randomized yaw and
     * delegates admission without waiting for the orb to become live.
     */
    default void dropXpOrb(Vector3dc pos, int xp, Vector3dc motion) {
        dropXpOrb(pos, xp, motion, 10);
    }

    /**
     * Creates an experience orb with the supplied value, motion, and pickup
     * delay in entity ticks. Initial yaw is randomized over a full rotation,
     * and the entity manager receives a queued addition request.
     */
    default void dropXpOrb(Vector3dc pos, int xp, Vector3dc motion, int pickupDelay) {
        var rand = ThreadLocalRandom.current();
        var entityXpOrb = EntityTypes.XP_ORB.createEntity(
                EntityInitInfo.builder()
                        .dimension(this)
                        .pos(pos)
                        .motion(motion)
                        // Convert the uniform fraction to yaw degrees for the orb.
                        .rot(rand.nextDouble() * 360, 0)
                        .build()
        );
        entityXpOrb.setExperienceValue(xp);
        entityXpOrb.setPickupDelay(pickupDelay);
        getEntityManager().addEntity(entityXpOrb);
    }

    /**
     * Requests an event-aware break of the main-layer block at the supplied
     * coordinates. This overload supplies no item or entity context. Break
     * particles are enabled by default. The delegated result reports
     * break-path acceptance rather than a persistence guarantee.
     *
     * @see #breakBlock(int, int, int, ItemStack, Entity, boolean)
     */
    default boolean breakBlock(Vector3ic pos) {
        return breakBlock(pos.x(), pos.y(), pos.z(), null, null);
    }

    /**
     * Requests an event-aware break of the main-layer block at the supplied
     * coordinates. Break particles are enabled by default. The delegated
     * result reports break-path acceptance rather than a persistence
     * guarantee.
     *
     * @see #breakBlock(int, int, int, ItemStack, Entity, boolean)
     */
    default boolean breakBlock(Vector3ic pos, ItemStack usedItem, Entity entity) {
        return breakBlock(pos.x(), pos.y(), pos.z(), usedItem, entity);
    }

    /**
     * Requests an event-aware break of the main-layer block at the supplied
     * coordinates. This overload supplies no item or entity context. Break
     * particles are enabled by default. The delegated result reports
     * break-path acceptance rather than a persistence guarantee.
     *
     * @see #breakBlock(int, int, int, ItemStack, Entity, boolean)
     */
    default boolean breakBlock(int x, int y, int z) {
        return breakBlock(x, y, z, null, null);
    }

    /**
     * Requests an event-aware break of the main-layer block at the supplied
     * coordinates. Break particles are enabled by default. The delegated
     * result reports break-path acceptance rather than a persistence
     * guarantee.
     *
     * @see #breakBlock(int, int, int, ItemStack, Entity, boolean)
     */
    default boolean breakBlock(int x, int y, int z, ItemStack usedItem, Entity entity) {
        return breakBlock(x, y, z, usedItem, entity, true);
    }

    /**
     * Requests an event-aware break of the main-layer block at the supplied
     * coordinates. The supplied particle flag is forwarded unchanged. The
     * delegated result reports break-path acceptance rather than a persistence
     * guarantee.
     *
     * @see #breakBlock(int, int, int, ItemStack, Entity, boolean)
     */
    default boolean breakBlock(Vector3ic pos, ItemStack usedItem, Entity entity, boolean hasParticle) {
        return breakBlock(pos.x(), pos.y(), pos.z(), usedItem, entity, hasParticle);
    }

    /**
     * Runs the event-aware break path for a main-layer block using optional
     * item and entity context. Both contexts may be {@code null}; air or
     * cancelled break dispatch returns {@code false}, while an accepted path
     * may still encounter a separately rejected air placement.
     */
    boolean breakBlock(int x, int y, int z, ItemStack usedItem, Entity entity, boolean sendParticle);

    /**
     * Returns the stored surface height for a loaded column without loading
     * its chunk. Missing chunks use the dimension minimum height, which is
     * also the empty-column baseline rather than a distinct missing-data
     * marker.
     */
    default int getHeight(int x, int z) {
        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return getDimensionType().getMinHeight();
        }

        return chunk.getHeight(x & 15, z & 15);
    }

    /**
     * Returns the main-layer block at the column's reported surface height.
     * The ordinary height and block lookups supply their unloaded-chunk
     * fallbacks, so this helper does not request missing data from storage.
     */
    default BlockState getHighestBlockState(int x, int z) {
        return getBlockState(x, getHeight(x, z), z);
    }

    /**
     * Searches up to ten random columns around the supplied horizontal center.
     * The full operation waits for each selected chunk and returns the first
     * standing position accepted by {@code predicate}, or {@code null}.
     *
     * @see #findSuitableGroundPosAround(Predicate, int, int, int, int)
     */
    default Vector3ic findSuitableGroundPosAround(Predicate<Position3ic> predicate, int x, int z, @Range(from = 0, to = Integer.MAX_VALUE) int range) {
        return findSuitableGroundPosAround(predicate, x, z, range, 10);
    }

    /**
     * Searches random columns within the inclusive horizontal range and
     * returns the first accepted standing position. Nonpositive {@code
     * attemptCount} returns {@code null}; exhausted attempts also return
     * {@code null}. Callers supply a nonnegative range with valid random
     * bounds and coordinate sums.
     *
     * <p>
     * Each attempt joins explicit chunk loading before using surface height
     * plus one. Callers run where chunk loading can progress while waiting,
     * rather than blocking the ticking context required to advance it.
     * Candidate rejection retries another sampled column, and the returned
     * vector is newly allocated after {@code predicate} accepts the
     * dimension-aware position.
     */
    default Vector3ic findSuitableGroundPosAround(Predicate<Position3ic> predicate, int x, int z, @Range(from = 0, to = Integer.MAX_VALUE) int range, @Range(from = 0, to = Integer.MAX_VALUE) int attemptCount) {
        var rand = ThreadLocalRandom.current();
        while (attemptCount > 0) {
            attemptCount--;
            var px = x + rand.nextInt(-range, range + 1);
            var pz = z + rand.nextInt(-range, range + 1);
            // Wait for the selected chunk before consulting surface height, otherwise
            // the unloaded lookup fallback could be mistaken for actual ground
            // and pass an unrelated standing position to the predicate.
            getChunkManager().getOrLoadChunk(px >> 4, pz >> 4).join();
            var py = getHeight(px, pz) + 1;
            if (predicate.test(new Position3i(px, py, pz, this))) {
                return new Vector3i(px, py, pz);
            }
        }
        return null;
    }

    /**
     * Returns whether the column's reported surface height is at or below the
     * supplied position. The helper does not inspect skylight support or
     * individual opacity, and missing chunks use the ordinary minimum-height
     * fallback.
     *
     * @see #canPosSeeSky(int, int, int)
     */
    default boolean canPosSeeSky(Vector3ic pos) {
        return canPosSeeSky(pos.x(), pos.y(), pos.z());
    }

    /**
     * Returns whether the column's reported surface height is at or below the
     * supplied position. The helper does not inspect skylight support or
     * individual opacity, and missing chunks use the ordinary minimum-height
     * fallback.
     */
    default boolean canPosSeeSky(int x, int y, int z) {
        return getHeight(x, z) <= y;
    }

    /**
     * Returns the biome at the supplied dimension block coordinates without
     * loading data. Missing chunks and heights outside the dimension select
     * {@code BiomeTypes.PLAINS}, while a loaded in-range position uses its
     * stored biome.
     *
     * @see #getBiome(int, int, int)
     */
    default BiomeType getBiome(Vector3ic pos) {
        return getBiome(pos.x(), pos.y(), pos.z());
    }

    /**
     * Returns the biome at the supplied dimension block coordinates without
     * loading data. Missing chunks and heights outside the dimension select
     * {@code BiomeTypes.PLAINS}, while a loaded in-range position uses its
     * stored biome.
     */
    default BiomeType getBiome(int x, int y, int z) {
        // Use the same plains fallback for missing chunks and invalid heights,
        // giving biome consumers a concrete registered biome without loading
        // storage merely to answer an unavailable-position query.
        if (y < this.getDimensionType().getMinHeight() || y > getDimensionType().getMaxHeight())
            return BiomeTypes.PLAINS;

        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return BiomeTypes.PLAINS;
        }

        return chunk.getBiome(x & 15, y, z & 15);
    }

    /**
     * Stores the supplied biome through the position's loaded chunk without
     * requesting storage loads. Missing chunks remain unchanged; callers
     * provide a valid dimension height before the chunk setter receives its
     * local coordinates.
     *
     * @see #setBiome(int, int, int, BiomeType)
     */
    default void setBiome(Vector3ic pos, BiomeType biome) {
        setBiome(pos.x(), pos.y(), pos.z(), biome);
    }

    /**
     * Stores the supplied biome through the position's loaded chunk without
     * requesting storage loads. Missing chunks remain unchanged; callers
     * provide a valid dimension height before the chunk setter receives its
     * local coordinates.
     */
    default void setBiome(int x, int y, int z, BiomeType biome) {
        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return;
        }

        chunk.setBiome(x & 15, y, z & 15, biome);
    }

    /**
     * Returns the foreground or extra-layer liquid selected by the layered
     * lookup. Foreground liquid takes precedence, and no selected liquid
     * returns {@link #PAIR_LIQUID_NOT_FOUND} rather than a nullable pair.
     *
     * @see #getLiquid(Vector3ic)
     */
    default Pair<Integer, BlockState> getLiquid(int x, int y, int z) {
        return getLiquid(new Vector3i(x, y, z));
    }

    /**
     * Returns the selected liquid state together with its layer number. A
     * foreground liquid wins immediately; otherwise the foreground must
     * advertise liquid containment before layer {@code 1} is inspected. No
     * selected liquid returns {@link #PAIR_LIQUID_NOT_FOUND}.
     *
     * <p>
     * The containment check preserves the foreground block's rule for whether
     * extra-layer liquid is meaningful at that position. Ordinary block
     * lookups provide air for missing chunks or invalid heights, and the
     * returned pair retains the selected shared block-state reference rather
     * than creating a new state.
     */
    default Pair<Integer, BlockState> getLiquid(Vector3ic pos) {
        var layer0 = getBlockState(pos);
        if (layer0.getBehavior() instanceof BlockLiquidBaseComponent) {
            return new Pair<>(0, layer0);
        }

        if (!layer0.getBlockStateData().canContainLiquid()) {
            return PAIR_LIQUID_NOT_FOUND;
        }

        var layer1 = getBlockState(pos, 1);
        if (layer1.getBehavior() instanceof BlockLiquidBaseComponent) {
            return new Pair<>(1, layer1);
        }

        return PAIR_LIQUID_NOT_FOUND;
    }

    /**
     * Requests a liquid replacement through the layered liquid helper at
     * dimension coordinates. {@code null} requests removal; missing chunks or
     * invalid heights cause no work through the helper's world-availability
     * check.
     *
     * @see #setLiquid(Vector3ic, BlockState)
     */
    default void setLiquid(int x, int y, int z, BlockState liquid) {
        setLiquid(new Vector3i(x, y, z), liquid);
    }

    /**
     * Requests a liquid placement using foreground replacement or extra-layer
     * containment. Missing chunks or invalid heights cause no work, and {@code
     * liquid == null} requests removal. A nonreplaceable foreground must
     * permit the supplied liquid's source or flowing form before either layer
     * changes.
     *
     * <p>
     * The helper first runs {@code removeLiquid}; its boolean selects
     * foreground layer {@code 0} when empty or liquid, otherwise extra layer
     * {@code 1}. Individual event-aware placement results are ignored, so
     * cancellation can leave an earlier removal applied or reject the later
     * replacement without rollback. Callers supply a liquid block state for
     * non-{@code null} requests.
     */
    default void setLiquid(Vector3ic pos, BlockState liquid) {
        if (!isInWorld(pos.x(), pos.y(), pos.z())) {
            return;
        }

        if (liquid == null) {
            removeLiquid(pos);
            return;
        }

        var existingBlockState = getBlockState(pos);
        if (!existingBlockState.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            var blockStateData = existingBlockState.getBlockStateData();
            if (!(isSource(liquid) ? blockStateData.canContainLiquidSource() : blockStateData.canContainLiquid())) {
                return;
            }
        }

        // removeLiquid reports the foreground branch rather than placement success.
        // Use that distinction to choose the destination layer without treating
        // a cancelled earlier air placement as a completed transactional removal.
        if (removeLiquid(pos)) {
            setBlockState(pos, liquid, 0);
        } else {
            setBlockState(pos, liquid, 1);
        }
    }

    /**
     * Requests liquid removal and returns the foreground-based layer-selection
     * result. {@code true} means the helper selected the empty or
     * foreground-liquid branch, while extra-layer removal returns {@code
     * false} even when requested.
     *
     * @see #removeLiquid(Vector3ic)
     */
    default boolean removeLiquid(int x, int y, int z) {
        return removeLiquid(new Vector3i(x, y, z));
    }

    /**
     * Requests removal of a liquid in the foreground or extra layer. An air
     * foreground returns {@code true} immediately, and a foreground liquid
     * requests air placement before returning {@code true}. A containing
     * foreground with extra-layer liquid requests its removal and returns
     * {@code false}.
     *
     * <p>
     * The boolean describes foreground-based layer selection rather than
     * verified mutation success. Placement events may reject the requested air
     * state, and their result is not checked; callers must not interpret
     * {@code true} as proof that no liquid or block remains at the position.
     */
    default boolean removeLiquid(Vector3ic pos) {
        var layer0 = getBlockState(pos);
        if (layer0.getBlockType() == AIR) {
            return true;
        }

        if (layer0.getBehavior() instanceof BlockLiquidBaseComponent) {
            setBlockState(pos, AIR.getDefaultState());
            return true;
        }

        if (layer0.getBlockStateData().canContainLiquid()) {
            var layer1 = getBlockState(pos, 1);
            if (layer1.getBehavior() instanceof BlockLiquidBaseComponent) {
                setBlockState(pos, AIR.getDefaultState(), 1);
                return false;
            }
        }

        return false;
    }

    /**
     * Returns whether the computed incoming power at this position exceeds
     * zero.
     */
    default boolean isPoweredAt(Vector3ic pos) {
        return this.getPowerAt(pos) > 0;
    }

    /**
     * Returns the strongest neighboring power contribution directed toward the
     * query position. Direct weak signals contribute alongside strong signals
     * conducted through solid neighbors, while ordinary block lookup supplies
     * the behavior for missing data. The computation compares contributions
     * rather than summing them.
     *
     * <p>
     * Conduction through a solid neighbor examines its other faces, excluding
     * the direction leading back to the target. This distinction prevents the
     * immediate return path from being counted as the neighbor's incoming
     * source and preserves stronger independent contributions when other sides
     * also provide power.
     */
    int getPowerAt(Vector3ic pos);

    /**
     * Returns the greatest incoming strong power while considering all six
     * faces. This overload supplies an empty exclusion array and delegates
     * directional sampling to the full power query.
     */
    default int getStrongPowerAt(Vector3ic pos) {
        return this.getStrongPowerAt(pos, new BlockFace[0]);
    }

    /**
     * Returns the greatest incoming strong power after excluding selected
     * neighbor directions. Callers supply a non-{@code null} face array; an
     * empty array includes every face. Sampling queries each neighbor on the
     * face directed toward this position rather than using the query's outward
     * face.
     *
     * <p>
     * The directional exclusions allow conduction checks to omit the immediate
     * path back toward their original target. The result compares
     * contributions rather than summing them, and no contributing signal
     * leaves zero; callers rely on block behaviors for the reported signal
     * values.
     */
    int getStrongPowerAt(Vector3ic pos, BlockFace... excludeFaces);

    /**
     * Returns the greatest incoming weak power while considering all six
     * faces. This overload supplies an empty exclusion array and delegates
     * directional sampling to the full power query.
     */
    default int getWeakPowerAt(Vector3ic pos) {
        return this.getWeakPowerAt(pos, new BlockFace[0]);
    }

    /**
     * Returns the greatest incoming weak power after excluding selected
     * neighbor directions. Callers supply a non-{@code null} face array; an
     * empty array includes every face. Sampling queries each neighbor on the
     * face directed toward this position rather than using the query's outward
     * face.
     *
     * <p>
     * The directional exclusions allow conduction checks to omit the immediate
     * path back toward their original target. The result compares
     * contributions rather than summing them, and no contributing signal
     * leaves zero; callers rely on block behaviors for the reported signal
     * values.
     */
    int getWeakPowerAt(Vector3ic pos, BlockFace... excludeFaces);

    /**
     * Queues updates for horizontal diodes affected by this block's comparator
     * output. A solid neighboring block also permits checking the diode
     * directly behind it; unloaded chunks are skipped and the block update
     * manager controls later behavior execution.
     *
     * @see #updateComparatorOutputLevel(int, int, int)
     */
    default void updateComparatorOutputLevel(Vector3ic pos) {
        updateComparatorOutputLevel(pos.x(), pos.y(), pos.z());
    }

    /**
     * Queues updates for horizontal diodes affected by this block's comparator
     * output. A solid neighboring block also permits checking the diode
     * directly behind it; unloaded chunks are skipped and the block update
     * manager controls later behavior execution.
     */
    void updateComparatorOutputLevel(int x, int y, int z);

    /**
     * Creates a lightning proposal at the supplied dimension position. The
     * supplied cause is retained by the cancellable strike event. Cancellation
     * returns {@code false}; accepted dispatch requests entity addition and
     * returns {@code true} without awaiting admission.
     */
    boolean strikeLightning(double x, double y, double z, LightningStrikeEvent.Cause cause);

    /**
     * Creates a lightning proposal at the supplied dimension position. This
     * overload reports {@code LightningStrikeEvent.Cause.CUSTOM}. Cancellation
     * returns {@code false}; accepted dispatch requests entity addition and
     * returns {@code true} without awaiting admission.
     */
    default boolean strikeLightning(double x, double y, double z) {
        return strikeLightning(x, y, z, LightningStrikeEvent.Cause.CUSTOM);
    }

    /**
     * Creates a lightning proposal at the supplied dimension position. The
     * supplied cause is retained by the cancellable strike event. Cancellation
     * returns {@code false}; accepted dispatch requests entity addition and
     * returns {@code true} without awaiting admission.
     */
    default boolean strikeLightning(Vector3dc pos, LightningStrikeEvent.Cause cause) {
        return strikeLightning(pos.x(), pos.y(), pos.z(), cause);
    }

    /**
     * Creates a lightning proposal at the supplied dimension position. This
     * overload reports {@code LightningStrikeEvent.Cause.CUSTOM}. Cancellation
     * returns {@code false}; accepted dispatch requests entity addition and
     * returns {@code true} without awaiting admission.
     */
    default boolean strikeLightning(Vector3dc pos) {
        return strikeLightning(pos.x(), pos.y(), pos.z());
    }

    /**
     * Returns whether the owning world currently accepts creator-bound
     * scheduled tasks.
     */
    @Override
    default boolean isValid() {
        return getWorld().isValid();
    }

    /**
     * Returns the nearest matching point of interest among loaded chunks in a
     * square radius. No matching entry returns {@code null}, and the radius is
     * measured in chunks around the query's containing chunk rather than
     * blocks around the exact position. Unloaded chunks are skipped.
     *
     * <p>
     * Selection compares three-dimensional squared distance after converting
     * local position keys to dimension coordinates. The returned vector is a
     * new position value; equal distances keep the first encountered
     * candidate, so callers must not rely on a stable tie choice from map
     * iteration order.
     */
    Vector3ic findNearestPoi(PoiType type, int x, int y, int z, int chunkRadius);
}
