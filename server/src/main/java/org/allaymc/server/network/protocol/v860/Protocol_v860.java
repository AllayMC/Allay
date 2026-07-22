package org.allaymc.server.network.protocol.v860;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v859.Protocol_v859;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v860.Bedrock_v860;

public class Protocol_v860 extends Protocol_v859 {
    public Protocol_v860() {
        this(Bedrock_v860.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected Protocol_v860(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v860(data);
    }
}
