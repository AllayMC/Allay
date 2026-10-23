package org.allaymc.server.network.protocol.v860;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v859.PacketEncoder_v859;

/**
 * {@code PacketEncoder_v860} provides the packet encoder selected for protocol
 * v860 using the implementation inherited from {@code PacketEncoder_v859}. This
 * class introduces no packet overrides; the constructor retains the supplied
 * protocol data and its definition identities through the existing encoder
 * chain.
 */
public class PacketEncoder_v860 extends PacketEncoder_v859 {
    /**
     * Creates the v860 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v860(ProtocolData data) {
        super(data);
    }
}
