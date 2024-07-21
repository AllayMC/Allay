package org.allaymc.server.network.processor.login;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.network.processor.ILoginPacketProcessor;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackChunkRequestPacket;

import static org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType.RESOURCE_PACK_CHUNK_REQUEST;

/**
 * Allay Project 2024/2/2
 *
 * @author daoge_cmd
 */
public class ResourcePackChunkRequestPacketProcessor extends ILoginPacketProcessor<ResourcePackChunkRequestPacket> {
    @Override
    public void handle(EntityPlayer player, ResourcePackChunkRequestPacket packet) {
        var pack = Registries.PACKS.get(packet.getPackId());
        if (pack == null) {
            player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_RESOURCEPACK);
            return;
        }

        player.sendPacket(pack.getChunkDataPacket(packet.getChunkIndex()));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return RESOURCE_PACK_CHUNK_REQUEST;
    }
}
