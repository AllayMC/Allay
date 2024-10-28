package org.allaymc.server.world.service;

import io.netty.util.internal.PlatformDependent;
import it.unimi.dsi.fastutil.longs.*;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.Weather;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.heightmap.HeightMap;
import org.allaymc.api.world.service.LightService;
import org.allaymc.server.datastruct.ChunkSectionNibbleArray;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;

import java.util.Queue;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public class AllayLightService implements LightService {

    // TODO: move skylight calculation logic to independent class
    // TODO(perf): max update count per tick
    protected static final BlockFace[] BLOCK_FACE_UPDATE_ORDER = BlockFace.values();
    protected Dimension dimension;
    protected final int minHeight;
    protected final int maxHeight;
    protected final int sectionCount;
    protected final boolean hasSkyLight;
    protected final Long2ObjectMap<ChunkSectionNibbleArray[]> lightDampening;
    protected final Long2ObjectMap<ChunkSectionNibbleArray[]> blockLight;
    protected final LongSet chunks = new LongOpenHashSet();
    // TODO(perf): replace with long queue
    protected final Queue<Runnable> queue = PlatformDependent.newMpscQueue();
    protected final Queue<LightUpdateEntry> lightIncreaseQueue = PlatformDependent.newMpscQueue();
    protected final Queue<LightUpdateEntry> lightDecreaseQueue = PlatformDependent.newMpscQueue();
    protected Long2ObjectMap<HeightMap> lightHeightMap;
    protected Long2ObjectMap<ChunkSectionNibbleArray[]> skyLight;

    public AllayLightService(Dimension dimension) {
        this.dimension = dimension;
        this.minHeight = dimension.getDimensionInfo().minHeight();
        this.maxHeight = dimension.getDimensionInfo().maxHeight();
        this.sectionCount = dimension.getDimensionInfo().chunkSectionCount();
        this.hasSkyLight = dimension.getDimensionInfo().hasSkyLight();
        this.lightDampening = new Long2ObjectOpenHashMap<>();
        this.blockLight = new Long2ObjectOpenHashMap<>();
        if (hasSkyLight) {
            this.lightHeightMap = new Long2ObjectOpenHashMap<>();
            this.skyLight = new Long2ObjectOpenHashMap<>();
        }
    }

    public void tick() {
        Runnable runnable;
        while ((runnable = queue.poll()) != null) {
            runnable.run();
        }
    }

    protected void addChunk(Chunk chunk) {
        ChunkSectionNibbleArray[] chunkLightDampening = createNibbleArrays();
        var chunkLightHeightMap = new HeightMap((short) (dimension.getDimensionInfo().minHeight() - 1));
        AllayUnsafeChunk unsafeChunk = (AllayUnsafeChunk) chunk.toUnsafeChunk();
        for (int y = maxHeight; y >= minHeight; y--) {
            var sectionY = y >> 4;
            var section = unsafeChunk.getSection(sectionY);
            if (section == null) {
                y -= 15;
                continue;
            }
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    var blockStateData = section.getBlockState(x, y & 0xf, z, 0).getBlockStateData();
                    var lightEmission = blockStateData.lightEmission();
                    if (lightEmission != 0) {
                        int finalX = x;
                        int finalY = y;
                        int finalZ = z;
                        queue.add(() -> setBlockLightAndUpdateAround((chunk.getX() << 4) + finalX, finalY, (chunk.getZ() << 4) + finalZ, lightEmission));
                    }
                    var lightDampening = blockStateData.lightDampening();
                    if (lightDampening != 0) {
                        chunkLightDampening[sectionY].set(x, y & 0xf, z, lightDampening);
                        if (chunkLightHeightMap.get(x, z) == minHeight - 1) {
                            chunkLightHeightMap.set(x, z, (short) y);
                        }
                    }
                }
            }
        }
    }

    protected ChunkSectionNibbleArray[] createNibbleArrays() {
        ChunkSectionNibbleArray[] nibbleArrays = new ChunkSectionNibbleArray[sectionCount];
        for (int i = 0; i < sectionCount; i++) {
            nibbleArrays[i] = new ChunkSectionNibbleArray();
        }
        return nibbleArrays;
    }

    @Override
    public int getSkyLight(int x, int y, int z) {
        return hasSkyLight ? get(skyLight, x, y, z, 15) : 0;
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
        return hasSkyLight ? Math.max(0, getSkyLight(x, y, z) - calculateSkylightReduction(dimension.getWorld().getWorldData().getTime(), dimension.getWorld().getWeathers())) : 0;
    }

    @Override
    public void onBlockChange(int x, int y, int z, int lightEmission, int lightDampening) {
        queue.add(() -> {
            setLightDampening(x, y, z, lightDampening);
            if (getBlockLight(x, y, z) != lightEmission || getLightDampening(x, y, z) != lightDampening) {
                // Update only if lightEmission or lightDampening are changed
                setBlockLightAndUpdateAround(x, y, z, lightEmission);
            }
        });
    }

    protected void setBlockLightAndUpdateAround(int x, int y, int z, int lightValue) {
        var oldLightValue = getBlockLight(x, y, z);
        // this is very important: we need to set the light value
        // for positions we add into the queue. the propagator WILL NOT
        // do it for us! Remember, it only sets NEIGHBOUR light values
        setBlockLight(x, y, z, lightValue);
        lightIncreaseQueue.add(new LightUpdateEntry(x, y, z, lightValue));
        if (lightValue > oldLightValue) {
            propagateIncrease();
        } else if (lightValue < oldLightValue) {
            propagateDecrease();
        }
    }

    protected void propagateIncrease() {
        while(!lightIncreaseQueue.isEmpty()) {
            var entry = lightIncreaseQueue.poll();
            var x = entry.x();
            var y = entry.y();
            var z = entry.z();
            var lightValue = entry.lightValue();
            for (var blockFace : BLOCK_FACE_UPDATE_ORDER) {
                var offset = blockFace.getOffset();
                var ox = x + offset.x();
                var oy = y + offset.y();
                var oz = z + offset.z();
                int neighborLightValue = getBlockLight(ox, oy, oz);
                if (neighborLightValue >= (lightValue - 1)) {
                    // quick short circuit for when the light value is already greater-than where we could set it
                    continue;
                }
                int newNeighborLightValue = lightValue - Math.max(1, getLightDampening(ox, oy, oz));
                if (newNeighborLightValue > neighborLightValue) {
                    // sometimes the neighbour is brighter, maybe it's a source we're propagating.
                    setBlockLight(ox, oy, oz, newNeighborLightValue);
                    lightIncreaseQueue.add(new LightUpdateEntry(ox, oy, oz, newNeighborLightValue));
                }
            }
        }
    }

    protected void propagateDecrease() {
        while(!lightDecreaseQueue.isEmpty()) {
            var entry = lightDecreaseQueue.poll();
            var x = entry.x();
            var y = entry.y();
            var z = entry.z();
            var lightValue = entry.lightValue();
            for (var blockFace : BLOCK_FACE_UPDATE_ORDER) {
                var offset = blockFace.getOffset();
                var ox = x + offset.x();
                var oy = y + offset.y();
                var oz = z + offset.z();
                int currentNeighborLightValue = getBlockLight(ox, oy, oz);
                if (currentNeighborLightValue != 0 && currentNeighborLightValue < lightValue) {
                    setBlockLight(ox, oy, oz, 0);
                    lightDecreaseQueue.add(new LightUpdateEntry(ox, oy, oz, 0));
                } else if (currentNeighborLightValue >= lightValue) {
                    lightIncreaseQueue.add(new LightUpdateEntry(ox, oy, oz, currentNeighborLightValue));
                }
            }
        }
        propagateIncrease();
    }

    @Override
    public void onChunkLoad(Chunk chunk) {
        queue.add(() -> addChunk(chunk));
    }

    @Override
    public void onChunkUnload(Chunk chunk) {
        queue.add(() -> {
            var hash = HashUtils.hashXZ(chunk.getX(), chunk.getZ());
            lightDampening.remove(hash);
            blockLight.remove(hash);
            if (hasSkyLight) {
                skyLight.remove(hash);
            }
        });
    }

    protected int getLightDampening(int x, int y, int z) {
        return get(lightDampening, x, y, z, 0);
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
    }

    protected void set(Long2ObjectMap<ChunkSectionNibbleArray[]> target, int x, int y, int z, int value) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        if (!chunks.contains(hash)) {
            return;
        }
        var array = target.get(HashUtils.hashXZ(x >> 4, z >> 4));
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
        return value - (double)lfloor(value);
    }

    protected static long lfloor(double value) {
        long l = (long)value;
        return value < (double)l ? l - 1L : l;
    }

    protected record LightUpdateEntry(int x, int y, int z, int lightValue) {}
}
