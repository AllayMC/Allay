package org.allaymc.api.world.service;

import org.allaymc.api.server.ServerSettings;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.allaymc.api.world.chunk.ChunkSource;
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
     * Get the chunk loading future of the specified chunk.
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     *
     * @return the chunk loading future, or {@code null} if chunk is not loading or already loaded.
     */
    CompletableFuture<Chunk> getChunkLoadingFuture(int x, int z);

    /**
     * Get the specified chunk or load it if it is not loaded.
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     *
     * @return the chunk future.
     */
    CompletableFuture<Chunk> getOrLoadChunk(int x, int z);

    /**
     * Get chunks within a specified range, or load them if they are not loaded
     *
     * @param x     the x coordinate of the center.
     * @param z     the z coordinate of the center.
     * @param range the range of chunks.
     *
     * @return the chunk future.
     */
    CompletableFuture<Set<Chunk>> getOrLoadRangedChunk(int x, int z, int range);

    /**
     * Load the specified chunk.
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     *
     * @return the chunk future.
     */
    CompletableFuture<Chunk> loadChunk(int x, int z);

    /**
     * Get the specified chunk, or load the specified chunk in current thread.
     * <p>
     * This method will block the current thread until the chunk is loaded if it is not loaded.
     * This method shouldn't be used in world thread, otherwise the world will be frozen!
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     *
     * @return the chunk.
     */
    Chunk getOrLoadChunkSync(int x, int z);

    /**
     * Unload the specified chunk.
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     *
     * @return a future to represent the result of unloading. {@code true} if the chunk is unloaded successfully, otherwise {@code false}.
     */
    CompletableFuture<Boolean> unloadChunk(int x, int z);

    /**
     * Unload the specified chunk.
     *
     * @param chunkHash the hash of the chunk.
     *
     * @return a future to represent the result of unloading. {@code true} if the chunk is unloaded successfully, otherwise {@code false}.
     */
    CompletableFuture<Boolean> unloadChunk(long chunkHash);

    /**
     * Unload all loaded chunks.
     *
     * @return a future, which will be completed when all chunks are unloaded.
     */
    CompletableFuture<Void> unloadAllChunks();

    /**
     * Check if the specified chunk is loaded.
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     *
     * @return {@code true} if the chunk is loaded, otherwise {@code false}.
     */
    boolean isChunkLoaded(int x, int z);

    /**
     * Check if the specified chunk is loaded.
     *
     * @param hashXZ the hash of the chunk.
     *
     * @return {@code true} if the chunk is loaded, otherwise {@code false}.
     */
    boolean isChunkLoaded(long hashXZ);

    /**
     * Check if the specified chunk is loading.
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     *
     * @return {@code true} if the chunk is loading, otherwise {@code false}.
     */
    boolean isChunkLoading(int x, int z);

    /**
     * Check if the specified chunk is loading.
     *
     * @param hashXZ the hash of the chunk.
     *
     * @return {@code true} if the chunk is loading, otherwise {@code false}.
     */
    boolean isChunkLoading(long hashXZ);

    /**
     * Check if the specified chunk is unloaded.
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     *
     * @return {@code true} if the chunk is unloaded, otherwise {@code false}.
     */
    boolean isChunkUnloaded(int x, int z);

    /**
     * Check if the specified chunk is unloaded.
     *
     * @param hashXZ the hash of the chunk.
     *
     * @return {@code true} if the chunk is unloaded, otherwise {@code false}.
     */
    boolean isChunkUnloaded(long hashXZ);

    /**
     * Add the specified chunk to the keep loading list.
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     */
    void addKeepLoadingChunk(int x, int z);

    /**
     * Remove the specified chunk from the keep loading list.
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     */
    void removeKeepLoadingChunk(int x, int z);

    /**
     * Get the keep loading chunks.
     *
     * @return the keep loading chunks.
     */
    @UnmodifiableView
    Set<Long> getKeepLoadingChunks();

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
     * Get the loaded chunks.
     *
     * @return the loaded chunks.
     */
    @UnmodifiableView
    Collection<Chunk> getLoadedChunks();

    /**
     * Get the loading chunks.
     *
     * @return the loading chunks.
     */
    @UnmodifiableView
    Collection<CompletableFuture<Chunk>> getLoadingChunks();

    /**
     * Remove chunks that are unused immediately.
     * <p>
     * An unused chunk is a chunk that is loaded but is not in any chunk loader's
     * range. Usually these chunks will still keep loaded for a period of time
     * (the time is specified by {@link ServerSettings.WorldSettings#removeUnneededChunkCycle()}).
     * <p>
     * Calling this method will set the countdown of all unused chunks to zero, which
     * will make these chunks be unloaded during the next tick.
     */
    void removeUnusedChunksImmediately();
}
