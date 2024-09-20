package org.allaymc.api.world.generator.context;

import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * @author daoge_cmd
 */
public class EntitySpawnContext extends Context {
    public EntitySpawnContext(UnsafeChunk currentChunk) {
        super(currentChunk);
    }
}
