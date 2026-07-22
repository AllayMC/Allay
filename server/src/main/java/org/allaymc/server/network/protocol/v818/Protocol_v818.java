package org.allaymc.server.network.protocol.v818;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v766.Protocol_v766;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v818.Bedrock_v818;

/** International v818 protocol. */
public class Protocol_v818 extends Protocol_v766 {
    public Protocol_v818() {
        this(Bedrock_v818.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected Protocol_v818(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v818(data);
    }
}
