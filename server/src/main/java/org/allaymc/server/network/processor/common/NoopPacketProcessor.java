package org.allaymc.server.network.processor.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

/**
 * A noop packet processor that just ignored the specific packet.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class NoopPacketProcessor extends PacketProcessor<BedrockPacket> {
    private final BedrockPacketType packetType;
}
