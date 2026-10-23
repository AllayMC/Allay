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
import org.allaymc.server.AllayServer;
import org.allaymc.server.world.generator.ChunkPyramid;
import org.allaymc.server.world.generator.ChunkStep;
import org.allaymc.server.world.manager.AllayChunkManager;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * {@code ChunkHolder} coordinates
 * a chunk's storage read, generation stages, residency countdown,
 * and eventual unload within its dimension. The holder uses a logical busy flag
 * to prevent normal ticks from starting overlapping work, while asynchronous
 * callbacks publish the read result or finish a generation step.
 *
 * <p>
 * Generation requests only raise the target stage. A step first requests
 * neighboring dependencies and marks its write region busy, then runs on the
 * compute executor. Removal instead marks the holder busy and waits for the
 * storage write future before completing its unload future and removing the
 * holder. These flags are not synchronization primitives: this type remains
 * non-thread-safe, failures can leave completion pending, and logged generation
 * failures currently still advance the stage.
 *
 * @author daoge_cmd
 */
@Slf4j
@NotThreadSafe
public final class ChunkHolder {

    /**
     * The chunk manager that owns this chunk holder.
     */
    private final AllayChunkManager chunkManager;
    /**
     * The dimension that this chunk belongs to.
     */
    private final Dimension dimension;
    /**
     * {@code worldGenerator} supplies the generator for this holder's chunk
     * generation steps.
     */
    private final WorldGenerator worldGenerator;
    /**
     * {@code worldStorage} supplies persistence operations for reading and
     * writing this holder's chunk.
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
     * {@code loadFuture} completes after {@code FULL} load hooks and event
     * delivery. Unloading a proto chunk cancels this shared future.
     */
    @Getter
    private final CompletableFuture<Chunk> loadFuture;
    /**
     * {@code unloadFuture} completes after successful storage-future completion.
     * Exceptional writes leave this shared future pending because no recovery
     * continuation completes it.
     */
    @Getter
    private final CompletableFuture<Void> unloadFuture;
    /**
     * {@code chunk} holds the shared chunk, initially {@code null}. The storage
     * read callback publishes its result or an empty fallback here.
     */
    @Getter
    private Chunk chunk;
    /**
     * {@code locked} gates holder work with a logical busy flag. This field
     * supplies neither volatile publication nor mutual exclusion for concurrent
     * callers.
     */
    private boolean locked;
    /**
     * {@code targetState} holds the highest requested generation stage, or
     * {@code null} before a request. Later requests cannot lower an existing
     * target.
     */
    private ChunkState targetState;
    /**
     * {@code removeCountDown} counts remaining idle ticks before removal. Zero
     * forces unloading on the next unlocked holder tick regardless of loader
     * membership.
     */
    @Setter
    private int removeCountDown;

    /**
     * Creates a holder and marks it busy until the supplied storage read
     * completes. A failed read is logged and replaced with an empty {@code FULL} chunk,
     * so downstream load completion can succeed despite lost stored content. A
     * {@code FULL} result runs load hooks before releasing the busy flag; less complete
     * results await generation ticks. The callback runs in the future's
     * completion context, and an uncaught hook failure can prevent unlocking.
     */
    public ChunkHolder(AllayChunkManager chunkManager, int x, int z, CompletableFuture<Chunk> chunkReadFuture) {
        this.chunkManager = chunkManager;
        this.dimension = chunkManager.getDimension();
        this.worldGenerator = chunkManager.getWorldGenerator();
        this.worldStorage = chunkManager.getWorldStorage();
        this.x = x;
        this.z = z;
        this.loadFuture = new CompletableFuture<>();
        this.unloadFuture = new CompletableFuture<>();
        this.removeCountDown = AllayServer.getSettings().worldSettings().removeUnusedProtoChunkCycle();
        // Hold the logical busy flag across the asynchronous read so normal ticks
        // do not inspect an unassigned chunk or begin generation before publication.
        this.lock();
        chunkReadFuture.whenComplete((c, t) -> {
            if (t != null) {
                log.error("Error while loading chunk ({},{}) !", x, z, t);
                // A failed read becomes an empty FULL chunk rather than an
                // exceptional load future; later saves can therefore persist
                // this replacement content.
                this.chunk = AllayUnsafeChunk.builder().voidChunk(x, z, this.dimension.getDimensionType()).toSafeChunk();
            } else {
                this.chunk = c;
            }
            if (this.chunk.getState() == ChunkState.FULL) {
                onChunkLoad();
            }
            this.unlock();
        });
    }

    /**
     * Returns whether the held chunk is {@code FULL} and no removal request is
     * pending. Generation completion and current availability are separate: a
     * zero {@code removeCountDown} hides even a fully generated chunk before
     * persistence and holder removal finish. A completed {@code loadFuture}
     * therefore does not override a later removal request. A storage read that
     * has not supplied {@code chunk} reports {@code NEW} through
     * {@code getCurrentState()} and cannot satisfy this availability check.
     */
    public boolean isValidFullChunk() {
        return isFullChunk() && !isUnloading();
    }

    /**
     * Returns whether work holds the busy flag or at least one loader
     * references the chunk. The busy check short-circuits access while storage
     * has not yet supplied the chunk.
     */
    public boolean isUsed() {
        return isLocked() || this.chunk.getChunkLoaderCount() != 0;
    }

    /**
     * Returns whether the current effective generation stage is {@code FULL}.
     */
    private boolean isFullChunk() {
        return getCurrentState() == ChunkState.FULL;
    }

    /**
     * Returns the held chunk's generation stage, using {@code NEW} while storage has
     * not supplied a chunk. This fallback describes progress only and does not
     * publish a usable empty chunk.
     */
    private ChunkState getCurrentState() {
        if (this.chunk == null) {
            // Before read completion there is no chunk to inspect; NEW keeps stage
            // comparisons conservative while the busy flag prevents generation from starting.
            return ChunkState.NEW;
        }

        return this.chunk.getState();
    }

    /**
     * Returns whether an assigned target lies after the current generation
     * stage. A missing target suppresses generation, and requests at or behind
     * the current stage require no further step.
     */
    private boolean shouldUpdate() {
        return this.targetState != null && getCurrentState().isBefore(this.targetState);
    }

    /**
     * Returns whether the removal countdown has reached the forced-removal
     * value.
     */
    private boolean isUnloading() {
        return this.removeCountDown == 0;
    }

    /**
     * Raises the requested generation stage without lowering an existing
     * target. The caller supplies a non-{@code null} state; setting the target
     * schedules no executor work until a later unlocked tick.
     */
    public void setTargetState(ChunkState state) {
        if (this.targetState != null && this.targetState.isAfter(state)) {
            return;
        }

        this.targetState = state;
    }

    /**
     * Advances removal or generation when no outstanding work holds the busy
     * flag. Removal takes precedence, cancels an unfinished proto load, invokes
     * full-chunk unload hooks when applicable, and always requests a storage
     * write. Only successful write-future completion removes the holder and
     * completes its unload future; an exceptional write leaves those steps
     * pending.
     *
     * <p>
     * Generation waits for requested neighbor stages and an available write
     * region before dispatching its task. Task failures are logged and
     * converted to normal completion, so the next stage is published even after
     * failure. {@code FULL} completion runs load hooks before releasing regional busy
     * flags, and uncaught completion-hook failures can leave those flags set.
     */
    public void tick() {
        if (isLocked()) {
            // The logical busy flag covers reads, generation, and unload writes;
            // starting another phase here would overlap their use of the held chunk.
            return;
        }

        if (updateRemoveCountDown()) {
            // Removal reserves this holder before hooks run. Its finally block
            // submits persistence even when hooks fail, and only successful
            // storage completion releases the reservation.
            lock();
            try {
                if (isFullChunk()) {
                    new ChunkUnloadEvent(this.dimension, this.chunk).call();
                    ((AllayUnsafeChunk) this.chunk.toUnsafeChunk()).onChunkUnload(this.dimension);
                } else {
                    // A proto chunk will never reach FULL after this removal request,
                    // so cancel its pending load outcome before writing the partial data.
                    this.loadFuture.cancel(true);
                }
                return;
            } catch (Throwable t) {
                log.error("Error while trying to unload chunk ({}, {}) !", this.x, this.z, t);
            } finally {
                // The storage future gates holder removal; backends completing
                // on batch admission can release this holder before bytes are
                // written, and exceptional completion has no recovery handler.
                worldStorage.writeChunk(this.chunk).thenRun(() -> {
                    this.unloadFuture.complete(null);
                    this.chunkManager.removeChunkHolder(this.x, this.z);
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
            // The dependency and region checks precede executor dispatch.
            // Keeping the region busy through completion prevents subsequent
            // holder ticks from scheduling conflicting generation steps.
            CompletableFuture.runAsync(() -> step.getTask().doWork(
                    this.worldGenerator,
                    this.chunk.toUnsafeChunk(),
                    // Region reservation assumes all required holders already exist;
                    // missing data is an invariant failure, not a reason to create a new
                    // dependency during the running generation task.
                    (x, z) -> Preconditions.checkNotNull(this.chunkManager.getChunkHolder(x, z).getChunk())
            ), Server.getInstance().getComputeThreadPool()).exceptionally(t -> {
                // Converting this task failure to normal completion
                // deliberately reaches the following stage publication path;
                // the current implementation does not retry or roll back
                // partial generation.
                log.error("Error while process chunk task in chunk ({},{}) from state {} to state {}!", this.x, this.z, chunk.getState(), nextState, t);
                return null;
            }).thenRun(() -> {
                ((AllayUnsafeChunk) chunk.toUnsafeChunk()).setState(nextState);
                if (nextState == ChunkState.FULL) {
                    onChunkLoad();
                }
                unlock(this.x, this.z, step.getWriteRange());
            });
        }
    }

    /**
     * Runs runtime load hooks, publishes the load event, and completes the load
     * future. Runtime-hook failures are logged and suppressed so event delivery
     * and future completion can continue. Exceptions from the event itself are
     * not caught here and therefore prevent the future from completing in this
     * invocation. The holder's surrounding callback releases its busy flag only
     * after this method returns normally.
     */
    private void onChunkLoad() {
        try {
            ((AllayUnsafeChunk) chunk.toUnsafeChunk()).onChunkLoad(dimension);
        } catch (Throwable t) {
            // Suppressing a runtime-hook failure allows event delivery and
            // successful load completion despite partial service registration;
            // event failures remain outside this recovery boundary.
            log.error("Error while calling onChunkLoad() at chunk ({},{}) !", x, z, t);
        }
        new ChunkLoadEvent(dimension, chunk).call();
        this.loadFuture.complete(this.chunk);
    }

    /**
     * Returns whether this holder should start unloading on the current tick.
     * An existing zero countdown wins before loader usage is checked,
     * preserving explicit unload requests even for referenced chunks. Otherwise
     * busy or referenced chunks reset their timer using the {@code FULL} or proto
     * retention setting, and idle chunks decrement it. The equality check
     * requires configured countdowns to reach exactly zero; this method does
     * not clamp negative values.
     */
    private boolean updateRemoveCountDown() {
        if (this.removeCountDown == 0) {
            // Zero takes precedence over current usage so an explicit unload request
            // is not silently extended by a loader still attached to the chunk.
            return true;
        }

        if (isUsed()) {
            // Generation state selects the retention period, allowing proto dependencies
            // and fully usable chunks to keep different idle lifetimes after use ends.
            this.removeCountDown = isFullChunk() ?
                    AllayServer.getSettings().worldSettings().removeUnusedFullChunkCycle() :
                    AllayServer.getSettings().worldSettings().removeUnusedProtoChunkCycle();
            return false;
        } else {
            this.removeCountDown--;
        }

        return this.removeCountDown == 0;
    }

    /**
     * Requests the generation stages required by a step and reports whether
     * every neighboring dependency is ready. Missing holders are created, and
     * each neighbor's target is raised even after another dependency makes the
     * step temporarily ineligible. The current chunk is excluded from neighbor
     * checks. A busy or insufficiently generated dependency delays this step;
     * the {@code AtomicBoolean} carries the loop result through callbacks and does not
     * make holder coordination thread-safe.
     */
    private boolean checkDependencies(ChunkStep step) {
        var canUpdate = new AtomicBoolean(true);
        for (var entry : step.getDependencies().entrySet()) {
            var state = entry.getKey();
            var range = entry.getValue();
            forEachChunkRanged(this.x, this.z, range, depChunk -> {
                if (depChunk.getX() == this.x && depChunk.getZ() == this.z) {
                    return;
                }

                // Request every dependency even after one is unready, so neighboring
                // generation can progress together rather than waiting for serial discovery.
                depChunk.setTargetState(state);
                if (canUpdate.get() && (depChunk.isLocked() || depChunk.getCurrentState().isBefore(state))) {
                    // Busy neighbors can still be changing data even at the requested
                    // stage; both readiness conditions must hold before this step runs.
                    canUpdate.set(false);
                }
            }, true);
        }

        return canUpdate.get();
    }

    /**
     * Returns whether this holder currently carries its logical busy flag.
     */
    private boolean isLocked() {
        return this.locked;
    }

    /**
     * Returns the logical busy flag of an existing holder at chunk coordinates.
     * The holder must already exist; a missing dependency fails the non-{@code null}
     * invariant instead of triggering a load.
     */
    private boolean isLocked(int chunkX, int chunkZ) {
        return Preconditions.checkNotNull(chunkManager.getChunkHolder(chunkX, chunkZ)).isLocked();
    }

    /**
     * Marks this holder busy and returns {@code true}, warning if it was already busy.
     * This is an unsynchronized logical flag rather than an atomic acquisition,
     * so callers must coordinate holder transitions externally.
     */
    private boolean lock() {
        if (this.isLocked()) {
            log.warn("Trying to lock an already locked chunk ({}, {}) !", this.x, this.z);
            return true;
        }

        this.locked = true;
        return true;
    }

    /**
     * Marks an existing square region busy only when the initial scan finds
     * every holder available. The availability scan completes before any busy
     * flags are set, avoiding a deliberately partial reservation when a known
     * holder is occupied. The two passes are not atomic against concurrent
     * callers, so they require the holder manager's coordinated execution
     * context. A missing holder violates the pre-established dependency
     * invariant and throws.
     */
    private boolean lock(int chunkX, int chunkZ, int range) {
        // Scanning before setting flags avoids partially reserving a region
        // when a known neighbor is busy; this two-pass sequence still requires
        // external serialization against competing scans.
        if (!checkChunkRanged(chunkX, chunkZ, range, chunkHolder -> !chunkHolder.isLocked())) {
            return false;
        }

        forEachChunkRanged(chunkX, chunkZ, range, ChunkHolder::lock);
        return true;
    }

    /**
     * Clears this holder's logical busy flag, warning when it is already clear.
     * No owning thread or acquisition count is tracked, so the caller must
     * match the surrounding lifecycle reservation.
     */
    public void unlock() {
        if (!this.isLocked()) {
            log.warn("Trying to unlock an already unlocked chunk ({}, {}) !", this.x, this.z);
            return;
        }

        this.locked = false;
    }

    /**
     * Clears busy flags throughout an existing square region centered at chunk
     * coordinates. The caller must match the region reserved for its generation
     * step; this method does not track ownership.
     */
    public void unlock(int chunkX, int chunkZ, int range) {
        forEachChunkRanged(chunkX, chunkZ, range, ChunkHolder::unlock);
    }

    /**
     * Visits every existing holder in the inclusive square centered at the
     * supplied chunk coordinates. A zero range visits only the center; missing
     * holders fail rather than being created.
     */
    private void forEachChunkRanged(int chunkX, int chunkZ, int range, Consumer<ChunkHolder> consumer) {
        forEachChunkRanged(chunkX, chunkZ, range, consumer, false);
    }

    /**
     * Visits holders in the inclusive square, optionally creating missing
     * holders and starting their storage reads. Range must be nonnegative; with
     * loading disabled, every selected holder must already exist.
     */
    private void forEachChunkRanged(int chunkX, int chunkZ, int range, Consumer<ChunkHolder> consumer, boolean load) {
        if (range == 0) {
            var chunkHolder = load ? chunkManager.getOrCreateChunkHolder(chunkX, chunkZ) : chunkManager.getChunkHolder(chunkX, chunkZ);
            consumer.accept(Preconditions.checkNotNull(chunkHolder));
            return;
        }

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                var chunkHolder = load ?
                        chunkManager.getOrCreateChunkHolder(chunkX + i, chunkZ + j) :
                        chunkManager.getChunkHolder(chunkX + i, chunkZ + j);
                consumer.accept(Preconditions.checkNotNull(chunkHolder));
            }
        }
    }

    /**
     * Returns whether every existing holder in an inclusive square satisfies
     * the predicate. Range must be nonnegative, missing holders fail the
     * invariant, and evaluation stops after the first {@code false} result.
     */
    private boolean checkChunkRanged(int chunkX, int chunkZ, int range, Predicate<ChunkHolder> predicator) {
        if (range == 0) {
            return predicator.test(Preconditions.checkNotNull(chunkManager.getChunkHolder(chunkX, chunkZ)));
        }

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                if (!predicator.test(Preconditions.checkNotNull(chunkManager.getChunkHolder(chunkX + i, chunkZ + j)))) {
                    return false;
                }
            }
        }

        return true;
    }
}
