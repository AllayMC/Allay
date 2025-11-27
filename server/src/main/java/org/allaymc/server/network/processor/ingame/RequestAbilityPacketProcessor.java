package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RequestAbilityPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public class RequestAbilityPacketProcessor extends PacketProcessor<RequestAbilityPacket> {

    @Override
    public PacketSignal handleAsync(Player player, RequestAbilityPacket packet, long receiveTime) {
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_ABILITY;
    }
}
