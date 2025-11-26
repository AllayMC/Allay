package org.allaymc.api.world.chunk;

import org.allaymc.api.math.location.Location3dc;

/**
 * ChunkLoader represents a loader that will load chunks in the dimension.
 *
 * @author daoge_cmd
 */
public interface ChunkLoader {

    /**
     * Get the location of the chunk loader.
     *
     * @return the location of the chunk loader
     */
    Location3dc getLocation();

    /**
     * Get the chunk loading radius.
     *
     * @return the chunk loading radius
     */
    int getChunkLoadingRadius();

    /**
     * Set the chunk loading radius.
     *
     * @param radius the chunk loading radius
     * @throws UnsupportedOperationException if the loading radius cannot be changed for this chunk loader
     */
    void setChunkLoadingRadius(int radius);

    /**
     * Get the maximum chunk count try to send per tick.
     *
     * @return the maximum chunk count try to send per tick
     */
    int getChunkMaxSendCountPerTick();
}