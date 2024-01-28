package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.pack.PackRegistry;
import org.allaymc.server.network.DataPacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackChunkRequestPacket;

/**
 * Allay Project 28/01/2024
 *
 * @author IWareQ
 */
public class ResourcePackChunkRequestPacketProcessor extends DataPacketProcessor<ResourcePackChunkRequestPacket> {

    @Override
    public void handle(EntityPlayer player, ResourcePackChunkRequestPacket pk) {
        var resourcePack = PackRegistry.getRegistry().get(pk.getPackId());
        if (resourcePack == null) {
            player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_RESOURCEPACK);
            return;
        }

        player.sendPacket(resourcePack.getChunkDataPacket(pk.getChunkIndex()));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESOURCE_PACK_CHUNK_REQUEST;
    }
}
