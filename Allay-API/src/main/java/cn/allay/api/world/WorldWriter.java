package cn.allay.api.world;

import cn.allay.api.world.chunk.Chunk;

import java.util.concurrent.CompletableFuture;

/**
 * Author: Cool_Loong <br>
 * Date: 6/4/2023 <br>
 * Allay Project
 */
public interface WorldWriter {
    CompletableFuture<Void> writeChunk(Chunk chunk);

    void writeWorldData(WorldData worldData);
}
