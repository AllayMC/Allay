package org.allaymc.server.network.protocol.v827;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v819.Protocol_v819;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v827.Bedrock_v827;

/** International v827 protocol. */
public class Protocol_v827 extends Protocol_v819 {
    public Protocol_v827() {
        this(Bedrock_v827.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected Protocol_v827(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v827(data);
    }
}
