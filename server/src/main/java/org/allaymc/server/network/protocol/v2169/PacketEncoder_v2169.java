package org.allaymc.server.network.protocol.v2169;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v2168.PacketEncoder_v2168;

/**
 * {@code PacketEncoder_v2169} provides the packet encoder selected for protocol
 * v2169 using the implementation inherited from {@code PacketEncoder_v2168}.
 * This class introduces no packet overrides; the constructor retains the
 * supplied protocol data and its definition identities through the existing
 * encoder chain.
 */
public class PacketEncoder_v2169 extends PacketEncoder_v2168 {
    /**
     * Creates the v2169 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v2169(ProtocolData data) {
        super(data);
    }
}
