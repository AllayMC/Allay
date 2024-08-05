package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;

/**
 * Allay Project 2024/08/03
 *
 * @author Dhaiven
 */
@Getter
public class ChunkLoadEvent extends ChunkEvent {
    public ChunkLoadEvent(Dimension dimension, Chunk chunk) {
        super(dimension, chunk);
    }
}
