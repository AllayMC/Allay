package org.allaymc.server.world.generator;

import lombok.AllArgsConstructor;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkSource;

/**
 * {@code LimitedChunkSource} restricts chunk lookup to an inclusive square
 * around a fixed center chunk. The generated constructor retains the underlying
 * source and range; coordinates outside the square return {@code null} without
 * consulting the source, while in-range lookups preserve its own availability
 * rules.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class LimitedChunkSource implements ChunkSource {

    /**
     * {@code chunkSource} is consulted only inside the permitted square. Its own
     * accessibility and synchronization requirements remain in effect.
     */
    protected final ChunkSource chunkSource;
    /**
     * Center X in chunk coordinates.
     */
    protected final int centerChunkX;
    /**
     * Center Z in chunk coordinates.
     */
    protected final int centerChunkZ;
    /**
     * {@code range} is the inclusive radius in chunks on each axis. Callers
     * supply a nonnegative value suitable for integer-distance calculations.
     */
    protected final int range;

    /**
     * Returns the underlying source's chunk only when both coordinate offsets
     * lie within the configured range. Out-of-range requests return {@code null}
     * without loading or consulting the source; integer coordinate arithmetic
     * is not overflow-checked.
     */
    @Override
    public Chunk getChunk(int x, int z) {
        if (Math.abs(x - centerChunkX) > range || Math.abs(z - centerChunkZ) > range) {
            // Access outside the reserved generation square must not reach the
            // underlying source, which may otherwise expose chunks this step cannot use.
            return null;
        }

        return chunkSource.getChunk(x, z);
    }
}
