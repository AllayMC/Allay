package cn.allay.level;

import cn.allay.level.chunk.Chunk;
import cn.allay.nbt.tag.CompoundTag;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface LevelProvider {
    String getPath();

    CompletableFuture<Chunk> getChunkAtAsync(int x, int z);

    Chunk getChunk(int x, int z);

    CompletableFuture<Void> saveChunk(Chunk chunk);

    CompletableFuture<Void> saveChunks();

    Map<Long, Chunk> getLoadedChunks();

    default boolean loadChunk(int x, int z) {
        return this.loadChunk(x, z, true);
    }

    boolean loadChunk(int x, int z, boolean create);

    default boolean unloadChunk(int x, int z) {
        return this.unloadChunk(x, z, true);
    }

    boolean unloadChunk(int x, int z, boolean save);

    void unloadChunks();

    CompletableFuture<Void> saveLevelData();

    CompoundTag getLevelData();

    void close();
}
