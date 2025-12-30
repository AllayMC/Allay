package org.allaymc.server.network.processor.login;

import org.allaymc.api.message.TrKeys;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.NetworkData;
import org.allaymc.server.network.multiversion.MultiVersion;
import org.allaymc.server.network.multiversion.MultiVersionHelper;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.data.ResourcePackType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackClientResponsePacket;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackDataInfoPacket;

import java.util.UUID;

/**
 * @author daoge_cmd
 */
public class ResourcePackClientResponsePacketProcessor extends ILoginPacketProcessor<ResourcePackClientResponsePacket> {
    @Override
    @MultiVersion(version = "*", details = "MultiVersionHelper is used")
    public void handle(Player player, ResourcePackClientResponsePacket packet) {
        switch (packet.getStatus()) {
            case SEND_PACKS -> {
                for (var packId : packet.getPackIds()) {
                    var pack = Registries.PACKS.get(UUID.fromString(packId.split("_")[0]));
                    if (pack == null) {
                        player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_RESOURCEPACK);
                        return;
                    }

                    player.sendPacket(toNetwork(pack));
                }
            }
            case HAVE_ALL_PACKS -> {
                var packetToSend = NetworkData.RESOURCES_PACK_STACK_PACKET.get();
                MultiVersionHelper.adaptExperimentData(player, packetToSend.getExperiments());
                player.sendPacket(packetToSend);
            }
            case COMPLETED -> ((AllayPlayer) player).spawnEntityPlayer();
            default -> player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESOURCE_PACK_CLIENT_RESPONSE;
    }

    protected static ResourcePackDataInfoPacket toNetwork(Pack pack) {
        var chunkSize = AllayServer.getSettings().resourcePackSettings().maxChunkSize() * 1024;
        var packet = new ResourcePackDataInfoPacket();
        packet.setPackId(pack.getId());
        packet.setPackVersion(pack.getStringVersion());
        packet.setMaxChunkSize(chunkSize);
        packet.setChunkCount((long) Math.ceil(pack.getSize() / (double) chunkSize));
        packet.setCompressedPackSize(pack.getSize());
        packet.setHash(pack.getHash());
        packet.setType(toNetwork(pack.getType()));
        return packet;
    }

    protected static ResourcePackType toNetwork(Pack.Type type) {
        return switch (type) {
            case RESOURCES -> ResourcePackType.RESOURCES;
            case DATA -> ResourcePackType.DATA_ADD_ON;
            case WORLD_TEMPLATE -> ResourcePackType.WORLD_TEMPLATE;
            case SCRIPT -> ResourcePackType.ADDON;
        };
    }
}
