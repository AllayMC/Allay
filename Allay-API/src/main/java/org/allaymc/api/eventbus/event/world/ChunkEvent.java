package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;

/**
 * Allay Project 2024/8/5
 *
 * @author daoge_cmd
 */
public abstract class ChunkEvent extends WorldEvent {
    protected Dimension dimension;
    protected Chunk chunk;

    public ChunkEvent(Dimension dimension, Chunk chunk) {
        super(dimension.getWorld());
        this.dimension = dimension;
        this.chunk = chunk;
    }
}
