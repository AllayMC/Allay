package org.allaymc.api.world.service;

import org.allaymc.api.annotation.SlowOperation;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkAccessible;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface ChunkService extends ChunkAccessible {
    void tick(long currentTick);

    CompletableFuture<Chunk> getChunkLoadingFuture(int x, int z);

    CompletableFuture<Chunk> getOrLoadChunk(int x, int z);

    CompletableFuture<Set<Chunk>> getOrLoadRangedChunk(int x, int z, int range);

    CompletableFuture<Chunk> loadChunk(int x, int z);

    @SlowOperation
    Chunk getOrLoadChunkSync(int x, int z);

    boolean unloadChunk(int x, int z);

    boolean unloadChunk(long chunkHash);

    void unloadAllChunks();

    boolean isChunkLoaded(int x, int z);

    boolean isChunkLoaded(long hashXZ);

    boolean isChunkLoading(int x, int z);

    boolean isChunkLoading(long hashXZ);

    boolean isChunkUnloaded(int x, int z);

    boolean isChunkUnloaded(long hashXZ);

    void addKeepLoadingChunk(int x, int z);

    void removeKeepLoadingChunk(int x, int z);

    @UnmodifiableView
    Set<Long> getKeepLoadingChunks();

    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    void addChunkLoader(ChunkLoader chunkLoader);

    void removeChunkLoader(ChunkLoader chunkLoader);

    void forEachLoadedChunks(Consumer<Chunk> consumer);

    @UnmodifiableView
    Collection<Chunk> getLoadedChunks();

    @UnmodifiableView
    Collection<CompletableFuture<Chunk>> getLoadingChunks();
}
