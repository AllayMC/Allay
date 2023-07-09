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
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.*;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Hashtable;
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
    private final Long2ObjectNonBlockingMap<Chunk> loadedChunks = new Long2ObjectNonBlockingMap<>();
    private final Long2ObjectNonBlockingMap<CompletableFuture<Chunk>> loadingChunks = new Long2ObjectNonBlockingMap<>();
    private final Hashtable<ChunkLoader, ChunkLoaderManager> chunkLoaderManagers = new Hashtable<>();
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

    private void tickChunkLoaders() {
        chunkLoaderManagers.values().parallelStream().forEach(ChunkLoaderManager::tick);
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
        return Collections.unmodifiableSet(chunkLoaderManagers.keySet());
    }

    @Override
    public void addChunkLoader(ChunkLoader chunkLoader) {
        this.chunkLoaderManagers.put(chunkLoader, new ChunkLoaderManager(chunkLoader));
    }

    @Override
    public void removeChunkLoader(ChunkLoader chunkLoader) {
        this.chunkLoaderManagers.remove(chunkLoader).onRemoved();
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
        var future = worldStorage.readChunk(x, z, world.getDimensionInfo()).thenApplyAsync(loadedChunk -> {
            if (loadedChunk != null) {
                setChunk(x, z, loadedChunk);
                loadingChunks.remove(hashXZ);
                return loadedChunk;
            }
            worldGenerationService.submitGenerationTask(new SingleChunkLimitedWorldRegion(null), single -> {
                setChunk(x, z, single.getChunk(0, 0));
                loadingChunks.remove(hashXZ);
            });
            return getChunk(x, z);
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
        LongComparator chunkDistanceComparator = new LongComparator() {
            @Override
            public int compare(long chunkHash1, long chunkHash2) {
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
            }
        };

        @Getter
        private final ChunkLoader chunkLoader;

        //保存着上tick已经发送的全部区块hash值
        private final LongOpenHashSet sentChunks = new LongOpenHashSet();

        //保存着这tick要发送的全部区块hash值
        private final LongOpenHashSet inRadiusChunks = new LongOpenHashSet();
        private final LongArrayPriorityQueue chunkSendQueue = new LongArrayPriorityQueue(100, chunkDistanceComparator);
        private final int chunkSentPerTick;
        private long lastLoaderChunkPosHashed = -1;

        ChunkLoaderManager(ChunkLoader chunkLoader) {
            this.chunkLoader = chunkLoader;
            this.chunkSentPerTick = 8;//TODO: Config
        }

        public void onRemoved() {
            sentChunks.forEach(chunkHash -> {
                var chunk = getChunk(chunkHash);
                if (chunk != null)
                    chunk.removeChunkLoader(chunkLoader);
            });
            chunkLoader.unloadChunks(sentChunks);
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

        private void updateAndLoadInRadiusChunks() {
            var loaderChunkX = chunkLoader.getLocation().getChunkX();
            var loaderChunkZ = chunkLoader.getLocation().getChunkZ();
            var chunkLoadingRadius = chunkLoader.getChunkLoadingRadius();
            inRadiusChunks.clear();
            for (int rx = -chunkLoadingRadius; rx <= chunkLoadingRadius; rx++) {
                for (int rz = -chunkLoadingRadius; rz <= chunkLoadingRadius; rz++) {
                    if (!isChunkInRadius(rx, rz, chunkLoadingRadius)) continue;
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

        private void removeOutOfRadiusChunks() {
            Sets.SetView<Long> difference = Sets.difference(sentChunks, inRadiusChunks);
            difference.forEach(chunkHash -> {
                var chunk = getChunk(chunkHash);
                if (chunk != null)
                    chunk.removeChunkLoader(chunkLoader);
            });
            chunkLoader.unloadChunks(difference);
            //剩下sentChunks和inRadiusChunks的交集
            sentChunks.removeAll(difference);
        }


        private void updateChunkSendingQueue() {
            chunkSendQueue.clear();
            Sets.SetView<Long> difference = Sets.difference(inRadiusChunks, sentChunks);
            for (long hash : difference) {
                chunkSendQueue.enqueue(hash);
            }
        }

        private void sendQueuedChunks() {
            if (chunkSendQueue.isEmpty()) return;
            var chunkReadyToSend = new Long2ObjectOpenHashMap<Chunk>();
            int sentChunkCount = 0;
            do {
                long chunkHash = chunkSendQueue.firstLong();
                var chunk = getChunk(chunkHash);
                if (chunk == null) {
                    continue;
                }
                sentChunkCount++;
                chunkSendQueue.dequeueLong();
                chunk.addChunkLoader(chunkLoader);
                chunkReadyToSend.put(chunkHash, chunk);
            } while (!chunkSendQueue.isEmpty() && sentChunkCount < chunkSentPerTick);
            chunkLoader.preSendChunks(chunkReadyToSend.keySet());
            chunkReadyToSend.forEach((chunkHash, chunk) -> sentChunks.add(chunkHash.longValue()));
            chunkReadyToSend.values().forEach(chunkLoader::sendChunk);
        }

        private boolean isChunkInRadius(int chunkX, int chunkZ, int radius) {
            return chunkX * chunkX + chunkZ * chunkZ <= radius * radius;
        }
    }
}
