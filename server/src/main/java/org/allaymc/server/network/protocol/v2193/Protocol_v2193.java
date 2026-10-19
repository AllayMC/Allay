package org.allaymc.server.network.protocol.v2193;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v2169.Protocol_v2169;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v2193.Bedrock_v2193;

public class Protocol_v2193 extends Protocol_v2169 {
    public Protocol_v2193() {
        this(Bedrock_v2193.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected Protocol_v2193(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v2193(data);
    }
}
