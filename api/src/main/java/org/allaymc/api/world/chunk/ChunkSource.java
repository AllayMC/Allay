package org.allaymc.api.world.chunk;

import org.allaymc.api.utils.HashUtils;

/**
 * ChunkSource represents an object that you can get chunks from. It provides methods to retrieve
 * chunks based on their coordinates or hash. Implementations can decide which chunks are accessible,
 * and can return {@code null} if the chunks are not accessible.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface ChunkSource {

    /**
     * Get the chunk at the specified chunk pos.
     *
     * @param x the x coordinate of the chunk.
     * @param z the z coordinate of the chunk.
     *
     * @return the chunk at the specified chunk coordinates, or {@code null} if not
     * found or is not accessible by this chunk source.
     */
    Chunk getChunk(int x, int z);

    /**
     * Get the chunk which contains the specified dimension pos.
     *
     * @param x the x coordinate of the dimension pos.
     * @param z the z coordinate of the dimension pos.
     *
     * @return the chunk which the specified dimension pos is in, or {@code null} if not
     * found or is not accessible by this chunk source.
     */
    default Chunk getChunkByDimensionPos(int x, int z) {
        return getChunk(x >> 4, z >> 4);
    }

    /**
     * Get the chunk by chunk hash.
     *
     * @param chunkHash the hash of the chunk.
     *
     * @return the chunk which has the specified hash, or {@code null} if not found
     * or is not accessible by this chunk source.
     */
    default Chunk getChunk(long chunkHash) {
        return getChunk(
                HashUtils.getXFromHashXZ(chunkHash),
                HashUtils.getZFromHashXZ(chunkHash)
        );
    }
}
