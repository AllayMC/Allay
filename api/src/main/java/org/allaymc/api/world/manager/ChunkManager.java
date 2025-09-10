package org.allaymc.api.world.manager;

import org.allaymc.api.server.ServerSettings;
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
 * ChunkManager is responsible for loading, unloading and sending chunks.
 *
 * @author daoge_cmd
 */
public interface ChunkManager extends ChunkSource {

    /**
     * Get the world generator of this dimension.
     *
     * @return the world generator
     */
    WorldGenerator getWorldGenerator();

    /**
     * Get the chunk future of the specified chunk. The return value of method {@link CompletableFuture#isDone}
     * represents the chunk status: {@code true} if the chunk is loaded or is cancelled, {@code false} if the
     * chunk is still loading. This method will return {@code null} if chunk is unloaded.
     *
     * @param x the x coordinate of the chunk
     * @param z the z coordinate of the chunk
     * @return the chunk future, or {@code null} if the chunk is unloaded
     */
    CompletableFuture<Chunk> getChunkFuture(int x, int z);

    /**
     * @see #getChunkFuture(long)
     */
    default CompletableFuture<Chunk> getChunkFuture(long hash) {
        return getChunkFuture(HashUtils.getXFromHashXZ(hash), HashUtils.getZFromHashXZ(hash));
    }

    /**
     * Get the specified chunk or load it if it is not loaded.
     *
     * @param x the x coordinate of the chunk
     * @param z the z coordinate of the chunk
     * @return the chunk future. The result of the future will become a {@link Chunk}
     */
    CompletableFuture<Chunk> getOrLoadChunk(int x, int z);

    /**
     * @see #getOrLoadChunk(int, int)
     */
    default CompletableFuture<Chunk> getOrLoadChunk(long hash) {
        return getOrLoadChunk(HashUtils.getXFromHashXZ(hash), HashUtils.getZFromHashXZ(hash));
    }

    /**
     * Get the specified chunk or load it if it is not loaded.
     *
     * @param x the x coordinate of the chunk
     * @param z the z coordinate of the chunk
     * @return the chunk future. The result of the future will become a {@link Chunk}
     * instance with state of {@link ChunkState#FULL} once the chunk is fully loaded.
     */
    CompletableFuture<Chunk> loadChunk(int x, int z);

    /**
     * @see #loadChunk(int, int)
     */
    default CompletableFuture<Chunk> loadChunk(long hash) {
        return loadChunk(
                HashUtils.getXFromHashXZ(hash),
                HashUtils.getZFromHashXZ(hash)
        );
    }

    /**
     * Unload the specified chunk.
     *
     * @param x the x coordinate of the chunk
     * @param z the z coordinate of the chunk
     * @return a future to represent the result of unloading. The future will be completed when the chunk is unloaded and saved
     */
    CompletableFuture<Void> unloadChunk(int x, int z);

    /**
     * @see #unloadChunk(int, int)
     */
    default CompletableFuture<Void> unloadChunk(long hash) {
        return unloadChunk(
                HashUtils.getXFromHashXZ(hash),
                HashUtils.getZFromHashXZ(hash)
        );
    }

    /**
     * Unload all loaded chunks.
     *
     * @return a future, which will be completed when all chunks are unloaded
     */
    CompletableFuture<Void> unloadAllChunks();

    /**
     * Check if the specified chunk is loaded.
     *
     * @param x the x coordinate of the chunk
     * @param z the z coordinate of the chunk
     * @return {@code true} if the chunk is loaded, otherwise {@code false}.
     */
    boolean isChunkLoaded(int x, int z);

    /**
     * @see #isChunkLoaded(int, int)
     */
    default boolean isChunkLoaded(long hash) {
        return isChunkLoaded(
                HashUtils.getXFromHashXZ(hash),
                HashUtils.getZFromHashXZ(hash)
        );
    }

    /**
     * Check if the specified chunk is loading.
     *
     * @param x the x coordinate of the chunk
     * @param z the z coordinate of the chunk
     * @return {@code true} if the chunk is loading, otherwise {@code false}.
     */
    boolean isChunkLoading(int x, int z);

    /**
     * @see #isChunkLoading(int, int)
     */
    default boolean isChunkLoading(long hash) {
        return isChunkLoading(
                HashUtils.getXFromHashXZ(hash),
                HashUtils.getZFromHashXZ(hash)
        );
    }

    /**
     * Check if the specified chunk is unloaded. Please note that if the chunk is
     * loading ({@link #isChunkLoading(int, int) return {@code true}}), this method
     * will return {@code true} not {@code false}.
     *
     * @param x the x coordinate of the chunk
     * @param z the z coordinate of the chunk
     * @return {@code true} if the chunk is unloaded, otherwise {@code false}.
     */
    boolean isChunkUnloaded(int x, int z);

    /**
     * @see #isChunkUnloaded(int, int)
     */
    default boolean isChunkUnloaded(long hash) {
        return isChunkUnloaded(
                HashUtils.getXFromHashXZ(hash),
                HashUtils.getZFromHashXZ(hash)
        );
    }

    /**
     * Get the chunk loaders in this service.
     *
     * @return the chunk loaders
     */
    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    /**
     * Add a chunk loader to this service.
     *
     * @param chunkLoader the chunk loader to add
     */
    void addChunkLoader(ChunkLoader chunkLoader);

    /**
     * Remove a chunk loader from this service.
     *
     * @param chunkLoader the chunk loader to remove
     */
    void removeChunkLoader(ChunkLoader chunkLoader);

    /**
     * For each loaded chunk, execute the specified action.
     *
     * @param consumer the action to execute
     */
    void forEachLoadedChunks(Consumer<Chunk> consumer);

    /**
     * Get the loaded chunks.
     *
     * @return the loaded chunks
     */
    @UnmodifiableView
    Collection<Chunk> getLoadedChunks();

    /**
     * Remove all proto chunks, and full chunks that are unused in the next tick.
     * <p>
     * An unused full chunk is a chunk that is fully loaded but is not holding any chunk
     * loader. Usually these chunks will still keep loaded for a period of time (the time
     * is specified by {@link ServerSettings.WorldSettings#removeUnusedFullChunkCycle()}).
     * <p>
     * Proto chunks are chunks that are not fully loaded, they are used during world
     * generation and will be removed after a period of time which is specified by
     * {@link ServerSettings.WorldSettings#removeUnusedProtoChunkCycle()} since they
     * are added.
     * <p>
     * Calling this method will set the countdown of all unused chunks to zero, which
     * will make these chunks be unloaded during the next tick.
     */
    void removeUnusedChunksImmediately();
}
