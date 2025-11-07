package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;

/**
 * @author Dhaiven
 */
@CallerThread(ThreadType.COMPUTE)
public class ChunkLoadEvent extends ChunkEvent {
    public ChunkLoadEvent(Dimension dimension, Chunk chunk) {
        super(dimension, chunk);
    }
}
