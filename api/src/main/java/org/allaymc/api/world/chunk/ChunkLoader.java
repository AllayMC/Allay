package org.allaymc.api.world.chunk;

import org.allaymc.api.entity.Entity;
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
     */
    void setChunkLoadingRadius(int radius);

    /**
     * Get chunk try to send count per tick.
     *
     * @return the chunk try to send count per tick.
     */
    int getChunkTrySendCountPerTick();

    /**
     * A method which will be called before sending chunks.
     */
    @ApiStatus.OverrideOnly
    void beforeSendChunks();

    /**
     * A method which will be called after a chunk is sent.
     */
    @ApiStatus.OverrideOnly
    void onChunkInRangeSend(Chunk chunk);

    /**
     * Spawn an entity to this chunk loader.
     *
     * @param entity the entity to spawn.
     */
    @ApiStatus.OverrideOnly
    void spawnEntity(Entity entity);

    /**
     * Despawn an entity from this chunk loader.
     *
     * @param entity the entity to despawn.
     */
    @ApiStatus.OverrideOnly
    void despawnEntity(Entity entity);

    /**
     * A method which will be called when a chunk is out of range.
     *
     * @param chunkHashes the chunk hashes that are out of range.
     */
    @ApiStatus.OverrideOnly
    void onChunkOutOfRange(Set<Long> chunkHashes);
}