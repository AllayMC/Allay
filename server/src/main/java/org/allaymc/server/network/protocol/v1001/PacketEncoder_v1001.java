package org.allaymc.server.network.protocol.v1001;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v975.PacketEncoder_v975;

/**
 * {@code PacketEncoder_v1001} provides the packet encoder selected for protocol
 * v1001 using the implementation inherited from {@code PacketEncoder_v975}.
 * This class introduces no packet overrides; the constructor retains the
 * supplied protocol data and its definition identities through the existing
 * encoder chain.
 */
public class PacketEncoder_v1001 extends PacketEncoder_v975 {
    /**
     * Creates the v1001 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v1001(ProtocolData data) {
        super(data);
    }
}
