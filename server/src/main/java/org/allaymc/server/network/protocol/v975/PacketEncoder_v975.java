package org.allaymc.server.network.protocol.v975;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v944.PacketEncoder_v944;

/**
 * {@code PacketEncoder_v975} provides the packet encoder selected for protocol
 * v975 using the implementation inherited from {@code PacketEncoder_v944}. This
 * class introduces no packet overrides; the constructor retains the supplied
 * protocol data and its definition identities through the existing encoder
 * chain.
 */
public class PacketEncoder_v975 extends PacketEncoder_v944 {
    /**
     * Creates the v975 encoder using the supplied protocol data. The parent
     * constructor retains that exact bundle and builds definition lookups;
     * callers must supply the data prepared for the selected protocol.
     */
    public PacketEncoder_v975(ProtocolData data) {
        super(data);
    }
}
