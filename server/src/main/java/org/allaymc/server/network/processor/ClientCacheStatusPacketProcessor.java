package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ClientCacheStatusPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public class ClientCacheStatusPacketProcessor extends PacketProcessor<ClientCacheStatusPacket> {

    @Override
    public PacketSignal handleAsync(EntityPlayer player, ClientCacheStatusPacket packet, long receiveTime) {
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_CACHE_STATUS;
    }
}
