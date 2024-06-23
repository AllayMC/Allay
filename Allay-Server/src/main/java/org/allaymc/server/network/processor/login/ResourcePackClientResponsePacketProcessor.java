package org.allaymc.server.network.processor.login;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.network.processor.ILoginPacketProcessor;
import org.allaymc.api.pack.PackRegistry;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackClientResponsePacket;

import java.util.UUID;

/**
 * Allay Project 2024/2/2
 *
 * @author daoge_cmd
 */
public class ResourcePackClientResponsePacketProcessor extends ILoginPacketProcessor<ResourcePackClientResponsePacket> {
    @Override
    public void handle(EntityPlayer player, ResourcePackClientResponsePacket packet) {
        switch (packet.getStatus()) {
            case SEND_PACKS -> {
                for (var packId : packet.getPackIds()) {
                    var pack = PackRegistry.getRegistry().get(UUID.fromString(packId.split("_")[0]));
                    if (pack == null) {
                        player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_RESOURCEPACK);
                        return;
                    }

                    player.sendPacket(pack.toNetwork());
                }
            }
            case HAVE_ALL_PACKS -> player.sendPacket(PackRegistry.getRegistry().getPackStackPacket());
            case COMPLETED -> player.initializePlayer();
            default -> player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_NOREASON);
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESOURCE_PACK_CLIENT_RESPONSE;
    }
}
