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
import java.util.function.Consumer;
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
    private final Map<ChunkLoader, ChunkLoaderManager> chunkLoaders = new ConcurrentHashMap<>();
    private record ChunkAddingQueueEntry(Chunk chunk, long hashXZ, Consumer<Chunk> chunkAddingCallback) {}
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
        addInQueueChunks();
        tickChunkLoaders();
        //TODO: Remove unused chunk;
    }

    private void addInQueueChunks() {
        while (!chunkAddingQueue.isEmpty()) {
            var entry = chunkAddingQueue.poll();
            entry.chunkAddingCallback().accept(entry.chunk());
            loadedChunks.put(entry.hashXZ(), entry.chunk());
            loadingChunks.remove(entry.hashXZ());
        }
    }

    private void tickChunkLoaders() {
        for (var chunkLoaderManager : chunkLoaders.values()) {
            Thread.ofVirtual().start(chunkLoaderManager::tick);
        }
//        var neededChunkXZ = new HashSet<Long>();
//        for (var chunkLoaderEntry : chunkLoaders.entrySet()) {
//            var chunkLoader = chunkLoaderEntry.getKey();
//            if (!chunkLoader.isLoaderActive()) continue;
//            var loaderChunkX = chunkLoader.getLocation().getChunkX();
//            var loaderChunkZ = chunkLoader.getLocation().getChunkZ();
//            var viewDistance = world.getViewDistance();
//            for (int rx = -viewDistance; rx <= viewDistance; rx++) {
//                for (int rz = -viewDistance; rz <= viewDistance; rz++) {
//                    if (rx * rx + rz * rz > viewDistance * viewDistance) continue;
//                    neededChunkXZ.add(HashUtils.hashXZ(loaderChunkX + rx, loaderChunkZ + rz));
//                }
//            }
//        }
//        var loadedOrLoadingChunkXZ = new HashSet<>(loadedChunks.keySet());
//        loadedOrLoadingChunkXZ.addAll(loadingChunks.keySet());
//        var needToLoadChunkXZ = new HashSet<>(neededChunkXZ);
//        needToLoadChunkXZ.removeAll(loadedOrLoadingChunkXZ);
//
//        for (var hashXZ : needToLoadChunkXZ) {
//            var x = HashUtils.getXFromHashXZ(hashXZ);
//            var z = HashUtils.getZFromHashXZ(hashXZ);
//            loadChunk(x, z);
//        }
//
//        if (tickCounter % REMOVE_UNNEEDED_CHUNK_CYCLE == 0) {
//            var unneededChunkXZ = new HashSet<>(loadedOrLoadingChunkXZ);
//            unneededChunkXZ.removeAll(neededChunkXZ);
//
//            for (var hashXZ : unneededChunkXZ) {
//                loadedChunks.remove(hashXZ);
//            }
//        }
    }

    @Override
    public void setChunk(int x, int z, Chunk chunk) {
        setChunk(x, z, chunk, c -> {});
    }

    @Override
    public void setChunk(int x, int z, Chunk chunk, Consumer<Chunk> chunkAddingCallback) {
        chunkAddingQueue.offer(new ChunkAddingQueueEntry(chunk, HashUtils.hashXZ(x, z), chunkAddingCallback));
    }

    @Override
    public @Nullable Chunk getChunk(int x, int z) {
        return loadedChunks.get(HashUtils.hashXZ(x, z));
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return loadedChunks.containsKey(HashUtils.hashXZ(x, z));
    }

    @Override
    public boolean isChunkLoaded(long hashXZ) {
        return loadedChunks.containsKey(hashXZ);
    }

    @Override
    public boolean isChunkLoading(int x, int z) {
        return loadingChunks.containsKey(HashUtils.hashXZ(x, z));
    }

    @Override
    public boolean isChunkLoading(long hashXZ) {
        return loadingChunks.containsKey(hashXZ);
    }

    @Override
    public boolean isChunkUnloaded(int x, int z) {
        return isChunkUnloaded(HashUtils.hashXZ(x, z));
    }

    @Override
    public boolean isChunkUnloaded(long hashXZ) {
        return !isChunkLoaded(hashXZ) && !isChunkLoading(hashXZ);
    }

    @Override
    @UnmodifiableView
    public Set<ChunkLoader> getChunkLoaders() {
        return Collections.unmodifiableSet(chunkLoaders.keySet());
    }

    @Override
    public void addChunkLoader(ChunkLoader chunkLoader) {
        this.chunkLoaders.put(chunkLoader, new ChunkLoaderManager(chunkLoader));
    }

    @Override
    public void removeChunkLoader(ChunkLoader chunkLoader) {
        this.chunkLoaders.remove(chunkLoader);
    }

    @Override
    public CompletableFuture<Chunk> getOrLoadChunk(int x, int z) {
        return getOrLoadChunk(x, z, c -> {});
    }

    @Override
    public CompletableFuture<Chunk> getOrLoadChunk(int x, int z, Consumer<Chunk> chunkAddingCallback) {
        var chunk = getChunk(x, z);
        if (chunk != null) {
            return CompletableFuture.completedFuture(chunk);
        }
        return loadChunk(x, z, chunkAddingCallback);
    }

    @Override
    public CompletableFuture<Chunk> loadChunk(int x, int z) {
        return loadChunk(x, z, c -> {});
    }

    @Override
    public CompletableFuture<Chunk> loadChunk(int x, int z, Consumer<Chunk> chunkAddingCallback) {
        var hashXZ = HashUtils.hashXZ(x, z);
        if (isChunkLoaded(hashXZ)) {
            throw new IllegalStateException("Chunk is already loaded");
        }
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
                    var chunk = new AllayChunk(x, z, world.getDimensionInfo());
                    worldGenerationService.submitGenerationTask(new SingleChunkLimitedWorldRegion(chunk), single -> setChunk(x, z, chunk, chunkAddingCallback));
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

    private final class ChunkLoaderManager {
        @Getter
        private final ChunkLoader chunkLoader;
        private final Set<Long> heldChunks = new LongOpenHashSet();
        private final Queue<Long> chunkSendingQueue = new ConcurrentLinkedQueue<>();
        private long lastLoaderChunkPosHashed = -1;
        private final int chunkSentPerTick;

        ChunkLoaderManager(ChunkLoader chunkLoader) {
            this.chunkLoader = chunkLoader;
            this.chunkSentPerTick = 4;//TODO: Config
        }

        public void tick() {
            if (!chunkLoader.isLoaderActive()) return;
            var loaderChunkPosChanged = false;
            long currentLoaderChunkPosHashed;
            if ((currentLoaderChunkPosHashed = HashUtils.hashXZ(chunkLoader.getLocation().getChunkX(), chunkLoader.getLocation().getChunkZ())) != lastLoaderChunkPosHashed) {
                lastLoaderChunkPosHashed = currentLoaderChunkPosHashed;
                loaderChunkPosChanged = true;
            }
            loadNeededChunk(loaderChunkPosChanged);
        }

        private void loadNeededChunk(boolean loaderChunkPosChanged) {
            if (!loaderChunkPosChanged)
                return;
            var loaderChunkX = chunkLoader.getLocation().getChunkX();
            var loaderChunkZ = chunkLoader.getLocation().getChunkZ();
            var viewDistance = world.getViewDistance();
            for (int rx = -viewDistance; rx <= viewDistance; rx++) {
                for (int rz = -viewDistance; rz <= viewDistance; rz++) {
                    if (rx * rx + rz * rz > viewDistance * viewDistance) continue;
                    var chunkX = loaderChunkX + rx;
                    var chunkZ = loaderChunkZ + rz;
                    var hashXZ = HashUtils.hashXZ(chunkX, chunkZ);
                    chunkSendingQueue.add(hashXZ);
                    if (isChunkUnloaded(hashXZ)) {
                        loadChunk(chunkX, chunkZ);
                    }
                }
            }
        }
    }
}
