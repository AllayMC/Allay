package org.allaymc.server.network.processor.common;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;
import org.cloudburstmc.protocol.bedrock.packet.PacketViolationWarningPacket;

/**
 * @author daoge_cmd
 */
@Slf4j
public class PacketViolationWarningPacketProcessor extends PacketProcessor<PacketViolationWarningPacket> {

    @Override
    public PacketSignal handleAsync(Player player, PacketViolationWarningPacket packet, long receiveTime) {
        log.warn("Received PacketViolationWarningPacket: {}", packet.toString());
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PACKET_VIOLATION_WARNING;
    }
}
