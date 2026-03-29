package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataDrivenScreenClosedPacket;

public final class ServerboundDataDrivenScreenClosedPacketProcessor extends PacketProcessor<ServerboundDataDrivenScreenClosedPacket> {

    @Override
    public void handleSync(Player player, ServerboundDataDrivenScreenClosedPacket packet, long receiveTime) {
        player.removeDataDrivenScreen();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_DATA_DRIVEN_SCREEN_CLOSED;
    }
}
