package org.allaymc.api.world.chunk;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3fc;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;

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

    void beforeSendChunks();

    void onChunkInRangeSend(Chunk chunk);

    void spawnEntity(Entity entity);

    void despawnEntity(Entity entity);

    void onChunkOutOfRange(Set<Long> chunkHashes);

    void sendLevelChunkPacket(LevelChunkPacket lcp);

    void handleChunkPacket(BedrockPacket packet);

    void handleChunkPacketImmediately(BedrockPacket packet);
}