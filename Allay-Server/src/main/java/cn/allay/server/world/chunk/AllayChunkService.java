package cn.allay.server.world.chunk;

import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.World;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.chunk.ChunkLoader;
import cn.allay.api.world.chunk.ChunkService;
import cn.allay.api.world.generator.WorldGenerationService;
import cn.allay.api.world.storage.WorldStorage;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Function;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class AllayChunkService implements ChunkService {

    public static final int REMOVE_UNNEEDED_CHUNK_CYCLE = 100;

    private final Map<Long, Chunk> loadedChunks = new Long2ObjectOpenHashMap<>();
    private final Map<Long, CompletableFuture<Chunk>> loadingChunks = new ConcurrentHashMap<>();
    @Getter
    private final World world;
    @Getter
    private final WorldGenerationService worldGenerationService;
    @Getter
    private final WorldStorage worldStorage;
    private final Map<ChunkLoader, Set<Long>> chunkLoaders = new ConcurrentHashMap<>();
    private final Queue<ChunkAddingQueueEntry> chunkAddingQueue = new ConcurrentLinkedQueue<>();
    private long tickCounter = 0;

    public AllayChunkService(World world, Function<ChunkService, WorldGenerationService> worldGenerationServiceSupplier, WorldStorage worldStorage) {
        this.world = world;
        this.worldGenerationService = worldGenerationServiceSupplier.apply(this);
        this.worldStorage = worldStorage;
    }

    @Override
    public void tick() {
        tickCounter++;
        addChunks();
        updateChunks();
    }

    private void addChunks() {
        while (!chunkAddingQueue.isEmpty()) {
            var entry = chunkAddingQueue.poll();
            loadedChunks.put(entry.hashXZ(), entry.chunk());
            loadingChunks.remove(entry.hashXZ());
        }
    }

    private void updateChunks() {
        var neededChunkXZ = new HashSet<Long>();
        for (var chunkLoaderEntry : chunkLoaders.entrySet()) {
            var chunkLoader = chunkLoaderEntry.getKey();
            if (!chunkLoader.isLoaderActive()) continue;
            var loaderChunkX = chunkLoader.getLocation().getChunkX();
            var loaderChunkZ = chunkLoader.getLocation().getChunkZ();
            var viewDistance = world.getViewDistance();
            for (int rx = -viewDistance; rx <= viewDistance; rx++) {
                for (int rz = -viewDistance; rz <= viewDistance; rz++) {
                    if (rx * rx + rz * rz > viewDistance * viewDistance) continue;
                    neededChunkXZ.add(HashUtils.hashXZ(loaderChunkX + rx, loaderChunkZ + rz));
                }
            }
        }
        var loadedOrLoadingChunkXZ = new HashSet<>(loadedChunks.keySet());
        loadedOrLoadingChunkXZ.addAll(loadingChunks.keySet());
        var needToLoadChunkXZ = new HashSet<>(neededChunkXZ);
        needToLoadChunkXZ.removeAll(loadedOrLoadingChunkXZ);

        for (var hashXZ : needToLoadChunkXZ) {
            var x = HashUtils.getXFromHashXZ(hashXZ);
            var z = HashUtils.getZFromHashXZ(hashXZ);
            loadChunk(x, z);
        }

        if (tickCounter % REMOVE_UNNEEDED_CHUNK_CYCLE == 0) {
            var unneededChunkXZ = new HashSet<>(loadedOrLoadingChunkXZ);
            unneededChunkXZ.removeAll(neededChunkXZ);

            for (var hashXZ : unneededChunkXZ) {
                loadedChunks.remove(hashXZ);
            }
        }
    }

    @Override
    public void setChunk(int x, int z, Chunk chunk) {
        chunkAddingQueue.offer(new ChunkAddingQueueEntry(chunk, HashUtils.hashXZ(x, z)));
    }

    @Override
    public @Nullable Chunk getChunk(int x, int z) {
        return loadedChunks.get(HashUtils.hashXZ(x, z));
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return loadedChunks.containsKey(HashUtils.hashXZ(x, z));
    }

    public boolean isChunkLoaded(long hashXZ) {
        return loadedChunks.containsKey(hashXZ);
    }

    @Override
    public boolean isChunkLoading(int x, int z) {
        return loadingChunks.containsKey(HashUtils.hashXZ(x, z));
    }

    public boolean isChunkLoading(long hashXZ) {
        return loadingChunks.containsKey(hashXZ);
    }

    @Override
    @UnmodifiableView
    public Set<ChunkLoader> getChunkLoaders() {
        return Collections.unmodifiableSet(chunkLoaders.keySet());
    }

    @Override
    public void addChunkLoader(ChunkLoader chunkLoader) {
        this.chunkLoaders.put(chunkLoader, new LongOpenHashSet());
    }

    @Override
    public void removeChunkLoader(ChunkLoader chunkLoader) {
        this.chunkLoaders.remove(chunkLoader);
    }

    @Override
    public CompletableFuture<Chunk> getOrLoadChunk(int x, int z) {
        var chunk = getChunk(x, z);
        if (chunk != null) {
            return CompletableFuture.completedFuture(chunk);
        }
        return loadChunk(x, z);
    }

    private CompletableFuture<Chunk> loadChunk(int x, int z) {
        var hashXZ = HashUtils.hashXZ(x, z);
        var loadingChunk = loadingChunks.get(hashXZ);
        if (loadingChunk != null) {
            return loadingChunk;
        }
        var future = worldStorage
                .readChunk(x, z, world.getDimensionInfo())
                .thenApply(loadedChunk -> {
                    if (loadedChunk != null) {
                        setChunk(x, z, loadedChunk);
                        return loadedChunk;
                    }
                    var chunk = new AllayChunk(world.getDimensionInfo(), x, z);
                    worldGenerationService.submitGenerationTask(new SingleChunkLimitedWorldRegion(chunk), single -> setChunk(x, z, chunk));
                    return chunk;
                });
        loadingChunks.put(hashXZ, future);
        return future;
    }

    @Override
    public int maxX() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int maxZ() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int minX() {
        return Integer.MIN_VALUE;
    }

    @Override
    public int minZ() {
        return Integer.MIN_VALUE;
    }

    private record ChunkAddingQueueEntry(Chunk chunk, long hashXZ) {
    }
}
