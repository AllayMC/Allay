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
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

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
        unusedChunkClearCountDown.entrySet().removeIf(entry -> {
            var chunk = getChunk(entry.getKey());
            return chunk == null || chunk.getChunkLoaderCount() > 0;
        });
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
        chunkLoaders.values().stream().parallel().forEach(ChunkLoaderManager::tick);
    }

    @Override
    public void setChunk(int x, int z, Chunk chunk) {
        var chunkHash = HashUtils.hashXZ(x, z);
        if (isChunkLoaded(chunkHash)) {
            unloadChunk(chunkHash);
        }
        if (chunk.getChunkX() != x) chunk.setChunkX(x);
        if (chunk.getChunkZ() != z) chunk.setChunkZ(z);
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
        this.chunkLoaders.remove(chunkLoader).onRemoved();
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
                .readChunk(x, z, world.getDimensionInfo());
        loadingChunks.put(hashXZ, future);
        future.thenApply(loadedChunk -> {
            if (loadedChunk != null) {
                setChunk(x, z, loadedChunk);
                loadingChunks.remove(hashXZ);
                return loadedChunk;
            }
            var chunk = new AllayChunk(x, z, world.getDimensionInfo());
            worldGenerationService.submitGenerationTask(new SingleChunkLimitedWorldRegion(chunk), single -> {
                setChunk(x, z, chunk);
                loadingChunks.remove(hashXZ);
            });
            return chunk;
        });
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
        loadedChunks.remove(chunkHash);
        worldStorage.writeChunk(chunk.getChunkX(), chunk.getChunkZ(), chunk);
    }

    @Override
    public int maxChunkX() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int maxChunkZ() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int minChunkX() {
        return Integer.MIN_VALUE;
    }

    @Override
    public int minChunkZ() {
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
            this.chunkSentPerTick = 8;//TODO: Config
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

        public void onRemoved() {
            sentChunks.forEach(chunkHash -> {
                var chunk = getChunk(chunkHash);
                if (chunk != null)
                    chunk.removeChunkLoader(chunkLoader);
            });
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
            var chunkReadyToSend = new Long2ObjectOpenHashMap<Chunk>();
            for (var sentChunkCount = 0; sentChunkCount < chunkSentPerTick; sentChunkCount++) {
                if (chunkSendingQueue.isEmpty()) break;
                var chunkHash = chunkSendingQueue.get(0);
                var chunk = getChunk(chunkHash);
                if (chunk == null) {
                    sentChunkCount--;
                    continue;
                }
                chunkSendingQueue.remove(0);
                chunk.addChunkLoader(chunkLoader);
                chunkReadyToSend.put(chunkHash, chunk);
            }
            chunkReadyToSend.forEach((chunkHash, chunk) -> sentChunks.add(chunkHash));
            chunkReadyToSend.values().stream().parallel().forEach(chunkLoader::sendChunk);
        }

        private void removeOutOfRadiusChunks() {
            var outOfRadiusChunks = new LongOpenHashSet(sentChunks);
            outOfRadiusChunks.removeAll(inRadiusChunks);
            outOfRadiusChunks.forEach(chunkHash -> {
                var chunk = getChunk(chunkHash);
                if (chunk != null)
                    chunk.removeChunkLoader(chunkLoader);
            });
            chunkLoader.unloadChunks(outOfRadiusChunks);
            sentChunks.removeAll(outOfRadiusChunks);
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
