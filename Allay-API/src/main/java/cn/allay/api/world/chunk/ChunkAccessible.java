package cn.allay.api.world.chunk;

import cn.allay.api.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface ChunkAccessible {
    @Nullable
    Chunk getChunk(int x, int z);

    @Nullable
    Chunk getChunk(long chunkHash);

    void setChunk(int x, int z, Chunk chunk);

    int maxChunkX();

    int maxChunkZ();

    int minChunkX();

    int minChunkZ();
}
