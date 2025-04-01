package org.allaymc.server.world.service;

import com.google.common.base.Preconditions;
import io.netty.util.internal.PlatformDependent;
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
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
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
    protected final int minHeight;
    protected final int maxHeight;
    protected final int sectionCount;
    protected final boolean hasSkyLight;
    protected final BlockingQueueWrapper<Runnable> chunkAndBlockUpdateQueue;
    protected final BlockingQueueWrapper<Runnable> blockLightUpdateQueue;
    protected final Set<Long> chunks;
    protected final NonBlockingHashMapLong<ChunkSectionNibbleArray[]> lightDampening;
    protected final NonBlockingHashMapLong<ChunkSectionNibbleArray[]> blockLight;
    protected final LightPropagator blockLightPropagator;

    protected BlockingQueueWrapper<Runnable> skyLightUpdateQueue;
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
        this.minHeight = dimensionInfo.minHeight();
        this.maxHeight = dimensionInfo.maxHeight();
        this.sectionCount = dimensionInfo.chunkSectionCount();
        this.hasSkyLight = dimensionInfo.hasSkyLight();
        this.chunkAndBlockUpdateQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
        this.blockLightUpdateQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
        this.chunks = new CopyOnWriteArraySet<>();
        this.lightDampening = new NonBlockingHashMapLong<>();
        this.blockLight = new NonBlockingHashMapLong<>();
        this.blockLightPropagator = new LightPropagator(new BlockLightAccessor());
        if (hasSkyLight) {
            this.skyLightUpdateQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
            this.lightHeightMap = new NonBlockingHashMapLong<>();
            this.skyLight = new NonBlockingHashMapLong<>();
            this.skyLightPropagator = new LightPropagator(new SkyLightAccessor());
        }
    }

    public void startTick() {
        var dimensionName = worldName + ":" + dimensionInfo.toString();
        startCalculatingThread("Light Calculating Thread (Chunk & Block) - " + dimensionName, chunkAndBlockUpdateQueue);
        startCalculatingThread("Light Calculating Thread (Block Light) - " + dimensionName, blockLightUpdateQueue);
        if (hasSkyLight) {
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
    public void handleUpdateUninterruptedlyInAllQueues() {
        handleUpdateUninterruptedlyIn(chunkAndBlockUpdateQueue);
        handleUpdateUninterruptedlyIn(blockLightUpdateQueue);
        if (hasSkyLight) {
            handleUpdateUninterruptedlyIn(skyLightUpdateQueue);
        }
    }

    protected void handleUpdateUninterruptedlyIn(BlockingQueueWrapper<Runnable> queue) {
        Runnable runnable;
        while ((runnable = queue.pollNow()) != null) {
            runnable.run();
        }
    }

    /**
     * Add and calculate the light of a chunk.
     *
     * @param chunk the chunk.
     */
    protected void addChunk(UnsafeChunk chunk) {
        ChunkSectionNibbleArray[] chunkLightDampening = createNibbleArrays();
        var blockLightUpdates = new ArrayList<Runnable>();
        var skyLightUpdates = new ArrayList<Runnable>();
        var chunkLightHeightMap = hasSkyLight ? new HeightMap((short) minHeight) : null;

        for (int y = maxHeight; y >= minHeight; y--) {
            var section = (AllayChunkSection) chunk.getSection(y >> 4);
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    var blockStateData = section.getBlockState(x, y & 0xf, z, 0).getBlockStateData();
                    final int finalX = (chunk.getX() << 4) + x;
                    final int finalY = y;
                    final int finalZ = (chunk.getZ() << 4) + z;

                    byte lightEmission = (byte) blockStateData.lightEmission();
                    if (lightEmission != 0) {
                        blockLightUpdates.add(() -> blockLightPropagator.setLightAndPropagate(finalX, finalY, finalZ, 0, lightEmission));
                    }

                    boolean isSkyLightSourceAbove = hasSkyLight && y != maxHeight && chunkLightHeightMap.get(x, z) == minHeight;
                    if (isSkyLightSourceAbove) {
                        skyLightUpdates.add(() -> skyLightPropagator.setLightAndPropagate(finalX, finalY + 1, finalZ, 0, 15));
                    }

                    var lightDampening = blockStateData.lightDampening();
                    if (lightDampening != 0) {
                        chunkLightDampening[(y - minHeight) >> 4].set(x, y & 0xf, z, lightDampening);
                        if (isSkyLightSourceAbove) {
                            chunkLightHeightMap.set(x, z, (short) (y + 1));
                        }
                    }
                }
            }
        }

        var hash = HashUtils.hashXZ(chunk.getX(), chunk.getZ());
        lightDampening.put(hash, chunkLightDampening);
        blockLight.put(hash, createNibbleArrays());
        if (hasSkyLight) {
            lightHeightMap.put(hash, chunkLightHeightMap);
            skyLight.put(hash, createNibbleArrays());
        }
        chunks.add(hash);

        // Let neighbor chunks' light propagate to this chunk
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            var ox = face.getOffset().x();
            var oz = face.getOffset().z();
            var cx = chunk.getX() + ox;
            var cz = chunk.getZ() + oz;
            if (!chunks.contains(HashUtils.hashXZ(cx, cz))) {
                continue;
            }
            for (int y = minHeight; y <= maxHeight; y++) {
                for (int i = 0; i <= 15; i++) {
                    final var finalX = (cx << 4) + ox != 0 ? 15 : i;
                    final var finalY = y;
                    final var finalZ = (cz << 4) + oz != 0 ? 15 : i;
                    byte blockLight = (byte) getBlockLight(finalX, finalY, finalZ);
                    if (blockLight != 0) {
                        blockLightUpdates.add(() -> blockLightPropagator.setLightAndPropagate(finalX, finalY, finalZ, blockLight, blockLight));
                    }
                    if (hasSkyLight) {
                        byte skyLightValue = (byte) get(skyLight, finalX, finalY, finalZ, 0);
                        if (skyLightValue != 0) {
                            skyLightUpdates.add(() -> skyLightPropagator.setLightAndPropagate(finalX, finalY, finalZ, skyLightValue, skyLightValue));
                        }
                    }
                }
            }
        }

        // Add the light updates to the queue
        blockLightUpdates.forEach(blockLightUpdateQueue::offer);
        skyLightUpdates.forEach(skyLightUpdateQueue::offer);
    }

    /**
     * Create nibble arrays based on section count.
     *
     * @return the nibble arrays.
     */
    protected ChunkSectionNibbleArray[] createNibbleArrays() {
        ChunkSectionNibbleArray[] nibbleArrays = new ChunkSectionNibbleArray[sectionCount];
        for (int i = 0; i < sectionCount; i++) {
            nibbleArrays[i] = new ChunkSectionNibbleArray();
        }
        return nibbleArrays;
    }

    @Override
    public int getSkyLight(int x, int y, int z) {
        if (!hasSkyLight) {
            return 0;
        }
        return get(skyLight, x, y, z, 0);
    }

    protected short getLightHeight(int x, int z) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        if (!chunks.contains(hash)) {
            return (short) minHeight;
        }
        return lightHeightMap.get(hash).get(x & 15, z & 15);
    }

    protected void setLightHeight(int x, int z, short value) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        if (!chunks.contains(hash)) {
            return;
        }
        lightHeightMap.get(hash).set(x & 15, z & 15, value);
    }

    @Override
    public int getBlockLight(int x, int y, int z) {
        return get(blockLight, x, y, z, 0);
    }

    @Override
    public int getInternalLight(int x, int y, int z) {
        return hasSkyLight ? Math.max(getInternalSkyLight(x, y, z), getBlockLight(x, y, z)) : getBlockLight(x, y, z);
    }

    @Override
    public int getInternalSkyLight(int x, int y, int z) {
        return hasSkyLight ? Math.max(0, getSkyLight(x, y, z) - calculateSkylightReduction(timeSupplier.get(), weatherSupplier.get())) : 0;
    }

    public void onBlockChange(int x, int y, int z, int le, int ld) {
        // Reduce memory usage by packing light data into a single byte
        var packedLightData = packLightData(le, ld);
        chunkAndBlockUpdateQueue.offer(() -> {
            var lightDampening = unpackLightDampening(packedLightData);
            int oldLightHeight = hasSkyLight ? getLightHeight(x, z) : 0;
            var oldBlockDampening = getLightDampening(x, y, z);
            if (oldBlockDampening != lightDampening) {
                setLightDampening(x, y, z, lightDampening);
                if (hasSkyLight) {
                    skyLightPropagator.setLightAndPropagate(x, y, z, get(skyLight, x, y, z, 0), 0);
                    var newLightHeight = getLightHeight(x, z);
                    if (newLightHeight > oldLightHeight) {
                        for (int i = oldLightHeight; i < newLightHeight; i++) {
                            final int j = i;
                            skyLightUpdateQueue.offer(() -> skyLightPropagator.setLightAndPropagate(x, j, z, 15, 0));
                        }
                    } else if (newLightHeight < oldLightHeight) {
                        for (int i = newLightHeight; i < oldLightHeight; i++) {
                            final int j = i;
                            skyLightUpdateQueue.offer(() -> skyLightPropagator.setLightAndPropagate(x, j, z, 0, 15));
                        }
                    }
                }
            }
            blockLightUpdateQueue.offer(() -> blockLightPropagator.setLightAndPropagate(x, y, z, getBlockLight(x, y, z), unpackLightEmission(packedLightData)));
        });
    }

    public void onChunkLoad(Chunk chunk) {
        chunkAndBlockUpdateQueue.offer(() -> chunk.applyOperation(this::addChunk, OperationType.READ, OperationType.NONE));
    }

    public void onChunkUnload(Chunk chunk) {
        chunkAndBlockUpdateQueue.offer(() -> {
            var hash = HashUtils.hashXZ(chunk.getX(), chunk.getZ());
            lightDampening.remove(hash);
            blockLight.remove(hash);
            if (hasSkyLight) {
                lightHeightMap.remove(hash);
                skyLight.remove(hash);
            }
            chunks.remove(hash);
        });
    }

    @Override
    public int getQueuedUpdateCount() {
        return chunkAndBlockUpdateQueue.size() + blockLightUpdateQueue.size() + (skyLightUpdateQueue != null ? skyLightUpdateQueue.size() : 0);
    }

    protected int getLightDampening(int x, int y, int z) {
        return get(lightDampening, x, y, z, 0);
    }

    protected int getWithoutChunkCheck(NonBlockingHashMapLong<ChunkSectionNibbleArray[]> target, int x, int y, int z) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        var array = target.get(hash);
        return array[(y - minHeight) >> 4].get(x & 15, y & 15, z & 15);
    }

    protected int get(NonBlockingHashMapLong<ChunkSectionNibbleArray[]> target, int x, int y, int z, int defaultValue) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        if (!chunks.contains(hash)) {
            return defaultValue;
        }
        var array = target.get(hash);
        return array[(y - minHeight) >> 4].get(x & 15, y & 15, z & 15);
    }

    protected void setBlockLight(int x, int y, int z, int value) {
        set(blockLight, x, y, z, value);
    }

    protected void setLightDampening(int x, int y, int z, int value) {
        set(lightDampening, x, y, z, value);
        if (!hasSkyLight) return;

        var currentLightHeight = getLightHeight(x, z);
        if (value == 0 && currentLightHeight >= y + 1) {
            short newLightHeight = (short) minHeight;
            for (short i = currentLightHeight; i >= minHeight; i--) {
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

    protected void set(NonBlockingHashMapLong<ChunkSectionNibbleArray[]> target, int x, int y, int z, int value) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        if (!chunks.contains(hash)) {
            return;
        }
        var array = target.get(hash);
        array[(y - minHeight) >> 4].set(x & 15, y & 15, z & 15, value);
    }

    protected void setWithoutChunkCheck(NonBlockingHashMapLong<ChunkSectionNibbleArray[]> target, int x, int y, int z, int value) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        var array = target.get(hash);
        array[(y - minHeight) >> 4].set(x & 15, y & 15, z & 15, value);
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

    protected class BlockLightAccessor implements LightAccessor {
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
            return y >= minHeight && y <= maxHeight;
        }
    }

    protected class SkyLightAccessor implements LightAccessor {
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
            return y >= minHeight && y <= maxHeight;
        }
    }
}
