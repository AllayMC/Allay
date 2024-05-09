package org.allaymc.server.world.service;

import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.*;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.annotation.SlowOperation;
import org.allaymc.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import org.allaymc.api.server.Server;
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
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private final Map<ChunkLoader, ChunkLoaderManager> chunkLoaderManagers = new Object2ObjectArrayMap<>(Server.SETTINGS.genericSettings().maxClientCount());
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
            var chunkHash = entry.getKey();
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
    public synchronized Chunk getChunkImmediately(int x, int z) {
        return getOrLoadChunk(x, z).join();
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
        Set<CompletableFuture<Chunk>> futureSet = new HashSet<>();

        for (int dx = -range; dx <= range; dx++) {
            for (int dz = -range; dz <= range; dz++) {
                if (dx * dx + dz * dz <= range * range) {
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
        CompletableFuture<Chunk> future;
        // Prevent multiple threads from putting the same chunk into loadingChunks at the same time and wasting computing resources
        var presentValue = loadingChunks.putIfAbsent(hashXZ, future = worldStorage.readChunk(x, z, DimensionInfo.OVERWORLD)
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
                    loadingChunks.remove(hashXZ);
                    return prepareChunk;
                })
        );
        return presentValue == null ? future : presentValue;
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
    public Collection<Chunk> getLoadedChunks() {
        return loadedChunks.values();
    }

    public void unloadChunk(int x, int z) {
        unloadChunk(HashUtils.hashXZ(x, z));
    }

    public void unloadChunk(long chunkHash) {
        var chunk = getChunk(chunkHash);
        if (chunk == null) return;
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
        private final LongComparator chunkDistanceComparator = new LongComparator() {
            @Override
            public int compare(long chunkHash1, long chunkHash2) {
                Vector3i floor = MathUtils.floor(chunkLoader.getLocation());
                var loaderChunkX = floor.x >> 4;
                var chunkDX1 = loaderChunkX - HashUtils.getXFromHashXZ(chunkHash1);
                var chunkDX2 = loaderChunkX - HashUtils.getXFromHashXZ(chunkHash2);

                var loaderChunkZ = floor.z >> 4;
                var chunkDZ1 = loaderChunkZ - HashUtils.getZFromHashXZ(chunkHash1);
                var chunkDZ2 = loaderChunkZ - HashUtils.getZFromHashXZ(chunkHash2);
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
        private final LongArrayFIFOQueue chunkSendQueue;
        private long lastLoaderChunkPosHashed = Long.MAX_VALUE;

        ChunkLoaderManager(ChunkLoader chunkLoader) {
            this.chunkLoader = chunkLoader;
            // S = pi * radius ^ 2
            this.chunkSendQueue = new LongArrayFIFOQueue((int) Math.ceil(chunkLoader.getChunkLoadingRadius() * chunkLoader.getChunkLoadingRadius() * Math.PI));
            this.chunkTrySendCountPerTick = chunkLoader.getChunkTrySendCountPerTick();
        }

        public void onRemoved() {
            chunkLoader.onChunkOutOfRange(sentChunks);
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
            // Unload chunks out of range
            chunkLoader.onChunkOutOfRange(difference);
            // The intersection of sentChunks and inRadiusChunks
            sentChunks.removeAll(difference);
        }

        private void updateChunkSendingQueue() {
            chunkSendQueue.clear();
            // Blocks that have already been sent will not be resent
            Sets.SetView<Long> difference = Sets.difference(inRadiusChunks, sentChunks);
            difference.stream().sorted(chunkDistanceComparator).forEachOrdered(v -> chunkSendQueue.enqueue(v.longValue()));
        }

        private void loadAndSendQueuedChunks() {
            // Send ncp to a client every tick to reduce the possibility of chunk disappearing.
            // This solution is similar to what dragonfly does
            chunkLoader.publishClientChunkUpdate();
            if (chunkSendQueue.isEmpty()) return;
            var chunkReadyToSend = new Long2ObjectOpenHashMap<Chunk>();
            int triedSendChunkCount = 0;
            do {
                triedSendChunkCount++;
                long chunkHash = chunkSendQueue.dequeueLong();
                var chunk = getChunk(chunkHash);
                if (chunk == null) {
                    if (isChunkUnloaded(chunkHash)) {
                        loadChunk(HashUtils.getXFromHashXZ(chunkHash), HashUtils.getZFromHashXZ(chunkHash));
                    }
                    chunkSendQueue.enqueue(chunkHash);
                    continue;
                }
                chunk.addChunkLoader(chunkLoader);
                chunkReadyToSend.put(chunkHash, chunk);
            } while (!chunkSendQueue.isEmpty() && triedSendChunkCount < chunkTrySendCountPerTick);
            if (!chunkReadyToSend.isEmpty()) {
                chunkLoader.publishClientChunkUpdate();
                var worldSettings = Server.SETTINGS.worldSettings();
                var chunkSendingStrategy = worldSettings.chunkSendingStrategy();
                var useSubChunkSendingSystem = Server.SETTINGS.worldSettings().useSubChunkSendingSystem();
                if (useSubChunkSendingSystem) {
                    // Use SYNC mode if a sub-chunk sending system is enabled
                    // Because the encoding of sub-chunk lcp is very quick
                    chunkSendingStrategy = SYNC;
                }
                if (chunkSendingStrategy == ASYNC) {
                    List<CompletableFuture<Void>> completableFutureList = new ArrayList<>();
                    chunkReadyToSend.values().forEach(chunk -> {
                        completableFutureList.add(CompletableFuture.runAsync(() -> {
                            chunkLoader.sendLevelChunkPacket(chunk.createFullLevelChunkPacketChunk());
                            chunkLoader.onChunkInRangeSent(chunk);
                        }, Server.getInstance().getVirtualThreadPool()));
                    });
                    if (!completableFutureList.isEmpty()) {
                        CompletableFuture.allOf(completableFutureList.toArray(CompletableFuture<?>[]::new)).join();
                    }
                } else {
                    Stream<Chunk> lcpStream = chunkReadyToSend.values().stream();
                    lcpStream.map(chunk -> useSubChunkSendingSystem ? chunk.createSubChunkLevelChunkPacket() : chunk.createFullLevelChunkPacketChunk()).forEachOrdered(
                            chunkLoader::sendLevelChunkPacket
                    );
                    // 3. Call onChunkInRangeSent()
                    chunkReadyToSend.values().forEach(chunkLoader::onChunkInRangeSent);
                }
                sentChunks.addAll(chunkReadyToSend.keySet());
            }
        }

        private boolean isChunkInRadius(int chunkX, int chunkZ, int radius) {
            return chunkX * chunkX + chunkZ * chunkZ <= radius * radius;
        }
    }
}
