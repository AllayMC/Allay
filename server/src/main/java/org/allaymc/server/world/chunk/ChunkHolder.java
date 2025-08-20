package org.allaymc.server.world.chunk;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.annotation.NotThreadSafe;
import org.allaymc.api.eventbus.event.world.ChunkLoadEvent;
import org.allaymc.api.eventbus.event.world.ChunkUnloadEvent;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.world.generator.ChunkPyramid;
import org.allaymc.server.world.generator.ChunkStep;
import org.allaymc.server.world.service.AllayChunkService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * ChunkHolder is a class that holds a chunk and manages its state updating and unloading.
 *
 * @author daoge_cmd
 */
@Slf4j
@NotThreadSafe
public final class ChunkHolder {

    /**
     * The chunk service that owns this chunk holder.
     */
    private final AllayChunkService chunkService;
    /**
     * The dimension that this chunk belongs to.
     */
    private final Dimension dimension;
    /**
     * The world generator that used to generate the chunk.
     */
    private final WorldGenerator worldGenerator;
    /**
     * The world storage that used to read and write the chunk.
     */
    private final WorldStorage worldStorage;
    /**
     * The x coordinate of the chunk in the world.
     */
    @Getter
    private final int x;
    /**
     * The z coordinate of the chunk in the world.
     */
    @Getter
    private final int z;
    /**
     * A future which will be completed when the chunk is fully loaded (state == full). If the chunk
     * is unloaded while the state is not {@link ChunkState#FULL}, the future will be cancelled.
     */
    @Getter
    private final CompletableFuture<Chunk> loadFuture;
    /**
     * A future which will be completed when the chunk is unloaded.
     */
    @Getter
    private final CompletableFuture<Void> unloadFuture;
    /**
     * The chunk that the holder is holding. Can be {@code null} if the chunk read future is not done.
     */
    @Getter
    private Chunk chunk;
    /**
     * Whether the chunk is currently locked.
     */
    private boolean locked;
    /**
     * The target state that the chunk should be updated to. If the current state is lower than the target
     * state, the chunk holder will try to update the chunk to the next state until it reaches the target state.
     * Can be {@code null} if no target state is set.
     */
    private ChunkState targetState;
    /**
     * The countdown for unload the chunk. When the countdown reaches 0, the chunk will be unloaded. Set
     * this value to 0 manually will force the chunk to be unloaded in the next tick.
     */
    @Setter
    private int removeCountDown;

    public ChunkHolder(AllayChunkService chunkService, int x, int z, CompletableFuture<Chunk> chunkReadFuture) {
        this.chunkService = chunkService;
        this.dimension = chunkService.getDimension();
        this.worldGenerator = chunkService.getWorldGenerator();
        this.worldStorage = chunkService.getWorldStorage();
        this.x = x;
        this.z = z;
        this.loadFuture = new CompletableFuture<>();
        this.unloadFuture = new CompletableFuture<>();
        this.removeCountDown = Server.SETTINGS.worldSettings().removeUnusedProtoChunkCycle();
        // Wait chunk read future to complete
        this.lock();
        chunkReadFuture.whenComplete((c, t) -> {
            if (t != null) {
                log.error("Error while loading chunk ({},{}) !", x, z, t);
                this.chunk = AllayUnsafeChunk.builder().voidChunk(x, z, this.dimension.getDimensionInfo()).toSafeChunk();
            } else {
                this.chunk = c;
            }
            if (this.chunk.getState() == ChunkState.FULL) {
                onChunkLoad();
            }
            this.unlock();
        });
    }

    public boolean isValidFullChunk() {
        return isFullChunk() && !isUnloading();
    }

    public boolean isUsed() {
        return isLocked() || this.chunk.getChunkLoaderCount() != 0;
    }

    private boolean isFullChunk() {
        return getCurrentState() == ChunkState.FULL;
    }

    private ChunkState getCurrentState() {
        if (this.chunk == null) {
            // The chunk is still reading from the storage currently
            return ChunkState.NEW;
        }

        return this.chunk.getState();
    }

    private boolean shouldUpdate() {
        return this.targetState != null && getCurrentState().isBefore(this.targetState);
    }

    private boolean isUnloading() {
        return this.removeCountDown == 0;
    }

    public void setTargetState(ChunkState state) {
        if (this.targetState != null && this.targetState.isAfter(state)) {
            return;
        }

        this.targetState = state;
    }

    public void tick() {
        if (isLocked()) {
            // The chunk is locked, any operations on it should be skipped until the lock is released
            return;
        }

        if (updateRemoveCountDown()) {
            lock();
            try {
                if (isFullChunk()) {
                    new ChunkUnloadEvent(this.dimension, this.chunk).call();
                    ((AllayUnsafeChunk) this.chunk.toUnsafeChunk()).onChunkUnload(this.dimension);
                } else {
                    // Cancel the load future since the chunk haven't been fully loaded yet
                    this.loadFuture.cancel(true);
                }
                return;
            } catch (Throwable t) {
                log.error("Error while trying to unload chunk ({}, {}) !", this.x, this.z, t);
            } finally {
                worldStorage.writeChunk(this.chunk).thenRun(() -> {
                    this.unloadFuture.complete(null);
                    this.chunkService.removeChunkHolder(this.x, this.z);
                    unlock();
                });
            }
        }

        if (!shouldUpdate()) {
            return;
        }

        var nextState = chunk.getState().getNext();
        var step = ChunkPyramid.PYRAMID.getStepTo(nextState);
        if (checkDependencies(step) && lock(this.x, this.z, step.getWriteRange())) {
            CompletableFuture
                    .runAsync(() -> step.getTask().doWork(
                            this.worldGenerator,
                            this.chunk.toUnsafeChunk(),
                            // Should never be null here since we have locked the chunks that will be written
                            (x, z) -> Preconditions.checkNotNull(this.chunkService.getChunkHolder(x, z).getChunk())
                    ), Server.getInstance().getComputeThreadPool())
                    .exceptionally(t -> {
                        log.error("Error while process chunk task in chunk ({},{}) from state {} to state {}!", this.x, this.z, chunk.getState(), nextState, t);
                        return null;
                    })
                    .thenRun(() -> {
                        ((AllayUnsafeChunk) chunk.toUnsafeChunk()).setState(nextState);
                        if (nextState == ChunkState.FULL) {
                            onChunkLoad();
                        }
                        unlock(this.x, this.z, step.getWriteRange());
                    });
        }
    }

    private void onChunkLoad() {
        try {
            ((AllayUnsafeChunk) chunk.toUnsafeChunk()).onChunkLoad(dimension);
        } catch (Throwable t) {
            log.error("Error while calling onChunkLoad() at chunk ({},{}) !", x, z, t);
        }
        new ChunkLoadEvent(dimension, chunk).call();
        this.loadFuture.complete(this.chunk);
    }

    private boolean updateRemoveCountDown() {
        if (this.removeCountDown == 0) {
            // Countdown is externally set to 0 due to unloadChunk() call
            return true;
        }

        if (isUsed()) {
            // Reset the countdown if the chunk is used
            this.removeCountDown = isFullChunk() ?
                    Server.SETTINGS.worldSettings().removeUnusedFullChunkCycle() :
                    Server.SETTINGS.worldSettings().removeUnusedProtoChunkCycle();
            return false;
        } else {
            this.removeCountDown--;
        }

        return this.removeCountDown == 0;
    }

    private boolean checkDependencies(ChunkStep step) {
        var canUpdate = new AtomicBoolean(true);
        for (var entry : step.getDependencies().entrySet()) {
            var state = entry.getKey();
            var range = entry.getValue();
            forEachChunkRanged(this.x, this.z, range, depChunk -> {
                if (depChunk.getX() == this.x && depChunk.getZ() == this.z) {
                    // Skip the current chunk
                    return;
                }

                // Inform the dependency chunk that it should be updated to the target state
                depChunk.setTargetState(state);
                if (canUpdate.get() && (depChunk.isLocked() || depChunk.getCurrentState().isBefore(state))) {
                    // The dependency chunk is locked or its current state is lower than the target state,
                    // so we cannot update the current chunk
                    canUpdate.set(false);
                }
            }, true);
        }

        return canUpdate.get();
    }

    private boolean isLocked() {
        return this.locked;
    }

    private boolean isLocked(int chunkX, int chunkZ) {
        return Preconditions.checkNotNull(chunkService.getChunkHolder(chunkX, chunkZ)).isLocked();
    }

    private boolean lock() {
        if (this.isLocked()) {
            log.warn("Trying to lock an already locked chunk ({}, {}) !", this.x, this.z);
            return true;
        }

        this.locked = true;
        return true;
    }

    private boolean lock(int chunkX, int chunkZ, int range) {
        if (!checkChunkRanged(chunkX, chunkZ, range, chunkHolder -> !chunkHolder.isLocked())) {
            return false;
        }

        forEachChunkRanged(chunkX, chunkZ, range, ChunkHolder::lock);
        return true;
    }

    public void unlock() {
        if (!this.isLocked()) {
            log.warn("Trying to unlock an already unlocked chunk ({}, {}) !", this.x, this.z);
            return;
        }

        this.locked = false;
    }

    public void unlock(int chunkX, int chunkZ, int range) {
        forEachChunkRanged(chunkX, chunkZ, range, ChunkHolder::unlock);
    }

    private void forEachChunkRanged(int chunkX, int chunkZ, int range, Consumer<ChunkHolder> consumer) {
        forEachChunkRanged(chunkX, chunkZ, range, consumer, false);
    }

    private void forEachChunkRanged(int chunkX, int chunkZ, int range, Consumer<ChunkHolder> consumer, boolean load) {
        if (range == 0) {
            var chunkHolder = load ? chunkService.getOrCreateChunkHolder(chunkX, chunkZ) : chunkService.getChunkHolder(chunkX, chunkZ);
            consumer.accept(Preconditions.checkNotNull(chunkHolder));
            return;
        }

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                var chunkHolder = load ?
                        chunkService.getOrCreateChunkHolder(chunkX + i, chunkZ + j) :
                        chunkService.getChunkHolder(chunkX + i, chunkZ + j);
                consumer.accept(Preconditions.checkNotNull(chunkHolder));
            }
        }
    }

    private boolean checkChunkRanged(int chunkX, int chunkZ, int range, Predicate<ChunkHolder> predicator) {
        if (range == 0) {
            return predicator.test(Preconditions.checkNotNull(chunkService.getChunkHolder(chunkX, chunkZ)));
        }

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                if (!predicator.test(Preconditions.checkNotNull(chunkService.getChunkHolder(chunkX + i, chunkZ + j)))) {
                    return false;
                }
            }
        }

        return true;
    }
}
