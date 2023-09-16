package cn.allay.api.world.storage;

import cn.allay.api.client.Client;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.chunk.Chunk;
import org.cloudburstmc.nbt.NbtMap;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
public interface WorldStorage {
    CompletableFuture<Chunk> readChunk(int chunkX, int chunkZ) throws WorldStorageException;

    CompletableFuture<Void> writeChunk(Chunk chunk) throws WorldStorageException;

    boolean containChunk(int chunkX, int chunkZ);

    void writeWorldData(WorldData worldData) throws WorldStorageException;

    WorldData readWorldData() throws WorldStorageException;

    WorldData getWorldDataCache();

    void close();
}
