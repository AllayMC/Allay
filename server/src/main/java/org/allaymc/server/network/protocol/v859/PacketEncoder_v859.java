package org.allaymc.server.network.protocol.v859;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v844.PacketEncoder_v844;

/**
 * {@code PacketEncoder_v859} provides the packet encoder selected for protocol
 * v859 using the implementation inherited from {@code PacketEncoder_v844}. This
 * class introduces no packet overrides; the constructor retains the supplied
 * protocol data and its definition identities through the existing encoder
 * chain.
 */
public class PacketEncoder_v859 extends PacketEncoder_v844 {
    /**
     * Creates the v859 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v859(ProtocolData data) {
        super(data);
    }
}
