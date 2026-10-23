package org.allaymc.server.world.manager;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.manager.ChunkManager;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.AllayServer;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.chunk.ChunkHolder;
import org.jctools.maps.NonBlockingHashMapLong;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

/**
 * {@code AllayChunkManager} coordinates
 * chunk holders, loader demand, and active chunk ticks for one
 * dimension. Holders own asynchronous read, generation, and unload transitions,
 * while loader holders request nearby chunks and attach residency demand as
 * chunks become available.
 *
 * <p>
 * A tick updates loader demand first, then holder progress, then active {@code FULL}
 * chunks under block and biome write locks. Lookup hides holders already marked
 * for unloading, even if their load future completed earlier. The holder map is
 * concurrent, but loader registration and holder state transitions are not
 * generally thread-safe. Shutdown drives holder ticks while waiting for unload
 * futures; storage-future completion semantics and exceptional paths therefore
 * directly affect when shutdown returns or keeps waiting.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class AllayChunkManager implements ChunkManager {

    /**
     * Dimension owning this chunk manager.
     */
    @Getter
    private final Dimension dimension;
    /**
     * Generator used for holder generation steps.
     */
    @Getter
    private final WorldGenerator worldGenerator;
    /**
     * {@code worldStorage} reads and saves chunks. Its future completion
     * behavior controls when holders finish their load and unload transitions.
     */
    @Getter
    private final WorldStorage worldStorage;
    /**
     * {@code chunkHolders} maps packed chunk coordinates to holders. This map
     * supports concurrent access, but each holder still requires coordinated
     * state transitions.
     */
    private final Map<Long, ChunkHolder> chunkHolders;
    /**
     * {@code chunkLoaders} holds mutable loader registrations used on manager
     * ticks. Callers serialize registration, removal, and traversal because the
     * map is not concurrent.
     */
    private final Map<ChunkLoader, ChunkLoaderHolder> chunkLoaders;

    /**
     * Creates a manager retaining the dimension, generator, and storage
     * references. It starts with no holders or loaders; callers coordinate
     * subsequent registration and ticking within the dimension lifecycle.
     */
    public AllayChunkManager(Dimension dimension, WorldGenerator worldGenerator, WorldStorage worldStorage) {
        this.dimension = dimension;
        this.worldGenerator = worldGenerator;
        this.worldStorage = worldStorage;
        this.chunkHolders = new NonBlockingHashMapLong<>();
        this.chunkLoaders = new Object2ObjectOpenHashMap<>();
    }

    /**
     * Requests unloading of current holders and drives holder ticks while
     * waiting for their combined completion. Interrupted or exceptional waits
     * are logged by the helper, so returning does not always establish
     * successful persistence.
     */
    public void shutdown() {
        tickAndWait(unloadAllChunks());
    }

    /**
     * Ticks holders before waiting up to one second for the supplied completion
     * future. A timeout recursively repeats this process so holders previously
     * blocked by asynchronous work can enter their unload phase after becoming
     * available. There is no retry limit, and repeated timeouts retain
     * recursive stack frames. Interrupted and exceptional waits are logged and
     * returned from; interruption is not restored and failure is not rethrown.
     */
    private void tickAndWait(CompletableFuture<?> future) {
        // An unload request only sets holder state; ticking starts its write phase
        // before this wait can observe the resulting storage-dependent completion.
        tickChunkHolders();
        try {
            future.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException $) {
            // A previously busy holder may now be available to unload. Re-entering
            // drives it again, but the recursive retry has no stack-depth bound.
            tickAndWait(future);
        } catch (InterruptedException | ExecutionException e) {
            // This recovery path logs and returns even when unload did not
            // succeed; interrupted waits also lose their interrupt status
            // because it is not restored here.
            log.error("Error while waiting for chunk unload future!", e);
        }
    }

    /**
     * Updates loader demand, advances holder transitions, and ticks active
     * loaded chunks in that order. Callers must serialize manager ticks and
     * loader mutations; holder work can also complete asynchronously between
     * phases.
     */
    public void tick(long currentTick) {
        tickChunkLoaders();
        tickChunkHolders();
        tickChunks(currentTick);
    }

    /**
     * Ticks registered loader holders sequentially in the manager's execution
     * context. Each loader failure is logged and suppressed so later
     * registrations still receive their tick during this pass.
     */
    private void tickChunkLoaders() {
        // Loader holders mutate their queues and residency sets without internal
        // synchronization, so this traversal keeps their updates in the manager context.
        for (var chunkLoaderHolder : chunkLoaders.values()) {
            try {
                chunkLoaderHolder.tick();
            } catch (Throwable t) {
                // One loader failure must not skip unrelated registrations in
                // this pass; the failing holder retains whatever partial state
                // its tick already changed.
                log.error("Error while ticking chunk loader {}!", chunkLoaderHolder.getChunkLoader(), t);
            }
        }
    }

    /**
     * Ticks the current holder collection to advance generation and unload
     * requests. Holder exceptions propagate from this traversal, so an uncaught
     * synchronous failure can stop the remaining manager tick phases.
     */
    private void tickChunkHolders() {
        this.chunkHolders.values().forEach(ChunkHolder::tick);
    }

    /**
     * Ticks only available {@code FULL} chunks that have an attached loader within the
     * configured activity radius. Each participating chunk holds both block and
     * biome write locks while its block entities and block updates run.
     * Autosave is checked afterward outside that operation and only when
     * ticking returned normally. Per-chunk failures are logged and suppressed,
     * allowing later chunks to continue while preserving any changes already
     * made by the failed tick.
     */
    private void tickChunks(long currentTick) {
        forEachLoadedChunks(chunk -> {
            if (!isChunkActive(chunk.getX(), chunk.getZ())) {
                return;
            }

            try {
                // Both palette categories are write-locked during ticking
                // because block behaviors can change blocks and biomes;
                // autosave submission follows only after that protected
                // callback returns.
                chunk.applyOperation(unsafeChunk -> ((AllayUnsafeChunk) unsafeChunk).tick(currentTick, dimension), OperationType.WRITE, OperationType.WRITE);
                ((AllayUnsafeChunk) chunk.toUnsafeChunk()).checkAutoSave(worldStorage);
            } catch (Throwable t) {
                // A failing chunk keeps any mutations already performed, but
                // suppressing the exception allows other active chunks to
                // receive their ticks in this pass.
                log.error("Error while ticking chunk({}, {})!", chunk.getX(), chunk.getZ(), t);
            }
        });
    }

    /**
     * Returns whether an available {@code FULL} chunk has an attached loader inside the
     * configured tick radius. Each loader location is floored to block
     * coordinates before conversion to chunk coordinates, preserving
     * negative-position boundaries. Squared horizontal distance is compared
     * with the squared radius, so boundary chunks qualify. A loaded chunk with
     * no qualifying attached loader remains resident but is not selected for
     * this manager's active chunk tick.
     */
    @Override
    public boolean isChunkActive(int x, int z) {
        var chunk = getChunk(x, z);
        if (chunk == null) return false;

        for (var chunkLoader : chunk.getChunkLoaders()) {
            // Flooring before shifting keeps positions just below zero in the
            // negative chunk.
            var lcx = ((int) Math.floor(chunkLoader.getLocation().x())) >> 4;
            var lcz = ((int) Math.floor(chunkLoader.getLocation().z())) >> 4;
            if (Math.pow(lcx - x, 2) + Math.pow(lcz - z, 2) <= Math.pow(AllayServer.getSettings().worldSettings().tickRadius(), 2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets unused holders' removal countdowns to zero for a later unlocked
     * tick. Busy holders count as used, so this implementation leaves
     * in-progress proto chunks untouched despite the broader interface wording.
     */
    @Override
    public void removeUnusedChunksImmediately() {
        this.chunkHolders.values().forEach(chunkHolder -> {
            if (!chunkHolder.isUsed()) {
                chunkHolder.setRemoveCountDown(0);
            }
        });
    }

    /**
     * Returns the holder at chunk coordinates, creating it with an asynchronous
     * storage read when absent. Creation alone does not request {@code FULL}
     * generation; callers raise the holder target separately.
     */
    public ChunkHolder getOrCreateChunkHolder(int x, int z) {
        return this.chunkHolders.computeIfAbsent(HashUtils.hashXZ(x, z), $ -> new ChunkHolder(this, x, z, worldStorage.readChunk(x, z, dimension.getDimensionType())));
    }

    /**
     * Returns the existing holder at chunk coordinates, or {@code null} when absent.
     * The returned holder is shared and non-thread-safe even though its
     * containing lookup map supports concurrent access.
     */
    public ChunkHolder getChunkHolder(int x, int z) {
        return this.chunkHolders.get(HashUtils.hashXZ(x, z));
    }

    /**
     * Removes the holder mapping at chunk coordinates and warns when no mapping
     * existed. This method performs no unload hooks or writes; lifecycle
     * callers must complete those steps before removal.
     */
    public void removeChunkHolder(int x, int z) {
        var chunkHolder = this.chunkHolders.remove(HashUtils.hashXZ(x, z));
        if (chunkHolder == null) {
            log.warn("Trying to remove a chunk holder ({}, {}) that is not loaded!", x, z);
        }
    }

    /**
     * Requests {@code FULL} generation on an existing or newly created holder and
     * returns its shared load future. This does not reverse a pending unload
     * request or replace a previously completed outcome.
     */
    @Override
    public CompletableFuture<Chunk> loadChunk(int x, int z) {
        var chunkHolder = getOrCreateChunkHolder(x, z);
        chunkHolder.setTargetState(ChunkState.FULL);
        return chunkHolder.getLoadFuture();
    }

    /**
     * Forces an existing holder's removal countdown to zero and returns its
     * unload future. An absent holder is warned about and returns an already
     * completed future; actual removal waits for holder ticking and storage
     * completion.
     */
    @Override
    public CompletableFuture<Void> unloadChunk(int x, int z) {
        var chunkHolder = getChunkHolder(x, z);
        if (chunkHolder == null) {
            log.warn("Trying to unload a chunk ({}, {}) that is not loaded!", x, z);
            // No holder means there is no lifecycle work to wait for, so this
            // fallback completes successfully without issuing another storage
            // operation.
            return CompletableFuture.completedFuture(null);
        }

        chunkHolder.setRemoveCountDown(0);
        return chunkHolder.getUnloadFuture();
    }

    /**
     * Returns an existing holder's shared load future, or {@code null} when no holder
     * exists. A completed load future can outlive the period when normal lookup
     * considers that holder available.
     */
    @Override
    public CompletableFuture<Chunk> getChunkFuture(int x, int z) {
        var chunkHolder = getChunkHolder(x, z);
        return chunkHolder != null ? chunkHolder.getLoadFuture() : null;
    }

    /**
     * Returns the held chunk only when its state is {@code FULL} and removal is not
     * pending. Otherwise returns {@code null} without starting a load, including during
     * an in-progress unload.
     */
    @Override
    public Chunk getChunk(int x, int z) {
        var chunkHolder = getChunkHolder(x, z);
        if (chunkHolder == null || !chunkHolder.isValidFullChunk()) {
            return null;
        }

        return chunkHolder.getChunk();
    }

    /**
     * Raises the selected holder's target to {@code FULL} and returns its existing load
     * future. The holder is created when absent, but pending unload state and
     * completed future outcomes are retained.
     */
    @Override
    public CompletableFuture<Chunk> getOrLoadChunk(int x, int z) {
        var chunkHolder = getOrCreateChunkHolder(x, z);
        chunkHolder.setTargetState(ChunkState.FULL);
        return chunkHolder.getLoadFuture();
    }

    /**
     * Returns whether a holder currently contains a {@code FULL} chunk without a
     * pending removal countdown. A previously completed load future does not
     * override this current availability check.
     */
    @Override
    public boolean isChunkLoaded(int x, int z) {
        var chunkHolder = getChunkHolder(x, z);
        if (chunkHolder == null) {
            return false;
        }

        return chunkHolder.isValidFullChunk();
    }

    /**
     * Returns whether a holder exists but is not currently a valid {@code FULL} chunk.
     * This implementation also reports {@code true} for {@code FULL} holders marked for
     * unloading, so the result includes more than generation progress.
     */
    @Override
    public boolean isChunkLoading(int x, int z) {
        var chunkHolder = getChunkHolder(x, z);
        if (chunkHolder == null) {
            return false;
        }

        return !chunkHolder.isValidFullChunk();
    }

    /**
     * Returns whether no holder currently exists at the supplied chunk
     * coordinates. This implementation returns {@code false} while a holder is loading,
     * which differs from the interface contract classifying loading chunks as
     * unloaded.
     */
    @Override
    public boolean isChunkUnloaded(int x, int z) {
        return getChunkHolder(x, z) == null;
    }

    /**
     * Returns an unmodifiable live view of the loader registration keys. The
     * backing map is not concurrent, so callers must coordinate traversal with
     * registration and removal operations.
     */
    @Override
    @UnmodifiableView
    public Set<ChunkLoader> getChunkLoaders() {
        return Collections.unmodifiableSet(chunkLoaders.keySet());
    }

    /**
     * Installs a fresh holder for the loader, then cleans up any replaced
     * holder. Registration must be serialized with ticking and removal because
     * {@code chunkLoaders} and loader-holder transitions are not concurrent.
     *
     * <p>
     * The new registration becomes visible before {@code onRemoved()} detaches
     * old sent-chunk demand and stops the old sender. A cleanup failure leaves
     * that new registration installed, so callers cannot interpret the exception
     * as evidence that registration had no effect.
     */
    @Override
    public void addChunkLoader(ChunkLoader chunkLoader) {
        // The replacement is published before old-holder cleanup; a cleanup
        // exception therefore leaves the new registration installed even if
        // previous residency or sender cleanup is incomplete.
        var old = this.chunkLoaders.put(chunkLoader, new ChunkLoaderHolder(this, chunkLoader));
        if (old != null) old.onRemoved();
    }

    /**
     * Removes the loader registration and invokes cleanup on its holder when
     * present. Cleanup detaches sent-chunk demand and stops its sender; callers
     * serialize this operation with manager ticking.
     */
    @Override
    public void removeChunkLoader(ChunkLoader chunkLoader) {
        var removed = this.chunkLoaders.remove(chunkLoader);
        if (removed != null) removed.onRemoved();
    }

    /**
     * Invokes the consumer for holders currently exposing valid {@code FULL} chunks
     * during traversal. Chunk instances remain shared, and changes in
     * asynchronous holder state can affect later observations of the same
     * mapping.
     */
    @Override
    public void forEachLoadedChunks(Consumer<Chunk> consumer) {
        for (var chunkHolder : chunkHolders.values()) {
            if (chunkHolder.isValidFullChunk()) {
                consumer.accept(chunkHolder.getChunk());
            }
        }
    }

    /**
     * Returns an unmodifiable snapshot list of chunks from currently valid {@code FULL}
     * holders. Chunk instances remain shared and can later unload; the
     * collection does not retain residency or copy their data.
     */
    @Override
    @UnmodifiableView
    public Collection<Chunk> getLoadedChunks() {
        return chunkHolders.values().stream().filter(ChunkHolder::isValidFullChunk).map(ChunkHolder::getChunk).toList();
    }

    /**
     * Requests unloading for holders encountered in the current map traversal
     * and combines their futures. The call does not tick holders or include
     * holders added after traversal; completion depends on each requested
     * unload outcome.
     */
    @Override
    public CompletableFuture<Void> unloadAllChunks() {
        return CompletableFuture.allOf(chunkHolders.keySet().stream().map(this::unloadChunk).toArray(CompletableFuture[]::new));
    }
}
