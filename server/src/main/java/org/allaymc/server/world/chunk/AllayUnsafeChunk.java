package org.allaymc.server.world.chunk;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import io.netty.util.internal.PlatformDependent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.block.BlockRandomUpdateEvent;
import org.allaymc.api.eventbus.event.block.BlockScheduleUpdateEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.chunk.*;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.poi.PoiType;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.AllayServer;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.world.light.AllayLightEngine;
import org.allaymc.server.world.manager.AllayEntityManager;
import org.jctools.maps.NonBlockingHashMap;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * {@code AllayUnsafeChunk} owns
 * mutable chunk palettes, height caches, scheduled updates, and queues for
 * viewer notifications and deferred tasks. The safe wrapper shares this data
 * and supplies palette locks, while the unsafe implementation assumes its
 * caller establishes the required execution context.
 *
 * <p>
 * Primary-layer writes mark horizontal height columns dirty before invoking the
 * installed lighting callback and optionally queuing notifications. Height
 * reads recompute dirty columns from current sections, then clear their flags.
 * Load hooks connect lighting and entity services, while unload hooks detach
 * callbacks without resetting the stored loaded flag. Scheduled updates are
 * removed before callbacks execute, and queued viewer work can be discarded
 * when no loaders remain. These independent lifecycle and cache states must not
 * be inferred solely from generation state. The immutable {@code x} and
 * {@code z} identify the chunk origin; methods shift these coordinates by four
 * bits when converting local horizontal positions into dimension block
 * positions.
 *
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class AllayUnsafeChunk implements UnsafeChunk {

    /**
     * {@code LCG_CONSTANT} defines
     * the XOR constant used with the multiply-by-three random block
     * selection sequence.
     */
    protected static final int LCG_CONSTANT = 1013904223;

    /**
     * Absolute chunk X coordinate {@code x} and absolute chunk Z coordinate
     * {@code z}.
     */
    @Getter
    protected final int x, z;
    /**
     * Dimension type defining vertical bounds.
     */
    @Getter
    protected final DimensionType dimensionType;
    /**
     * {@code sections} holds sections ordered from minimum section Y. The
     * builder and codecs may share this array and its mutable entries.
     */
    protected final AllayChunkSection[] sections;
    /**
     * {@code heightMap} holds the retained height cache. Height reads refresh
     * columns lazily when {@code heightMapDirtyFlags} marks primary-layer changes.
     */
    protected final HeightMap heightMap;
    /**
     * {@code scheduledUpdates} maps local positions to pending update descriptors.
     * The generated getter exposes the same mutable map used by ticking and codecs.
     */
    @Getter
    protected final NonBlockingHashMap<Integer, ScheduledUpdateInfo> scheduledUpdates;
    /**
     * {@code blockEntities} maps local positions to shared block entities.
     * Insertion does not establish the entities' world or chunk ownership.
     */
    protected final NonBlockingHashMap<Integer, BlockEntity> blockEntities;
    /**
     * {@code poiEntries} maps local positions to point-of-interest types.
     * Callers receive an unmodifiable live view of this concurrent mapping.
     */
    protected final NonBlockingHashMap<Integer, PoiType> poiEntries;
    /**
     * {@code heightMapDirtyFlags} tracks dirty columns across sections without
     * internal synchronization. Primary-layer writes set flags, and height
     * calculations clear them after updating {@code heightMap}.
     */
    protected final ChunkBitMap heightMapDirtyFlags;
    /**
     * {@code chunkLoaders} holds concurrent loader membership. Empty membership
     * causes notification and task processing to discard pending viewer updates
     * and deferred chunk tasks.
     */
    protected final Set<ChunkLoader> chunkLoaders;
    /**
     * {@code blockUpdates} queues primary-layer notifications concurrently.
     * {@code sendBlockUpdates()} drains notifications for actual players or
     * clears them when no loaders remain.
     */
    protected final Queue<WorldViewer.BlockUpdate> blockUpdates;
    /**
     * {@code extraBlockUpdates} queues secondary-layer notifications concurrently.
     * {@code sendBlockUpdates()} drains both layer queues together or clears
     * them when no loaders remain.
     */
    protected final Queue<WorldViewer.BlockUpdate> extraBlockUpdates;
    /**
     * {@code chunkTaskQueue} accepts deferred tasks concurrently.
     * {@code performChunkTasks()} drains the queue or clears pending tasks
     * when no loaders remain.
     */
    protected final Queue<Runnable> chunkTaskQueue;
    /**
     * {@code safeChunk} caches the single safe wrapper over this chunk's shared
     * mutable state. Coordinated callers reuse its palette lock domain.
     */
    protected final AllayChunk safeChunk;
    /**
     * {@code state} publishes the current generation stage. Holder transitions
     * update it independently of {@code loaded} and {@code chunkLoaders}.
     */
    @Getter
    @Setter
    protected volatile ChunkState state;
    /**
     * {@code blockChangeCallback} holds an optional synchronous mutation callback.
     * Load hooks install it, unload hooks clear it, and direct setters require
     * lifecycle coordination.
     */
    @Setter
    protected BlockChangeCallback blockChangeCallback;
    /**
     * {@code loaded} becomes {@code true} after load hooks. Unload hooks do not
     * reset it, so this flag does not establish current residency.
     */
    @Getter
    protected volatile boolean loaded;
    /**
     * {@code autoSaveTimer} counts autosave checks since the last request.
     * Submission resets the counter without observing persistence completion.
     */
    protected int autoSaveTimer = 0;
    /**
     * {@code updateLCG} holds pseudo-random selection state seeded per chunk.
     * Random ticking advances this value without synchronization between callers.
     */
    protected int updateLCG = ThreadLocalRandom.current().nextInt();

    /**
     * Creates a chunk retaining the supplied sections, maps, height cache,
     * dimension, and generation state. The caller must provide
     * dimension-aligned data and compatible ownership because no defensive
     * copies are made. Fresh dirty flags initially treat the supplied height
     * cache as authoritative, and independent concurrent queues collect later
     * work. A single safe wrapper is created over the shared data so
     * coordinated callers use one palette-lock domain.
     */
    AllayUnsafeChunk(
            int x, int z, DimensionType dimensionType,
            AllayChunkSection[] sections, HeightMap heightMap,
            NonBlockingHashMap<Integer, ScheduledUpdateInfo> scheduledUpdates,
            ChunkState state, NonBlockingHashMap<Integer, BlockEntity> blockEntities,
            NonBlockingHashMap<Integer, PoiType> poiEntries) {
        this.x = x;
        this.z = z;
        this.dimensionType = dimensionType;
        this.sections = sections;
        this.heightMap = heightMap;
        this.scheduledUpdates = scheduledUpdates;
        this.state = state;
        this.blockEntities = blockEntities;
        this.poiEntries = poiEntries;
        this.heightMapDirtyFlags = new ChunkBitMap();
        this.chunkLoaders = Sets.newConcurrentHashSet();
        this.blockUpdates = PlatformDependent.newMpscQueue();
        this.extraBlockUpdates = PlatformDependent.newMpscQueue();
        this.chunkTaskQueue = PlatformDependent.newMpscQueue();
        this.safeChunk = new AllayChunk(this);
    }

    /**
     * Returns a fresh mutable builder initially set to {@code FULL} at zero
     * chunk coordinates. Its data maps are independent, and callers must select
     * a dimension before {@code build()} can create a chunk.
     */
    public static AllayChunkBuilder builder() {
        return new AllayChunkBuilder();
    }

    /**
     * Rejects local X or Z coordinates outside zero through fifteen with {@link
     * IllegalArgumentException}. This validation establishes horizontal chunk
     * bounds but does not inspect height or a selected block layer.
     */
    public static void checkXZ(int x, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    /**
     * Rejects block Y outside this dimension's inclusive minimum and maximum
     * heights. The coordinate is absolute dimension height, and an invalid
     * value causes {@link IllegalArgumentException} before subsequent indexed
     * access.
     */
    public void checkY(int y) {
        Preconditions.checkArgument(y >= dimensionType.getMinHeight() && y <= dimensionType.getMaxHeight());
    }

    /**
     * Rejects absolute section Y outside this dimension's inclusive section
     * range. The argument is not an array index; invalid section coordinates
     * cause {@link IllegalArgumentException} before indexed offset calculation.
     */
    public void checkSectionY(int sectionY) {
        Preconditions.checkArgument(sectionY >= dimensionType.minSectionY() && sectionY <= dimensionType.maxSectionY());
    }

    /**
     * Rejects positions outside local X/Z bounds or this dimension's absolute Y
     * range. Valid X and Z are zero through fifteen, and invalid coordinates
     * cause {@link IllegalArgumentException}.
     */
    public void checkXYZ(int x, int y, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        checkY(y);
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    /**
     * Ticks block entities, due scheduled updates, and random block updates in
     * that order. The caller must own the chunk's required mutation context; an
     * exception stops later phases and propagates.
     */
    public void tick(long currentTick, Dimension dimension) {
        blockEntities.values().forEach(blockEntity -> ((BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent()).tick(currentTick));
        tickScheduledUpdates(currentTick, dimension);
        tickRandomUpdates(dimension);
    }

    /**
     * Advances the chunk autosave counter and requests a write when the
     * configured cycle is reached. Submission resets the counter; the returned
     * storage future is ignored, so this method does not report persistence
     * completion or failure.
     */
    public void checkAutoSave(WorldStorage worldStorage) {
        autoSaveTimer++;
        if (autoSaveTimer >= AllayServer.getSettings().storageSettings().chunkAutoSaveCycle()) {
            // Submission precedes the counter reset, but persistence completion does
            // not. An asynchronous failure cannot restore this cycle's elapsed count.
            worldStorage.writeChunk(safeChunk);
            autoSaveTimer = 0;
        }
    }

    /**
     * Executes updates whose absolute deadline has reached the current world
     * tick. Due entries are collected and removed before callbacks, allowing
     * callbacks to schedule replacement work without the previous entries
     * remaining pending. The current block state is looked up at execution time
     * rather than restored from persisted metadata. Cancelling the
     * primary-layer event also skips the liquid-layer update, and exceptions do
     * not restore removed entries.
     */
    protected void tickScheduledUpdates(long currentTick, Dimension dimension) {
        // Removing due entries before invoking callbacks lets rescheduling
        // create fresh work, but cancellation or callback failure does not
        // restore the consumed deadline.
        List<ScheduledUpdateInfo> positions = new ArrayList<>(scheduledUpdates.size());
        for (var entry : scheduledUpdates.entrySet()) {
            if (entry.getValue().getTime() <= currentTick) {
                positions.add(entry.getValue());
                scheduledUpdates.remove(entry.getKey());
            }
        }

        positions.forEach(info -> {
            var pos = info.getPos();
            // Scheduled descriptors retain dimension positions; masking
            // recovers local horizontal coordinates.
            var blockState = getBlockState(pos.x() & 15, pos.y(), pos.z() & 15);

            var block = new Block(blockState, new Position3i(pos, dimension));
            if (!new BlockScheduleUpdateEvent(block).call()) {
                return;
            }

            blockState.getBehavior().onScheduledUpdate(block);

            // Also update liquid on layer 1 if present, so that waterlogged blocks
            // (e.g. sea pickle, kelp) properly trigger liquid flow.
            var layer1State = getBlockState(pos.x() & 15, pos.y(), pos.z() & 15, 1);
            if (layer1State.getBehavior() instanceof BlockLiquidBehavior) {
                var layer1Block = new Block(layer1State, new Position3i(pos, dimension), 1);
                if (new BlockScheduleUpdateEvent(layer1Block).call()) {
                    layer1State.getBehavior().onScheduledUpdate(layer1Block);
                }
            }
        });
    }

    /**
     * Samples primary-layer blocks for random updates according to the world
     * game rule. Nonpositive speed disables this phase; sections classified as
     * air and palettes with no eligible behavior are skipped before sampling.
     * Each remaining section receives three attempts per configured speed unit,
     * and only sampled eligible blocks dispatch events. The current air
     * classification can also skip uniform non-air sections, so eligibility is
     * limited by that implementation behavior.
     */
    protected void tickRandomUpdates(Dimension dimension) {
        int randomTickSpeed = dimension.getWorld().getWorldData().getGameRuleValue(GameRule.RANDOM_TICK_SPEED);
        if (randomTickSpeed <= 0) {
            return;
        }

        for (var section : sections) {
            if (section.isAirSection()) {
                continue;
            }
            // Palette-level rejection avoids position sampling when every registered
            // entry lacks random-update behavior; individual sampled states still need
            // their own eligibility check.
            if (section.blockLayers()[0].allEntriesMatch(blockState -> !blockState.getBehavior().canRandomUpdate())) {
                continue;
            }

            int sectionY = section.sectionY();
            // Each speed unit produces three candidate positions per
            // participating section.
            for (int i = 0; i < randomTickSpeed * 3; i++) {
                int lcg = nextUpdateLCG();
                // Separate four-bit samples select section-local coordinates on
                // all three axes.
                int localX = lcg & 0x0f;
                int localZ = lcg >>> 8 & 0x0f;
                int localY = lcg >>> 16 & 0x0f;
                // TODO: Benchmark an eligibility bitset before replacing palette
                // lookups, including the cost of keeping it synchronized with mutations.
                // Section origins restore absolute height before event
                // coordinates restore the chunk origin.
                var blockState = getBlockState(localX, sectionY * 16 + localY, localZ, 0);
                if (blockState.getBehavior().canRandomUpdate()) {
                    var block = new Block(blockState, new Position3i(localX + (this.x << 4), localY + (sectionY << 4), localZ + (this.z << 4), dimension), 0);
                    if (new BlockRandomUpdateEvent(block).call()) {
                        blockState.getBehavior().onRandomUpdate(block);
                    }
                }
            }
        }
    }

    /**
     * Advances and returns this chunk's integer random-selection state using
     * multiplication and XOR. Java integer overflow is part of the sequence,
     * and callers must serialize access to this mutable state.
     */
    public int nextUpdateLCG() {
        return (this.updateLCG = (this.updateLCG * 3) ^ LCG_CONSTANT);
    }

    /**
     * Connects the chunk to dimension lighting and entity loading, then
     * publishes the loaded flag. Lighting registration precedes installing a
     * primary-layer mutation callback, which converts local horizontal
     * positions to dimension coordinates. Entity loading is requested
     * asynchronously and is not awaited before the flag becomes {@code true}. Callers
     * must serialize this lifecycle transition; a thrown hook stops later steps
     * without rolling back earlier registration.
     */
    public void onChunkLoad(Dimension dimension) {
        // Lighting registration precedes change callbacks so later
        // primary-layer writes refer to an attached chunk; entity loading
        // begins afterward and can finish independently.
        ((AllayLightEngine) dimension.getLightEngine()).onChunkLoad(toSafeChunk());
        setBlockChangeCallback((x, y, z, blockState, layer) -> {
            if (layer == 0) {
                // Lighting addresses dimension blocks, so restore the chunk
                // origin to local X/Z.
                ((AllayLightEngine) dimension.getLightEngine()).onBlockChange(x + (this.x << 4), y, z + (this.z << 4), blockState.getBlockStateData().lightEmission(), blockState.getBlockStateData().lightDampening());
            }
        });
        ((AllayEntityManager) dimension.getEntityManager()).onChunkLoad(this.x, this.z);

        loaded = true;
    }

    /**
     * Notifies lighting and entity services of unloading before clearing the
     * mutation callback. The entity manager queues its work, and this method
     * does not await its persistence effects. The loaded flag is not reset
     * here, so retaining an old chunk can still expose {@code true} after detachment.
     * Hook failures propagate and can prevent later cleanup steps; callers must
     * coordinate this transition with chunk use.
     */
    public void onChunkUnload(Dimension dimension) {
        // Detachment is ordered before callback removal; a failing service hook
        // leaves later cleanup unperformed rather than rolling back earlier
        // service notifications.
        ((AllayLightEngine) dimension.getLightEngine()).onChunkUnload(safeChunk);
        ((AllayEntityManager) dimension.getEntityManager()).onChunkUnload(this.x, this.z);
        blockChangeCallback = null;
    }

    /**
     * Returns the shared section for a validated absolute section Y coordinate.
     * The array index subtracts the dimension minimum; callers must establish
     * palette synchronization before operating on the returned section.
     */
    @Override
    public AllayChunkSection getSection(int sectionY) {
        checkSectionY(sectionY);
        return sections[sectionY - this.getDimensionType().minSectionY()];
    }

    /**
     * Returns an unmodifiable list containing this chunk's section references
     * in vertical order. The sections remain shared mutable objects, and this
     * method neither acquires locks nor copies palette data.
     */
    @Override
    public List<ChunkSection> getSections() {
        return List.of(sections);
    }

    /**
     * Returns an unmodifiable set of shared block entities whose absolute block
     * Y belongs to the requested section. The section coordinate is validated
     * before filtering the current block-entity mapping.
     */
    @Override
    public Collection<BlockEntity> getSectionBlockEntities(int sectionY) {
        checkSectionY(sectionY);
        return getBlockEntities().values().stream()
                .filter(blockEntity -> blockEntity.getPosition().y() >> 4 == sectionY)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Replaces the scheduled update at a validated local position with the
     * supplied absolute world tick. {@code scheduledUpdates} uses a chunk-local
     * position hash, while the descriptor retains dimension block coordinates
     * for execution and serialization. For example, local X one in chunk X two
     * is dimension X thirty-three; storing only the local position would lose
     * that chunk origin. Replacing an entry discards its earlier deadline.
     */
    @Override
    public void addScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, long time) {
        checkXYZ(x, y, z);
        var key = HashUtils.hashChunkXYZ(x, y, z);
        // The map key stays chunk-local, while the descriptor stores dimension
        // coordinates for event positions and persistence; combining both
        // prevents losing the chunk origin.
        scheduledUpdates.put(key, new ScheduledUpdateInfo(new org.joml.Vector3i((this.x << 4) + x, y, (this.z << 4) + z), time));
    }

    /**
     * Returns whether a validated local position has an entry in the concurrent
     * schedule map. This is a momentary observation and does not reserve or
     * prevent execution of that update.
     */
    @Override
    public boolean hasScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z) {
        checkXYZ(x, y, z);
        var scheduledUpdateInfo = scheduledUpdates.get(HashUtils.hashChunkXYZ(x, y, z));
        return scheduledUpdateInfo != null;
    }

    /**
     * Returns the selected block layer at local X/Z and absolute Y.
     * Out-of-height reads return air before horizontal validation; valid
     * heights require local X/Z and a valid layer index.
     */
    @Override
    public BlockState getBlockState(int x, int y, int z, int layer) {
        if (y < dimensionType.getMinHeight() || y > dimensionType.getMaxHeight()) {
            return BlockTypes.AIR.getDefaultState();
        }

        checkXZ(x, z);
        // Shifting selects absolute section Y; masking supplies its local block
        // height.
        return this.getSection(y >> 4).getBlockState(x, y & 0xf, z, layer);
    }

    /**
     * Replaces the selected palette entry before updating dependent chunk
     * state. Primary-layer changes mark the column height dirty, then the
     * installed callback runs, and viewer notification is queued only when
     * requested and the loaded flag is {@code true}. These steps are not rolled back if
     * a callback or notification-layer check throws. Callers must validate the
     * layer and coordinate shared access, including dirty flags spanning
     * independently locked sections.
     */
    @Override
    public void setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send) {
        checkXYZ(x, y, z);
        // The section coordinate retains the sign while masking produces local
        // Y.
        this.getSection(y >> 4).setBlockState(x, y & 0xf, z, blockState, layer);

        if (layer == 0) {
            // Every primary-layer replacement invalidates the column cache, including
            // removals; deferring the scan avoids computing a height until it is needed.
            heightMapDirtyFlags.set(x, z, true);
        }

        // The palette and dirty flag already changed before this callback; an
        // exception propagates without rollback and prevents the later viewer
        // notification from being queued.
        if (blockChangeCallback != null) {
            blockChangeCallback.onBlockChange(x, y, z, blockState, layer);
        }

        if (send && loaded) {
            var update = new WorldViewer.BlockUpdate((this.x << 4) + x, y, (this.z << 4) + z, blockState);
            switch (layer) {
                case 0 -> blockUpdates.offer(update);
                case 1 -> extraBlockUpdates.offer(update);
                default -> throw new IllegalArgumentException("Unsupported layer: " + layer);
            }
        }
    }

    /**
     * Returns the cached height for a validated local column, recalculating it
     * when dirty. Recalculation scans current primary-layer data, writes the
     * cache, and clears the dirty flag before returning. An empty or skipped
     * column uses the dimension minimum; the section air-classification
     * limitation also affects this scan. The sequence is unsynchronized here,
     * so callers must coordinate both palette access and height-cache mutation
     * rather than treating this as a pure read.
     */
    @Override
    public short getHeight(int x, int z) {
        checkXZ(x, z);
        short height;
        if (heightMapDirtyFlags.get(x, z)) {
            // Publishing the recomputed height before clearing its dirty flag
            // makes the cache current for this scan; concurrent writers still
            // require external synchronization around both steps.
            height = (short) calculateHeight(x, z);
            setHeightUnsafe(HeightMap.computeIndex(x, z), height);
            heightMapDirtyFlags.set(x, z, false);
            return height;
        } else {
            return getHeightUnsafe(HeightMap.computeIndex(x, z));
        }
    }

    /**
     * Returns the cached height at the supplied X-major column index.
     */
    protected short getHeightUnsafe(int index) {
        return this.heightMap.get(index);
    }

    /**
     * Sets a cached column height after validating local X/Z and dimension Y
     * bounds. This testing hook does not clear an existing dirty flag, so a
     * later height lookup may overwrite the assigned cache value.
     */
    @VisibleForTesting
    public void setHeight(int x, int z, short height) {
        checkXYZ(x, height, z);
        setHeightUnsafe(HeightMap.computeIndex(x, z), height);
    }

    /**
     * Stores the cached height at the supplied X-major column index.
     */
    protected void setHeightUnsafe(int index, short height) {
        this.heightMap.set(index, height);
    }

    /**
     * Refreshes every dirty column and returns the retained height map used by
     * storage codecs. Each refreshed height is stored before its dirty flag is
     * cleared, while clean columns retain their existing cached values. The
     * returned map is shared rather than copied and can change after return.
     * Callers must coordinate palette and cache access for the whole scan; this
     * method establishes no internal lock or snapshot boundary.
     */
    public HeightMap calculateAndGetHeightMap() {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                if (heightMapDirtyFlags.get(x, z)) {
                    // Storage receives the same cache object after dirty
                    // columns are refreshed; clearing flags follows each write
                    // and does not create a snapshot against concurrent
                    // mutations.
                    setHeightUnsafe(HeightMap.computeIndex(x, z), (short) calculateHeight(x, z));
                    heightMapDirtyFlags.set(x, z, false);
                }
            }
        }

        return heightMap;
    }

    /**
     * Finds the highest primary-layer non-air block in a valid local column.
     * Sections are visited from the dimension maximum downward, and positions
     * within a participating section are scanned top down so the first match
     * wins. Sections reporting air are skipped, including the current
     * implementation's uniform non-air false positives. If no participating
     * section supplies a block, the dimension minimum is returned as the
     * empty-column convention.
     */
    protected int calculateHeight(int x, int z) {
        // The dimension minimum represents an empty column, so the cache has no
        // separate below-world sentinel when every participating section contains air.
        var newHeight = dimensionType.getMinHeight();
        for (int sectionY = dimensionType.maxSectionY(); sectionY >= dimensionType.minSectionY(); sectionY--) {
            var section = getSection(sectionY);
            if (section.isAirSection()) {
                continue;
            }

            boolean found = false;
            for (int localY = 15; localY >= 0; localY--) {
                if (section.getBlockState(x, localY, z, 0).getBlockType() != BlockTypes.AIR) {
                    // Restore the section origin so cached heights use absolute
                    // block Y.
                    newHeight = localY + (sectionY << 4);
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        return newHeight;
    }

    /**
     * Drains currently available updates into a new collection in queue order.
     * Concurrent producers can extend the drain, and removed entries are not
     * restored if later delivery fails.
     */
    protected Collection<WorldViewer.BlockUpdate> collectUpdates(Queue<WorldViewer.BlockUpdate> queue) {
        var list = new ArrayList<WorldViewer.BlockUpdate>();
        WorldViewer.BlockUpdate update;
        // Polling transfers ownership from the pending queue into this delivery
        // batch; subsequent viewer failures do not put those updates back into
        // the queue.
        while ((update = queue.poll()) != null) {
            list.add(update);
        }

        return list;
    }

    /**
     * Replaces the biome at a validated local position using the containing
     * section palette. Callers must synchronize shared biome access; this
     * mutation does not itself queue a biome packet.
     */
    @Override
    public void setBiome(int x, int y, int z, BiomeType biomeType) {
        checkXYZ(x, y, z);
        // The section coordinate retains the sign while masking produces local
        // Y.
        this.getSection(y >> 4).setBiomeType(x, y & 0xf, z, biomeType);
    }

    /**
     * Returns the biome at local X/Z and absolute Y, or plains when Y is
     * outside dimension bounds. Valid heights require local horizontal
     * coordinates and compatible synchronization with biome writers.
     */
    @Override
    public BiomeType getBiome(int x, int y, int z) {
        if (y < getDimensionType().getMinHeight() || y > getDimensionType().getMaxHeight()) {
            return BiomeTypes.PLAINS;
        }

        AllayUnsafeChunk.checkXZ(x, z);
        // Shifting selects absolute section Y; masking supplies its local biome
        // height.
        return this.getSection(y >> 4).getBiomeType(x, y & 0xf, z);
    }

    /**
     * Stores the supplied non-{@code null} block entity under its masked horizontal
     * position and absolute Y. No chunk-ownership validation is performed, so
     * callers must ensure the entity belongs to this chunk.
     */
    @Override
    public void addBlockEntity(BlockEntity blockEntity) {
        Preconditions.checkNotNull(blockEntity);
        var pos = blockEntity.getPosition();
        // Masking retains local horizontal coordinates but discards chunk
        // identity, so callers must verify ownership before inserting entities
        // from dimension positions.
        var key = HashUtils.hashChunkXYZ(pos.x() & 15, pos.y(), pos.z() & 15);
        blockEntities.put(key, blockEntity);
    }

    /**
     * Removes and returns the shared block entity at a validated local
     * position, or {@code null} when absent. Removing the mapping does not invoke
     * entity lifecycle or persistence hooks.
     */
    @Override
    public BlockEntity removeBlockEntity(int x, int y, int z) {
        checkXYZ(x, y, z);
        var key = HashUtils.hashChunkXYZ(x, y, z);
        return blockEntities.remove(key);
    }

    /**
     * Returns the shared block entity at a validated local position, or {@code null}
     * when absent. Concurrent map lookup does not make the returned entity's
     * own operations thread-safe.
     */
    @Override
    public BlockEntity getBlockEntity(int x, int y, int z) {
        checkXYZ(x, y, z);
        return blockEntities.get(HashUtils.hashChunkXYZ(x, y, z));
    }

    /**
     * Returns an unmodifiable live view of the concurrent block-entity position
     * map. Values remain shared entities, and later insertions or removals can
     * become visible through this view.
     */
    @Override
    public Map<Integer, BlockEntity> getBlockEntities() {
        return Collections.unmodifiableMap(blockEntities);
    }

    /**
     * Returns an unmodifiable live view of point-of-interest types keyed by
     * local position hashes. Later position-based changes remain visible, while
     * callers cannot mutate the mapping through this view.
     */
    @Override
    public Map<Integer, PoiType> getPoiEntries() {
        return Collections.unmodifiableMap(poiEntries);
    }

    /**
     * Replaces the point-of-interest type at a validated local position in the
     * concurrent map. This mapping change does not alter the block state or
     * send a viewer update.
     */
    @Override
    public void addPoi(int x, int y, int z, PoiType type) {
        checkXYZ(x, y, z);
        poiEntries.put(HashUtils.hashChunkXYZ(x, y, z), type);
    }

    /**
     * Removes the point-of-interest entry at a validated local position if
     * present. Missing entries have no effect, and this mapping operation does
     * not alter block palettes or viewer notifications.
     */
    @Override
    public void removePoi(int x, int y, int z) {
        checkXYZ(x, y, z);
        poiEntries.remove(HashUtils.hashChunkXYZ(x, y, z));
    }

    /**
     * Returns the point-of-interest type at a validated local position, or {@code null}
     * when absent. The concurrent lookup observes the mapping only and does not
     * load or inspect neighboring chunks.
     */
    @Override
    public PoiType getPoi(int x, int y, int z) {
        checkXYZ(x, y, z);
        return poiEntries.get(HashUtils.hashChunkXYZ(x, y, z));
    }

    /**
     * Returns the single cached safe wrapper sharing this chunk's mutable data.
     * The wrapper's lock domain is reused, but returning it does not acquire
     * locks for direct unsafe access.
     */
    @Override
    public Chunk toSafeChunk() {
        return safeChunk;
    }

    /**
     * Drains both block-update queues and delivers the resulting batches to
     * attached actual players. With no loaders, pending updates are discarded
     * because there is no current recipient. A loader that is only a world
     * viewer does not receive this path's player-specific notification. Queues
     * are drained before callbacks, so delivery failures do not restore
     * updates, and concurrent additions may remain for a later batch.
     */
    public void sendBlockUpdates() {
        if (chunkLoaders.isEmpty()) {
            // No loader currently retains a delivery target, so both
            // notification queues are discarded instead of accumulating updates
            // for a future viewer.
            blockUpdates.clear();
            extraBlockUpdates.clear();
            return;
        }

        // Both queues are drained before any player callback; one delivery
        // failure can interrupt later recipients without restoring either batch
        // to pending storage.
        var collectedBlockUpdates = collectUpdates(blockUpdates);
        var collectedExtraBlockUpdates = collectUpdates(extraBlockUpdates);
        chunkLoaders.forEach(loader -> {
            if (loader instanceof EntityPlayer player && player.isActualPlayer()) {
                player.getController().viewBlockUpdates(toSafeChunk(), collectedBlockUpdates, collectedExtraBlockUpdates);
            }
        });
    }

    /**
     * Runs queued tasks on the calling thread while loaders remain attached at
     * the initial check. If no loaders exist, the queue is cleared instead, so
     * the interface's next-tick execution promise is limited by this
     * implementation. Tasks are removed before invocation, and a thrown task
     * stops the current drain without restoring that task. Work added during
     * draining may also execute in the same pass.
     */
    public void performChunkTasks() {
        if (chunkLoaders.isEmpty()) {
            // The implementation discards deferred work when no loaders remain;
            // callers cannot rely on queued callbacks to perform mandatory
            // cleanup after loader removal.
            chunkTaskQueue.clear();
            return;
        }

        if (!chunkTaskQueue.isEmpty()) {
            Runnable task;
            // Polling removes each task before invocation, so a thrown task is
            // consumed while later queued tasks remain available for another
            // processing pass.
            while ((task = chunkTaskQueue.poll()) != null) {
                task.run();
            }
        }
    }

    /**
     * Appends a task to the concurrent queue for later chunk processing.
     * Processing may discard it when no loaders remain, and submission does not
     * provide a completion or failure signal.
     */
    @Override
    public void addChunkTask(Runnable task) {
        chunkTaskQueue.add(task);
    }

    /**
     * Returns an unmodifiable live view of the concurrent loader membership
     * set. Individual loader objects remain shared, and concurrent changes can
     * be visible while callers traverse the view.
     */
    @Override
    public Set<ChunkLoader> getChunkLoaders() {
        return Collections.unmodifiableSet(chunkLoaders);
    }

    /**
     * Adds the supplied loader to this chunk's concurrent membership set.
     */
    @Override
    public void addChunkLoader(ChunkLoader chunkLoader) {
        chunkLoaders.add(chunkLoader);
    }

    /**
     * Removes the supplied loader from this chunk's concurrent membership set.
     */
    @Override
    public void removeChunkLoader(ChunkLoader chunkLoader) {
        chunkLoaders.remove(chunkLoader);
    }
}
