package org.allaymc.api.world.generator.context;

import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * {@code NoiseContext} supplies the shared unsafe chunk to noise generation
 * functions. Its access surface contains only the current chunk, so terrain
 * functions must not assume neighboring chunks are available. The caller
 * establishes the generation-stage access rules before creating and passing
 * this context.
 *
 * @author daoge_cmd
 */
public class NoiseContext extends Context {
    /**
     * Creates a noise context retaining the supplied unsafe chunk without
     * copying it. The caller must establish the appropriate generation
     * reservation and keep use within that stage's execution lifetime.
     */
    public NoiseContext(UnsafeChunk currentChunk) {
        super(currentChunk);
    }
}
