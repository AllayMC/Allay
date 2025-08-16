package org.allaymc.api.world.service;

import org.allaymc.api.server.ServerSettings;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.utils.Utils;
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
 * ChunkService is responsible for loading, unloading and sending chunks.
 *
 * @author daoge_cmd
 */
public interface ChunkService extends ChunkSource {

    /**
     * Get the world generator of this dimension.
     *
     * @return the world generator.
     */
    WorldGenerator getWorldGenerator();

    /**
     * Get the chunk future of the specified chunk. The return value of method {@link CompletableFuture#isDone}
     * represents the chunk status: {@code true} if the chunk is loaded or is cancelled, {@code false} if the
     * chunk is still loading. This method will return {@code null} if chunk is neither loading nor already loaded.
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     *
     * @return the chunk future.
     */
    CompletableFuture<Chunk> getChunkFuture(int x, int z);

    /**
     * @see #getChunkFuture(long)
     */
    default CompletableFuture<Chunk> getChunkFuture(long hashXZ) {
        return getChunkFuture(
                HashUtils.getXFromHashXZ(hashXZ),
                HashUtils.getZFromHashXZ(hashXZ)
        );
    }

    @Override
    default Chunk getChunk(int x, int z) {
        var future = getChunkFuture(x, z);

        if (future != null && Utils.isDoneNormally(future)) {
            return future.resultNow();
        }

        return null;
    }

    @Override
    default Chunk getChunk(long chunkHash) {
        return getChunk(
                HashUtils.getXFromHashXZ(chunkHash),
                HashUtils.getZFromHashXZ(chunkHash)
        );
    }

    /**
     * Get the specified chunk or load it if it is not loaded.
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     *
     * @return the chunk future. The result of the future will become a {@link Chunk}.
     */
    default CompletableFuture<Chunk> getOrLoadChunk(int x, int z) {
        if (isChunkUnloaded(x, z)) {
            return loadChunk(x, z);
        }

        return getChunkFuture(x, z);
    }

    /**
     * @see #getOrLoadChunk(int, int)
     */
    default CompletableFuture<Chunk> getOrLoadChunk(long hash) {
        if (isChunkUnloaded(hash)) {
            return loadChunk(hash);
        }

        return getChunkFuture(hash);
    }

    /**
     * Get the specified chunk or load it if it is not loaded.
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     *
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
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     *
     * @return a future to represent the result of unloading. The future will be completed when the chunk is unloaded and saved,
     * and the result of the future indicates whether the chunk is unloaded successfully. Specifically, a future with {@code false}
     * result will be returned immediately if the chunk is not loaded.
     */
    CompletableFuture<Boolean> unloadChunk(int x, int z);

    /**
     * @see #unloadChunk(int, int)
     */
    default CompletableFuture<Boolean> unloadChunk(long hash) {
        return unloadChunk(
                HashUtils.getXFromHashXZ(hash),
                HashUtils.getZFromHashXZ(hash)
        );
    }

    /**
     * Unload all loaded chunks.
     *
     * @return a future, which will be completed when all chunks are unloaded.
     */
    CompletableFuture<Void> unloadAllChunks();

    /**
     * @see #isChunkLoaded(long)
     */
    default boolean isChunkLoaded(int x, int z) {
        return isChunkLoaded(HashUtils.hashXZ(x, z));
    }

    /**
     * Check if the specified chunk is loaded.
     *
     * @param hashXZ the hash of the chunk.
     *
     * @return {@code true} if the chunk is loaded, otherwise {@code false}.
     */
    default boolean isChunkLoaded(long hashXZ) {
        var future = getChunkFuture(hashXZ);
        return future != null && Utils.isDoneNormally(future);
    }

    /**
     * @see #isChunkLoading(long)
     */
    default boolean isChunkLoading(int x, int z) {
        return isChunkLoading(HashUtils.hashXZ(x, z));
    }

    /**
     * Check if the specified chunk is loading.
     *
     * @param hashXZ the hash of the chunk.
     *
     * @return {@code true} if the chunk is loading, otherwise {@code false}.
     */
    default boolean isChunkLoading(long hashXZ) {
        var future = getChunkFuture(hashXZ);
        return future != null && !future.isDone();
    }

    /**
     * @see #isChunkUnloaded(long)
     */
    default boolean isChunkUnloaded(int x, int z) {
        return isChunkUnloaded(HashUtils.hashXZ(x, z));
    }

    /**
     * Check if the specified chunk is unloaded.
     *
     * @param hashXZ the hash of the chunk.
     *
     * @return {@code true} if the chunk is unloaded, otherwise {@code false}.
     */
    default boolean isChunkUnloaded(long hashXZ) {
        return getChunkFuture(hashXZ) == null;
    }

    /**
     * Get the chunk loaders in this service.
     *
     * @return the chunk loaders.
     */
    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    /**
     * Add a chunk loader to this service.
     *
     * @param chunkLoader the chunk loader to add.
     */
    void addChunkLoader(ChunkLoader chunkLoader);

    /**
     * Remove a chunk loader from this service.
     *
     * @param chunkLoader the chunk loader to remove.
     */
    void removeChunkLoader(ChunkLoader chunkLoader);

    /**
     * For each loaded chunk, execute the specified action.
     *
     * @param consumer the action to execute.
     */
    void forEachLoadedChunks(Consumer<Chunk> consumer);

    /**
     * Get the loading chunks.
     *
     * @return the loading chunks.
     */
    @UnmodifiableView
    Collection<CompletableFuture<Chunk>> getLoadingChunks();

    /**
     * Get the loaded chunks.
     *
     * @return the loaded chunks.
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
