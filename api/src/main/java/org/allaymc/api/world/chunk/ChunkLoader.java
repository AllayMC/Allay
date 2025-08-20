package org.allaymc.api.world.chunk;

import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.network.PacketReceiver;
import org.jetbrains.annotations.ApiStatus;

import java.util.Set;

/**
 * ChunkLoader represents a loader that will load chunks in the dimension.
 *
 * @author daoge_cmd
 */
public interface ChunkLoader extends PacketReceiver {

    /**
     * Get the location of the chunk loader.
     *
     * @return the location of the chunk loader.
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
     * @return the chunk loading radius.
     */
    int getChunkLoadingRadius();

    /**
     * Set the chunk loading radius.
     *
     * @param radius the chunk loading radius.
     *
     * @throws UnsupportedOperationException if the loading radius cannot be changed for this chunk loader.
     */
    void setChunkLoadingRadius(int radius);

    /**
     * Get the maximum chunk count try to send per tick.
     *
     * @return the maximum chunk count try to send per tick.
     */
    int getChunkMaxSendCountPerTick();

    /**
     * A method which will be called when the chunk pos of the loader is changed.
     */
    @ApiStatus.OverrideOnly
    void onChunkPosChanged();

    /**
     * A method which will be called after a chunk is sent.
     */
    @ApiStatus.OverrideOnly
    void onChunkInRangeSend(Chunk chunk);

    /**
     * A method which will be called when a chunk is out of range.
     *
     * @param chunkHashes the chunk hashes that are out of range.
     */
    @ApiStatus.OverrideOnly
    void onChunkOutOfRange(Set<Long> chunkHashes);
}