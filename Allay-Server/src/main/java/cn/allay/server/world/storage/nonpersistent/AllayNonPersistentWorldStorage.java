package cn.allay.server.world.storage.nonpersistent;

import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.storage.WorldStorage;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class AllayNonPersistentWorldStorage implements WorldStorage {
    @Override
    public CompletableFuture<Chunk> readChunk(int x, int z, DimensionInfo dimensionData) {
        return null;
    }

    @Override
    public CompletableFuture<Void> writeChunk(Chunk chunk) {
        return null;
    }

    @Override
    public void writeWorldData(WorldData worldData) {

    }

    @Override
    public WorldData readWorldData() throws IOException {
        return null;
    }

    @Override
    public void close() {

    }
}
