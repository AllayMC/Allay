package org.allaymc.server.network.protocol.v859;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v844.ProtocolV844;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v859.Bedrock_v859;

/** International v859 protocol. */
public class ProtocolV859 extends ProtocolV844 {
    public ProtocolV859() {
        this(Bedrock_v859.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected ProtocolV859(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoderV859(data);
    }
}
