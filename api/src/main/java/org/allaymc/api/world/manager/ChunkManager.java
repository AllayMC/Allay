package org.allaymc.api.world.manager;

import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.allaymc.api.world.chunk.ChunkSource;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.generator.WorldGenerator;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * {@code ChunkManager} manages
 * chunk residency, generation requests, and loader demand for one
 * dimension. Asynchronous load and unload results distinguish completion from
 * submission, while direct lookup exposes chunks currently available for use.
 *
 * <p>
 * Loading progresses toward {@code FULL} generation state and may depend on neighboring
 * chunks. Loader membership and activity determine continued residency and
 * ticking, but those are separate from a future merely being done: a done
 * future may also be cancelled or failed. Unload completion represents the
 * requested removal and save operation, so consumers relying on persistence
 * must also respect the storage implementation's completion behavior.
 * Collection views and callbacks expose shared chunks rather than copies.
 *
 * @author daoge_cmd
 */
public interface ChunkManager extends ChunkSource {

    /**
     * Returns the world generator assigned to this managed dimension.
     */
    WorldGenerator getWorldGenerator();

    /**
     * Returns the existing load future for the requested chunk, or {@code null}
     * when unloaded. A done future may contain a loaded chunk, cancellation, or
     * failure; inspect its outcome rather than treating completion alone as
     * successful loading.
     */
    CompletableFuture<Chunk> getChunkFuture(int x, int z);

    /**
     * Returns the existing load future, or {@code null} when no future is available for
     * a packed chunk-coordinate hash. The hash uses {@link
     * HashUtils#hashXZ(int, int)} and delegates to the coordinate overload with
     * the same lifecycle rules.
     *
     * @see #getChunkFuture(int, int)
     */
    default CompletableFuture<Chunk> getChunkFuture(long hash) {
        return getChunkFuture(HashUtils.getXFromHashXZ(hash), HashUtils.getZFromHashXZ(hash));
    }

    /**
     * Returns a future for the requested chunk, starting loading when
     * necessary. Coordinates identify whole chunks, and callers must wait for
     * successful completion before using the delivered chunk.
     */
    CompletableFuture<Chunk> getOrLoadChunk(int x, int z);

    /**
     * Returns the load future, starting a request if needed for a packed
     * chunk-coordinate hash. The hash uses {@link HashUtils#hashXZ(int, int)}
     * and delegates to the coordinate overload with the same lifecycle rules.
     *
     * @see #getOrLoadChunk(int, int)
     */
    default CompletableFuture<Chunk> getOrLoadChunk(long hash) {
        return getOrLoadChunk(HashUtils.getXFromHashXZ(hash), HashUtils.getZFromHashXZ(hash));
    }

    /**
     * Requests the chunk at the supplied coordinates through {@link
     * ChunkState#FULL} and returns its load future. Successful completion
     * supplies the usable chunk; callers must handle cancellation or failure
     * separately.
     */
    CompletableFuture<Chunk> loadChunk(int x, int z);

    /**
     * Returns the future for loading the chunk through {@code FULL} state for a packed
     * chunk-coordinate hash. The hash uses {@link HashUtils#hashXZ(int, int)}
     * and delegates to the coordinate overload with the same lifecycle rules.
     *
     * @see #loadChunk(int, int)
     */
    default CompletableFuture<Chunk> loadChunk(long hash) {
        return loadChunk(
                HashUtils.getXFromHashXZ(hash),
                HashUtils.getZFromHashXZ(hash)
        );
    }

    /**
     * Requests unloading of the chunk at the supplied chunk coordinates. The
     * returned future completes when the chunk is unloaded and saved, so
     * submission alone does not establish either result.
     */
    CompletableFuture<Void> unloadChunk(int x, int z);

    /**
     * Returns the completion future for unloading and saving the chunk for a
     * packed chunk-coordinate hash. The hash uses {@link HashUtils#hashXZ(int,
     * int)} and delegates to the coordinate overload with the same lifecycle
     * rules.
     *
     * @see #unloadChunk(int, int)
     */
    default CompletableFuture<Void> unloadChunk(long hash) {
        return unloadChunk(
                HashUtils.getXFromHashXZ(hash),
                HashUtils.getZFromHashXZ(hash)
        );
    }

    /**
     * Requests unloading of all loaded chunks and returns their combined
     * completion future. Callers must wait for completion to observe the end of
     * the requested unload operations.
     */
    CompletableFuture<Void> unloadAllChunks();

    /**
     * Returns whether the chunk at the supplied coordinates is loaded and
     * available for use. This query does not initiate loading or wait for an
     * existing load request to complete.
     */
    boolean isChunkLoaded(int x, int z);

    /**
     * Returns whether the chunk is loaded and available for a packed
     * chunk-coordinate hash. The hash uses {@link HashUtils#hashXZ(int, int)}
     * and delegates to the coordinate overload with the same lifecycle rules.
     *
     * @see #isChunkLoaded(int, int)
     */
    default boolean isChunkLoaded(long hash) {
        return isChunkLoaded(
                HashUtils.getXFromHashXZ(hash),
                HashUtils.getZFromHashXZ(hash)
        );
    }

    /**
     * Returns whether the chunk at the supplied coordinates is currently
     * loading. This query observes residency progress without starting a
     * request or waiting for a load future to complete.
     */
    boolean isChunkLoading(int x, int z);

    /**
     * Returns whether the chunk is currently loading for a packed
     * chunk-coordinate hash. The hash uses {@link HashUtils#hashXZ(int, int)}
     * and delegates to the coordinate overload with the same lifecycle rules.
     *
     * @see #isChunkLoading(int, int)
     */
    default boolean isChunkLoading(long hash) {
        return isChunkLoading(
                HashUtils.getXFromHashXZ(hash),
                HashUtils.getZFromHashXZ(hash)
        );
    }

    /**
     * Returns whether the chunk at the supplied coordinates is unloaded. The
     * public contract also classifies a loading chunk as unloaded until it
     * becomes loaded; implementations must preserve that distinction.
     */
    boolean isChunkUnloaded(int x, int z);

    /**
     * Returns whether the chunk is unloaded under the coordinate overload's
     * contract for a packed chunk-coordinate hash. The hash uses {@link
     * HashUtils#hashXZ(int, int)} and delegates to the coordinate overload with
     * the same lifecycle rules.
     *
     * @see #isChunkUnloaded(int, int)
     */
    default boolean isChunkUnloaded(long hash) {
        return isChunkUnloaded(
                HashUtils.getXFromHashXZ(hash),
                HashUtils.getZFromHashXZ(hash)
        );
    }

    /**
     * Returns an unmodifiable view of this manager's registered chunk loaders.
     * Loader objects remain shared, and later registrations or removals may
     * become visible through the returned view.
     */
    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    /**
     * Registers a loader whose location and radius supply chunk demand for this
     * dimension. The loader remains shared and may be queried on later ticks as
     * its location changes.
     */
    void addChunkLoader(ChunkLoader chunkLoader);

    /**
     * Unregisters a loader and removes its demand from this manager. Removing
     * demand does not itself promise immediate unloading, since other loaders
     * and configured retention periods can keep chunks resident.
     */
    void removeChunkLoader(ChunkLoader chunkLoader);

    /**
     * Invokes the consumer for chunks currently available as loaded chunks in
     * this manager. The supplied chunks remain shared, and the callback must
     * respect their access and lifecycle rules.
     */
    void forEachLoadedChunks(Consumer<Chunk> consumer);

    /**
     * Returns an unmodifiable collection of chunks currently available as
     * loaded chunks. The chunk instances remain shared mutable objects, and the
     * collection does not pin their lifetime or make them copies.
     */
    @UnmodifiableView
    Collection<Chunk> getLoadedChunks();

    /**
     * Returns whether the chunk lies within the tick radius of at least one
     * chunk loader. Activity controls whether its entities are considered
     * active; it is distinct from simply retaining a loaded chunk.
     */
    boolean isChunkActive(int x, int z);

    /**
     * Requests removal of proto chunks and unused full chunks during the next tick.
     * <p>
     * An unused full chunk is a chunk that is fully loaded but is not holding any chunk
     * loader. Usually these chunks will still keep loaded for a period of time.
     * <p>
     * Proto chunks are chunks that are not fully loaded, they are used during world
     * generation and will be removed after a period of time since they are added.
     * <p>
     * Calling this method will set the countdown of all unused chunks to zero, which
     * will make these chunks be unloaded during the next tick.
     */
    void removeUnusedChunksImmediately();
}
