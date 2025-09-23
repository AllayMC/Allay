package org.allaymc.server.network.processor.ingame;

import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundLoadingScreenPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public class ServerboundLoadingScreenPacketProcessor extends PacketProcessor<ServerboundLoadingScreenPacket> {

    @Override
    public PacketSignal handleAsync(EntityPlayerImpl player, ServerboundLoadingScreenPacket packet, long receiveTime) {
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_LOADING_SCREEN;
    }
}
