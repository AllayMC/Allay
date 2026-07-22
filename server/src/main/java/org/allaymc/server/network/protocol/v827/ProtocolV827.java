package org.allaymc.server.network.protocol.v827;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v819.ProtocolV819;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v827.Bedrock_v827;

/** International v827 protocol. */
public class ProtocolV827 extends ProtocolV819 {
    public ProtocolV827() {
        this(Bedrock_v827.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected ProtocolV827(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoderV827(data);
    }
}
