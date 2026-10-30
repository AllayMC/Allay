package org.allaymc.api.world.generator.context;

import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * {@code PostProcessContext} supplies the shared unsafe chunk to
 * post-processing generation functions. Its access surface contains only the
 * current chunk, so final-stage functions cannot request neighbors through this
 * context. The caller establishes the generation-stage access rules before
 * creating and passing this context.
 *
 * @author daoge_cmd
 */
public class PostProcessContext extends Context {
    /**
     * Creates a post-processing context retaining the supplied unsafe chunk
     * without copying it. The caller must establish the appropriate generation
     * reservation and keep use within that stage's execution lifetime.
     */
    public PostProcessContext(UnsafeChunk currentChunk) {
        super(currentChunk);
    }
}
