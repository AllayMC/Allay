package org.allaymc.server.network.protocol.v1001;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v975.ProtocolV975;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v1001.Bedrock_v1001;

/** International v1001 protocol. */
public class ProtocolV1001 extends ProtocolV975 {
    public ProtocolV1001() {
        this(Bedrock_v1001.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected ProtocolV1001(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoderV1001(data);
    }
}
