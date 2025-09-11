package org.allaymc.api.world.chunk;

import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.network.PacketReceiver;
import org.allaymc.api.world.WorldViewer;
import org.jetbrains.annotations.ApiStatus;

/**
 * ChunkLoader represents a loader that will load chunks in the dimension. A chunk loader
 * is also a {@link WorldViewer}.
 *
 * @author daoge_cmd
 */
public interface ChunkLoader extends WorldViewer, PacketReceiver {

    /**
     * Get the location of the chunk loader.
     *
     * @return the location of the chunk loader
     */
    Location3dc getLocation();

    /**
     * Check if the loader is active.
     *
     * @return {@code true} if the loader is active, {@code false} otherwise.
     */
    boolean isLoaderActive();

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
     * A method which will be called when the chunk pos of the loader is changed.
     */
    @ApiStatus.OverrideOnly
    void onLoaderChunkPosChange();
}