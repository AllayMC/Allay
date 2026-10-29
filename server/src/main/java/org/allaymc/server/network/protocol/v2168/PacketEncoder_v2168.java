package org.allaymc.server.network.protocol.v2168;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v1001.PacketEncoder_v1001;

/**
 * Packet encoder for protocol v2168.
 *
 * <p>The wire-format changes for this version are handled by the Bedrock codec;
 * the domain-to-packet mappings remain compatible with v1001.</p>
 */
public class PacketEncoder_v2168 extends PacketEncoder_v1001 {

    public PacketEncoder_v2168(ProtocolData data) {
        super(data);
    }
}
