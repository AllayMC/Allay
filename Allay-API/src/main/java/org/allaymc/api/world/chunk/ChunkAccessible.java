package org.allaymc.api.world.chunk;

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
    default Chunk getChunkByLevelPos(int x, int z) {
        return getChunk(x >> 4, z >> 4);
    }

    @Nullable
    Chunk getChunk(long chunkHash);

    int maxChunkX();

    int maxChunkZ();

    int minChunkX();

    int minChunkZ();
}
