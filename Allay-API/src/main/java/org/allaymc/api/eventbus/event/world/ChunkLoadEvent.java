package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;

/**
 * Allay Project 2024/08/03
 *
 * @author Dhaiven
 */
public class ChunkLoadEvent extends ChunkEvent {
    public ChunkLoadEvent(Dimension dimension, Chunk chunk) {
        super(dimension, chunk);
    }
}
