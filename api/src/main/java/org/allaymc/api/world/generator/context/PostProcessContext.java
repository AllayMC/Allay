package org.allaymc.api.world.generator.context;

import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * @author daoge_cmd
 */
public class PostProcessContext extends Context {
    public PostProcessContext(UnsafeChunk currentChunk) {
        super(currentChunk);
    }
}
