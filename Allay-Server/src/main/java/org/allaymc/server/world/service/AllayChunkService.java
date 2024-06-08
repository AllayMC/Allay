package org.allaymc.server.world.service;

import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
import it.unimi.dsi.fastutil.longs.LongComparator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.annotation.SlowOperation;
import org.allaymc.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayComputeThread;
import org.allaymc.api.utils.GameLoop;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.utils.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.allaymc.api.world.generator.ChunkGenerateContext;
import org.allaymc.api.world.service.ChunkService;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3i;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.allaymc.api.server.ServerSettings.WorldConfig.ChunkSendingStrategy.ASYNC;
import static org.allaymc.api.server.ServerSettings.WorldConfig.ChunkSendingStrategy.SYNC;
import static org.allaymc.api.world.chunk.ChunkState.FINISHED;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayChunkService implements ChunkService {

    private final Map<Long, Chunk> loadedChunks = new Long2ObjectNonBlockingMap<>();
    private final Map<Long, CompletableFuture<Chunk>> loadingChunks = new Long2ObjectNonBlockingMap<>();
    private final Map<ChunkLoader, ChunkLoaderManager> chunkLoaderManagers = new Object2ObjectOpenHashMap<>();
    private final Dimension dimension;
    private final WorldStorage worldStorage;
    private final Map<Long, Integer> unusedChunkClearCountDown = new Long2IntOpenHashMap();
    private final Set<Long> keepLoadingChunks = Sets.newConcurrentHashSet();

    public AllayChunkService(Dimension dimension, WorldStorage worldStorage) {
        this.dimension = dimension;
        this.worldStorage = worldStorage;
    }

    @Override
    public void tick() {
        sendChunkPackets();
        tickChunkLoaders();
        removeUnusedChunks();
        tickChunks();
    }

    private void tickChunks() {
        loadedChunks.values().forEach(Chunk::tick);
    }

    private void sendChunkPackets() {
        loadedChunks.values().forEach(Chunk::sendChunkPackets);
    }

    private void tickChunkLoaders() {
        // NOTICE: There is no need to use parallel stream here
        chunkLoaderManagers.values().forEach(ChunkLoaderManager::tick);
    }

    private void removeUnusedChunks() {
        unusedChunkClearCountDown.entrySet().removeIf(entry -> {
            var chunk = getChunk(entry.getKey());
            return chunk == null || chunk.getChunkLoaderCount() > 0 || keepLoadingChunks.contains(entry.getKey());
        });
        // Update countdown
        unusedChunkClearCountDown.replaceAll((chunkHash, countDown) -> countDown - 1);
        // Remove countdown ended unused chunks
        unusedChunkClearCountDown.entrySet().removeIf(entry -> {
            boolean shouldRemove = entry.getValue() == 0;
            if (shouldRemove) {
                unloadChunk(entry.getKey());
            }
            return shouldRemove;
        });

        // Add unused chunk to the clear countdown map
        for (var entry : loadedChunks.entrySet()) {
            Long chunkHash = entry.getKey();
            var loadedChunk = entry.getValue();
            if (loadedChunk.getChunkLoaderCount() == 0 && !keepLoadingChunks.contains(chunkHash) && !unusedChunkClearCountDown.containsKey(chunkHash)) {
                unusedChunkClearCountDown.put(chunkHash, Server.SETTINGS.worldSettings().removeUnneededChunkCycle());
            }
        }
    }

    private Chunk generateChunkIfNeed(Chunk chunk) {
        var unsafeChunk = chunk.toUnsafeChunk();
        if (unsafeChunk.getState() != FINISHED) {
            var chunkGenerateContext = new ChunkGenerateContext(unsafeChunk, dimension);
            dimension.getWorldGenerator().generate(chunkGenerateContext);
            unsafeChunk.setState(FINISHED);
        }
        return chunk;
    }

    private void setChunk(int x, int z, Chunk chunk) {
        var chunkHash = HashUtils.hashXZ(x, z);
        if (loadedChunks.putIfAbsent(chunkHash, chunk) != null)
            throw new IllegalStateException("Trying to set a chunk (" + x + "," + z + ") which is already loaded");
    }

    @Override
    public Chunk getChunk(int x, int z) {
        return loadedChunks.get(HashUtils.hashXZ(x, z));
    }

    @Override
    public Chunk getChunk(long chunkHash) {
        return loadedChunks.get(chunkHash);
    }

    @SlowOperation
    @Override
    public Chunk getOrLoadChunkSynchronously(int x, int z) {
        var chunk = getChunk(x, z);
        if (chunk != null) {
            return chunk;
        }
        return loadChunkSynchronously(x, z, null);
    }

    @Override
    public CompletableFuture<Chunk> getOrLoadChunk(int x, int z) {
        var chunk = getChunk(x, z);
        if (chunk != null) {
            return CompletableFuture.completedFuture(chunk);
        }
        return loadChunk(x, z);
    }

    @Override
    public CompletableFuture<Set<Chunk>> getOrLoadRangedChunk(int x, int z, int range) {
        // The set used to store CompletableFutures
        Set<CompletableFuture<Chunk>> futureSet = new HashSet<>();

        for (int dx = -range; dx <= range; dx++) {
            for (int dz = -range; dz <= range; dz++) {
                if (dx * dx + dz * dz <= range * range) {
                    // Get or load each chunk and add the returned CompletableFuture to the set
                    futureSet.add(getOrLoadChunk(x + dx, z + dz));
                }
            }
        }

        // When all the CompletableFutures are completed, return a new CompletableFuture
        return CompletableFuture.allOf(futureSet.toArray(new CompletableFuture[0]))
                .thenApplyAsync(v -> futureSet.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toSet()), Server.getInstance().getVirtualThreadPool());
    }

    @Override
    public CompletableFuture<Chunk> loadChunk(int x, int z) {
        var hashXZ = HashUtils.hashXZ(x, z);
        if (isChunkLoaded(hashXZ)) {
            throw new IllegalStateException("Chunk is already loaded");
        }
        var future = new CompletableFuture<Chunk>();
        // 只有一个线程可以成功向loadingChunks中写入future，其他线程将获取到写入成功线程的future
        var presentValue = loadingChunks.putIfAbsent(hashXZ, future);
        if (presentValue != null) {
            return presentValue;
        }
        if (AllayComputeThread.isAllayComputeThread(Thread.currentThread())) {
            // 若当前线程已经为计算线程，则直接在此线程上加载区块
            // 否则会出现一个计算线程等待另外一个计算线程的情况，造成线程资源的浪费
            // If the current thread is already a computing thread, load the block directly on this thread
            // Otherwise, one computing thread will wait for another computing thread, resulting in a waste of thread resources
            loadChunkSynchronously(x, z, future);
            return future;
        }
        worldStorage.readChunk(x, z, dimension.getDimensionInfo())
                .exceptionally(t -> {
                    log.error("Error while reading chunk ({},{}) !", x, z, t);
                    return AllayUnsafeChunk.builder().emptyChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
                })
                .thenApplyAsync(this::generateChunkIfNeed, Server.getInstance().getComputeThreadPool())
                .exceptionally(t -> {
                    log.error("Error while generating chunk ({},{}) !", x, z, t);
                    return AllayUnsafeChunk.builder().emptyChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
                })
                .thenApply(prepareChunk -> {
                    prepareChunk.beforeSetChunk(dimension);
                    setChunk(x, z, prepareChunk);
                    prepareChunk.afterSetChunk(dimension);
                    future.complete(prepareChunk);
                    loadingChunks.remove(hashXZ);
                    return prepareChunk;
                });
        return future;
    }

    @SneakyThrows
    protected Chunk loadChunkSynchronously(int x, int z, CompletableFuture<Chunk> futureAlreadyExists) {
        var hash = HashUtils.hashXZ(x, z);
        var synchronizedFuture = futureAlreadyExists;
        if (futureAlreadyExists == null) {
            synchronizedFuture = new CompletableFuture<>();
            loadingChunks.put(hash, synchronizedFuture);
        }
        Chunk chunk;
        try {
            chunk = worldStorage.readChunkSynchronously(x, z, dimension.getDimensionInfo());
        } catch (Throwable t) {
            log.error("Error while reading chunk ({},{}) !", x, z, t);
            chunk = AllayUnsafeChunk.builder().emptyChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
        }
        try {
            generateChunkIfNeed(chunk);
        } catch (Throwable t) {
            log.error("Error while generating chunk ({},{}) !", x, z, t);
            chunk = AllayUnsafeChunk.builder().emptyChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
        }
        chunk.beforeSetChunk(dimension);
        setChunk(x, z, chunk);
        chunk.afterSetChunk(dimension);
        synchronizedFuture.complete(chunk);
        loadingChunks.remove(hash);
        return chunk;
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
        return !isChunkLoading(hashXZ) && !isChunkLoaded(hashXZ);
    }

    @Override
    public void addKeepLoadingChunk(int x, int z) {
        keepLoadingChunks.add(HashUtils.hashXZ(x, z));
    }

    @Override
    public void removeKeepLoadingChunk(int x, int z) {
        keepLoadingChunks.remove(HashUtils.hashXZ(x, z));
    }

    @Override
    @UnmodifiableView
    public Set<Long> getKeepLoadingChunks() {
        return Collections.unmodifiableSet(keepLoadingChunks);
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
        var removed = this.chunkLoaderManagers.remove(chunkLoader);
        if (removed != null) removed.onRemoved();
    }

    @Override
    public void forEachLoadedChunks(Consumer<Chunk> consumer) {
        loadedChunks.values().forEach(consumer);
    }

    @Override
    @UnmodifiableView
    public Collection<Chunk> getLoadedChunks() {
        return Collections.unmodifiableCollection(loadedChunks.values());
    }

    @Override
    @UnmodifiableView
    public Collection<CompletableFuture<Chunk>> getLoadingChunks() {
        return Collections.unmodifiableCollection(loadingChunks.values());
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
        chunk.save(worldStorage);
        chunk.getEntities().forEach((runtimeId, entity) -> {
            entity.despawnFromAll();
            dimension.getEntityPhysicsService().removeEntity(entity);
        });
    }

    @Override
    public void unloadAllChunks() {
        this.loadedChunks.values().forEach((c) -> unloadChunk(c.getX(), c.getZ()));
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
        private final ChunkLoader chunkLoader;
        private final LongComparator chunkDistanceComparatorHashed = new LongComparator() {
            @Override
            public int compare(long chunkHash1, long chunkHash2) {
                Vector3i floor = MathUtils.floor(chunkLoader.getLocation());
                var loaderChunkX = floor.x >> 4;
                var loaderChunkZ = floor.z >> 4;
                var chunkDX1 = loaderChunkX - HashUtils.getXFromHashXZ(chunkHash1);
                var chunkDZ1 = loaderChunkZ - HashUtils.getZFromHashXZ(chunkHash1);
                var chunkDX2 = loaderChunkX - HashUtils.getXFromHashXZ(chunkHash2);
                var chunkDZ2 = loaderChunkZ - HashUtils.getZFromHashXZ(chunkHash2);
                // Compare distance to loader
                return Integer.compare(
                        chunkDX1 * chunkDX1 + chunkDZ1 * chunkDZ1,
                        chunkDX2 * chunkDX2 + chunkDZ2 * chunkDZ2
                );
            }
        };
        private final Comparator<Chunk> chunkDistanceComparator = new Comparator<>() {
            @Override
            public int compare(Chunk c1, Chunk c2) {
                Vector3i floor = MathUtils.floor(chunkLoader.getLocation());
                var loaderChunkX = floor.x >> 4;
                var loaderChunkZ = floor.z >> 4;
                var chunkDX1 = loaderChunkX - c1.getX();
                var chunkDZ1 = loaderChunkZ - c1.getZ();
                var chunkDX2 = loaderChunkX - c2.getX();
                var chunkDZ2 = loaderChunkZ - c2.getZ();
                // Compare distance to loader
                return Integer.compare(
                        chunkDX1 * chunkDX1 + chunkDZ1 * chunkDZ1,
                        chunkDX2 * chunkDX2 + chunkDZ2 * chunkDZ2
                );

            }
        };
        // Save all chunk hash values that have been sent in the last tick
        private final LongOpenHashSet sentChunks = new LongOpenHashSet();
        // Save all chunk hash values that will be sent in this tick
        private final LongOpenHashSet inRadiusChunks = new LongOpenHashSet();
        private final int chunkTrySendCountPerTick;
        private final LongArrayFIFOQueue chunkSendingQueue;
        private AsyncChunkSendingManager asyncChunkSendingManager;
        private long lastLoaderChunkPosHashed = Long.MAX_VALUE;

        ChunkLoaderManager(ChunkLoader chunkLoader) {
            this.chunkLoader = chunkLoader;
            // S = pi * radius ^ 2
            this.chunkSendingQueue = new LongArrayFIFOQueue((int) Math.ceil(chunkLoader.getChunkLoadingRadius() * chunkLoader.getChunkLoadingRadius() * Math.PI));
            this.chunkTrySendCountPerTick = chunkLoader.getChunkTrySendCountPerTick();
            if (Server.SETTINGS.worldSettings().chunkSendingStrategy() == ASYNC) {
                asyncChunkSendingManager = new AsyncChunkSendingManager();
            }
        }

        public void onRemoved() {
            chunkLoader.onChunkOutOfRange(sentChunks);
            if (asyncChunkSendingManager != null) {
                asyncChunkSendingManager.stop();
            }
        }

        public void tick() {
            if (!chunkLoader.isLoaderActive()) return;
            long currentLoaderChunkPosHashed;
            Vector3i floor = MathUtils.floor(chunkLoader.getLocation());
            if ((currentLoaderChunkPosHashed = HashUtils.hashXZ(floor.x >> 4, floor.z >> 4)) != lastLoaderChunkPosHashed) {
                lastLoaderChunkPosHashed = currentLoaderChunkPosHashed;
                updateInRadiusChunks(floor);
                removeOutOfRadiusChunks();
                updateChunkSendingQueue();
            }
            loadAndSendQueuedChunks();
        }

        private void updateInRadiusChunks(Vector3i currentPos) {
            inRadiusChunks.clear();
            var loaderChunkX = currentPos.x >> 4;
            var loaderChunkZ = currentPos.z >> 4;
            var chunkLoadingRadius = chunkLoader.getChunkLoadingRadius();
            for (int rx = -chunkLoadingRadius; rx <= chunkLoadingRadius; rx++) {
                for (int rz = -chunkLoadingRadius; rz <= chunkLoadingRadius; rz++) {
                    if (!isChunkInRadius(rx, rz, chunkLoadingRadius)) continue;
                    var chunkX = loaderChunkX + rx;
                    var chunkZ = loaderChunkZ + rz;
                    var hashXZ = HashUtils.hashXZ(chunkX, chunkZ);
                    inRadiusChunks.add(hashXZ);
                }
            }
        }

        private void removeOutOfRadiusChunks() {
            Sets.SetView<Long> difference = Sets.difference(sentChunks, inRadiusChunks);
            // Unload chunks out of range
            chunkLoader.onChunkOutOfRange(difference);
            // The intersection of sentChunks and inRadiusChunks
            sentChunks.removeAll(difference);
        }

        private void updateChunkSendingQueue() {
            chunkSendingQueue.clear();
            // Blocks that have already been sent will not be resent
            Sets.SetView<Long> difference = Sets.difference(inRadiusChunks, sentChunks);
            difference.stream().sorted(chunkDistanceComparatorHashed).forEachOrdered(v -> chunkSendingQueue.enqueue(v.longValue()));
        }

        private void loadAndSendQueuedChunks() {
            if (chunkSendingQueue.isEmpty()) return;
            var chunkReadyToSend = new Long2ObjectOpenHashMap<Chunk>();
            int triedSendChunkCount = 0;
            do {
                triedSendChunkCount++;
                long chunkHash = chunkSendingQueue.dequeueLong();
                var chunk = getChunk(chunkHash);
                if (chunk == null) {
                    if (isChunkUnloaded(chunkHash)) {
                        loadChunk(HashUtils.getXFromHashXZ(chunkHash), HashUtils.getZFromHashXZ(chunkHash));
                    }
                    chunkSendingQueue.enqueue(chunkHash);
                    continue;
                }
                chunk.addChunkLoader(chunkLoader);
                chunkReadyToSend.put(chunkHash, chunk);
            } while (!chunkSendingQueue.isEmpty() && triedSendChunkCount < chunkTrySendCountPerTick);
            if (!chunkReadyToSend.isEmpty()) {
                chunkLoader.publishClientChunkUpdate();
                var chunkSendingStrategy = Server.SETTINGS.worldSettings().chunkSendingStrategy();
                var useSubChunkSendingSystem = Server.SETTINGS.worldSettings().useSubChunkSendingSystem();
                if (useSubChunkSendingSystem) {
                    // Use SYNC mode if a sub-chunk sending system is enabled
                    // Because the encoding of sub-chunk lcp is very quick
                    chunkSendingStrategy = SYNC;
                }
                if (chunkSendingStrategy == ASYNC) {
                    asyncChunkSendingManager.addChunk(chunkReadyToSend.values());
                } else {
                    // Priority is given to sending chunks that are close to the chunk loader
                    var lcpStream = chunkReadyToSend.values().stream();
                    lcpStream.sorted(chunkDistanceComparator).forEachOrdered(
                            chunk -> {
                                var lcp = useSubChunkSendingSystem ? chunk.createSubChunkLevelChunkPacket() : chunk.createFullLevelChunkPacketChunk();
                                chunkLoader.sendLevelChunkPacket(lcp);
                                chunkLoader.onChunkInRangeSent(chunk);
                            }
                    );
                }
                sentChunks.addAll(chunkReadyToSend.keySet());
            }
        }

        private boolean isChunkInRadius(int chunkX, int chunkZ, int radius) {
            return chunkX * chunkX + chunkZ * chunkZ <= radius * radius;
        }

        private class AsyncChunkSendingManager {
            private static final int DEFAULT_INITIAL_CAPACITY = 11;
            private final PriorityBlockingQueue<Chunk> chunkSendingQueue = new PriorityBlockingQueue<>(DEFAULT_INITIAL_CAPACITY, chunkDistanceComparator);
            private final GameLoop loop = GameLoop.builder().loopCountPerSec(20).onTick(gl -> tick()).build();

            public AsyncChunkSendingManager() {
                Thread.ofVirtual().start(loop::startLoop);
            }

            public void addChunk(Collection<Chunk> chunks) {
                chunkSendingQueue.addAll(chunks);
            }

            public void stop() {
                loop.stop();
            }

            private void tick() {
                while (!chunkSendingQueue.isEmpty()) {
                    var chunk = chunkSendingQueue.poll();
                    var lcp = chunk.createFullLevelChunkPacketChunk();
                    chunkLoader.sendLevelChunkPacket(lcp);
                    chunkLoader.onChunkInRangeSent(chunk);
                }
            }
        }
    }
}
