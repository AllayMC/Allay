package org.allaymc.api.world.generator.context;

import org.allaymc.api.world.chunk.ChunkSource;
import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * @author daoge_cmd
 */
public class PopulateContext extends OtherChunkAccessibleContext {
    public PopulateContext(UnsafeChunk currentChunk, ChunkSource chunkSource) {
        super(currentChunk, chunkSource);
    }
}
