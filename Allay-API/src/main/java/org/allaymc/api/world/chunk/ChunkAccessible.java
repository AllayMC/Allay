package org.allaymc.api.world.chunk;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface ChunkAccessible {

    Chunk getChunk(int x, int z);

    default Chunk getChunkByLevelPos(int x, int z) {
        return getChunk(x >> 4, z >> 4);
    }

    Chunk getChunk(long chunkHash);

    int maxChunkX();

    int maxChunkZ();

    int minChunkX();

    int minChunkZ();
}
