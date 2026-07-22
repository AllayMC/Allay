package org.allaymc.server.network.protocol.v924;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v898.Protocol_v898;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v924.Bedrock_v924;

/** International v924 protocol. */
public class Protocol_v924 extends Protocol_v898 {
    public Protocol_v924() {
        this(Bedrock_v924.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected Protocol_v924(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v924(data);
    }
}
