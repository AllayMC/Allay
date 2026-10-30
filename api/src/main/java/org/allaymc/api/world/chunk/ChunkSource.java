package org.allaymc.api.world.chunk;

import org.allaymc.api.utils.hash.HashUtils;

/**
 * {@code ChunkSource} locates accessible chunks by chunk coordinates, block
 * coordinates, or a packed coordinate hash. Implementations determine which
 * chunks are accessible and return {@code null} for unavailable chunks; lookup
 * does not itself require missing chunks to be loaded.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface ChunkSource {

    /**
     * Returns the accessible chunk at the supplied chunk coordinates, or {@code
     * null} when unavailable. Implementations decide accessibility; this lookup
     * does not itself promise to load missing chunks.
     */
    Chunk getChunk(int x, int z);

    /**
     * Returns the accessible chunk containing the supplied dimension block
     * coordinates, or {@code null} when unavailable. Coordinates are converted
     * using floor division by sixteen, including for negative positions.
     */
    default Chunk getChunkByDimensionPos(int x, int z) {
        // Arithmetic shifts keep negative block positions in their containing
        // chunk.
        return getChunk(x >> 4, z >> 4);
    }

    /**
     * Returns the accessible chunk identified by a {@link HashUtils#hashXZ(int,
     * int)} value. The hash encodes both chunk coordinates, and the result is {@code
     * null} when that chunk is unavailable.
     */
    default Chunk getChunk(long chunkHash) {
        return getChunk(
                HashUtils.getXFromHashXZ(chunkHash),
                HashUtils.getZFromHashXZ(chunkHash)
        );
    }
}
