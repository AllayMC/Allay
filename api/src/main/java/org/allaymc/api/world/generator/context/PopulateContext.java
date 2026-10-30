package org.allaymc.api.world.generator.context;

import org.allaymc.api.world.chunk.ChunkSource;
import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * {@code PopulateContext} supplies the current chunk and a neighboring chunk
 * source to population functions. The runtime constrains that source to the
 * population region, while dimension-position helpers route current-chunk
 * access directly and delegate neighboring access through the provided source.
 *
 * @author daoge_cmd
 */
public class PopulateContext extends OtherChunkAccessibleContext {
    /**
     * Creates a population context retaining the target and neighboring chunk
     * source. The caller establishes access bounds and synchronization through
     * that source; this constructor does not validate or reserve neighboring
     * chunks.
     */
    public PopulateContext(UnsafeChunk currentChunk, ChunkSource chunkSource) {
        super(currentChunk, chunkSource);
    }
}
