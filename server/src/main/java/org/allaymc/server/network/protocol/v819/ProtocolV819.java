package org.allaymc.server.network.protocol.v819;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v818.ProtocolV818;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v819.Bedrock_v819;

/** International v819 protocol. */
public class ProtocolV819 extends ProtocolV818 {
    public ProtocolV819() {
        this(Bedrock_v819.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected ProtocolV819(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoderV819(data);
    }
}
