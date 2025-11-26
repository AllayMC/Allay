package org.allaymc.server.network.processor.login;

import io.netty.buffer.Unpooled;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackChunkDataPacket;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackChunkRequestPacket;

import static org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType.RESOURCE_PACK_CHUNK_REQUEST;

/**
 * @author daoge_cmd
 */
public class ResourcePackChunkRequestPacketProcessor extends ILoginPacketProcessor<ResourcePackChunkRequestPacket> {
    @Override
    public void handle(Player player, ResourcePackChunkRequestPacket packet) {
        var pack = Registries.PACKS.get(packet.getPackId());
        if (pack == null) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_RESOURCEPACK);
            return;
        }

        player.sendPacket(getChunkDataPacket(pack, packet.getChunkIndex()));
    }

    public ResourcePackChunkDataPacket getChunkDataPacket(Pack pack, int chunkIndex) {
        var chunkSize = AllayServer.getSettings().resourcePackSettings().maxChunkSize() * 1024;
        var packet = new ResourcePackChunkDataPacket();
        packet.setPackId(pack.getId());
        packet.setPackVersion(pack.getStringVersion());
        packet.setChunkIndex(chunkIndex);
        packet.setData(Unpooled.wrappedBuffer(pack.getChunk(chunkSize * chunkIndex, chunkSize)));
        packet.setProgress((long) chunkSize * chunkIndex);
        return packet;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return RESOURCE_PACK_CHUNK_REQUEST;
    }
}
