package org.allaymc.api.world.generator.context;

import org.allaymc.api.world.chunk.ChunkAccessible;
import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * Allay Project 2024/6/16
 *
 * @author daoge_cmd
 */
public class PopulateContext extends OtherChunkAccessibleContext {
    public PopulateContext(UnsafeChunk currentChunk, ChunkAccessible chunkAccessor) {
        super(currentChunk, chunkAccessor);
    }
}
