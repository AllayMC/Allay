package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.entity.component.player.EntityPlayerClientComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ClientCacheStatusPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public class ClientCacheStatusPacketProcessor extends PacketProcessor<ClientCacheStatusPacket> {

    @Override
    public PacketSignal handleAsync(EntityPlayer player, ClientCacheStatusPacket packet, long receiveTime) {
        var clientComponent = (EntityPlayerClientComponentImpl) ((EntityPlayerImpl) player).getPlayerClientComponent();
        clientComponent.setClientCacheEnabled(packet.isSupported());
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_CACHE_STATUS;
    }
}
