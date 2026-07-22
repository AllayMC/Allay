package org.allaymc.server.network.protocol.v819;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v818.Protocol_v818;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v819.Bedrock_v819;

public class Protocol_v819 extends Protocol_v818 {
    public Protocol_v819() {
        this(Bedrock_v819.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected Protocol_v819(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v819(data);
    }
}
