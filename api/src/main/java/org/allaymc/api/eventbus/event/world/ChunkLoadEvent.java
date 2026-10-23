package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;

/**
 * {@code ChunkLoadEvent} announces that a chunk has reached its runtime load
 * hooks. The chunk holder dispatches this notification after attempting those
 * hooks and before completing the load future, so listeners receive the loaded
 * chunk and its owning dimension.
 *
 * @author Dhaiven
 */
@CallerThread(ThreadType.COMPUTE)
public class ChunkLoadEvent extends ChunkEvent {
    /**
     * Creates a load notification for the supplied dimension and chunk.
     */
    public ChunkLoadEvent(Dimension dimension, Chunk chunk) {
        super(dimension, chunk);
    }
}
