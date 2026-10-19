package org.allaymc.server.network.protocol.v2169;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v2168.Protocol_v2168;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v2169.Bedrock_v2169;

public class Protocol_v2169 extends Protocol_v2168 {
    public Protocol_v2169() {
        this(Bedrock_v2169.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected Protocol_v2169(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v2169(data);
    }
}
