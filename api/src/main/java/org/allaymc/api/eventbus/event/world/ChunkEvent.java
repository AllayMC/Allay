package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;

/**
 * {@code ChunkEvent} associates a chunk notification with its dimension and
 * owning world. The constructor derives the world from the supplied dimension
 * and retains both handles, allowing listeners to inspect the same chunk
 * involved in the runtime operation.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class ChunkEvent extends WorldEvent {
    /**
     * Dimension owning the affected chunk.
     */
    protected Dimension dimension;
    /**
     * Chunk involved in the notification.
     */
    protected Chunk chunk;

    /**
     * Creates a chunk notification retaining the supplied dimension and chunk
     * handles.
     */
    public ChunkEvent(Dimension dimension, Chunk chunk) {
        super(dimension.getWorld());
        this.dimension = dimension;
        this.chunk = chunk;
    }
}
