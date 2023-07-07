package cn.allay.api.world.storage;

import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.chunk.Chunk;

import java.util.concurrent.CompletableFuture;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
public interface WorldStorage {
    CompletableFuture<Chunk> readChunk(int x, int z, DimensionInfo dimensionData) throws WorldStorageException;

    CompletableFuture<Void> writeChunk(int x, int z, Chunk chunk) throws WorldStorageException;

    boolean containChunk(int x, int z);

    void writeWorldData(WorldData worldData) throws WorldStorageException;

    WorldData readWorldData() throws WorldStorageException;

    void close();
}
