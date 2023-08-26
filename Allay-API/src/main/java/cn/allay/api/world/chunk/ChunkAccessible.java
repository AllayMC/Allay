package cn.allay.api.world.chunk;

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

    int maxChunkX();

    int maxChunkZ();

    int minChunkX();

    int minChunkZ();
}
