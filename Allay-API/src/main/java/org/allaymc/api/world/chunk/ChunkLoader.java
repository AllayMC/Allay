package org.allaymc.api.world.chunk;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3fc;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.ApiStatus;

import java.util.Set;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface ChunkLoader {

    Location3fc getLocation();

    boolean isLoaderActive();

    int getChunkLoadingRadius();

    void setChunkLoadingRadius(int radius);

    int getChunkTrySendCountPerTick();

    void publishClientChunkUpdate();

    void onChunkInRangeSent(Chunk chunk);

    void spawnEntity(Entity entity);

    void despawnEntity(Entity entity);

    void onChunkOutOfRange(Set<Long> chunkHashes);

    default void unloadChunk(Long chunkHash) {
        onChunkOutOfRange(Set.of(chunkHash));
    }

    @ApiStatus.Internal
    void sendLevelChunkPacket(LevelChunkPacket lcp);

    @ApiStatus.Internal
    void handleChunkPacket(BedrockPacket packet);

    @ApiStatus.Internal
    void handleChunkPacketImmediately(BedrockPacket packet);
}