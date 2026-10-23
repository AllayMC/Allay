package org.allaymc.server.network.processor.common;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;
import org.cloudburstmc.protocol.bedrock.packet.PacketViolationWarningPacket;

/**
 * {@code PacketViolationWarningPacketProcessor} logs client-reported packet
 * violations from the asynchronous receive path.
 * The warning includes the packet's string representation for diagnosis. This
 * processor consumes the warning without scheduling synchronous work or
 * deciding whether the connection should be disconnected.
 *
 * @author daoge_cmd
 */
@Slf4j
public class PacketViolationWarningPacketProcessor extends PacketProcessor<PacketViolationWarningPacket> {

    /**
     * Logs the received violation warning and returns {@code HANDLED} to consume it.
     * The player and receive tick do not affect the logging decision, and no
     * connection-state change is performed here.
     */
    @Override
    public PacketSignal handleAsync(Player player, PacketViolationWarningPacket packet, long receiveTime) {
        log.warn("Received PacketViolationWarningPacket: {}", packet.toString());
        return PacketSignal.HANDLED;
    }

    /**
     * Returns the packet violation warning packet type handled by this
     * processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PACKET_VIOLATION_WARNING;
    }
}
