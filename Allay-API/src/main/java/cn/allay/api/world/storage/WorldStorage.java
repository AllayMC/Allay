package cn.allay.api.world.storage;

import cn.allay.api.entity.Entity;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.chunk.Chunk;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
public interface WorldStorage {
    CompletableFuture<Chunk> readChunk(int chunkX, int chunkZ, DimensionInfo dimensionData) throws WorldStorageException;

    CompletableFuture<Void> writeChunk(int chunkX, int chunkZ, Chunk chunk) throws WorldStorageException;

    CompletableFuture<Set<NbtMap>> readEntities(int chunkX, int chunkZ) throws WorldStorageException;

    CompletableFuture<Void> writeEntities(int chunkX, int chunkZ, Set<Entity> entities) throws WorldStorageException;

    boolean containChunk(int chunkX, int chunkZ);

    void writeWorldData(WorldData worldData) throws WorldStorageException;

    WorldData readWorldData() throws WorldStorageException;

    void close();
}
