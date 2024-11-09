package org.allaymc.server.world.service;

import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.*;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.world.ChunkLoadEvent;
import org.allaymc.api.eventbus.event.world.ChunkPreLoadEvent;
import org.allaymc.api.eventbus.event.world.ChunkUnloadEvent;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.GameLoop;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.service.ChunkService;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import org.allaymc.server.world.chunk.AllayChunk;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.generator.AllayWorldGenerator;
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
 * @author daoge_cmd
 */
@Slf4j
@RequiredArgsConstructor
public final class AllayChunkService implements ChunkService {

    private final Map<Long, Chunk> loadedChunks = new Long2ObjectNonBlockingMap<>();
    private final Map<Long, CompletableFuture<Chunk>> loadingChunks = new Long2ObjectNonBlockingMap<>();
    private final Map<ChunkLoader, ChunkLoaderManager> chunkLoaderManagers = new Object2ObjectOpenHashMap<>();
    private final Dimension dimension;
    @Getter
    private final WorldGenerator worldGenerator;
    private final WorldStorage worldStorage;
    private final Map<Long, Integer> unusedChunkClearCountDown = new Long2IntOpenHashMap();
    private final Set<Long> keepLoadingChunks = Sets.newConcurrentHashSet();
    @Setter
    private int removeUnneededChunkCycle = Server.SETTINGS.worldSettings().removeUnneededChunkCycle();

    public void startTick() {
        ((AllayWorldGenerator) worldGenerator).startTick();
    }

    public void tick(long currentTick) {
        sendChunkPackets();
        tickChunkLoaders();
        removeUnusedChunks();
        tickChunks(currentTick);
    }

    private void tickChunks(long currentTick) {
        for (Chunk chunk : loadedChunks.values()) {
            try {
                ((AllayChunk) chunk).tick(currentTick, worldStorage);
            } catch (Throwable t) {
                log.error("Error while ticking chunk({}, {})!", chunk.getX(), chunk.getZ(), t);
            }
        }
    }

    private void sendChunkPackets() {
        loadedChunks.values().forEach(Chunk::sendChunkPackets);
    }

    private void tickChunkLoaders() {
        // NOTICE: There is no need to use parallel stream here
        for (ChunkLoaderManager chunkLoaderManager : chunkLoaderManagers.values()) {
            try {
                chunkLoaderManager.tick();
            } catch (Throwable t) {
                log.error("Error while ticking chunk loader {}!", chunkLoaderManager.chunkLoader, t);
            }
        }
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
            var shouldRemove = entry.getValue() == 0;
            if (shouldRemove) {
                if (!unloadChunk(entry.getKey()).getNow(true)) {
                    // Chunk cannot be unloaded, may because ChunkUnloadEvent is cancelled
                    // If chunk unloading is cancelled by a plugin, unloadChunk()
                    // will return CompletableFuture.completedFuture(false) so we can
                    // use getNow() method here
                    shouldRemove = false;
                    entry.setValue(removeUnneededChunkCycle);
                }
            }
            return shouldRemove;
        });

        // Add unused chunk to the clear countdown map
        loadedChunks.forEach((chunkHash, loadedChunk) -> {
            if (loadedChunk.getChunkLoaderCount() == 0 && !keepLoadingChunks.contains(chunkHash) && !unusedChunkClearCountDown.containsKey(chunkHash)) {
                unusedChunkClearCountDown.put(chunkHash, removeUnneededChunkCycle);
            }
        });
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

    @Override
    public CompletableFuture<Chunk> getChunkLoadingFuture(int x, int z) {
        return loadingChunks.get(HashUtils.hashXZ(x, z));
    }

    @Override
    public Chunk getOrLoadChunkSync(int x, int z) {
        return getOrLoadChunk(x, z).join();
    }

    @Override
    public CompletableFuture<Chunk> getOrLoadChunk(int x, int z) {
        var chunk = getChunk(x, z);
        if (chunk != null) return CompletableFuture.completedFuture(chunk);
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
        return CompletableFuture.allOf(futureSet.toArray(CompletableFuture[]::new)).thenApplyAsync($ ->
                futureSet.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toSet()), Server.getInstance().getVirtualThreadPool()
        );
    }

    @Override
    public CompletableFuture<Chunk> loadChunk(int x, int z) {
        var hashXZ = HashUtils.hashXZ(x, z);
        if (isChunkLoaded(hashXZ)) throw new IllegalStateException("Chunk is already loaded");

        var future = new CompletableFuture<Chunk>();
        // Only one thread can successfully put future into loadingChunks, other threads will get the successfully written thread's future
        var presentValue = loadingChunks.putIfAbsent(hashXZ, future);
        if (presentValue != null) return presentValue;

        var chunkPreLoadEvent = new ChunkPreLoadEvent(dimension, x, z);
        chunkPreLoadEvent.call();

        worldStorage.readChunk(x, z, dimension.getDimensionInfo()).exceptionally(t -> {
            log.error("Error while reading chunk ({},{}) !", x, z, t);
            return AllayUnsafeChunk.builder().emptyChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
        }).thenCompose(chunk -> {
            if (chunk.getState() != FINISHED) {
                // Re-generate chunk if it's not fully loaded
                return getWorldGenerator().generateChunk(x, z);
            }
            return CompletableFuture.completedFuture(chunk);
        }).exceptionally(t -> {
            log.error("Error while generating chunk ({},{}) !", x, z, t);
            return AllayUnsafeChunk.builder().emptyChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
        }).thenApply(preparedChunk -> {
            ((AllayChunk) preparedChunk).beforeSetChunk(dimension);
            setChunk(x, z, preparedChunk);
            ((AllayChunk) preparedChunk).afterSetChunk(dimension);
            future.complete(preparedChunk);
            loadingChunks.remove(hashXZ);

            var chunkLoadEvent = new ChunkLoadEvent(dimension, preparedChunk);
            chunkLoadEvent.call();

            return preparedChunk;
        }).exceptionally(t -> {
            log.error("Error while setting chunk ({},{}) !", x, z, t);
            return AllayUnsafeChunk.builder().emptyChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
        });
        return future;
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

    @Override
    public CompletableFuture<Boolean> unloadChunk(int x, int z) {
        return unloadChunk(HashUtils.hashXZ(x, z));
    }

    @Override
    public CompletableFuture<Boolean> unloadChunk(long chunkHash) {
        var chunk = getChunk(chunkHash);
        if (chunk == null) return CompletableFuture.completedFuture(false);

        var event = new ChunkUnloadEvent(dimension, chunk);
        event.call();
        if (event.isCancelled()) return CompletableFuture.completedFuture(false);

        loadedChunks.remove(chunkHash);
        chunk.getEntities().forEach((runtimeId, entity) -> {
            entity.despawnFromAll();
            ((AllayEntityPhysicsService) dimension.getEntityPhysicsService()).removeEntity(entity);
        });
        ((AllayLightService) dimension.getLightService()).onChunkUnload(chunk);

        var future = new CompletableFuture<Boolean>();
        worldStorage
                .writeChunk(chunk)
                .exceptionally(t -> {
                    future.complete(false);
                    return null;
                })
                .thenRun(() -> future.complete(true));
        return future;
    }

    @Override
    public CompletableFuture<Void> unloadAllChunks() {
        return CompletableFuture.allOf(loadedChunks.keySet().stream().map(this::unloadChunk).toArray(CompletableFuture[]::new));
    }

    private final class ChunkLoaderManager {
        private final ChunkLoader chunkLoader;
        private final LongComparator chunkDistanceComparatorHashed = new LongComparator() {
            @Override
            public int compare(long chunkHash1, long chunkHash2) {
                var floor = MathUtils.floor(chunkLoader.getLocation());
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
                var floor = MathUtils.floor(chunkLoader.getLocation());
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
            removeChunkLoaderInChunks(sentChunks);
            chunkLoader.onChunkOutOfRange(sentChunks);
            if (asyncChunkSendingManager != null)
                asyncChunkSendingManager.stop();
        }

        public void tick() {
            if (!chunkLoader.isLoaderActive()) return;
            long currentLoaderChunkPosHashed;
            var floor = MathUtils.floor(chunkLoader.getLocation());
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
            var difference = Sets.difference(sentChunks, inRadiusChunks);
            removeChunkLoaderInChunks(difference);
            // Unload chunks out of range
            chunkLoader.onChunkOutOfRange(difference);
            // The intersection of sentChunks and inRadiusChunks
            sentChunks.removeAll(difference);
        }

        private void removeChunkLoaderInChunks(Set<Long> chunkHashes) {
            chunkHashes.stream()
                    .map(AllayChunkService.this::getChunk)
                    .filter(Objects::nonNull)
                    .forEach(chunk -> chunk.removeChunkLoader(chunkLoader));
        }

        private void updateChunkSendingQueue() {
            chunkSendingQueue.clear();
            // Blocks that have already been sent will not be resent
            var difference = Sets.difference(inRadiusChunks, sentChunks);
            difference.stream().sorted(chunkDistanceComparatorHashed).forEachOrdered(v -> chunkSendingQueue.enqueue(v.longValue()));
        }

        private void loadAndSendQueuedChunks() {
            if (chunkSendingQueue.isEmpty()) return;
            var chunkReadyToSend = new Long2ObjectOpenHashMap<Chunk>();
            int triedSendChunkCount = 0;
            do {
                triedSendChunkCount++;
                var chunkHash = chunkSendingQueue.dequeueLong();
                var chunk = getChunk(chunkHash);
                if (chunk == null) {
                    if (isChunkUnloaded(chunkHash))
                        loadChunk(HashUtils.getXFromHashXZ(chunkHash), HashUtils.getZFromHashXZ(chunkHash));
                    chunkSendingQueue.enqueue(chunkHash);
                    continue;
                }

                chunk.addChunkLoader(chunkLoader);
                chunkReadyToSend.put(chunkHash, chunk);
            } while (!chunkSendingQueue.isEmpty() && triedSendChunkCount < chunkTrySendCountPerTick);

            if (!chunkReadyToSend.isEmpty()) {
                chunkLoader.beforeSendChunks();
                var chunkSendingStrategy = Server.SETTINGS.worldSettings().chunkSendingStrategy();
                var useSubChunkSendingSystem = Server.SETTINGS.worldSettings().useSubChunkSendingSystem();
                if (useSubChunkSendingSystem) {
                    // Use SYNC mode if a sub-chunk sending system is enabled
                    // Because the encoding of sub-chunk lcp is very quick
                    chunkSendingStrategy = SYNC;
                }

                if (chunkSendingStrategy == ASYNC) {
                    asyncChunkSendingManager.addChunkToSendingQueue(chunkReadyToSend.values());
                } else {
                    // Priority is given to sending chunks that are close to the chunk loader
                    var lcpStream = chunkReadyToSend.values().stream();
                    lcpStream.sorted(chunkDistanceComparator).forEachOrdered(chunk -> {
                        var lcp = useSubChunkSendingSystem ?
                                ((AllayChunk) chunk).createSubChunkLevelChunkPacket() :
                                ((AllayChunk) chunk).createFullLevelChunkPacketChunk();
                        chunkLoader.sendPacket(lcp);
                        chunkLoader.onChunkInRangeSend(chunk);
                    });
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

            public void addChunkToSendingQueue(Collection<Chunk> chunks) {
                chunkSendingQueue.addAll(chunks);
            }

            public void stop() {
                loop.stop();
            }

            private void tick() {
                while (!chunkSendingQueue.isEmpty()) {
                    var chunk = chunkSendingQueue.poll();
                    var lcp = ((AllayChunk) chunk).createFullLevelChunkPacketChunk();
                    chunkLoader.sendPacket(lcp);
                    chunkLoader.onChunkInRangeSend(chunk);
                }
            }
        }
    }
}
