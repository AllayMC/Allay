package org.allaymc.server.network.protocol.v859;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v844.Protocol_v844;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v859.Bedrock_v859;

public class Protocol_v859 extends Protocol_v844 {
    public Protocol_v859() {
        this(Bedrock_v859.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected Protocol_v859(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v859(data);
    }
}
