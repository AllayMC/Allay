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
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.data.Weather;
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
 * @author daoge_cmd
 */
public class AllayLightEngine implements LightEngine {

    protected final DimensionInfo dimensionInfo;
    protected final String worldName;
    protected final AtomicBoolean isRunning;
    protected final Supplier<Integer> timeSupplier;
    protected final Supplier<Weather> weatherSupplier;
    protected final int maxUpdateCount;
    protected final BlockingQueueWrapper<Runnable> chunkAndBlockUpdateQueue;
    protected final BlockingQueueWrapper<Runnable> blockLightUpdateQueue;
    protected final Set<Long> chunks;
    /**
     * Stores the chunks that are loaded but haven't calculated the light yet, because one
     * or more of their neighbor chunks are not loaded. Further block changes in these chunks
     * will be ignored since we will calculate the light in the whole chunk later once their
     * neighbor chunks are loaded.
     */
    protected final Set<Long> awaitingLightCalculationChunks;
    protected final NonBlockingHashMapLong<ChunkSectionNibbleArray[]> lightDampening;
    protected final NonBlockingHashMapLong<ChunkSectionNibbleArray[]> lightEmission;
    protected final NonBlockingHashMapLong<ChunkSectionNibbleArray[]> blockLight;
    protected final LightPropagator blockLightPropagator;
    /**
     * The light data accessor used in "Chunk & Block" thread. We use LightDataAccessor as much
     * as possible, because the implementation of LightDataAccessor will cache the last reading
     * chunk and avoid getting chunk from large hash table every time (which will be much faster)
     * To avoid race conditions, we limit the read and write threads for various data:
     * Light (Sky Light) - Sky Light Calculating Thread
     * Light Dampening&Emission - Chunk & Block Light Calculation Thread
     */
    protected final CachedLightDataAccessor lightDataAccessor;

    /*
     * The following fields are not null only when the dimension has sky light
     */

    protected BlockingQueueWrapper<Runnable> skyLightUpdateQueue;
    /**
     * Store the sky light sources' height in each chunk
     */
    protected NonBlockingHashMapLong<HeightMap> lightHeightMap;
    /**
     * Use CachedMapAccessor to get light height for better performance
     */
    protected CachedChunkMapAccessor<HeightMap> lightHeightMapAccessor;
    /**
     * This map only contains the propagation result of sky light sources that are in the
     * border (at critical light height or horizontal propagation needs to be considered).
     * Skylight sources have a value of 15 in this map
     */
    protected NonBlockingHashMapLong<ChunkSectionNibbleArray[]> skyLightInBorder;
    protected LightPropagator skyLightPropagator;

    public AllayLightEngine(Dimension dimension) {
        this(dimension.getDimensionInfo(), dimension.getWorld().getName(), dimension.getWorld().getWorldData()::getTimeOfDay, dimension.getWorld()::getWeather, AllayServer.getSettings().worldSettings().maxLightUpdateCountPerDimension());
    }

    @VisibleForTesting
    public AllayLightEngine(DimensionInfo dimensionInfo, String worldName, Supplier<Integer> timeSupplier, Supplier<Weather> weatherSupplier) {
        this(dimensionInfo, worldName, timeSupplier, weatherSupplier, Integer.MAX_VALUE);
    }

    protected AllayLightEngine(DimensionInfo dimensionInfo, String worldName, Supplier<Integer> timeSupplier, Supplier<Weather> weatherSupplier, int maxUpdateCount) {
        this.dimensionInfo = dimensionInfo;
        this.worldName = worldName;
        this.isRunning = new AtomicBoolean(true);
        this.timeSupplier = timeSupplier;
        this.weatherSupplier = weatherSupplier;
        this.maxUpdateCount = maxUpdateCount;
        this.chunkAndBlockUpdateQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
        this.blockLightUpdateQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
        this.chunks = new NonBlockingHashSet<>();
        // Will only be read and wrote in chunk & block update thread, so no need to use a thread-safe set
        this.awaitingLightCalculationChunks = new LongOpenHashSet();
        this.lightDampening = new NonBlockingHashMapLong<>();
        this.lightEmission = new NonBlockingHashMapLong<>();
        this.blockLight = new NonBlockingHashMapLong<>();
        this.blockLightPropagator = new LightPropagator(new CachedLightDataAccessor(dimensionInfo, blockLight, lightDampening, lightEmission));
        if (dimensionInfo.hasSkyLight()) {
            this.skyLightUpdateQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
            this.lightHeightMap = new NonBlockingHashMapLong<>();
            this.lightHeightMapAccessor = new CachedChunkMapAccessor<>(lightHeightMap);
            this.skyLightInBorder = new NonBlockingHashMapLong<>();
            this.skyLightPropagator = new LightPropagator(new CachedLightDataAccessor(dimensionInfo, skyLightInBorder, lightDampening, lightEmission));
        }
        // Initialize lightDataAccessor after we initialized skylight related things
        // NOTICE: we only cache skyLightInBorder, block light is not much used in "Chunk & Block" thread
        this.lightDataAccessor = new CachedLightDataAccessor(dimensionInfo, skyLightInBorder, lightDampening, lightEmission);
    }

    @VisibleForTesting
    public static int calculateSkylightReduction(long time, Weather weather) {
        double d = 1.0 - ((weather != Weather.CLEAR ? 1 : 0) * 5.0) / 16.0;
        double e = 1.0 - ((weather == Weather.THUNDER ? 1 : 0) * 5.0) / 16.0;
        double f = 0.5 + 2.0 * Math.clamp(MathUtils.fastCos(calculateCelestialAngle(time) * 6.2831855), -0.25, 0.25);
        return (int) ((1.0 - f * d * e) * 11.0);
    }

    @VisibleForTesting
    public static double calculateCelestialAngle(long time) {
        double d = frac(((double) time) / 24000.0 - 0.25);
        double e = 0.5 - Math.cos(d * Math.PI) / 2.0;
        return (d * 2.0 + e) / 3.0F;
    }

    protected static double frac(double value) {
        return value - (double) lfloor(value);
    }

    protected static long lfloor(double value) {
        long l = (long) value;
        return value < (double) l ? l - 1L : l;
    }

    protected static byte packLightData(@Range(from = 0, to = 16) int lightEmission, @Range(from = 0, to = 16) int lightDampening) {
        Preconditions.checkArgument(lightEmission < 16, "lightEmission must be less than 16");
        Preconditions.checkArgument(lightDampening < 16, "lightDampening must be less than 16");
        return (byte) (lightEmission | (lightDampening << 4));
    }

    protected static byte unpackLightEmission(byte data) {
        return (byte) (data & 0x0F);
    }

    protected static byte unpackLightDampening(byte data) {
        return (byte) ((data >> 4) & 0x0F);
    }

    public void startTick() {
        var dimensionName = worldName + ":" + dimensionInfo.toString();
        startCalculatingThread("Light Calculating Thread (Chunk & Block) #" + dimensionName, chunkAndBlockUpdateQueue);
        startCalculatingThread("Light Calculating Thread (Block Light) #" + dimensionName, blockLightUpdateQueue);
        if (dimensionInfo.hasSkyLight()) {
            startCalculatingThread("Light Calculating Thread (Sky Light) #" + dimensionName, skyLightUpdateQueue);
        }
    }

    public void shutdown() {
        this.isRunning.set(false);
    }

    protected void startCalculatingThread(String name, BlockingQueueWrapper<Runnable> queue) {
        Thread.ofPlatform().name(name).start(() -> {
            while (isRunning.get()) {
                this.handleUpdateIn(queue);
            }
        });
    }

    protected void handleUpdateIn(BlockingQueueWrapper<Runnable> queue) {
        Runnable runnable;
        while ((runnable = queue.tryPoll(1, TimeUnit.SECONDS)) != null) {
            runnable.run();
        }
    }

    protected void handleUpdateUninterruptibleIn(BlockingQueueWrapper<Runnable> queue) {
        Runnable runnable;
        while ((runnable = queue.pollNow()) != null) {
            runnable.run();
        }
    }

    @VisibleForTesting
    public void handleUpdateInAllQueues() {
        handleUpdateUninterruptibleIn(chunkAndBlockUpdateQueue);
        handleUpdateUninterruptibleIn(blockLightUpdateQueue);
        if (dimensionInfo.hasSkyLight()) {
            handleUpdateUninterruptibleIn(skyLightUpdateQueue);
        }
    }

    protected void addChunk(UnsafeChunk chunk) {
        var chunkLightDampening = createNibbleArrays();
        var chunkLightEmission = createNibbleArrays();
        var chunkLightHeightMap = dimensionInfo.hasSkyLight() ? new HeightMap((short) dimensionInfo.minHeight()) : null;
        for (int y = dimensionInfo.maxHeight(); y >= dimensionInfo.minHeight(); y--) {
            var section = (AllayChunkSection) chunk.getSection(y >> 4);
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    var blockStateData = section.getBlockState(x, y & 0xf, z, 0).getBlockStateData();
                    var lightDampening = blockStateData.lightDampening();
                    var lightEmission = blockStateData.lightEmission();

                    if (lightDampening != 0) {
                        chunkLightDampening[(y - dimensionInfo.minHeight()) >> 4].set(x, y & 0xf, z, lightDampening);
                        boolean skyLightSourceNotFound = dimensionInfo.hasSkyLight() && chunkLightHeightMap.get(x, z) == dimensionInfo.minHeight();
                        if (skyLightSourceNotFound) {
                            chunkLightHeightMap.set(x, z, (short) (y + 1));
                        }
                    }

                    if (lightEmission != 0) {
                        chunkLightEmission[(y - dimensionInfo.minHeight()) >> 4].set(x, y & 0xf, z, lightEmission);
                    }
                }
            }
        }

        var hash = HashUtils.hashXZ(chunk.getX(), chunk.getZ());
        lightDampening.put(hash, chunkLightDampening);
        lightEmission.put(hash, chunkLightEmission);
        blockLight.put(hash, createNibbleArrays());
        if (dimensionInfo.hasSkyLight()) {
            lightHeightMap.put(hash, chunkLightHeightMap);
            skyLightInBorder.put(hash, createNibbleArrays());
        }
        chunks.add(hash);
    }

    protected void tryCalculateChunkLightAt(int chunkX, int chunkZ, Runnable afterCalculated) {
        if (this.getQueuedUpdateCount() > maxUpdateCount) {
            // We have too many light updates in the queue, so we need to wait for a while
            chunkAndBlockUpdateQueue.offer(() -> {
                // Recheck is needed since there may be chunk load/unload
                if (canCalculateLightInChunk(chunkX, chunkZ)) {
                    tryCalculateChunkLightAt(chunkX, chunkZ, afterCalculated);
                }
            });
            return;
        }

        for (int worldY = dimensionInfo.maxHeight(); worldY >= dimensionInfo.minHeight(); worldY--) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    final int worldX = (chunkX << 4) + x;
                    final int worldZ = (chunkZ << 4) + z;
                    calculateBlockLightAt(worldX, worldY, worldZ);
                    if (dimensionInfo.hasSkyLight()) {
                        calculateSkyLightAt(worldX, worldZ);
                    }
                }
            }
        }

        if (afterCalculated != null) {
            afterCalculated.run();
        }
    }

    protected void calculateBlockLightAt(int x, int y, int z) {
        // Cast to byte to save memory
        byte lightEmissionValue = (byte) lightDataAccessor.getLightEmission(x, y, z);
        int minNeighborLightEmission = 15;
        if (lightEmissionValue != 0) {
            var ignoreCurrentBlockLight = false;
            for (var blockFace : BlockFace.values()) {
                // The following are world pos not chunk local pos
                var neighborX = x + blockFace.getOffset().x();
                var neighborY = y + blockFace.getOffset().y();
                var neighborZ = z + blockFace.getOffset().z();

                int neighborLightEmission = lightDataAccessor.getLightEmission(neighborX, neighborY, neighborZ);
                if (neighborLightEmission > lightEmissionValue) {
                    // We can ignore the current block, because the neighbor block's light will cover the current block
                    ignoreCurrentBlockLight = true;
                    break;
                }

                minNeighborLightEmission = Math.min(minNeighborLightEmission, neighborLightEmission);
            }

            // Check if the current block is surrounded with blocks that have the same light
            // emission, usually happen in lava pool where have a lot of lava blocks inside
            if (minNeighborLightEmission == lightEmissionValue) {
                setWithoutChunkCheck(blockLight, x, y, z, lightEmissionValue);
                ignoreCurrentBlockLight = true;
            }

            if (!ignoreCurrentBlockLight) {
                blockLightUpdateQueue.offer(() -> blockLightPropagator.setLightAndPropagate(x, y, z, 0, lightEmissionValue));
            }
        }
    }

    protected void calculateSkyLightAt(int x, int z) {
        var lightHeight = getLightHeight(x, z);
        if (lightHeight > dimensionInfo.maxHeight()) {
            // This x-z position is full of blocks
            return;
        }

        var maxNeighborLightHeight = getMaxNeighborLightHeight(x, z);
        for (int i = lightHeight; i <= maxNeighborLightHeight; i++) {
            final int skyLightSourceY = i;
            skyLightUpdateQueue.offer(() -> skyLightPropagator.setLightAndPropagate(x, skyLightSourceY, z, 0, 15));
        }
    }

    protected int getMaxNeighborLightHeight(int x, int z) {
        int max = Integer.MIN_VALUE;
        BlockFace[] horizontalBlockFaces = BlockFace.getHorizontalBlockFaces();
        for (BlockFace face : horizontalBlockFaces) {
            max = Math.max(max, getLightHeight(x + face.getOffset().x(), z + face.getOffset().z()));
        }

        return max;
    }

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

    protected ChunkSectionNibbleArray[] createNibbleArrays() {
        ChunkSectionNibbleArray[] nibbleArrays = new ChunkSectionNibbleArray[dimensionInfo.chunkSectionCount()];
        for (int i = 0; i < dimensionInfo.chunkSectionCount(); i++) {
            nibbleArrays[i] = new ChunkSectionNibbleArray();
        }
        return nibbleArrays;
    }

    @Override
    public int getSkyLight(int x, int y, int z) {
        if (!dimensionInfo.hasSkyLight()) {
            return 0;
        }

        if (!chunks.contains(HashUtils.hashXZ(x >> 4, z >> 4))) {
            // Chunk is not loaded
            return 15;
        }

        var lightHeight = getLightHeight(x, z);
        if (y >= lightHeight) {
            return 15;
        }

        return getWithoutChunkCheck(skyLightInBorder, x, y, z);
    }

    protected short getLightHeight(int x, int z) {
        return lightHeightMapAccessor.get(x, z).get(x & 15, z & 15);
    }

    protected void setLightHeight(int x, int z, short value) {
        lightHeightMapAccessor.get(x, z).set(x & 15, z & 15, value);
    }

    @Override
    public int getBlockLight(int x, int y, int z) {
        return get(blockLight, x, y, z, 0);
    }

    @Override
    public int getInternalLight(int x, int y, int z) {
        return dimensionInfo.hasSkyLight() ? Math.max(getInternalSkyLight(x, y, z), getBlockLight(x, y, z)) : getBlockLight(x, y, z);
    }

    @Override
    public int getInternalSkyLight(int x, int y, int z) {
        return dimensionInfo.hasSkyLight() ? Math.max(0, getSkyLight(x, y, z) - calculateSkylightReduction(timeSupplier.get(), weatherSupplier.get())) : 0;
    }

    public void onBlockChange(int x, int y, int z, int le, int ld) {
        if (!isRunning.get()) {
            return;
        }

        // Reduce memory usage by packing light data into a single byte
        var packedLightData = packLightData(le, ld);
        chunkAndBlockUpdateQueue.offer(() -> {
            var chunkHash = HashUtils.hashXZ(x >> 4, z >> 4);
            if (!chunks.contains(chunkHash)) {
                // Chunk is not loaded
                return;
            }

            var lightDampening = unpackLightDampening(packedLightData);
            var lightEmissionValue = unpackLightEmission(packedLightData);
            int oldLightHeight = dimensionInfo.hasSkyLight() ? getLightHeight(x, z) : 0;
            var oldBlockDampening = lightDataAccessor.getLightDampening(x, y, z);
            var oldBlockEmission = lightDataAccessor.getLightEmission(x, y, z);

            if (oldBlockDampening != lightDampening) {
                setLightDampening(x, y, z, lightDampening);
            }

            if (oldBlockEmission != lightEmissionValue) {
                lightDataAccessor.setLightEmission(x, y, z, lightEmissionValue);
            }

            var chunkLightCalculated = !awaitingLightCalculationChunks.contains(chunkHash);
            if (!chunkLightCalculated || (oldBlockDampening == lightDampening && oldBlockEmission == lightEmissionValue)) {
                return;
            }

            blockLightUpdateQueue.offer(() -> blockLightPropagator.setLightAndPropagate(x, y, z, getBlockLight(x, y, z), lightEmissionValue));
            if (!dimensionInfo.hasSkyLight() || oldBlockDampening == lightDampening) {
                // No change in light dampening, so no need to recalculate the sky light because
                // sky light is only related to block dampening
                return;
            }

            var newLightHeight = getLightHeight(x, z);
            if (newLightHeight == oldLightHeight) {
                // No change to light height, only need to make sure the sky light can be re-propagated to the changed block's position
                skyLightUpdateQueue.offer(() -> skyLightPropagator.setLightAndPropagate(x, y, z, lightDataAccessor.getLight(x, y, z), 0));
            } else {
                var min = Math.min(oldLightHeight, newLightHeight);
                var max = Math.max(oldLightHeight, newLightHeight);

                // Remove old skylight sources responsible for horizontal skylight propagation
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

                // Recalculate skylight
                calculateSkyLightAt(x, z);
                for (var face : BlockFace.getHorizontalBlockFaces()) {
                    var neighborX = x + face.getOffset().x();
                    var neighborZ = z + face.getOffset().z();
                    calculateSkyLightAt(neighborX, neighborZ);
                }
            }
        });
    }

    public void onChunkLoad(Chunk chunk) {
        if (!isRunning.get()) {
            return;
        }

        chunkAndBlockUpdateQueue.offer(() -> {
            chunk.applyOperation(this::addChunk, OperationType.READ, OperationType.NONE);

            // Check if we can calculate the light in this chunk immediately
            if (canCalculateLightInChunk(chunk.getX(), chunk.getZ())) {
                tryCalculateChunkLightAt(chunk.getX(), chunk.getZ(), null);
            } else {
                awaitingLightCalculationChunks.add(HashUtils.hashXZ(chunk.getX(), chunk.getZ()));
            }

            // Check if the neighbor chunks' light can be calculated since we have a
            // new chunk added (if the neighbor chunks' light haven't been calculated)
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

    public void onChunkUnload(Chunk chunk) {
        if (!isRunning.get()) {
            return;
        }

        chunkAndBlockUpdateQueue.offer(() -> {
            var hash = HashUtils.hashXZ(chunk.getX(), chunk.getZ());
            lightDampening.remove(hash);
            lightEmission.remove(hash);
            blockLight.remove(hash);
            if (dimensionInfo.hasSkyLight()) {
                lightHeightMap.remove(hash);
                skyLightInBorder.remove(hash);
            }
            chunks.remove(hash);
            awaitingLightCalculationChunks.remove(hash);

            // Add the loaded neighbor chunk to the awaiting light calculation chunks, since
            // their neighbor chunk has been unloaded. Once the neighbor chunk is loaded back,
            // the light in these chunks will be recalculated
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

    protected void resetBlockAndSkyLightInChunk(long hash) {
        for (var array : blockLight.get(hash)) {
            array.reset();
        }
    }

    @Override
    public int getQueuedUpdateCount() {
        return chunkAndBlockUpdateQueue.size() + blockLightUpdateQueue.size() + (skyLightUpdateQueue != null ? skyLightUpdateQueue.size() : 0);
    }

    protected int get(NonBlockingHashMapLong<ChunkSectionNibbleArray[]> target, int x, int y, int z, int defaultValue) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        if (!chunks.contains(hash)) {
            return defaultValue;
        }
        var array = target.get(hash);
        return array[(y - dimensionInfo.minHeight()) >> 4].get(x & 15, y & 15, z & 15);
    }

    protected int getWithoutChunkCheck(NonBlockingHashMapLong<ChunkSectionNibbleArray[]> target, int x, int y, int z) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        var array = target.get(hash);
        return array[(y - dimensionInfo.minHeight()) >> 4].get(x & 15, y & 15, z & 15);
    }

    protected void set(NonBlockingHashMapLong<ChunkSectionNibbleArray[]> target, int x, int y, int z, int value) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        if (!chunks.contains(hash)) {
            return;
        }
        var array = target.get(hash);
        array[(y - dimensionInfo.minHeight()) >> 4].set(x & 15, y & 15, z & 15, value);
    }

    protected void setWithoutChunkCheck(NonBlockingHashMapLong<ChunkSectionNibbleArray[]> target, int x, int y, int z, int value) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        var array = target.get(hash);
        array[(y - dimensionInfo.minHeight()) >> 4].set(x & 15, y & 15, z & 15, value);
    }

    protected void setLightDampening(int x, int y, int z, int value) {
        setWithoutChunkCheck(lightDampening, x, y, z, value);
        if (!dimensionInfo.hasSkyLight()) {
            return;
        }

        // Update light height
        var currentLightHeight = getLightHeight(x, z);
        if (value == 0 && currentLightHeight >= y + 1) {
            short newLightHeight = (short) dimensionInfo.minHeight();
            for (short i = currentLightHeight; i >= dimensionInfo.minHeight(); i--) {
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
