package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;

/**
 * {@code ChunkUnloadEvent} announces that a full chunk is entering the unload
 * path in its dimension. The chunk holder dispatches this notification before
 * runtime unload hooks and the storage write, so the notification does not
 * establish persistence completion.
 *
 * @author Dhaiven
 */
@CallerThread(ThreadType.DIMENSION)
public class ChunkUnloadEvent extends ChunkEvent {
    /**
     * Creates an unload notification for the supplied dimension and chunk.
     */
    public ChunkUnloadEvent(Dimension dimension, Chunk chunk) {
        super(dimension, chunk);
    }
}
