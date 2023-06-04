package cn.allay.api.world;

import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.dimension.DimensionData;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Author: Cool_Loong <br>
 * Date: 6/4/2023 <br>
 * Allay Project
 */
public interface WorldReader {
    CompletableFuture<Chunk> readChunk(int x, int z, DimensionData dimensionData);

    WorldData readWorldData() throws IOException;
}
