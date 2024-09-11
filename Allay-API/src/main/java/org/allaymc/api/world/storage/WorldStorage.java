package org.allaymc.api.world.storage;

import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.chunk.Chunk;

import java.util.concurrent.CompletableFuture;

/**
 * WorldStorage represents the storage of a world,
 * which is responsible for reading and writing chunks and world data.
 *
 * @author daoge_cmd | Cool_Loong
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
