package cn.allay.api.world.storage;

import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.chunk.Chunk;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

/**
 * Author: Cool_Loong <br>
 * Date: 5/30/2023 <br>
 * Allay Project
 */
public interface WorldProvider {
    CompletableFuture<Chunk> readChunk(int x, int z, DimensionInfo dimensionData);
    CompletableFuture<Void> writeChunk(Chunk chunk);
    void writeWorldData(WorldData worldData);
    WorldData readWorldData() throws IOException;
    void close();
}
