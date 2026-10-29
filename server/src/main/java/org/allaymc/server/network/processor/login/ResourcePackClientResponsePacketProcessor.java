package org.allaymc.server.network.processor.login;

import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackClientResponsePacket;

import java.util.UUID;

/**
 * @author daoge_cmd
 */
public class ResourcePackClientResponsePacketProcessor extends ILoginPacketProcessor<ResourcePackClientResponsePacket> {
    @Override
    public void handle(Player player, ResourcePackClientResponsePacket packet) {
        var allayPlayer = (AllayPlayer) player;
        switch (packet.getStatus()) {
            case SEND_PACKS -> {
                var protocol = allayPlayer.getProtocol();
                var encoder = protocol.getEncoder();
                int chunkSize = AllayServer.getSettings()
                        .resourcePackSettings()
                        .maxChunkSize() * 1024;
                for (var packId : packet.getPackIds()) {
                    UUID id;
                    try {
                        id = parsePackId(packId);
                    } catch (IllegalArgumentException ignored) {
                        player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_RESOURCEPACK);
                        return;
                    }

                    var pack = Registries.PACKS.get(id);
                    if (pack == null) {
                        player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_RESOURCEPACK);
                        return;
                    }

                    allayPlayer.sendPacket(encoder.encodeResourcePackDataInfo(pack, chunkSize));
                }
            }
            case HAVE_ALL_PACKS -> {
                var encoder = allayPlayer.getProtocol().getEncoder();
                allayPlayer.sendPacket(encoder.encodeResourcePackStack());
            }
            case COMPLETED -> allayPlayer.spawnEntityPlayer();
            default -> player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESOURCE_PACK_CLIENT_RESPONSE;
    }

    private static UUID parsePackId(String encodedId) {
        if (encodedId == null) {
            throw new IllegalArgumentException("Resource-pack ID cannot be null");
        }
        int versionSeparator = encodedId.indexOf('_');
        return UUID.fromString(versionSeparator < 0 ? encodedId : encodedId.substring(0, versionSeparator));
    }
}
