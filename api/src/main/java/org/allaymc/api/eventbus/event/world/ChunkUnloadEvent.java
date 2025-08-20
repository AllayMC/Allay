package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;

/**
 * @author Dhaiven
 */
public class ChunkUnloadEvent extends ChunkEvent {
    public ChunkUnloadEvent(Dimension dimension, Chunk chunk) {
        super(dimension, chunk);
    }
}
