package org.allaymc.api.world.generator.context;

import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * @author daoge_cmd
 */
public class NoiseContext extends Context {
    public NoiseContext(UnsafeChunk currentChunk) {
        super(currentChunk);
    }
}
