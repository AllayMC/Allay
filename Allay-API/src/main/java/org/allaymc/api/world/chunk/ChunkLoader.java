package org.allaymc.api.world.chunk;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3fc;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkRequestPacket;
import org.jetbrains.annotations.ApiStatus;

import java.util.Set;
import java.util.function.Function;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface ChunkLoader {

    Location3fc getLocation();

    boolean isLoaderActive();

    int getChunkLoadingRadius();

    int getChunkTrySendCountPerTick();

    void setChunkLoadingRadius(int radius);

    void publishClientChunkUpdate();

    void onChunkInRangeLoaded(Chunk chunk);

    void spawnEntity(Entity entity);

    void despawnEntity(Entity entity);

    void onChunkOutOfRange(Set<Long> chunkHashes);

    default void unloadChunk(Long chunkHash) {
        onChunkOutOfRange(Set.of(chunkHash));
    }

    void setSubChunkRequestHandler(Function<SubChunkRequestPacket, SubChunkPacket> handler);

    @ApiStatus.Internal
    void handleChunkPacket(BedrockPacket packet);

    void handleChunkPacketImmediately(BedrockPacket packet);
}