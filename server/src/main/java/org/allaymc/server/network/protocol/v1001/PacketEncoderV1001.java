package org.allaymc.server.network.protocol.v1001;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v975.PacketEncoderV975;

/** International v1001 encoder. */
public class PacketEncoderV1001 extends PacketEncoderV975 {
    public PacketEncoderV1001(ProtocolData data) {
        super(data);
    }
}
