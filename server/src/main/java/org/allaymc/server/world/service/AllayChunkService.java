package org.allaymc.server.world.service;

import com.google.common.base.Preconditions;
import io.netty.util.internal.PlatformDependent;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.world.ChunkLoadEvent;
import org.allaymc.api.eventbus.event.world.ChunkUnloadEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.*;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.service.ChunkService;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.datastruct.collections.queue.BlockingQueueWrapper;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.generator.ChunkLocks;
import org.allaymc.server.world.generator.ChunkPyramid;
import org.jctools.maps.NonBlockingHashMapLong;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * @author daoge_cmd
 */
@Slf4j
public final class AllayChunkService implements ChunkService {

    private final Dimension dimension;
    @Getter
    private final WorldGenerator worldGenerator;
    private final WorldStorage worldStorage;
    /**
     * Holds the loading and loaded chunks. The status of {@code CompletableFuture} represents
     * the status of the chunk (loading or loaded). May be query in multiple threads but should
     * only be written in chunk service thread.
     */
    @Getter
    private final Map<Long, CompletableFuture<Chunk>> chunks;
    /**
     * Holds the loading chunks that are not yet loaded. May be query in multiple threads but should
     * only be written in chunk service thread.
     */
    private final Map<Long, Chunk> protoChunks;
    /**
     * Holds all the proto chunk futures. These futures will be completed when the proto chunk become
     * to full chunk.
     */
    private final Map<Long, CompletableFuture<Chunk>> protoChunkFutures;
    /**
     * Chunk source that is accessible to both full chunks and proto chunks.
     */
    private final ChunkSource fullAndProtoChunkSource;
    /**
     * Used in chunk generation and not thread-safe. Should only be read/write in chunk service thread.
     */
    private final ChunkLocks chunkLocks;
    /**
     * Count down for unused full chunks.
     */
    private final Map<Long, Integer> unusedFullChunkCountDown;
    /**
     * Count down for unused proto chunks.
     */
    private final Map<Long, Integer> unusedProtoChunkCountDown;
    private final Map<ChunkLoader, ChunkLoaderHolder> chunkLoaders;
    private final BlockingQueueWrapper<Runnable> queue;
    private final AtomicBoolean isRunning;

    public AllayChunkService(Dimension dimension, WorldGenerator worldGenerator, WorldStorage worldStorage) {
        this.dimension = dimension;
        this.worldGenerator = worldGenerator;
        this.worldStorage = worldStorage;
        this.chunks = new NonBlockingHashMapLong<>();
        this.protoChunks = new NonBlockingHashMapLong<>();
        this.protoChunkFutures = new NonBlockingHashMapLong<>();
        this.fullAndProtoChunkSource = new FullAndProtoChunkSource();
        this.chunkLocks = new ChunkLocks();
        this.unusedFullChunkCountDown = new NonBlockingHashMapLong<>();
        this.unusedProtoChunkCountDown = new NonBlockingHashMapLong<>();
        this.chunkLoaders = new Object2ObjectOpenHashMap<>();
        this.queue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
        this.isRunning = new AtomicBoolean(true);
    }

    public void startTick() {
        Thread.ofPlatform().name("Chunk Service Thread - " + dimension.getWorld().getName()).start(() -> {
            while (isRunning.get()) {
                Runnable runnable;
                while ((runnable = queue.tryPoll(1, TimeUnit.SECONDS)) != null) {
                    runnable.run();
                }
            }
        });
    }

    public void shutdown() {
        this.isRunning.set(false);
        this.unloadAllChunks().join();
    }

    public void tick(long currentTick) {
        tickChunkLoaders();
        removeUnusedFullChunks();
        removeUnusedProtoChunks();
        tickChunks(currentTick);
    }

    private void tickChunks(long currentTick) {
        forEachLoadedChunks(chunk -> {
            if (!shouldTickChunk(chunk)) {
                return;
            }

            try {
                chunk.applyOperation(unsafeChunk -> ((AllayUnsafeChunk) unsafeChunk).tick(currentTick, dimension), OperationType.WRITE, OperationType.WRITE);
                ((AllayUnsafeChunk) chunk.toUnsafeChunk()).checkAutoSave(worldStorage);
            } catch (Throwable t) {
                log.error("Error while ticking chunk({}, {})!", chunk.getX(), chunk.getZ(), t);
            }
        });
    }

    private boolean shouldTickChunk(Chunk chunk) {
        var cx = chunk.getX();
        var cz = chunk.getZ();
        var shouldTick = false;
        for (var chunkLoader : chunk.getChunkLoaders()) {
            var lcx = ((int) Math.floor(chunkLoader.getLocation().x())) >> 4;
            var lcz = ((int) Math.floor(chunkLoader.getLocation().z())) >> 4;
            if (Math.pow(lcx - cx, 2) + Math.pow(lcz - cz, 2) <= Math.pow(Server.SETTINGS.worldSettings().tickRadius(), 2)) {
                shouldTick = true;
                break;
            }
        }
        return shouldTick;
    }

    public void sendChunkPackets() {
        forEachLoadedChunks(chunk -> ((AllayUnsafeChunk) chunk.toUnsafeChunk()).sendChunkPackets());
    }

    private void tickChunkLoaders() {
        // NOTICE: There is no need to use parallel stream here
        for (var chunkLoaderHolder : chunkLoaders.values()) {
            try {
                chunkLoaderHolder.tick();
            } catch (Throwable t) {
                log.error("Error while ticking chunk loader {}!", chunkLoaderHolder.getChunkLoader(), t);
            }
        }
    }

    private void runTask(Runnable task) {
        queue.offer(task);
    }

    private void removeUnusedFullChunks() {
        runTask(() -> {
            unusedFullChunkCountDown.entrySet().removeIf(entry -> {
                var chunk = getChunk(entry.getKey());
                return chunk == null || chunk.getChunkLoaderCount() > 0;
            });
            // Update countdown
            unusedFullChunkCountDown.replaceAll((chunkHash, countDown) -> countDown - 1);
            // Remove countdown ended unused chunks
            unusedFullChunkCountDown.entrySet().removeIf(entry -> {
                // It is possible that the value be smaller than zero, however it is not a problem
                if (entry.getValue() <= 0) {
                    unloadChunk(entry.getKey());
                    return true;
                }

                return false;
            });

            runTask(() -> {
                // Add unused chunk to the clear countdown map. Please note that we should do this in the next task
                // since chunks won't be removed immediately by calling unloadChunk(). The method unloadChunk()
                // actually passes a task to the queue and executes the chunk unload logic in the task
                forEachLoadedChunks(chunk -> {
                    var chunkHash = chunk.computeChunkHash();
                    if (chunk.getChunkLoaderCount() == 0 && !unusedFullChunkCountDown.containsKey(chunkHash)) {
                        unusedFullChunkCountDown.put(chunkHash, Server.SETTINGS.worldSettings().removeUnusedFullChunkCycle());
                    }
                });
            });
        });
    }

    private void removeUnusedProtoChunks() {
        runTask(() -> {
            // Update countdown directly since proto chunks won't hold chunk loaders
            unusedProtoChunkCountDown.replaceAll((chunkHash, countDown) -> countDown - 1);
            unusedProtoChunkCountDown.entrySet().removeIf(entry -> {
                if (entry.getValue() <= 0) {
                    var hash = entry.getKey();
                    removeProtoChunk(HashUtils.getXFromHashXZ(hash), HashUtils.getZFromHashXZ(hash));
                    return true;
                }

                return false;
            });
        });
    }

    @Override
    public void removeUnusedChunksImmediately() {
        unusedFullChunkCountDown.replaceAll((chunkHash, countDown) -> 0);
        unusedProtoChunkCountDown.replaceAll((chunkHash, countDown) -> 0);
    }

    @Override
    public CompletableFuture<Chunk> loadChunk(int x, int z) {
        var futureReturned = new CompletableFuture<Chunk>();
        runTask(() -> {
            try {
                var chunkHash = HashUtils.hashXZ(x, z);
                var presentFuture = chunks.get(chunkHash);
                if (presentFuture != null) {
                    Utils.mirror(presentFuture, futureReturned);
                    return;
                }

                var future = worldStorage.readChunk(x, z, dimension.getDimensionInfo()).thenCompose(chunk -> {
                    if (chunk.getState() != ChunkState.FULL) {
                        return generateChunk(chunk);
                    }

                    return CompletableFuture.completedFuture(chunk);
                }).exceptionally(t -> {
                    log.error("Error while loading chunk ({},{}) !", x, z, t);
                    return AllayUnsafeChunk.builder().voidChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
                }).thenApply(chunk -> {
                    if (chunk.getState() != ChunkState.FULL) {
                        // Chunk is not fully generated, may be a timeout proto chunk. Let's remove it from the
                        // chunks map and throw an exception to indicate that the future is completed exceptionally
                        runTask(() -> chunks.remove(chunkHash));
                        throw new CancellationException();
                    }

                    try {
                        ((AllayUnsafeChunk) chunk.toUnsafeChunk()).onChunkLoad(dimension);
                    } catch (Throwable t) {
                        log.error("Error while calling onChunkLoad() at chunk ({},{}) !", x, z, t);
                        return AllayUnsafeChunk.builder().voidChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
                    }
                    new ChunkLoadEvent(dimension, chunk).call();

                    return chunk;
                }).whenComplete((chunk, t) -> {
                    if (t != null) {
                        // Chunk is not fully generated
                        return;
                    }

                    // Check other chunks
                    var step = ChunkPyramid.PYRAMID.getStepTo(ChunkState.FULL);
                    var range = Math.max(step.getWriteRange(), ChunkPyramid.PYRAMID.getMaxDependentRange(ChunkState.FULL));
                    if (range == 0) {
                        return;
                    }

                    runTask(() -> forEachNeighborProtoChunks(chunk.getX(), chunk.getZ(), range, neighborChunk -> runTask(() -> tryStepToNextStatus(neighborChunk))));
                });
                chunks.put(chunkHash, future);
            } catch (Throwable t) {
                log.error("Error while loading chunk ({}, {}) !", x, z, t);
                futureReturned.completeExceptionally(t);
            }
        });

        return futureReturned;
    }

    private CompletableFuture<Chunk> generateChunk(Chunk chunk) {
        Preconditions.checkArgument(chunk.getState() == ChunkState.NEW);
        // We are safe to do that even we are not in the chunk service thread, that's
        // because generateChunk() method shouldn't be called in a same chunk multiple times
        var future = addProtoChunkFuture(chunk);
        runTask(() -> {
            // Add the chunk to the proto chunks map
            addProtoChunk(chunk);
            tryStepToNextStatus(chunk);
        });
        return future;
    }

    private void tryStepToNextStatus(Chunk chunk) {
        var chunkX = chunk.getX();
        var chunkZ = chunk.getZ();
        var nextState = chunk.getState().getNext();
        if (nextState == null) {
            // The chunk is already a full chunk
            return;
        }

        // The step to go to the next state
        var step = ChunkPyramid.PYRAMID.getStepTo(nextState);
        if (
                /* Check if we have the required dependencies. This method will
                   also try to load the chunk if it is not loaded yet */
                checkDependencies(step.getDependencies(), chunk) &&
                /* Lock the chunks that we may need to modify. This is depending on
                   the write range of the step */
                chunkLocks.lock(chunkX, chunkZ, step.getWriteRange())
        ) {
            CompletableFuture
                    // Do the heavy work in the compute thread pool
                    .runAsync(() -> step.getTask().doWork(worldGenerator, chunk.toUnsafeChunk(), fullAndProtoChunkSource), Server.getInstance().getComputeThreadPool())
                    .exceptionally(t -> {
                        log.error("Error while generating chunk ({},{}) from state {} to state {}!", chunkX, chunkZ, chunk.getState(), nextState, t);
                        return null;
                    })
                    .thenRun(() -> runTask(() -> {
                        // Release the locks after the task is done
                        chunkLocks.unlock(chunkX, chunkZ, step.getWriteRange());
                        // Update chunk state
                        ((AllayUnsafeChunk) chunk.toUnsafeChunk()).setState(nextState);

                        if (nextState == ChunkState.FULL) {
                            // The chunk is fully generated, remove it from the proto chunks map
                            removeProtoChunk(chunkX, chunkZ);
                            return;
                        } else {
                            // Continue check if we can step to the next state
                            runTask(() -> tryStepToNextStatus(chunk));
                        }

                        // The range we need to check is the maximum value of the dependent range and the write range
                        var range = Math.max(step.getWriteRange(), ChunkPyramid.PYRAMID.getMaxDependentRange(nextState));
                        if (range == 0) {
                            return;
                        }

                        forEachNeighborProtoChunks(chunkX, chunkZ, range, neighborChunk -> runTask(() -> tryStepToNextStatus(neighborChunk)));
                    }));
        }
    }

    private void forEachNeighborProtoChunks(int chunkX, int chunkZ, int range, Consumer<Chunk> consumer) {
        for (int x = chunkX - range; x <= chunkX + range; x++) {
            for (int z = chunkZ - range; z <= chunkZ + range; z++) {
                if (x == chunkX && z == chunkZ) {
                    // Skip the current chunk
                    continue;
                }

                var chunk = getProtoChunk(x, z);
                if (chunk != null) {
                    consumer.accept(chunk);
                }
            }
        }
    }

    private boolean checkDependencies(Map<ChunkState, Integer> dependencies, Chunk chunk) {
        if (dependencies.isEmpty()) {
            return true;
        }

        for (var entry : dependencies.entrySet()) {
            var minState = entry.getKey();
            var range = entry.getValue();
            var chunkX = chunk.getX();
            var chunkZ = chunk.getZ();
            for (int x = chunkX - range; x <= chunkX + range; x++) {
                for (int z = chunkZ - range; z <= chunkZ + range; z++) {
                    if (x == chunkX && z == chunkZ) {
                        // Skip the current chunk
                        continue;
                    }

                    // Get the dependency chunk from both loaded chunks and proto chunks
                    var dependencyChunk = getChunk(x, z);
                    if (dependencyChunk == null) {
                        dependencyChunk = getProtoChunk(x, z);
                    }

                    // The chunk is unloaded
                    if (dependencyChunk == null) {
                        if (Math.abs(computeChunkLoadLevel(chunkX, chunkZ)) < ChunkPyramid.PYRAMID.maxDependentRange()) {
                            loadChunk(x, z);
                        }
                        return false;
                    }

                    // The dependency chunk is not in the required state
                    if (dependencyChunk.getState().isBefore(minState)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    // load level = 0 -> chunk is loaded directly by at least one chunk loader
    // load level < 0 -> chunk is not loaded directly by any chunk loader, the value
    // is the max of "loading radius minus distance to chunk loader"
    private double computeChunkLoadLevel(int x, int z) {
        double loadLevel = -Double.MAX_VALUE;
        for (var chunkLoader : chunkLoaders.keySet()) {
            var lcx = ((int) Math.floor(chunkLoader.getLocation().x())) >> 4;
            var lcz = ((int) Math.floor(chunkLoader.getLocation().z())) >> 4;
            var distance = MathUtils.calculateEuclideanDistance(x, z, lcx, lcz);
            if (distance <= chunkLoader.getChunkLoadingRadius()) {
                // Return directly because we found a chunk loader that can load this chunk
                return 0;
            }

            // Otherwise, we compute the load level based on the distance
            loadLevel = Math.ceil(Math.max(loadLevel, distance - chunkLoader.getChunkLoadingRadius()));
        }

        return loadLevel;
    }

    private CompletableFuture<Chunk> addProtoChunkFuture(Chunk chunk) {
        var future = new CompletableFuture<Chunk>();
        this.protoChunkFutures.put(chunk.computeChunkHash(), future);
        return future;
    }

    // Should call addProtoChunkFuture() before calling this method
    private void addProtoChunk(Chunk chunk) {
        var hash = chunk.computeChunkHash();
        this.protoChunks.put(hash, chunk);
        this.unusedProtoChunkCountDown.put(hash, Server.SETTINGS.worldSettings().removeUnusedProtoChunkCycle());
    }

    private Chunk getProtoChunk(int x, int z) {
        return protoChunks.get(HashUtils.hashXZ(x, z));
    }

    private void removeProtoChunk(int x, int z) {
        var hash = HashUtils.hashXZ(x, z);
        var chunk = this.protoChunks.remove(hash);
        try {
            this.protoChunkFutures.remove(hash).complete(chunk);
        } catch (CancellationException e) {
            // Expected behavior, ignore it
        }
        this.unusedProtoChunkCountDown.remove(hash);
    }

    @Override
    public CompletableFuture<Boolean> unloadChunk(int x, int z) {
        var futureReturned = new CompletableFuture<Boolean>();
        runTask(() -> {
            try {
                var chunkHash = HashUtils.hashXZ(x, z);
                var future = chunks.get(chunkHash);
                if (future == null || !Utils.isDoneNormally(future)) {
                    futureReturned.complete(false);
                    return;
                }

                new ChunkUnloadEvent(dimension, future.resultNow()).call();
                var chunk = chunks.remove(chunkHash).resultNow();
                ((AllayUnsafeChunk) chunk.toUnsafeChunk()).onChunkUnload(dimension);
                worldStorage.writeChunk(chunk)
                        .exceptionally(t -> {
                            futureReturned.complete(false);
                            return null;
                        })
                        .thenRun(() -> futureReturned.complete(true));
            } catch (Throwable t) {
                log.error("Error while trying to unload chunk ({}, {}) !", x, z, t);
                futureReturned.completeExceptionally(t);
            }
        });

        return futureReturned;
    }

    @Override
    public CompletableFuture<Chunk> getChunkFuture(int x, int z) {
        return chunks.get(HashUtils.hashXZ(x, z));
    }

    @Override
    @UnmodifiableView
    public Set<ChunkLoader> getChunkLoaders() {
        return Collections.unmodifiableSet(chunkLoaders.keySet());
    }

    @Override
    public void addChunkLoader(ChunkLoader chunkLoader) {
        this.chunkLoaders.put(chunkLoader, new ChunkLoaderHolder(this, chunkLoader));
    }

    @Override
    public void removeChunkLoader(ChunkLoader chunkLoader) {
        var removed = this.chunkLoaders.remove(chunkLoader);
        if (removed != null) removed.onRemoved();
    }

    @Override
    public void forEachLoadedChunks(Consumer<Chunk> consumer) {
        for (var future : chunks.values()) {
            if (Utils.isDoneNormally(future)) {
                consumer.accept(future.resultNow());
            }
        }
    }

    @Override
    @UnmodifiableView
    public Collection<Chunk> getLoadedChunks() {
        return chunks.values().stream().filter(Utils::isDoneNormally).map(CompletableFuture::resultNow).toList();
    }

    @Override
    @UnmodifiableView
    public Collection<CompletableFuture<Chunk>> getLoadingChunks() {
        return chunks.values().stream().filter(future -> !future.isDone()).toList();
    }

    @Override
    public CompletableFuture<Void> unloadAllChunks() {
        return CompletableFuture.allOf(chunks.keySet().stream().map(this::unloadChunk).toArray(CompletableFuture[]::new));
    }

    protected class FullAndProtoChunkSource implements ChunkSource {
        @Override
        public Chunk getChunk(int x, int z) {
            var chunk = AllayChunkService.this.getChunk(x, z);
            return chunk != null ? chunk : AllayChunkService.this.getProtoChunk(x, z);
        }
    }
}
