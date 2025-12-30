package org.allaymc.api.world.chunk;

import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.world.WorldViewer;

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

    /**
     * Determines whether this chunk loader can function as a world viewer.
     *
     * @return {@code true} if this chunk loader is a world viewer, {@code false} otherwise.
     */
    default boolean isWorldViewer() {
        return toWorldViewer() != null;
    }

    /**
     * Convert this chunk loader to a world viewer if it is.
     *
     * @return the world viewer, or {@code null} if this chunk loader is not a world viewer
     */
    WorldViewer toWorldViewer();
}