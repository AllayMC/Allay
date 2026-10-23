package org.allaymc.server.network.protocol.v898;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v860.PacketEncoder_v860;

/**
 * {@code PacketEncoder_v898} provides the packet encoder selected for protocol
 * v898 using the implementation inherited from {@code PacketEncoder_v860}. This
 * class introduces no packet overrides; the constructor retains the supplied
 * protocol data and its definition identities through the existing encoder
 * chain.
 */
public class PacketEncoder_v898 extends PacketEncoder_v860 {
    /**
     * Creates the v898 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v898(ProtocolData data) {
        super(data);
    }
}
