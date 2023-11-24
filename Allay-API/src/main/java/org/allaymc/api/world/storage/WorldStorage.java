package org.allaymc.api.world.storage;

import org.allaymc.api.exception.WorldStorageException;
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
    CompletableFuture<Chunk> readChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) throws WorldStorageException;

    CompletableFuture<Void> writeChunk(Chunk chunk) throws WorldStorageException;

    boolean containChunk(int chunkX, int chunkZ);

    void writeWorldData(WorldData worldData) throws WorldStorageException;

    WorldData readWorldData() throws WorldStorageException;

    WorldData getWorldDataCache();

    void close();
}
