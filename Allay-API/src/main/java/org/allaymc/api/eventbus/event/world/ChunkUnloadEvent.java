package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.world.chunk.Chunk;

import lombok.Getter;

/**
 * Allay Project 2024/08/03
 *
 * @author Dhaiven
 */
@Getter
public class ChunkUnloadEvent extends Event {
    protected Chunk chunk;

    public ChunkUnloadEvent(Chunk chunk) {
        this.chunk = chunk;
    }
}
