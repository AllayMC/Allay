package org.allaymc.server.network.protocol.v2169;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v2168.PacketEncoder_v2168;

/**
 * Packet encoder for protocol v2169.
 *
 * <p>The wire-format changes for this version are handled by the Bedrock codec;
 * the domain-to-packet mappings remain compatible with v2168.</p>
 */
public class PacketEncoder_v2169 extends PacketEncoder_v2168 {

    public PacketEncoder_v2169(ProtocolData data) {
        super(data);
    }
}
