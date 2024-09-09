package org.allaymc.api.world.storage;

import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.chunk.Chunk;

import java.util.concurrent.CompletableFuture;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
public interface WorldStorage {
    default void tick(long currentTick) {}

    default void shutdown() {}

    CompletableFuture<Chunk> readChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo);

    Chunk readChunkSync(int chunkX, int chunkZ, DimensionInfo dimensionInfo);

    CompletableFuture<Void> writeChunk(Chunk chunk);

    void writeChunkSync(Chunk chunk);

    boolean containChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo);

    void writeWorldData(WorldData worldData);

    WorldData readWorldData();
}
