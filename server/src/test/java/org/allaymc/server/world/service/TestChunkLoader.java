package org.allaymc.server.world.service;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public class TestChunkLoader implements ChunkLoader {

    @Getter
    @Setter
    protected int chunkTrySendCountPerTick = 16;
    @Getter
    @Setter
    protected int chunkLoadingRadius = 8;
    @Getter
    @Setter
    protected boolean loaderActive = true;
    @Getter
    @Setter
    protected Location3dc location = new Location3d(0, 0, 0, null);

    @Override
    public void sendPacket(BedrockPacket packet) {
        if (packet instanceof LevelChunkPacket lcp) {
            lcp.release();
        }
    }

    @Override
    public void beforeSendChunks() {}

    @Override
    public void onChunkInRangeSend(Chunk chunk) {}

    @Override
    public void spawnEntity(Entity entity) {}

    @Override
    public void despawnEntity(Entity entity) {}

    @Override
    public void onChunkOutOfRange(Set<Long> chunkHashes) {}

    @Override
    public void sendPacketImmediately(BedrockPacket packet) {}
}
