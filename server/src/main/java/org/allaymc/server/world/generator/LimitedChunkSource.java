package org.allaymc.server.world.generator;

import lombok.AllArgsConstructor;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkSource;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
public class LimitedChunkSource implements ChunkSource {

    protected final ChunkSource chunkSource;
    protected final int centerChunkX;
    protected final int centerChunkZ;
    protected final int range;

    @Override
    public Chunk getChunk(int x, int z) {
        if (Math.abs(x - centerChunkX) > range || Math.abs(z - centerChunkZ) > range) {
            return null; // Out of range
        }

        return chunkSource.getChunk(x, z);
    }
}
