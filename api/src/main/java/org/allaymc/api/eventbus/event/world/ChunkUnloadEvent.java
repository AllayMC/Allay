package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;

/**
 * Called when a chunk is unloaded.
 *
 * @author Dhaiven
 */
@CallerThread(ThreadType.DIMENSION)
public class ChunkUnloadEvent extends ChunkEvent {
    public ChunkUnloadEvent(Dimension dimension, Chunk chunk) {
        super(dimension, chunk);
    }
}
