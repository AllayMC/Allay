package org.allaymc.api.world.chunk;

import org.allaymc.api.utils.HashUtils;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface ChunkAccessible {

    Chunk getChunk(int x, int z);

    default Chunk getChunkByDimensionPos(int x, int z) {
        return getChunk(x >> 4, z >> 4);
    }

    default Chunk getChunk(long chunkHash) {
        return getChunk(
                HashUtils.getXFromHashXZ(chunkHash),
                HashUtils.getZFromHashXZ(chunkHash)
        );
    }

    default int maxChunkX() {
        return Integer.MAX_VALUE;
    }

    default int maxChunkZ() {
        return Integer.MAX_VALUE;
    }

    default int minChunkX() {
        return Integer.MIN_VALUE;
    }

    default int minChunkZ() {
        return Integer.MIN_VALUE;
    }
}
