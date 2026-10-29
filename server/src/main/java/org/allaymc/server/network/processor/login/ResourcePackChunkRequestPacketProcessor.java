package org.allaymc.server.network.processor.login;

import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackChunkRequestPacket;

import static org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType.RESOURCE_PACK_CHUNK_REQUEST;

/**
 * @author daoge_cmd
 */
public class ResourcePackChunkRequestPacketProcessor extends ILoginPacketProcessor<ResourcePackChunkRequestPacket> {
    @Override
    public void handle(Player player, ResourcePackChunkRequestPacket packet) {
        var allayPlayer = (AllayPlayer) player;
        var protocol = allayPlayer.getProtocol();
        var pack = Registries.PACKS.get(packet.getPackId());
        if (pack == null) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_RESOURCEPACK);
            return;
        }

        int chunkSize = AllayServer.getSettings()
                .resourcePackSettings()
                .maxChunkSize() * 1024;
        try {
            allayPlayer.sendPacket(protocol.getEncoder().encodeResourcePackChunkData(
                    pack,
                    packet.getChunkIndex(),
                    chunkSize
            ));
        } catch (IllegalArgumentException ignored) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_RESOURCEPACK);
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return RESOURCE_PACK_CHUNK_REQUEST;
    }
}
