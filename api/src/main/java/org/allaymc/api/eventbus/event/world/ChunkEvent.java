package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;

/**
 * @author daoge_cmd
 */
@Getter
public abstract class ChunkEvent extends WorldEvent {
    protected Dimension dimension;
    protected Chunk chunk;

    public ChunkEvent(Dimension dimension, Chunk chunk) {
        super(dimension.getWorld());
        this.dimension = dimension;
        this.chunk = chunk;
    }
}
