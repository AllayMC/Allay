package org.allaymc.api.world.chunk;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3fc;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.ApiStatus;

import java.util.Set;

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
    Location3fc getLocation();

    /**
     * Check if the loader is active.
     *
     * @return {@code true} if the loader is active, {@code false} otherwise
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
     */
    void setChunkLoadingRadius(int radius);

    /**
     * Get chunk try to send count per tick.
     *
     * @return the chunk try to send count per tick
     */
    int getChunkTrySendCountPerTick();

    /**
     *
     */
    @ApiStatus.OverrideOnly
    void beforeSendChunks();

    void onChunkInRangeSend(Chunk chunk);

    void spawnEntity(Entity entity);

    void despawnEntity(Entity entity);

    void onChunkOutOfRange(Set<Long> chunkHashes);

    void sendLevelChunkPacket(LevelChunkPacket lcp);

    void handleChunkPacket(BedrockPacket packet);

    void handleChunkPacketImmediately(BedrockPacket packet);
}