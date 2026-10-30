package org.allaymc.server.world.light;

import com.google.common.base.Preconditions;
import io.netty.util.internal.PlatformDependent;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.chunk.UnsafeChunk;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.light.LightEngine;
import org.allaymc.server.AllayServer;
import org.allaymc.server.datastruct.ChunkSectionNibbleArray;
import org.allaymc.server.datastruct.collections.queue.BlockingQueueWrapper;
import org.allaymc.server.world.chunk.AllayChunkSection;
import org.allaymc.server.world.chunk.HeightMap;
import org.jctools.maps.NonBlockingHashMapLong;
import org.jctools.maps.NonBlockingHashSet;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/**
 * {@code AllayLightEngine} maintains dimension illumination from chunk
 * snapshots and queued block changes. One worker reads chunk material
 * properties and schedules separate block-light and skylight propagation
 * workers. A chunk waits for its surrounding three-by-three region before full
 * light seeding, allowing propagation to consult neighboring material data.
 * Backlog limiting requeues full-chunk seeding work; its completion callback
 * means that source work was submitted, rather than that the other propagation
 * queues have drained.
 *
 * <p>
 * Skylight uses a source-height map for fully exposed positions and stores
 * only the border propagation below those heights. Effective gameplay light
 * applies current time and weather attenuation to the sky contribution before
 * comparing it with block light. Concurrent maps organize channel
 * availability, but mutable nibble arrays and last-chunk accessors do not
 * establish an atomic read snapshot. Chunk removal can race with propagation,
 * whose own recovery suppresses {@code NullPointerException}. Shutdown clears a
 * running flag without waking or joining workers, and uncaught task failures
 * can terminate an individual worker.
 *
 * @author daoge_cmd
 */
public class AllayLightEngine implements LightEngine {

    /**
     * Dimension identity and vertical section bounds.
     */
    protected final DimensionType dimensionType;
    /**
     * World name included in worker diagnostics.
     */
    protected final String worldName;
    /**
     * {@code isRunning} admits new external updates and future worker drains;
     * shutdown clears it without interrupting tasks already executing inside a
     * drain.
     */
    protected final AtomicBoolean isRunning;
    /**
     * Supplier of current world time in game ticks.
     */
    protected final Supplier<Integer> timeSupplier;
    /**
     * Supplier of current world weather state.
     */
    protected final Supplier<Weather> weatherSupplier;
    /**
     * {@code maxUpdateCount} limits full-chunk source submission using the
     * total queued task count; exceeding it requeues that chunk's calculation
     * attempt.
     */
    protected final int maxUpdateCount;
    /**
     * Thread policy for calculation worker creation.
     */
    protected final boolean useVirtualThread;
    // chunkAndBlockUpdateQueue publishes source decisions to blockLightUpdateQueue
    // and, when present, skyLightUpdateQueue. Each worker consumes its own queue;
    // submission does not wait for downstream propagation to finish.
    /**
     * Queue for material snapshots and block change decisions.
     */
    protected final BlockingQueueWrapper<Runnable> chunkAndBlockUpdateQueue;
    /**
     * Queue for block illumination propagation tasks.
     */
    protected final BlockingQueueWrapper<Runnable> blockLightUpdateQueue;
    /**
     * {@code chunks} publishes registered chunk hashes after channel
     * allocation; its membership check does not lock the arrays against
     * concurrent removal.
     */
    protected final Set<Long> chunks;
    /**
     * {@code awaitingLightCalculationChunks} records chunks waiting for
     * neighbor readiness; the chunk worker suppresses their incremental
     * propagation until a full source scan is submitted.
     */
    protected final Set<Long> awaitingLightCalculationChunks;
    // lightDampening, lightEmission, and blockLight share section indices relative
    // to dimensionType.getMinHeight(). addChunk allocates these channels before
    // publishing chunks membership; unload removes them without a shared read lock.
    /**
     * Chunk-section arrays of block attenuation values.
     */
    protected final NonBlockingHashMapLong<ChunkSectionNibbleArray[]> lightDampening;
    /**
     * Chunk-section arrays of local block emission values.
     */
    protected final NonBlockingHashMapLong<ChunkSectionNibbleArray[]> lightEmission;
    /**
     * Chunk-section arrays of propagated block illumination.
     */
    protected final NonBlockingHashMapLong<ChunkSectionNibbleArray[]> blockLight;
    /**
     * Propagator consumed by the block-light worker.
     */
    protected final LightPropagator blockLightPropagator;
    /**
     * {@code lightDataAccessor} caches material and border-light arrays for
     * chunk work and queued sky callbacks; those shared caches have no
     * synchronization or removal invalidation.
     */
    protected final CachedLightDataAccessor lightDataAccessor;

    // skyLightUpdateQueue, lightHeightMap, lightHeightMapAccessor, skyLightInBorder,
    // and skyLightPropagator exist only when dimensionType.hasSkyLight() is true.
    // Column heights supply exposed skylight directly; border arrays retain the
    // propagated contribution needed below those source heights.
    /**
     * Optional queue for skylight propagation tasks.
     */
    protected BlockingQueueWrapper<Runnable> skyLightUpdateQueue;
    /**
     * First exposed skylight height for each chunk column.
     */
    protected NonBlockingHashMapLong<HeightMap> lightHeightMap;
    /**
     * Last-chunk cache for skylight source heights.
     */
    protected CachedChunkMapAccessor<HeightMap> lightHeightMapAccessor;
    /**
     * Propagated skylight below analytically exposed column heights.
     */
    protected NonBlockingHashMapLong<ChunkSectionNibbleArray[]> skyLightInBorder;
    /**
     * Optional propagator consumed by the skylight worker.
     */
    protected LightPropagator skyLightPropagator;

    /**
     * Creates an unstarted engine using a dimension's bounds and live world
     * suppliers. The configured update limit controls later source submission,
     * and callers invoke {@code startTick} separately to begin queue
     * consumers.
     */
    public AllayLightEngine(Dimension dimension, boolean useVirtualThread) {
        this(dimension.getDimensionType(), dimension.getWorld().getName(), dimension.getWorld().getWorldData()::getTimeOfDay, dimension.getWorld()::getWeather, AllayServer.getSettings().worldSettings().maxLightUpdateCountPerDimension(), useVirtualThread);
    }

    /**
     * Creates an unstarted engine with explicit environment suppliers for
     * synchronous tests. The update limit uses {@code Integer.MAX_VALUE} and
     * worker selection uses platform threads, avoiding ordinary backlog
     * throttling while tests drain queues themselves.
     */
    @VisibleForTesting
    public AllayLightEngine(DimensionType dimensionType, String worldName, Supplier<Integer> timeSupplier, Supplier<Weather> weatherSupplier) {
        this(dimensionType, worldName, timeSupplier, weatherSupplier, Integer.MAX_VALUE, false);
    }

    /**
     * Creates empty channel maps, propagation state, and queues for the
     * supplied dimension. Sky-specific state is allocated only when skylight
     * exists; suppliers and maps remain shared, and this constructor does not
     * start workers or validate the update limit.
     */
    protected AllayLightEngine(DimensionType dimensionType, String worldName, Supplier<Integer> timeSupplier, Supplier<Weather> weatherSupplier, int maxUpdateCount, boolean useVirtualThread) {
        this.dimensionType = dimensionType;
        this.worldName = worldName;
        this.isRunning = new AtomicBoolean(true);
        this.timeSupplier = timeSupplier;
        this.weatherSupplier = weatherSupplier;
        this.maxUpdateCount = maxUpdateCount;
        this.useVirtualThread = useVirtualThread;
        this.chunkAndBlockUpdateQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
        this.blockLightUpdateQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
        this.chunks = new NonBlockingHashSet<>();
        // The chunk decision queue owns waiting membership. Propagation workers
        // receive source tasks instead of reading this set, so ordinary set
        // storage suffices when lifecycle consumers remain serialized.
        this.awaitingLightCalculationChunks = new LongOpenHashSet();
        this.lightDampening = new NonBlockingHashMapLong<>();
        this.lightEmission = new NonBlockingHashMapLong<>();
        this.blockLight = new NonBlockingHashMapLong<>();
        this.blockLightPropagator = new LightPropagator(new CachedLightDataAccessor(dimensionType, blockLight, lightDampening, lightEmission));
        if (dimensionType.hasSkyLight()) {
            this.skyLightUpdateQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
            this.lightHeightMap = new NonBlockingHashMapLong<>();
            this.lightHeightMapAccessor = new CachedChunkMapAccessor<>(lightHeightMap);
            this.skyLightInBorder = new NonBlockingHashMapLong<>();
            this.skyLightPropagator = new LightPropagator(new CachedLightDataAccessor(dimensionType, skyLightInBorder, lightDampening, lightEmission));
        }
        // The decision accessor needs the optional sky border map reference.
        // Construct it after sky state; dimensions without skylight leave that
        // channel absent and must avoid its sky-read operations.
        this.lightDataAccessor = new CachedLightDataAccessor(dimensionType, skyLightInBorder, lightDampening, lightEmission);
    }

    /**
     * Returns the integer skylight attenuation derived from time and weather.
     * Rain reduces one daylight factor and thunder reduces a second, while the
     * celestial angle supplies a clamped day-night factor through the
     * approximate cosine helper. The final floating-point result is truncated
     * to an integer.
     *
     * <p>
     * The two weather factors multiply the daylight contribution before its
     * complement is scaled by eleven. This makes weather darken the daytime
     * result while the night extreme already reaches the maximum reduction, as
     * exercised by the light-engine regression samples.
     */
    @VisibleForTesting
    public static int calculateSkylightReduction(long time, Weather weather) {
        // Weather scales daylight before the eleven-level complement is truncated;
        // night already reaches the upper reduction bound in the sampled cycle.
        double d = 1.0 - ((weather != Weather.CLEAR ? 1 : 0) * 5.0) / 16.0;
        double e = 1.0 - ((weather == Weather.THUNDER ? 1 : 0) * 5.0) / 16.0;
        double f = 0.5 + 2.0 * Math.clamp(MathUtils.fastCos(calculateCelestialAngle(time) * 6.2831855), -0.25, 0.25);
        return (int) ((1.0 - f * d * e) * 11.0);
    }

    /**
     * Returns a smoothed celestial cycle fraction from world time in ticks.
     * The calculation first wraps a {@code 24000}-tick day after a
     * quarter-cycle shift, so repeated days use the same underlying phase. A
     * cosine curve then contributes one part to two parts of that wrapped
     * linear phase.
     *
     * <p>
     * This weighted result drives environmental skylight reduction rather than
     * changing stored propagation data. The arithmetic uses floating-point
     * conversion of the supplied time, so callers should not assume exact
     * integer tick distinctions remain visible at arbitrarily large elapsed
     * times.
     */
    @VisibleForTesting
    public static double calculateCelestialAngle(long time) {
        // Wrap ticks into one day after shifting the celestial origin by a
        // quarter cycle, then smooth that phase without changing elapsed time.
        double d = frac(((double) time) / 24000.0 - 0.25);
        double e = 0.5 - Math.cos(d * Math.PI) / 2.0;
        return (d * 2.0 + e) / 3.0F;
    }

    /**
     * Returns the supplied value minus its integer floor for cycle wrapping.
     * Callers use finite values within the floor helper's representable range;
     * this helper does not validate exceptional floating-point inputs.
     */
    protected static double frac(double value) {
        return value - (double) lfloor(value);
    }

    /**
     * Returns the mathematical floor for finite values within the
     * representable long range. The cast truncates toward zero, so negative
     * fractional values need one further decrement; exceptional or
     * out-of-range inputs are not validated.
     */
    protected static long lfloor(double value) {
        long l = (long) value;
        return value < (double) l ? l - 1L : l;
    }

    /**
     * Packs emission in the low nibble and dampening in the high nibble.
     * Callers supply values from {@code 0} through {@code 15}; the checks
     * reject values reaching {@code 16} but do not reject negative inputs.
     */
    protected static byte packLightData(@Range(from = 0, to = 16) int lightEmission, @Range(from = 0, to = 16) int lightDampening) {
        Preconditions.checkArgument(lightEmission < 16, "lightEmission must be less than 16");
        Preconditions.checkArgument(lightDampening < 16, "lightDampening must be less than 16");
        // Keep emission in the low nibble and dampening in the high nibble
        // so queued block decisions retain both material properties in one byte.
        return (byte) (lightEmission | (lightDampening << 4));
    }

    /**
     * Returns the unsigned emission nibble from the packed light byte.
     */
    protected static byte unpackLightEmission(byte data) {
        return (byte) (data & 0x0F);
    }

    /**
     * Returns the unsigned dampening nibble from the packed light byte.
     */
    protected static byte unpackLightDampening(byte data) {
        return (byte) ((data >> 4) & 0x0F);
    }

    /**
     * Starts separate workers for chunk decisions, block propagation, and
     * optional sky propagation. Callers invoke this operation once because no
     * guard prevents multiple workers consuming the same queues and sharing
     * mutable propagation state. Worker names include the world and dimension
     * identity.
     *
     * <p>
     * The workers progress independently, so starting them does not wait for
     * existing queued updates to complete. Each worker stops checking its
     * running flag while it drains available tasks; shutdown and task failures
     * follow the individual worker loop rather than a joined lifecycle
     * barrier.
     */
    public void startTick() {
        var dimensionName = worldName + ":" + dimensionType.getIdentifier();
        startCalculatingThread("Light Calculating Thread (Chunk & Block) #" + dimensionName, chunkAndBlockUpdateQueue);
        startCalculatingThread("Light Calculating Thread (Block Light) #" + dimensionName, blockLightUpdateQueue);
        if (dimensionType.hasSkyLight()) {
            startCalculatingThread("Light Calculating Thread (Sky Light) #" + dimensionName, skyLightUpdateQueue);
        }
    }

    /**
     * Clears the flag used by update admission and worker loop checks. The
     * request does not wake blocked consumers, interrupt an active drain, or
     * wait for threads and their already queued work to finish.
     */
    public void shutdown() {
        this.isRunning.set(false);
    }

    /**
     * Starts one named consumer using the configured thread policy. The worker
     * repeatedly drains its assigned queue while {@code isRunning} remains
     * set, and the queue's timed wait lets an idle drain eventually return for
     * another flag check.
     *
     * <p>
     * Shutdown does not interrupt the drain itself, so continuously arriving
     * tasks can delay observation of the cleared flag. An uncaught task or
     * polling failure exits this thread without restarting it or updating the
     * engine-wide running flag; callers cannot infer worker liveness from that
     * flag alone.
     */
    protected void startCalculatingThread(String name, BlockingQueueWrapper<Runnable> queue) {
        // Each worker owns one queue drain. Check the running flag between
        // drains rather than interrupting task bodies, so shutdown cannot assume
        // immediate cancellation of already admitted propagation work.
        (useVirtualThread ? Thread.ofVirtual() : Thread.ofPlatform()).name(name).start(() -> {
            while (isRunning.get()) {
                this.handleUpdateIn(queue);
            }
        });
    }

    /**
     * Runs queued tasks until a timed poll returns no item after one second.
     * The running flag is not rechecked within this drain, and task or
     * interruption failures propagate to the worker thread.
     */
    protected void handleUpdateIn(BlockingQueueWrapper<Runnable> queue) {
        Runnable runnable;
        while ((runnable = queue.tryPoll(1, TimeUnit.SECONDS)) != null) {
            runnable.run();
        }
    }

    /**
     * Runs currently available tasks without waiting for new queue permits.
     * Callers own the queue's consumer context; task failures propagate and
     * later tasks remain queued, and this helper does not clear interrupt
     * status.
     */
    protected void handleUpdateUninterruptibleIn(BlockingQueueWrapper<Runnable> queue) {
        Runnable runnable;
        while ((runnable = queue.pollNow()) != null) {
            runnable.run();
        }
    }

    /**
     * Drains chunk decisions, block propagation, and optional sky propagation
     * synchronously in that order. Tests use an unstarted engine so these
     * drains do not race worker consumers; backlog retries can otherwise keep
     * the first drain busy before downstream queues are reached.
     */
    @VisibleForTesting
    public void handleUpdateInAllQueues() {
        handleUpdateUninterruptibleIn(chunkAndBlockUpdateQueue);
        handleUpdateUninterruptibleIn(blockLightUpdateQueue);
        if (dimensionType.hasSkyLight()) {
            handleUpdateUninterruptibleIn(skyLightUpdateQueue);
        }
    }

    /**
     * Copies a chunk's first-layer material properties into engine-owned
     * channel arrays. The caller holds the chunk read operation and runs in
     * the chunk worker context; skylight height records the first block above
     * the highest dampening block in each column.
     *
     * <p>
     * Channel maps are populated before {@code chunks} membership is
     * published, allowing later readiness checks to find allocated material
     * data. This publication order is not a shared read lock: unload can
     * remove maps while another worker reads them, and cached accessors may
     * retain an older array reference.
     */
    protected void addChunk(UnsafeChunk chunk) {
        var chunkLightDampening = createNibbleArrays();
        var chunkLightEmission = createNibbleArrays();
        var chunkLightHeightMap = dimensionType.hasSkyLight() ? new HeightMap((short) dimensionType.getMinHeight()) : null;
        for (int y = dimensionType.getMaxHeight(); y >= dimensionType.getMinHeight(); y--) {
            var section = (AllayChunkSection) chunk.getSection(y >> 4);
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    var blockStateData = section.getBlockState(x, y & 0xf, z, 0).getBlockStateData();
                    var lightDampening = blockStateData.lightDampening();
                    var lightEmission = blockStateData.lightEmission();

                    if (lightDampening != 0) {
                        chunkLightDampening[(y - dimensionType.getMinHeight()) >> 4].set(x, y & 0xf, z, lightDampening);
                        boolean skyLightSourceNotFound = dimensionType.hasSkyLight() && chunkLightHeightMap.get(x, z) == dimensionType.getMinHeight();
                        if (skyLightSourceNotFound) {
                            chunkLightHeightMap.set(x, z, (short) (y + 1));
                        }
                    }

                    if (lightEmission != 0) {
                        chunkLightEmission[(y - dimensionType.getMinHeight()) >> 4].set(x, y & 0xf, z, lightEmission);
                    }
                }
            }
        }

        var hash = HashUtils.hashXZ(chunk.getX(), chunk.getZ());
        lightDampening.put(hash, chunkLightDampening);
        lightEmission.put(hash, chunkLightEmission);
        blockLight.put(hash, createNibbleArrays());
        if (dimensionType.hasSkyLight()) {
            lightHeightMap.put(hash, chunkLightHeightMap);
            skyLightInBorder.put(hash, createNibbleArrays());
        }
        // Publish membership only after all applicable channel maps contain arrays;
        // readiness checks use membership as their indication that source data
        // was allocated, without reserving it against later removal.
        chunks.add(hash);
    }

    /**
     * Submits full-chunk source work or requeues the attempt when backlog
     * exceeds the limit. Retry processing rechecks surrounding chunk
     * membership because load and unload decisions can change readiness while
     * this attempt waits. The chunk worker owns the scan and optional
     * callback.
     *
     * <p>
     * An admitted scan visits material emission and sky columns and publishes
     * downstream propagation tasks. {@code afterCalculated} runs after
     * submission, not after those tasks complete, so clearing a waiting marker
     * permits later incremental updates while prior propagation remains
     * queued. A retry that loses neighbor readiness does not invoke the
     * callback.
     */
    protected void tryCalculateChunkLightAt(int chunkX, int chunkZ, Runnable afterCalculated) {
        if (this.getQueuedUpdateCount() > maxUpdateCount) {
            // Defer full source scans when downstream queues exceed the limit.
            // Retrying in the chunk queue lets propagation consumers reduce their
            // backlog without dropping this chunk's requested scan.
            chunkAndBlockUpdateQueue.offer(() -> {
                // Queue waiting does not reserve the surrounding region. Recheck
                // membership so a removed neighbor prevents a scan from assuming
                // that its material channel data still exists.
                if (canCalculateLightInChunk(chunkX, chunkZ)) {
                    tryCalculateChunkLightAt(chunkX, chunkZ, afterCalculated);
                }
            });
            return;
        }

        for (int worldY = dimensionType.getMaxHeight(); worldY >= dimensionType.getMinHeight(); worldY--) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    final int worldX = (chunkX << 4) + x;
                    final int worldZ = (chunkZ << 4) + z;
                    calculateBlockLightAt(worldX, worldY, worldZ);
                    if (dimensionType.hasSkyLight()) {
                        calculateSkyLightAt(worldX, worldZ);
                    }
                }
            }
        }

        if (afterCalculated != null) {
            afterCalculated.run();
        }
    }

    /**
     * Selects whether one emitting block needs a propagation seed based on
     * neighboring emission. A nonemitting block submits no work. A strictly
     * brighter neighboring emitter suppresses this seed, while the minimum
     * sampled neighboring emission can select a direct assignment when it
     * equals this block's emission.
     *
     * <p>
     * The remaining case queues block propagation with the source's emission
     * value. These comparisons use material emission rather than a completed
     * illumination snapshot, avoiding redundant source submissions according
     * to this heuristic; they do not wait for neighboring propagation or
     * verify every intervening dampening path.
     */
    protected void calculateBlockLightAt(int x, int y, int z) {
        // Emission occupies four bits, so byte capture preserves its range
        // when the source value is retained by a queued propagation task.
        byte lightEmissionValue = (byte) lightDataAccessor.getLightEmission(x, y, z);
        int minNeighborLightEmission = 15;
        if (lightEmissionValue != 0) {
            var ignoreCurrentBlockLight = false;
            for (var blockFace : BlockFace.VALUES) {
                var neighborX = x + blockFace.getOffset().x();
                var neighborZ = z + blockFace.getOffset().z();
                var neighborY = y + blockFace.getOffset().y();
                if (!lightDataAccessor.isYInRange(neighborY)) {
                    continue;
                }

                int neighborLightEmission = lightDataAccessor.getLightEmission(neighborX, neighborY, neighborZ);
                if (neighborLightEmission > lightEmissionValue) {
                    // A brighter material emitter suppresses this source submission
                    // under the neighboring-emission heuristic; propagation itself
                    // will still apply dampening when its queued task runs.
                    ignoreCurrentBlockLight = true;
                    break;
                }

                minNeighborLightEmission = Math.min(minNeighborLightEmission, neighborLightEmission);
            }

            // Matching minimum emission selects direct source storage instead of
            // another propagation seed, reducing repeated work inside groups of
            // equal emitters without waiting for their queued results.
            if (minNeighborLightEmission == lightEmissionValue) {
                setWithoutChunkCheck(blockLight, x, y, z, lightEmissionValue);
                ignoreCurrentBlockLight = true;
            }

            if (!ignoreCurrentBlockLight) {
                blockLightUpdateQueue.offer(() -> blockLightPropagator.setLightAndPropagate(x, y, z, 0, lightEmissionValue));
            }
        }
    }

    /**
     * Queues sky source propagation for a column's exposed-height boundary. A
     * source height above the dimension maximum skips the column, while the
     * highest horizontal neighbor height determines the upper end of source
     * submissions. The caller supplies available height-map data in a skylight
     * dimension.
     *
     * <p>
     * Only the boundary interval is queued because {@code getSkyLight} answers
     * positions at or above their own source height analytically. The
     * neighbor-derived upper bound is not clamped here, so callers must
     * account for source heights that can extend one block above the stored
     * dimension.
     */
    protected void calculateSkyLightAt(int x, int z) {
        var lightHeight = getLightHeight(x, z);
        if (lightHeight > dimensionType.getMaxHeight()) {
            // A source above the dimension ceiling has no stored position to
            // seed in this column; exposed skylight queries are handled analytically.
            return;
        }

        var maxNeighborLightHeight = getMaxNeighborLightHeight(x, z);
        for (int i = lightHeight; i <= maxNeighborLightHeight; i++) {
            final int skyLightSourceY = i;
            skyLightUpdateQueue.offer(() -> skyLightPropagator.setLightAndPropagate(x, skyLightSourceY, z, 0, 15));
        }
    }

    /**
     * Returns the greatest source height among the four horizontal neighbor
     * columns. Callers provide available skylight height data for all four
     * positions; this helper does not load chunks or clamp the selected
     * height.
     */
    protected int getMaxNeighborLightHeight(int x, int z) {
        int max = Integer.MIN_VALUE;
        BlockFace[] horizontalBlockFaces = BlockFace.getHorizontalBlockFaces();
        for (BlockFace face : horizontalBlockFaces) {
            max = Math.max(max, getLightHeight(x + face.getOffset().x(), z + face.getOffset().z()));
        }

        return max;
    }

    /**
     * Returns whether all nine chunk hashes in the surrounding square are
     * registered. This membership test is a readiness observation rather than
     * a reservation, so later removal can invalidate the result.
     */
    protected boolean canCalculateLightInChunk(int chunkX, int chunkZ) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!chunks.contains(HashUtils.hashXZ(chunkX + i, chunkZ + j))) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Creates zero-valued nibble storage for every section in this dimension.
     */
    protected ChunkSectionNibbleArray[] createNibbleArrays() {
        ChunkSectionNibbleArray[] nibbleArrays = new ChunkSectionNibbleArray[dimensionType.chunkSectionCount()];
        for (int i = 0; i < dimensionType.chunkSectionCount(); i++) {
            nibbleArrays[i] = new ChunkSectionNibbleArray();
        }
        return nibbleArrays;
    }

    /**
     * Returns raw skylight using exposed heights and stored border
     * propagation. A dimension without skylight returns zero, while an
     * unregistered chunk returns full sky illumination. Registered positions
     * at or above the source height also return {@code 15} without indexing a
     * border array.
     *
     * <p>
     * Positions below the source height read the cached propagation channel,
     * requiring a valid stored height and available chunk data. The membership
     * test does not reserve maps against removal, and neither this lookup nor
     * the underlying cache establishes a consistent concurrent snapshot.
     */
    @Override
    public int getSkyLight(int x, int y, int z) {
        if (!dimensionType.hasSkyLight()) {
            return 0;
        }

        if (!chunks.contains(HashUtils.hashXZ(x >> 4, z >> 4))) {
            // Unregistered chunks use full raw skylight without reading absent
            // border arrays; this fallback is independent of time or weather.
            return 15;
        }

        var lightHeight = getLightHeight(x, z);
        if (y >= lightHeight) {
            return 15;
        }

        return getWithoutChunkCheck(skyLightInBorder, x, y, z);
    }

    /**
     * Returns the cached first-exposed height for a dimension column. Callers
     * supply a skylight dimension and available chunk height data; the
     * one-entry accessor can retain a map removed from the backing store.
     */
    protected short getLightHeight(int x, int z) {
        return lightHeightMapAccessor.get(x, z).get(x & 15, z & 15);
    }

    /**
     * Stores the first-exposed height through the last-chunk height-map
     * accessor. Callers own the chunk update context and provide available
     * data; this assignment does not queue propagation or invalidate another
     * accessor's cache.
     */
    protected void setLightHeight(int x, int z, short value) {
        lightHeightMapAccessor.get(x, z).set(x & 15, z & 15, value);
    }

    /**
     * Returns stored block illumination or zero for an unregistered chunk.
     * Registered positions require a valid dimension height and available
     * channel arrays; the lookup does not reserve the chunk against concurrent
     * removal.
     */
    @Override
    public int getBlockLight(int x, int y, int z) {
        return get(blockLight, x, y, z, 0);
    }

    /**
     * Returns the larger of attenuated skylight and block illumination when
     * skylight exists. A dimension without sky sources uses block illumination
     * directly. Environmental reduction affects only the sky component,
     * preserving the brightness of local block sources at night.
     *
     * <p>
     * The contributing queries read their current data separately rather than
     * under a common snapshot lock. Callers receive a gameplay estimate from
     * the currently available channels, and queued updates or chunk removal
     * can change those channels while the observations are made.
     */
    @Override
    public int getInternalLight(int x, int y, int z) {
        return dimensionType.hasSkyLight() ? Math.max(getInternalSkyLight(x, y, z), getBlockLight(x, y, z)) : getBlockLight(x, y, z);
    }

    /**
     * Returns raw skylight reduced by the current environmental attenuation
     * and clamped at zero. A dimension without skylight returns zero without
     * consulting the suppliers. This keeps propagation storage independent of
     * time and weather changes while allowing gameplay queries to reflect
     * them.
     *
     * <p>
     * The raw light, time, and weather are read separately, so the result does
     * not promise a jointly captured world snapshot. Callers provide suppliers
     * returning usable values and account for the same chunk availability
     * rules as raw skylight lookup.
     */
    @Override
    public int getInternalSkyLight(int x, int y, int z) {
        return dimensionType.hasSkyLight() ? Math.max(0, getSkyLight(x, y, z) - calculateSkylightReduction(timeSupplier.get(), weatherSupplier.get())) : 0;
    }

    /**
     * Queues a material update and any resulting source propagation while
     * update admission remains active. Callers supply valid dimension
     * coordinates and four-bit emission and dampening values. The queued task
     * discards an update if its chunk is no longer registered, but first
     * preserves changed material data when a registered chunk is still waiting
     * for full calculation.
     *
     * <p>
     * Ready chunks submit block propagation after material updates. A
     * dampening change can also alter the source height, requiring old sky
     * boundary sources in the changed column and its neighbors to be removed
     * before replacement sources are queued. The separate propagation workers
     * consume those requests later without a completion future returned to the
     * caller.
     */
    public void onBlockChange(int x, int y, int z, int le, int ld) {
        if (!isRunning.get()) {
            return;
        }

        // Capture both material values before queueing so later execution sees
        // the emission and dampening from this change together, rather than
        // rereading whichever block state exists at processing time.
        var packedLightData = packLightData(le, ld);
        chunkAndBlockUpdateQueue.offer(() -> {
            var chunkHash = HashUtils.hashXZ(x >> 4, z >> 4);
            if (!chunks.contains(chunkHash)) {
                // Chunk removal can win before this queued change is consumed.
                // Discard the material update instead of recreating channels for
                // a chunk that is no longer registered.
                return;
            }

            var lightDampening = unpackLightDampening(packedLightData);
            var lightEmissionValue = unpackLightEmission(packedLightData);
            int oldLightHeight = dimensionType.hasSkyLight() ? getLightHeight(x, z) : 0;
            var oldBlockDampening = lightDataAccessor.getLightDampening(x, y, z);
            var oldBlockEmission = lightDataAccessor.getLightEmission(x, y, z);

            if (oldBlockDampening != lightDampening) {
                setLightDampening(x, y, z, lightDampening);
            }

            if (oldBlockEmission != lightEmissionValue) {
                lightDataAccessor.setLightEmission(x, y, z, lightEmissionValue);
            }

            // Keep material changes while a full scan waits for neighbors, but
            // suppress incremental seeds until that scan has been submitted so
            // partial initialization does not add duplicate source work.
            var chunkLightCalculated = !awaitingLightCalculationChunks.contains(chunkHash);
            if (!chunkLightCalculated || (oldBlockDampening == lightDampening && oldBlockEmission == lightEmissionValue)) {
                return;
            }

            blockLightUpdateQueue.offer(() -> blockLightPropagator.setLightAndPropagate(x, y, z, getBlockLight(x, y, z), lightEmissionValue));
            if (!dimensionType.hasSkyLight() || oldBlockDampening == lightDampening) {
                // Emission changes affect block illumination only. Sky sources depend
                // on dampening and source heights, so skip their queue when
                // the changed material cannot alter that contribution.
                return;
            }

            var newLightHeight = getLightHeight(x, z);
            if (newLightHeight == oldLightHeight) {
                // The column source boundary stayed fixed, but local attenuation
                // changed. Remove this position's old contribution so surviving
                // neighboring illumination can refill it with the new dampening.
                skyLightUpdateQueue.offer(() -> skyLightPropagator.setLightAndPropagate(x, y, z, lightDataAccessor.getLight(x, y, z), 0));
            } else {
                var min = Math.min(oldLightHeight, newLightHeight);
                var max = Math.max(oldLightHeight, newLightHeight);

                // A moved height boundary changes horizontal sources in this
                // column and adjacent columns. Queue their old full-light removal
                // before replacement boundary seeds so stale paths can be cleared.
                for (int i = min; i <= max; i++) {
                    var skyLightSourceY = i;
                    skyLightUpdateQueue.offer(() -> {
                        if (lightDataAccessor.getLight(x, skyLightSourceY, z) == 15) {
                            skyLightPropagator.setLightAndPropagate(x, skyLightSourceY, z, 15, 0);
                        }
                    });
                    for (var face : BlockFace.getHorizontalBlockFaces()) {
                        var ox = x + face.getOffset().x();
                        var oz = z + face.getOffset().z();
                        skyLightUpdateQueue.offer(() -> {
                            if (lightDataAccessor.getLight(ox, skyLightSourceY, oz) == 15) {
                                skyLightPropagator.setLightAndPropagate(ox, skyLightSourceY, oz, 15, 0);
                            }
                        });
                    }
                }

                // Source removals precede these new seeds in the sky queue,
                // allowing propagation to rebuild affected boundaries from the
                // updated height map rather than retaining obsolete source paths.
                calculateSkyLightAt(x, z);
                for (var face : BlockFace.getHorizontalBlockFaces()) {
                    var neighborX = x + face.getOffset().x();
                    var neighborZ = z + face.getOffset().z();
                    calculateSkyLightAt(neighborX, neighborZ);
                }
            }
        });
    }

    /**
     * Queues a chunk material snapshot and full source submission when
     * surrounding chunks are ready. The chunk worker copies material data
     * under the chunk read operation, then either scans the chunk or marks it
     * as waiting for neighbor availability. Shutdown admission rejects new
     * requests.
     *
     * <p>
     * A newly registered chunk can complete the surrounding region for waiting
     * neighbors, so those neighbors are checked as part of the same task.
     * Their waiting markers clear only after their scans submit propagation
     * work, not after downstream illumination has converged; queue retries
     * preserve that callback distinction.
     */
    public void onChunkLoad(Chunk chunk) {
        if (!isRunning.get()) {
            return;
        }

        chunkAndBlockUpdateQueue.offer(() -> {
            chunk.applyOperation(this::addChunk, OperationType.READ, OperationType.NONE);

            // Full seeding reads neighboring material arrays. Wait for the whole
            // surrounding region rather than attempting propagation into chunks
            // whose channels have not yet been registered.
            if (canCalculateLightInChunk(chunk.getX(), chunk.getZ())) {
                tryCalculateChunkLightAt(chunk.getX(), chunk.getZ(), null);
            } else {
                awaitingLightCalculationChunks.add(HashUtils.hashXZ(chunk.getX(), chunk.getZ()));
            }

            // This registration can complete a waiting neighbor's surrounding
            // region. Retry only waiting neighbors and clear their markers after
            // source submission, preserving deferred incremental update handling.
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }

                    var neighborChunkHash = HashUtils.hashXZ(chunk.getX() + i, chunk.getZ() + j);
                    if (awaitingLightCalculationChunks.contains(neighborChunkHash)) {
                        if (canCalculateLightInChunk(chunk.getX() + i, chunk.getZ() + j)) {
                            tryCalculateChunkLightAt(chunk.getX() + i, chunk.getZ() + j, () -> {
                                awaitingLightCalculationChunks.remove(neighborChunkHash);
                            });
                        }
                    }
                }
            }
        });
    }

    /**
     * Queues removal of registered chunk channels and marks loaded neighbors
     * for later recalculation. Shutdown admission rejects new requests, while
     * an accepted task removes maps before its final chunk membership update.
     * Propagation readers can encounter missing arrays during that interval.
     *
     * <p>
     * Neighboring loaded chunks lose the complete surrounding region needed
     * for full calculation, so they enter the waiting set and their
     * block-light arrays are reset. The helper named {@code
     * resetBlockAndSkyLightInChunk} currently resets only block light;
     * retained sky border data is not cleared by that call.
     */
    public void onChunkUnload(Chunk chunk) {
        if (!isRunning.get()) {
            return;
        }

        chunkAndBlockUpdateQueue.offer(() -> {
            var hash = HashUtils.hashXZ(chunk.getX(), chunk.getZ());
            // Remove channel storage before clearing membership. Other workers have
            // no shared reservation around this sequence and may encounter missing
            // arrays or retained last-chunk cache references during the transition.
            lightDampening.remove(hash);
            lightEmission.remove(hash);
            blockLight.remove(hash);
            if (dimensionType.hasSkyLight()) {
                lightHeightMap.remove(hash);
                skyLightInBorder.remove(hash);
            }
            chunks.remove(hash);
            awaitingLightCalculationChunks.remove(hash);

            // Loaded neighbors now lack a complete surrounding region. Mark them
            // waiting and clear block illumination so a later neighbor registration
            // can submit a fresh full scan instead of applying incremental seeds.
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }

                    var neighborChunkHash = HashUtils.hashXZ(chunk.getX() + i, chunk.getZ() + j);
                    if (chunks.contains(neighborChunkHash) && !awaitingLightCalculationChunks.contains(neighborChunkHash)) {
                        awaitingLightCalculationChunks.add(neighborChunkHash);
                        resetBlockAndSkyLightInChunk(neighborChunkHash);
                    }
                }
            }
        });
    }

    /**
     * Resets the registered chunk's block-light arrays without clearing
     * skylight border storage. Callers supply existing block-light channel
     * data and serialize the reset with chunk decisions; the method name does
     * not imply a sky-channel reset.
     */
    protected void resetBlockAndSkyLightInChunk(long hash) {
        for (var array : blockLight.get(hash)) {
            array.reset();
        }
    }

    /**
     * Returns the sum of queued chunk, block, and optional sky tasks. Counts
     * exclude running work and are read separately, so the result is an
     * approximate concurrent backlog rather than a propagation-completion
     * barrier.
     */
    @Override
    public int getQueuedUpdateCount() {
        return chunkAndBlockUpdateQueue.size() + blockLightUpdateQueue.size() + (skyLightUpdateQueue != null ? skyLightUpdateQueue.size() : 0);
    }

    /**
     * Reads a channel value or returns {@code defaultValue} for an
     * unregistered chunk. Registered reads require an in-range height and
     * available arrays; membership does not lock the map against concurrent
     * removal.
     */
    protected int get(NonBlockingHashMapLong<ChunkSectionNibbleArray[]> target, int x, int y, int z, int defaultValue) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        if (!chunks.contains(hash)) {
            return defaultValue;
        }
        var array = target.get(hash);
        return array[(y - dimensionType.getMinHeight()) >> 4].get(x & 15, y & 15, z & 15);
    }

    /**
     * Reads a channel value without checking chunk registration or height
     * bounds. Callers provide available section arrays and a valid dimension
     * height; missing or removed data can fail during lookup.
     */
    protected int getWithoutChunkCheck(NonBlockingHashMapLong<ChunkSectionNibbleArray[]> target, int x, int y, int z) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        var array = target.get(hash);
        return array[(y - dimensionType.getMinHeight()) >> 4].get(x & 15, y & 15, z & 15);
    }

    /**
     * Stores a four-bit channel value only while its chunk appears registered.
     * Callers supply {@code value} from {@code 0} through {@code 15}, an
     * in-range height, and available arrays; concurrent removal after the
     * membership check can still invalidate the write.
     */
    protected void set(NonBlockingHashMapLong<ChunkSectionNibbleArray[]> target, int x, int y, int z, int value) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        if (!chunks.contains(hash)) {
            return;
        }
        var array = target.get(hash);
        array[(y - dimensionType.getMinHeight()) >> 4].set(x & 15, y & 15, z & 15, value);
    }

    /**
     * Stores a channel value without checking chunk registration or height
     * bounds. Callers supply available arrays, a valid dimension height, and
     * {@code value} from {@code 0} through {@code 15}, while coordinating
     * writes with the channel consumer.
     */
    protected void setWithoutChunkCheck(NonBlockingHashMapLong<ChunkSectionNibbleArray[]> target, int x, int y, int z, int value) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        var array = target.get(hash);
        array[(y - dimensionType.getMinHeight()) >> 4].set(x & 15, y & 15, z & 15, value);
    }

    /**
     * Stores four-bit material dampening and updates the column's skylight
     * source height when skylight exists. Callers keep {@code value} from
     * {@code 0} through {@code 15}. Removing dampening at or below the current
     * source boundary scans downward for the next blocking material; adding a
     * boundary at or above the previous height raises it above that block.
     *
     * <p>
     * The scan reads material data from the current recorded height and
     * retains the dimension minimum when no blocker is found. This helper
     * updates the height map only; {@code onBlockChange} separately compares
     * old and new heights to schedule source removal and repropagation.
     * Callers provide available channel data and account for the scan's
     * unvalidated starting height.
     */
    protected void setLightDampening(int x, int y, int z, int value) {
        setWithoutChunkCheck(lightDampening, x, y, z, value);
        if (!dimensionType.hasSkyLight()) {
            return;
        }

        // A column's first exposed height follows its highest dampening block.
        // Recompute after removal or raise the boundary after a new blocker;
        // source propagation remains a separate queued decision.
        var currentLightHeight = getLightHeight(x, z);
        if (value == 0 && currentLightHeight >= y + 1) {
            short newLightHeight = (short) dimensionType.getMinHeight();
            for (short i = currentLightHeight; i >= dimensionType.getMinHeight(); i--) {
                if (lightDataAccessor.getLightDampening(x, i, z) != 0) {
                    newLightHeight = (short) (i + 1);
                    break;
                }
            }
            setLightHeight(x, z, newLightHeight);
        } else if (currentLightHeight <= y) {
            setLightHeight(x, z, (short) (y + 1));
        }
    }
}
