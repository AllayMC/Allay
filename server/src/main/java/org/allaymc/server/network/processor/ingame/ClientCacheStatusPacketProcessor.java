package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ClientCacheStatusPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public class ClientCacheStatusPacketProcessor extends PacketProcessor<ClientCacheStatusPacket> {

    @Override
    public PacketSignal handleAsync(Player player, ClientCacheStatusPacket packet, long receiveTime) {
        ((AllayPlayer) player).setClientCacheEnabled(packet.isSupported());
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_CACHE_STATUS;
    }
}
