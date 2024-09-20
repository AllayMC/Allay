package org.allaymc.api.world.generator.context;

import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * @author daoge_cmd
 */
public class LightContext extends Context {
    public LightContext(UnsafeChunk currentChunk) {
        super(currentChunk);
    }
}
