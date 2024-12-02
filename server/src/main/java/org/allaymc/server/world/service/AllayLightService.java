package org.allaymc.server.world.service;

import io.netty.util.internal.PlatformDependent;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.Weather;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.heightmap.HeightMap;
import org.allaymc.api.world.service.LightService;
import org.allaymc.server.datastruct.ChunkSectionNibbleArray;
import org.allaymc.server.datastruct.collections.queue.BlockingQueueWrapper;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class AllayLightService implements LightService {

    protected static final int MAX_LIGHT_UPDATE_COUNT_PER_TICK = Server.SETTINGS.worldSettings().maxLightUpdateCountPerTick();
    protected final DimensionInfo dimensionInfo;
    protected final Supplier<Integer> timeSupplier;
    protected final Supplier<Set<Weather>> weatherSupplier;
    protected final int minHeight;
    protected final int maxHeight;
    protected final int sectionCount;
    protected final boolean hasSkyLight;
    protected final BlockingQueueWrapper<Runnable> queue;
    protected final LongSet chunks;
    protected final Long2ObjectMap<ChunkSectionNibbleArray[]> lightDampening;
    protected final Long2ObjectMap<ChunkSectionNibbleArray[]> blockLight;
    protected final LightPropagator blockLightPropagator;
    protected Long2ObjectMap<HeightMap> lightHeightMap;
    protected Long2ObjectMap<ChunkSectionNibbleArray[]> skyLight;
    protected LightPropagator skyLightPropagator;

    public AllayLightService(Dimension dimension) {
        this(dimension.getDimensionInfo(), dimension.getWorld().getWorldData()::getTimeOfDay, dimension.getWorld()::getWeathers);
    }

    public AllayLightService(DimensionInfo dimensionInfo, Supplier<Integer> timeSupplier, Supplier<Set<Weather>> weatherSupplier) {
        this.dimensionInfo = dimensionInfo;
        this.timeSupplier = timeSupplier;
        this.weatherSupplier = weatherSupplier;
        this.minHeight = dimensionInfo.minHeight();
        this.maxHeight = dimensionInfo.maxHeight();
        this.sectionCount = dimensionInfo.chunkSectionCount();
        this.hasSkyLight = dimensionInfo.hasSkyLight();
        this.queue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
        this.chunks = new LongOpenHashSet();
        this.lightDampening = new Long2ObjectOpenHashMap<>();
        this.blockLight = new Long2ObjectOpenHashMap<>();
        this.blockLightPropagator = new LightPropagator(new BlockLightAccessor());
        if (hasSkyLight) {
            this.lightHeightMap = new Long2ObjectOpenHashMap<>();
            this.skyLight = new Long2ObjectOpenHashMap<>();
            this.skyLightPropagator = new LightPropagator(new SkyLightAccessor());
        }
    }

    public void tick() {
        int processedCount = 0;
        Runnable runnable;
        while (processedCount < MAX_LIGHT_UPDATE_COUNT_PER_TICK && (runnable = queue.pollNow()) != null) {
            runnable.run();
            processedCount++;
        }
    }

    public void tickIgnoreLimit() {
        Runnable runnable;
        while ((runnable = queue.tryPoll(1, TimeUnit.SECONDS)) != null) {
            runnable.run();
        }
    }

    public void tickIgnoreLimitUnblocking() {
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
    protected void addChunk(Chunk chunk) {
        ChunkSectionNibbleArray[] chunkLightDampening = createNibbleArrays();
        var chunkLightHeightMap = hasSkyLight ? new HeightMap((short) dimensionInfo.minHeight()) : null;
        AllayUnsafeChunk unsafeChunk = (AllayUnsafeChunk) chunk.toUnsafeChunk();
        for (int y = maxHeight; y >= minHeight; y--) {
            var section = unsafeChunk.getSection(y >> 4);
            if (section == null) {
                y -= 15;
                continue;
            }
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    var blockStateData = section.getBlockState(x, y & 0xf, z, 0).getBlockStateData();
                    var lightEmission = blockStateData.lightEmission();
                    final int finalX = (chunk.getX() << 4) + x;
                    final int finalY = y;
                    final int finalZ = (chunk.getZ() << 4) + z;
                    if (lightEmission != 0) {
                        queue.offer(() -> blockLightPropagator.setLightAndPropagate(finalX, finalY, finalZ, 0, lightEmission));
                    }
                    var lightDampening = blockStateData.lightDampening();
                    if (lightDampening == 0) continue;

                    chunkLightDampening[(y - minHeight) >> 4].set(x, y & 0xf, z, lightDampening);
                    if (chunkLightHeightMap != null && chunkLightHeightMap.get(x, z) == minHeight) {
                        chunkLightHeightMap.set(x, z, (short) (y + 1));
                        queue.offer(() -> skyLightPropagator.setLightAndPropagate(finalX, finalY + 1, finalZ, 0, 15));
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
                    var blockLight = getBlockLight(finalX, finalY, finalZ);
                    if (blockLight != 0) {
                        queue.offer(() -> blockLightPropagator.setLightAndPropagate(finalX, finalY, finalZ, blockLight, blockLight));
                    }
                    if (!hasSkyLight) continue;

                    var skyLightValue = get(skyLight, finalX, finalY, finalZ, 0);
                    if (skyLightValue != 0) {
                        queue.offer(() -> skyLightPropagator.setLightAndPropagate(finalX, finalY, finalZ, skyLightValue, skyLightValue));
                    }
                }
            }
        }
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
        return y >= getLightHeight(x, z) ? 15 : get(skyLight, x, y, z, 0);
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

    public void onBlockChange(int x, int y, int z, int lightEmission, int lightDampening) {
        queue.offer(() -> {
            int oldLightHeight = hasSkyLight ? getLightHeight(x, z) : 0;
            var oldBlockDampening = getLightDampening(x, y, z);
            if (oldBlockDampening != lightDampening) {
                setLightDampening(x, y, z, lightDampening);
                if (hasSkyLight) {
                    if (lightDampening != 0) {
                        skyLightPropagator.setLightAndPropagate(x, y, z, get(skyLight, x, y, z, 0), 0);
                    }
                    var newLightHeight = getLightHeight(x, z);
                    if (oldLightHeight != newLightHeight) {
                        // Skylight source is changed
                        // Remove the old source
                        skyLightPropagator.setLightAndPropagate(x, oldLightHeight, z, 15, 0);
                        // Set the new source
                        skyLightPropagator.setLightAndPropagate(x, newLightHeight, z, 0, 15);
                    }
                }
            }
            blockLightPropagator.setLightAndPropagate(x, y, z, getBlockLight(x, y, z), lightEmission);
        });
    }

    public void onChunkLoad(Chunk chunk) {
        queue.offer(() -> addChunk(chunk));
    }

    public void onChunkUnload(Chunk chunk) {
        queue.offer(() -> {
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
        return queue.size();
    }

    protected int getLightDampening(int x, int y, int z) {
        return get(lightDampening, x, y, z, 0);
    }

    protected int getWithoutCheck(Long2ObjectMap<ChunkSectionNibbleArray[]> target, int x, int y, int z) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        var array = target.get(hash);
        return array[(y - minHeight) >> 4].get(x & 15, y & 15, z & 15);
    }

    protected int get(Long2ObjectMap<ChunkSectionNibbleArray[]> target, int x, int y, int z, int defaultValue) {
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

    protected void set(Long2ObjectMap<ChunkSectionNibbleArray[]> target, int x, int y, int z, int value) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        if (!chunks.contains(hash)) {
            return;
        }
        var array = target.get(hash);
        array[(y - minHeight) >> 4].set(x & 15, y & 15, z & 15, value);
    }

    protected void setWithoutCheck(Long2ObjectMap<ChunkSectionNibbleArray[]> target, int x, int y, int z, int value) {
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

    protected class BlockLightAccessor implements LightAccessor {
        @Override
        public int getLight(int x, int y, int z) {
            return getWithoutCheck(blockLight, x, y, z);
        }

        @Override
        public int getLightDampening(int x, int y, int z) {
            return getWithoutCheck(lightDampening, x, y, z);
        }

        @Override
        public void setLight(int x, int y, int z, int lightValue) {
            setWithoutCheck(blockLight, x, y, z, lightValue);
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
            return getWithoutCheck(skyLight, x, y, z);
        }

        @Override
        public int getLightDampening(int x, int y, int z) {
            return getWithoutCheck(lightDampening, x, y, z);
        }

        @Override
        public void setLight(int x, int y, int z, int lightValue) {
            setWithoutCheck(skyLight, x, y, z, lightValue);
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
