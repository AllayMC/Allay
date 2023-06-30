package cn.allay.api.world.storage;

import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.chunk.Chunk;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
public interface WorldStorage {
    CompletableFuture<Chunk> readChunk(int x, int z, DimensionInfo dimensionData);

    CompletableFuture<Void> writeChunk(Chunk chunk);

    void writeWorldData(WorldData worldData);

    WorldData readWorldData() throws IOException;

    void close();
}
