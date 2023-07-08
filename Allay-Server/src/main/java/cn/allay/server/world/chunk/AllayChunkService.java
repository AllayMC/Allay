package cn.allay.server.world.chunk;

import cn.allay.api.annotation.SlowOperation;
import cn.allay.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.World;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.chunk.ChunkLoader;
import cn.allay.api.world.chunk.ChunkService;
import cn.allay.api.world.generator.WorldGenerationService;
import cn.allay.api.world.storage.WorldStorage;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class AllayChunkService implements ChunkService {

    public static final int REMOVE_UNNEEDED_CHUNK_CYCLE = 600;

    private final Map<Long, Chunk> loadedChunks = new Long2ObjectNonBlockingMap<>();
    private final Map<Long, CompletableFuture<Chunk>> loadingChunks = new Long2ObjectNonBlockingMap<>();
    @Getter
    private final World world;
    @Getter
    private final WorldGenerationService worldGenerationService;
    @Getter
    private final WorldStorage worldStorage;
    private final Map<ChunkLoader, ChunkLoaderManager> chunkLoaders = new ConcurrentHashMap<>();
    private final Map<Long, Integer> unusedChunkClearCountDown = new Long2IntOpenHashMap();

    public AllayChunkService(World world, Function<ChunkService, WorldGenerationService> worldGenerationServiceSupplier, WorldStorage worldStorage) {
        this.world = world;
        this.worldGenerationService = worldGenerationServiceSupplier.apply(this);
        this.worldStorage = worldStorage;
    }

    @Override
    public void tick() {
        tickChunkLoaders();
        removeUnusedChunks();
    }

    private void removeUnusedChunks() {
        //Update countdown
        unusedChunkClearCountDown.replaceAll((chunkHash, countDown) -> countDown - 1);
        //Remove countdown ended unused chunks
        unusedChunkClearCountDown.entrySet().removeIf(entry -> {
            var shouldRemove = entry.getValue() == 0;
            if (shouldRemove) {
                unloadChunk(entry.getKey());
            }
            return shouldRemove;
        });

        //Add unused chunk to the clear countdown map
        for (var entry : loadedChunks.entrySet()) {
            var chunkHash = entry.getKey();
            var loadedChunk = entry.getValue();
            if (loadedChunk.getChunkLoaderCount() == 0 && !unusedChunkClearCountDown.containsKey(chunkHash)) {
                unusedChunkClearCountDown.put(chunkHash, REMOVE_UNNEEDED_CHUNK_CYCLE);
            }
        }
    }

    private void tickChunkLoaders() {
        for (var chunkLoaderManager : chunkLoaders.values()) {
            Thread.ofVirtual().start(chunkLoaderManager::tick);
        }
    }

    @Override
    public void setChunk(int x, int z, Chunk chunk) {
        var chunkHash = HashUtils.hashXZ(x, z);
        if (isChunkLoaded(chunkHash)) {
            unloadChunk(chunkHash);
        }
        loadedChunks.put(chunkHash, chunk);
    }

    @Override
    @Nullable
    public Chunk getChunk(int x, int z) {
        return loadedChunks.get(HashUtils.hashXZ(x, z));
    }

    @Override
    @Nullable
    public Chunk getChunk(long chunkHash) {
        return loadedChunks.get(chunkHash);
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
        var chunk = getChunk(x, z);
        if (chunk != null) {
            return CompletableFuture.completedFuture(chunk);
        }
        return loadChunk(x, z);
    }

    @SlowOperation
    @Override
    public CompletableFuture<Chunk> loadChunk(int x, int z) {
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
                        loadingChunks.remove(hashXZ);
                        setChunk(x, z, loadedChunk);
                        return loadedChunk;
                    }
                    var chunk = new AllayChunk(x, z, world.getDimensionInfo());
                    worldGenerationService.submitGenerationTask(new SingleChunkLimitedWorldRegion(chunk), single -> {
                        loadingChunks.remove(hashXZ);
                        setChunk(x, z, chunk);
                    });
                    return chunk;
                });
        loadingChunks.put(hashXZ, future);
        return future;
    }

    public void unloadChunk(int x, int z) {
        unloadChunk(HashUtils.hashXZ(x, z));
    }

    public void unloadChunk(long chunkHash) {
        var chunk = getChunk(chunkHash);
        if (chunk == null) {
            return;
        }
        worldStorage.writeChunk(chunk.getChunkX(), chunk.getChunkZ(), chunk);
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
        private final Set<Long> sentChunks = new LongOpenHashSet();
        private final Set<Long> inRadiusChunks = new LongOpenHashSet();
        private final List<Long> chunkSendingQueue = new LongArrayList();
        private final int chunkSentPerTick;
        private long lastLoaderChunkPosHashed = -1;

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
            if (loaderChunkPosChanged) {
                updateAndLoadInRadiusChunks();
                removeOutOfRadiusChunks();
                updateChunkSendingQueue();
            }
            sendQueuedChunks();
        }

        private void updateChunkSendingQueue() {
            chunkSendingQueue.clear();
            chunkSendingQueue.addAll(inRadiusChunks);
            chunkSendingQueue.removeAll(sentChunks);
            chunkSendingQueue.sort((chunkHash1, chunkHash2) -> {
                var loaderChunkX = chunkLoader.getLocation().getChunkX();
                var loaderChunkZ = chunkLoader.getLocation().getChunkZ();
                var chunkDX1 = loaderChunkX - HashUtils.getXFromHashXZ(chunkHash1);
                var chunkDZ1 = loaderChunkZ - HashUtils.getZFromHashXZ(chunkHash1);
                var chunkDX2 = loaderChunkX - HashUtils.getXFromHashXZ(chunkHash2);
                var chunkDZ2 = loaderChunkZ - HashUtils.getZFromHashXZ(chunkHash2);
                //Compare distance to loader
                return Integer.compare(
                        chunkDX1 * chunkDX1 + chunkDZ1 * chunkDZ1,
                        chunkDX2 * chunkDX2 + chunkDZ2 * chunkDZ2
                );
            });
        }

        private void sendQueuedChunks() {
            var chunkReadyToSend = Stream.<Chunk>builder();
            for (var sentChunkCount = 0; sentChunkCount < chunkSentPerTick; sentChunkCount++) {
                if (chunkSendingQueue.isEmpty()) break;
                var chunkHash = chunkSendingQueue.remove(0);
                var chunk = getChunk(chunkHash);
                if (chunk == null) {
                    sentChunkCount--;
                    continue;
                }
                chunk.addChunkLoader(chunkLoader);
                chunkReadyToSend.add(chunk);
            }
            chunkReadyToSend.build().parallel().forEach(chunk -> {
                chunkLoader.sendChunk(chunk);
                sentChunks.add(HashUtils.hashXZ(chunk.getChunkX(), chunk.getChunkZ()));
            });
        }

        private void removeOutOfRadiusChunks() {
            var outOfRadiusChunks = new LongOpenHashSet(sentChunks);
            outOfRadiusChunks.removeAll(inRadiusChunks);
            chunkLoader.unloadChunks(outOfRadiusChunks);
        }

        private void updateAndLoadInRadiusChunks() {
            var loaderChunkX = chunkLoader.getLocation().getChunkX();
            var loaderChunkZ = chunkLoader.getLocation().getChunkZ();
            var chunkLoadingRadius = chunkLoader.getChunkLoadingRadius();
            inRadiusChunks.clear();
            for (int rx = -chunkLoadingRadius; rx <= chunkLoadingRadius; rx++) {
                for (int rz = -chunkLoadingRadius; rz <= chunkLoadingRadius; rz++) {
                    if (isChunkInRadius(rx, rz, chunkLoadingRadius)) continue;
                    var chunkX = loaderChunkX + rx;
                    var chunkZ = loaderChunkZ + rz;
                    var hashXZ = HashUtils.hashXZ(chunkX, chunkZ);
                    inRadiusChunks.add(hashXZ);
                    if (isChunkUnloaded(hashXZ)) {
                        loadChunk(chunkX, chunkZ);
                    }
                }
            }
        }

        private boolean isChunkInRadius(int chunkX, int chunkZ, int radius) {
            return chunkX * chunkX + chunkZ * chunkZ <= radius * radius;
        }
    }
}
