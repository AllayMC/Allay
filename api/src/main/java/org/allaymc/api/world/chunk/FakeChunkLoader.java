package org.allaymc.api.world.chunk;

import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;

import java.util.Set;
import java.util.function.Supplier;

/**
 * Chunk loader that only loads chunks and do not handle data packets. Useful
 * for loading a range of chunks in some places like spawn point.
 *
 * @author daoge_cmd
 */
public record FakeChunkLoader(Supplier<Location3dc> locationSupplier, int radius) implements ChunkLoader {

    @Override
    public Location3dc getLocation() {
        return locationSupplier.get();
    }

    @Override
    public boolean isLoaderActive() {
        return true;
    }

    @Override
    public int getChunkLoadingRadius() {
        return radius;
    }

    @Override
    public int getChunkTrySendCountPerTick() {
        return Server.SETTINGS.worldSettings().chunkTrySendCountPerTick();
    }

    @Override
    public void sendPacket(BedrockPacket packet) {
        if (packet instanceof LevelChunkPacket lcp) {
            lcp.release();
        }
    }

    @Override
    public void sendPacketImmediately(BedrockPacket packet) {
        if (packet instanceof LevelChunkPacket lcp) {
            lcp.release();
        }
    }

    @Override
    public void setChunkLoadingRadius(int radius) {}

    @Override
    public void onChunkPosChanged() {}

    @Override
    public void onChunkInRangeSend(Chunk chunk) {}

    @Override
    public void onChunkOutOfRange(Set<Long> chunkHashes) {}

}
