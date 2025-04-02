package org.allaymc.server.world.service;

import com.google.common.base.Preconditions;
import io.netty.util.internal.PlatformDependent;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.Weather;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.chunk.UnsafeChunk;
import org.allaymc.api.world.service.LightService;
import org.allaymc.server.datastruct.ChunkSectionNibbleArray;
import org.allaymc.server.datastruct.collections.queue.BlockingQueueWrapper;
import org.allaymc.server.world.chunk.AllayChunkSection;
import org.allaymc.server.world.chunk.HeightMap;
import org.jctools.maps.NonBlockingHashMapLong;
import org.jctools.maps.NonBlockingHashSet;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class AllayLightService implements LightService {

    protected final DimensionInfo dimensionInfo;
    protected final String worldName;
    protected final Supplier<Boolean> runningFlagSupplier;
    protected final Supplier<Integer> timeSupplier;
    protected final Supplier<Set<Weather>> weatherSupplier;
    protected final BlockingQueueWrapper<Runnable> chunkAndBlockUpdateQueue;
    protected final BlockingQueueWrapper<Runnable> blockLightUpdateQueue;
    protected final Set<Long> chunks;
    protected final Set<Long> awaitingLightCalculationChunks;
    protected final NonBlockingHashMapLong<ChunkSectionNibbleArray[]> lightDampening;
    protected final NonBlockingHashMapLong<ChunkSectionNibbleArray[]> lightEmission;
    protected final NonBlockingHashMapLong<ChunkSectionNibbleArray[]> blockLight;
    protected final LightPropagator blockLightPropagator;

    protected BlockingQueueWrapper<Runnable> skyLightUpdateQueue;
    // Store the sky light sources' height in each chunk
    protected NonBlockingHashMapLong<HeightMap> lightHeightMap;
    protected NonBlockingHashMapLong<ChunkSectionNibbleArray[]> skyLight;
    protected LightPropagator skyLightPropagator;

    public AllayLightService(Dimension dimension) {
        this(dimension.getDimensionInfo(), dimension.getWorld().getName(), dimension.getWorld()::isRunning, dimension.getWorld().getWorldData()::getTimeOfDay, dimension.getWorld()::getWeathers);
    }

    @VisibleForTesting
    public AllayLightService(DimensionInfo dimensionInfo, String worldName, Supplier<Boolean> runningFlagSupplier, Supplier<Integer> timeSupplier, Supplier<Set<Weather>> weatherSupplier) {
        this.dimensionInfo = dimensionInfo;
        this.worldName = worldName;
        this.runningFlagSupplier = runningFlagSupplier;
        this.timeSupplier = timeSupplier;
        this.weatherSupplier = weatherSupplier;
        this.chunkAndBlockUpdateQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
        this.blockLightUpdateQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
        this.chunks = new NonBlockingHashSet<>();
        // Will only be read and wrote in chunk & block update thread, so no need to use thread-safe set
        this.awaitingLightCalculationChunks = new LongOpenHashSet();
        this.lightDampening = new NonBlockingHashMapLong<>();
        this.lightEmission = new NonBlockingHashMapLong<>();
        this.blockLight = new NonBlockingHashMapLong<>();
        this.blockLightPropagator = new LightPropagator(new BlockLightDataAccessor());
        if (dimensionInfo.hasSkyLight()) {
            this.skyLightUpdateQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
            this.lightHeightMap = new NonBlockingHashMapLong<>();
            this.skyLight = new NonBlockingHashMapLong<>();
            this.skyLightPropagator = new LightPropagator(new SkyLightDataAccessor());
        }
    }

    public void startTick() {
        var dimensionName = worldName + ":" + dimensionInfo.toString();
        startCalculatingThread("Light Calculating Thread (Chunk & Block) - " + dimensionName, chunkAndBlockUpdateQueue);
        startCalculatingThread("Light Calculating Thread (Block Light) - " + dimensionName, blockLightUpdateQueue);
        if (dimensionInfo.hasSkyLight()) {
            startCalculatingThread("Light Calculating Thread (Sky Light) - " + dimensionName, skyLightUpdateQueue);
        }
    }

    protected void startCalculatingThread(String name, BlockingQueueWrapper<Runnable> queue) {
        Thread.ofPlatform().name(name).start(() -> {
            while (runningFlagSupplier.get()) {
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

    @VisibleForTesting
    public void handleUpdateInAllQueues() {
        handleUpdateIn(chunkAndBlockUpdateQueue);
        handleUpdateIn(blockLightUpdateQueue);
        if (dimensionInfo.hasSkyLight()) {
            handleUpdateIn(skyLightUpdateQueue);
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
            skyLight.put(hash, createNibbleArrays());
        }
        chunks.add(hash);

        // Check if we can calculate the light in this chunk immediately
        if (canCalculateLightInChunk(chunk.getX(), chunk.getZ())) {
            calculateLightInChunk(chunk.getX(), chunk.getZ());
        } else {
            awaitingLightCalculationChunks.add(hash);
        }
    }

    protected void calculateLightInChunk(int chunkX, int chunkZ) {
        var pendingBlockLightUpdates = new ArrayList<Runnable>();
        var pendingSkyLightUpdates = dimensionInfo.hasSkyLight() ? new ArrayList<Runnable>() : null;
        for (int y = dimensionInfo.maxHeight(); y >= dimensionInfo.minHeight(); y--) {
            final int worldY = y;
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    final int worldX = (chunkX << 4) + x;
                    final int worldZ = (chunkZ << 4) + z;
                    byte lightEmissionValue = (byte) get(lightEmission, worldX, worldY, worldZ, 0);

                    byte minNeighborLightEmission = 15;
                    if (lightEmissionValue != 0) {
                        var ignoreCurrentBlockLight = false;
                        for (var blockFace : BlockFace.values()) {
                            // The following are world pos not chunk local pos
                            var neighborWorldX = worldX + blockFace.getOffset().x();
                            var neighborWorldY = worldY + blockFace.getOffset().y();
                            var neighborWorldZ = worldZ + blockFace.getOffset().z();

                            byte neighborLightEmission = (byte) get(lightEmission, neighborWorldX, neighborWorldY, neighborWorldZ, 0);
                            if (neighborLightEmission > lightEmissionValue) {
                                // We can ignore the current block, because the neighbor block's light will cover the current block
                                ignoreCurrentBlockLight = true;
                                break;
                            }

                            minNeighborLightEmission = (byte) Math.min(minNeighborLightEmission, neighborLightEmission);
                        }

                        // Check if the current block is surrounded with blocks that have the same light
                        // emission, usually happen in lava pool where have a lot of lava blocks inside
                        if (minNeighborLightEmission == lightEmissionValue) {
                            setWithoutChunkCheck(blockLight, worldX, worldY, worldZ, lightEmissionValue);
                            ignoreCurrentBlockLight = true;
                        }

                        if (!ignoreCurrentBlockLight) {
                            pendingBlockLightUpdates.add(() -> blockLightPropagator.setLightAndPropagate(worldX, worldY, worldZ, 0, lightEmissionValue));
                        }
                    }

                    // TODO: sky light calculation too slow
                    if (dimensionInfo.hasSkyLight()) {
                        var lightHeight = getLightHeight(worldX, worldZ);
                        if (lightHeight > dimensionInfo.maxHeight()) {
                            // This x-z position is full of blocks
                            continue;
                        }

                        int[] neighborLightHeights = new int[4];
                        for (int index = 0; index < BlockFace.getHorizontalBlockFaces().length; index++) {
                            var horizontalBlockFace = BlockFace.getHorizontalBlockFaces()[index];
                            var neighborWorldX = worldX + horizontalBlockFace.getOffset().x();
                            var neighborWorldZ = worldZ + horizontalBlockFace.getOffset().z();
                            neighborLightHeights[index] = getLightHeight(neighborWorldX, neighborWorldZ);
                        }
                        for (int i = lightHeight; i <= dimensionInfo.maxHeight(); i++) {
                            final int skyLightSourceY = i;
                            var skipSkyLightCalculation = true;

                            for (int index = 0; index < BlockFace.getHorizontalBlockFaces().length; index++) {
                                if (neighborLightHeights[index] > skyLightSourceY) {
                                    // The neighbor light height is greater than the current light height,
                                    // so it is essential to propagate the sky light in the current pos
                                    skipSkyLightCalculation = false;
                                    break;
                                }
                            }

                            if (skipSkyLightCalculation && skyLightSourceY != dimensionInfo.minHeight() && skyLightSourceY == (int) lightHeight && get(lightDampening, worldX, skyLightSourceY - 1, worldZ, 0) < 14) {
                                skipSkyLightCalculation = false;
                            }

                            if (skipSkyLightCalculation) {
                                setWithoutChunkCheck(skyLight, worldX, skyLightSourceY, worldZ, 15);
                            } else {
                                pendingSkyLightUpdates.add(() -> skyLightPropagator.setLightAndPropagate(worldX, skyLightSourceY, worldZ, 0, 15));
                            }
                        }
                    }
                }
            }
        }

        pendingBlockLightUpdates.forEach(blockLightUpdateQueue::offer);
        if (dimensionInfo.hasSkyLight()) {
            pendingSkyLightUpdates.forEach(skyLightUpdateQueue::offer);
        }
    }

    protected boolean canCalculateLightInChunk(int chunkX, int chunkZ) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

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
        return get(skyLight, x, y, z, 0);
    }

    protected short getLightHeight(int x, int z) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        return lightHeightMap.get(hash).get(x & 15, z & 15);
    }

    protected void setLightHeight(int x, int z, short value) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        lightHeightMap.get(hash).set(x & 15, z & 15, value);
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
        // Reduce memory usage by packing light data into a single byte
        var packedLightData = packLightData(le, ld);
        chunkAndBlockUpdateQueue.offer(() -> {
            var chunkLightCalculated = !awaitingLightCalculationChunks.contains(HashUtils.hashXZ(x >> 4, z >> 4));
            var lightDampening = unpackLightDampening(packedLightData);
            var lightEmissionValue = unpackLightEmission(packedLightData);
            int oldLightHeight = dimensionInfo.hasSkyLight() ? getLightHeight(x, z) : 0;
            var oldBlockDampening = getLightDampening(x, y, z);
            if (oldBlockDampening == lightDampening) {
                return;
            }

            setLightDampening(x, y, z, lightDampening);
            setWithoutChunkCheck(lightEmission, x, y, z, lightEmissionValue);
            if (chunkLightCalculated) {
                blockLightUpdateQueue.offer(() -> blockLightPropagator.setLightAndPropagate(x, y, z, getBlockLight(x, y, z), lightEmissionValue));
                if (!dimensionInfo.hasSkyLight()) {
                    return;
                }

                var newLightHeight = getLightHeight(x, z);
                if (newLightHeight > oldLightHeight) {
                    for (int i = oldLightHeight; i < newLightHeight; i++) {
                        final int lightHeight = i;
                        skyLightUpdateQueue.offer(() -> skyLightPropagator.setLightAndPropagate(x, lightHeight, z, 15, 0));
                    }
                } else if (newLightHeight < oldLightHeight) {
                    for (int i = newLightHeight; i < oldLightHeight; i++) {
                        final int lightHeight = i;
                        skyLightUpdateQueue.offer(() -> skyLightPropagator.setLightAndPropagate(x, lightHeight, z, 0, 15));
                    }
                }
            }
        });
    }

    public void onChunkLoad(Chunk chunk) {
        chunkAndBlockUpdateQueue.offer(() -> {
            chunk.applyOperation(this::addChunk, OperationType.READ, OperationType.NONE);
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
                            calculateLightInChunk(chunk.getX() + i, chunk.getZ() + j);
                            awaitingLightCalculationChunks.remove(neighborChunkHash);
                        }
                    }
                }
            }
        });
    }

    public void onChunkUnload(Chunk chunk) {
        chunkAndBlockUpdateQueue.offer(() -> {
            var hash = HashUtils.hashXZ(chunk.getX(), chunk.getZ());
            lightDampening.remove(hash);
            lightEmission.remove(hash);
            blockLight.remove(hash);
            if (dimensionInfo.hasSkyLight()) {
                lightHeightMap.remove(hash);
                skyLight.remove(hash);
            }
            chunks.remove(hash);
            awaitingLightCalculationChunks.remove(hash);
        });
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

    protected int getLightDampening(int x, int y, int z) {
        return getWithoutChunkCheck(lightDampening, x, y, z);
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
                if (getLightDampening(x, i, z) != 0) {
                    newLightHeight = (short) (i + 1);
                    break;
                }
            }
            setLightHeight(x, z, newLightHeight);
        } else if (currentLightHeight <= y) {
            setLightHeight(x, z, (short) (y + 1));
        }
    }

    public static int calculateSkylightReduction(long time, Set<Weather> weathers) {
        double d = 1.0 - ((weathers.contains(Weather.RAIN) ? 1 : 0) * 5.0) / 16.0;
        double e = 1.0 - ((weathers.contains(Weather.THUNDER) ? 1 : 0) * 5.0) / 16.0;
        double f = 0.5 + 2.0 * Math.clamp(MathUtils.fastCos(calculateCelestialAngle(time) * 6.2831855), -0.25, 0.25);
        return (int) ((1.0 - f * d * e) * 11.0);
    }

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

    protected class BlockLightDataAccessor implements LightDataAccessor {
        @Override
        public int getLight(int x, int y, int z) {
            return getWithoutChunkCheck(blockLight, x, y, z);
        }

        @Override
        public int getLightDampening(int x, int y, int z) {
            return getWithoutChunkCheck(lightDampening, x, y, z);
        }

        @Override
        public void setLight(int x, int y, int z, int lightValue) {
            setWithoutChunkCheck(blockLight, x, y, z, lightValue);
        }

        @Override
        public boolean isChunkLoaded(int cx, int cz) {
            return chunks.contains(HashUtils.hashXZ(cx, cz));
        }

        @Override
        public boolean isYInRange(int y) {
            return y >= dimensionInfo.minHeight() && y <= dimensionInfo.maxHeight();
        }
    }

    protected class SkyLightDataAccessor implements LightDataAccessor {
        @Override
        public int getLight(int x, int y, int z) {
            return getWithoutChunkCheck(skyLight, x, y, z);
        }

        @Override
        public int getLightDampening(int x, int y, int z) {
            return getWithoutChunkCheck(lightDampening, x, y, z);
        }

        @Override
        public void setLight(int x, int y, int z, int lightValue) {
            setWithoutChunkCheck(skyLight, x, y, z, lightValue);
        }

        @Override
        public boolean isChunkLoaded(int cx, int cz) {
            return chunks.contains(HashUtils.hashXZ(cx, cz));
        }

        @Override
        public boolean isYInRange(int y) {
            return y >= dimensionInfo.minHeight() && y <= dimensionInfo.maxHeight();
        }
    }
}
