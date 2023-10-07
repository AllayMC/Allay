package cn.allay.api.world.chunk;

import cn.allay.api.entity.Entity;
import cn.allay.api.math.location.Location3fc;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkRequestPacket;

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

    void preSendChunks(Set<Long> chunkHashes);

    void onChunkInRangeLoaded(Chunk chunk);

    void spawnEntity(Entity entity);

    void despawnEntity(Entity entity);

    void onChunkOutOfRange(Set<Long> chunkHashes);

    default void unloadChunk(Long chunkHash) {
        onChunkOutOfRange(Set.of(chunkHash));
    }

    void setSubChunkRequestHandler(Function<SubChunkRequestPacket, SubChunkPacket> handler);

    default void sendPacket(BedrockPacket packet) {}

    default void sendPacketImmediately(BedrockPacket packet) {}
}