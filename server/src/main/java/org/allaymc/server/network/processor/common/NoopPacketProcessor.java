package org.allaymc.server.network.processor.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

/**
 * {@code NoopPacketProcessor} ignores one configured packet type through the
 * inherited processor hooks. The
 * inherited asynchronous hook returns {@code UNHANDLED}, so the session still performs
 * its normal synchronous dispatch before the inherited empty handler completes.
 * The generated constructor and accessor retain the supplied packet type
 * without adding validation.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class NoopPacketProcessor extends PacketProcessor<BedrockPacket> {
    /**
     * {@code packetType} identifies the ignored packet type returned by the
     * generated accessor and supplied directly through the generated constructor.
     */
    private final BedrockPacketType packetType;
}
