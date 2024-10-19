package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.world.Dimension;

/**
 * @author Dhaiven
 */
@Getter
public class ChunkPreLoadEvent extends WorldEvent {
    protected Dimension dimension;
    protected int chunkX;
    protected int chunkZ;

    public ChunkPreLoadEvent(Dimension dimension, int chunkX, int chunkZ) {
        super(dimension.getWorld());
        this.dimension = dimension;
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }
}
