package org.allaymc.api.world.chunk;

import org.allaymc.api.utils.HashUtils;

/**
 * ChunkSource represents an object that you can get chunks from.
 *
 * @author daoge_cmd
 */
public interface ChunkSource {

    /**
     * Get the chunk at the specified chunk pos.
     *
     * @param x the x coordinate of the chunk
     * @param z the z coordinate of the chunk
     *
     * @return the chunk at the specified chunk coordinates, or {@code null} if not found
     */
    Chunk getChunk(int x, int z);

    /**
     * Get the chunk which contains the specified dimension pos.
     *
     * @param x the x coordinate of the dimension pos
     * @param z the z coordinate of the dimension pos
     *
     * @return the chunk which the specified dimension pos is in, or {@code null} if not found
     */
    default Chunk getChunkByDimensionPos(int x, int z) {
        return getChunk(x >> 4, z >> 4);
    }

    /**
     * Get the chunk by chunk hash.
     *
     * @param chunkHash the hash of the chunk
     *
     * @return the chunk which has the specified hash, or {@code null} if not found
     */
    default Chunk getChunk(long chunkHash) {
        return getChunk(
                HashUtils.getXFromHashXZ(chunkHash),
                HashUtils.getZFromHashXZ(chunkHash)
        );
    }

    /**
     * Get the max chunk x coordinate that can be accessed.
     *
     * @return the max chunk x coordinate
     */
    default int maxChunkX() {
        return Integer.MAX_VALUE;
    }

    /**
     * Get the max chunk z coordinate that can be accessed.
     *
     * @return the max chunk z coordinate
     */
    default int maxChunkZ() {
        return Integer.MAX_VALUE;
    }

    /**
     * Get the min chunk x coordinate that can be accessed.
     *
     * @return the min chunk x coordinate
     */
    default int minChunkX() {
        return Integer.MIN_VALUE;
    }

    /**
     * Get the min chunk z coordinate that can be accessed.
     *
     * @return the min chunk z coordinate
     */
    default int minChunkZ() {
        return Integer.MIN_VALUE;
    }
}
